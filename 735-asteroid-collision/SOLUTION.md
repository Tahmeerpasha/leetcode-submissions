# 📘 Problem: Asteroid Collision

We have an array `asteroids`, where each asteroid moves:

* **Positive → right direction**
* **Negative → left direction**

When two asteroids collide:

* Smaller one explodes.
* If equal size → both explode.
* Asteroids moving in the **same direction never collide**.

We must return the state of asteroids after all collisions.

---

## 1. 🐌 Brute Force Approach

**Idea:**

* Simulate collisions repeatedly until the array stabilizes.
* At each step, check for pairs `(i, i+1)` where `asteroids[i] > 0` and `asteroids[i+1] < 0`.
* Resolve collision → remove the smaller one (or both).
* Restart until no more collisions occur.

### Code (Brute Force Simulation):

```java
class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        boolean changed = true;
        while (changed) {
            changed = false;
            for (int i = 0; i < asteroids.length - 1; i++) {
                if (asteroids[i] > 0 && asteroids[i + 1] < 0) {
                    if (Math.abs(asteroids[i]) > Math.abs(asteroids[i + 1])) {
                        asteroids[i + 1] = 0; // right survives
                    } else if (Math.abs(asteroids[i]) < Math.abs(asteroids[i + 1])) {
                        asteroids[i] = 0; // left survives
                    } else {
                        asteroids[i] = 0; // both destroyed
                        asteroids[i + 1] = 0;
                    }
                    changed = true;
                }
            }
            asteroids = Arrays.stream(asteroids).filter(x -> x != 0).toArray();
        }
        return asteroids;
    }
}
```

### Complexity:

* **Time:** Worst O(n²) (multiple passes, removing one asteroid per pass).
* **Space:** O(n) (since new arrays created).

✅ Works for small input, **too slow** for large `n`.

---

## 2. ⚡ Better Approach (Using LinkedList/Deque)

**Idea:**

* Use a **Deque (double-ended queue)** to simulate collisions.
* For each asteroid:

  * If moving right → push into deque.
  * If moving left → check collisions from rightmost element.
* Still less efficient than stack because deque operations can cause more checks.

👉 Not commonly used in practice since the **stack** is the natural fit.

---

## 3. 🚀 Optimal Approach (Stack Simulation)

**Idea:**

* Use a **stack** (or dynamic list like in your code) to simulate survivors.
* For each asteroid:

  * If moving right (`>0`) → just push.
  * If moving left (`<0`) → check for collisions with stack top:

    * While top is smaller right-moving asteroid → pop it.
    * If top is same size → pop it and discard current.
    * If stack empty or top is also left-moving → push current.

### Code (Your Optimized Version):

```java
class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        List<Integer> st = new ArrayList<>();
        for (int a : asteroids) {
            if (a > 0) {
                st.add(a);
            } else {
                while (!st.isEmpty() && st.get(st.size() - 1) > 0 && st.get(st.size() - 1) < Math.abs(a)) {
                    st.remove(st.size() - 1); // smaller right asteroids destroyed
                }

                if (!st.isEmpty() && st.get(st.size() - 1) == Math.abs(a)) {
                    st.remove(st.size() - 1); // equal size collision
                } else if (st.isEmpty() || st.get(st.size() - 1) < 0) {
                    st.add(a); // no collision
                }
            }
        }

        int[] result = new int[st.size()];
        for (int i = 0; i < st.size(); i++) result[i] = st.get(i);
        return result;
    }
}
```

---

### Complexity:

* **Time:** O(n) → Each asteroid is pushed and popped at most once.
* **Space:** O(n) → For the stack survivors.

✅ Efficient, handles all cases.

---

## 🔑 Tips to Remember

1. **Collision only happens when right-moving asteroid meets left-moving asteroid.**

   * (`>0` followed by `<0`).
2. **Stack is the natural fit** for problems where new events can cause chain reactions backwards.
3. **Careful with equal size case** → both destroyed.
4. Think of it as **simulating real physics** → survivors remain in stack.
5. Always dry run on tricky cases:

   * `[5,10,-5] → [5,10]`
   * `[8,-8] → []`
   * `[10,2,-5] → [10]`

---
