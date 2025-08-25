# 🔹 Problem: Min Stack (LeetCode 155)

Design a stack that supports:

* `push(val)`
* `pop()`
* `top()`
* `getMin()`

All in **O(1)** time complexity.

---

## 🟢 Brute Force Approach

### Idea:

* Use a **normal stack** for push, pop, and top.
* For `getMin()`, traverse the whole stack and find the minimum.

### Implementation:

```java
class MinStack {
    Stack<Integer> st;

    public MinStack() {
        st = new Stack<>();
    }

    public void push(int val) {
        st.push(val);
    }

    public void pop() {
        st.pop();
    }

    public int top() {
        return st.peek();
    }

    public int getMin() {
        int min = Integer.MAX_VALUE;
        for (int x : st) {
            min = Math.min(min, x);
        }
        return min;
    }
}
```

### Complexity:

* Push → **O(1)**
* Pop → **O(1)**
* Top → **O(1)**
* getMin → **O(N)**
  **Space:** O(N) (only stack storage)

⚠️ Not efficient since `getMin()` is **O(N)**.

---

## 🟡 Better Approach (Store Pair of Value + Min)

### Idea:

* Along with each value, also store the **minimum up to that point**.
* Use a stack of **pairs `(value, minSoFar)`**.

### Implementation:

```java
class Pair {
    int val, min;
    Pair(int v, int m) {
        val = v;
        min = m;
    }
}

class MinStack {
    Stack<Pair> st;

    public MinStack() {
        st = new Stack<>();
    }

    public void push(int val) {
        if (st.isEmpty())
            st.push(new Pair(val, val));
        else
            st.push(new Pair(val, Math.min(val, st.peek().min)));
    }

    public void pop() {
        st.pop();
    }

    public int top() {
        return st.peek().val;
    }

    public int getMin() {
        return st.peek().min;
    }
}
```

### Complexity:

* Push → **O(1)**
* Pop → **O(1)**
* Top → **O(1)**
* getMin → **O(1)**
  **Space:** O(2N) (since storing both value + min)

✅ Much better than brute.

---

## 🔴 Optimal Approach (Encoding Min using Math)

### Idea:

* Store only **long values** in stack.
* Use a **variable `min`** to track the current minimum.
* When pushing a value smaller than `min`, encode it as `2*val - min` and update `min`.
  (This trick helps retrieve the previous min when popping.)

### Implementation:

```java
class MinStack {
    Stack<Long> st;
    long min;

    public MinStack() {
        st = new Stack<>();
        min = Long.MAX_VALUE;
    }

    public void push(int val) {
        long x = val;
        if (st.isEmpty()) {
            st.push(x);
            min = x;
        } else {
            if (x >= min) {
                st.push(x);
            } else {
                st.push(2 * x - min);  // Encode
                min = x;
            }
        }
    }

    public void pop() {
        if (st.isEmpty()) return;
        long top = st.pop();
        if (top < min) {
            min = 2 * min - top; // Decode previous min
        }
    }

    public int top() {
        long top = st.peek();
        if (top >= min) return (int) top;
        else return (int) min;
    }

    public int getMin() {
        return (int) min;
    }
}
```

### Complexity:

* Push → **O(1)**
* Pop → **O(1)**
* Top → **O(1)**
* getMin → **O(1)**
  **Space:** O(N) (only storing modified values)

✅ This is the most **space-efficient** solution.

---

# 📌 Final Notes (for interviews)

* **Brute Force:** Normal stack + linear scan → O(N) for getMin.
* **Better:** Stack of pairs `(val, minSoFar)` → O(1) everything, O(2N) space.
* **Optimal:** Encoded values trick → O(1) everything, O(N) space.

---
