## 🧩 Problem: 135. Candy

**Link:** [Leetcode 135 - Candy](https://leetcode.com/problems/candy/)
**Level:** Hard
**Tags:** Greedy, Array

### 📝 Problem Statement

There are `n` children standing in a line. Each child is assigned a rating value given by an integer array `ratings`.
You must give candies to these children following two rules:

1. Each child must have at least one candy.
2. Children with a higher rating get more candies than their immediate neighbors.

Return **the minimum number of candies** you must give.

---

## 🔹 Example

```
Input: ratings = [1,0,2]
Output: 5
Explanation: You can give 2,1,2 candies respectively.
```

---

## 🧠 BRUTE FORCE (Simulation / Incremental Fix)

### **Idea**

* Start by giving everyone 1 candy.
* Then repeatedly check all adjacent pairs `(i, i+1)` and fix violations:

  * If `ratings[i] > ratings[i-1]` and `candies[i] <= candies[i-1]`, increase `candies[i] = candies[i-1] + 1`.
  * Similarly for the reverse direction.
* Repeat until no change occurs.

### **Code Sketch**

```java
int[] candies = new int[n];
Arrays.fill(candies, 1);
boolean changed = true;
while (changed) {
    changed = false;
    for (int i = 0; i < n; i++) {
        if (i > 0 && ratings[i] > ratings[i-1] && candies[i] <= candies[i-1]) {
            candies[i] = candies[i-1] + 1;
            changed = true;
        }
        if (i < n-1 && ratings[i] > ratings[i+1] && candies[i] <= candies[i+1]) {
            candies[i] = candies[i+1] + 1;
            changed = true;
        }
    }
}
return Arrays.stream(candies).sum();
```

### **Complexity**

* **Time:** O(n²) worst case (multiple passes until stable).
* **Space:** O(n)

### **Intuition Tip:**

Think of it like “fixing inequality violations” iteratively until equilibrium.

---

## ⚡ BETTER SOLUTION (Two Arrays / Two Pass)

### **Idea**

Handle the two directions independently:

1. **Left-to-right:** If `ratings[i] > ratings[i-1]`, then `candies[i] = candies[i-1] + 1`.
2. **Right-to-left:** If `ratings[i] > ratings[i+1]`, ensure `candies[i] = max(candies[i], candies[i+1] + 1)`.

### **Code**

```java
public int candy(int[] ratings) {
    int n = ratings.length;
    int[] candies = new int[n];
    Arrays.fill(candies, 1);

    // Left to right
    for (int i = 1; i < n; i++) {
        if (ratings[i] > ratings[i-1])
            candies[i] = candies[i-1] + 1;
    }

    // Right to left
    for (int i = n - 2; i >= 0; i--) {
        if (ratings[i] > ratings[i+1])
            candies[i] = Math.max(candies[i], candies[i+1] + 1);
    }

    int sum = 0;
    for (int c : candies) sum += c;
    return sum;
}
```

### **Complexity**

* **Time:** O(n)
* **Space:** O(n)

### **Intuition Tip:**

Each child’s candy count depends only on **local relationships**. Handle one direction at a time, then merge the results by taking the max.

---

## 🚀 OPTIMAL SOLUTION (Greedy with Single Pass Logic)

### **Core Idea**

Use the **slope tracking** approach (increasing / decreasing trend).
Track:

* Length of **uphill** (increasing ratings) sequence → `peak`
* Length of **downhill** (decreasing ratings) sequence → `down`
* Give candies accordingly while maintaining minimal total.

This is what your code is implementing.

### **Intuition**

* While ratings go **up**, give increasing candies (`1, 2, 3...`).
* While ratings go **down**, give decreasing candies (`...3, 2, 1`).
* Adjust the **peak overlap**, since the peak child gets counted twice.

### **Code (cleaned version)**

```java
public int candy(int[] ratings) {
    int n = ratings.length;
    int sum = 1, i = 1;
    while (i < n) {
        if (ratings[i] == ratings[i - 1]) {
            sum += 1;
            i++;
            continue;
        }

        // Increasing slope
        int up = 1;
        while (i < n && ratings[i] > ratings[i - 1]) {
            up++;
            sum += up;
            i++;
        }

        // Decreasing slope
        int down = 0;
        while (i < n && ratings[i] < ratings[i - 1]) {
            down++;
            sum += down;
            i++;
        }
        down++;
        // Adjust peak overlap
        if (down > up)
            sum += down - up;
    }
    return sum;
}
```

### **Complexity**

* **Time:** O(n)
* **Space:** O(1)

### **Intuition Tip:**

The “peak child” is shared by both up and down slopes — adjust once to prevent double counting.

---

## 🎯 Interview Tips

✅ **Patterns to Recognize:**

* Neighbor comparison rules → typically solved by greedy or two-pass methods.
* “Higher value → more reward” often leads to directional dependency logic.

✅ **Common Pitfalls:**

* Forgetting equal ratings → each should still get 1 candy.
* Not adjusting peak overlap → causes off-by-one errors.
* Trying DP unnecessarily — this problem is **purely greedy**.

✅ **When asked to explain:**
Say:

> “We assign candies based on relative order in both directions. The left-to-right ensures local increasing correctness, and the right-to-left ensures decreasing correctness. Finally, we take the max to satisfy both constraints.”

✅ **Edge Cases:**

* All equal ratings → all get 1.
* Strictly increasing → candies = [1,2,3,...,n].
* Strictly decreasing → candies = [n, n-1, ..., 1].
* Zig-zag patterns.

---

## 💡 Key Takeaways

* **Two-pass greedy** is the simplest and most reliable in interviews.
* **One-pass slope-based** is more space-efficient but tricky — mention it only if you’re confident.
* Always **think local dependencies**, not global optimization.

---
