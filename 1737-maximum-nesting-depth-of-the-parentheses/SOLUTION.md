## 🔍 Problem Summary

**Given:** A valid parentheses string `s` (containing '(', ')', and other characters).
**Goal:** Return the **maximum nesting depth** of the parentheses.

### 🔸 Example:

* **Input:** `"(1+(2*3)+((8)/4))+1"`
* **Output:** `3`
* Explanation: The maximum depth of nested parentheses is 3 in `((8)/4)`.

---

## 🧠 Key Idea

* Use a counter `cnt` to track the current depth.
* Every time you see `'('`, increase `cnt`.
* Every time you see `')'`, decrease `cnt`.
* Keep updating the maximum value of `cnt` during traversal.

---

## ✅ Optimal Solution (Greedy, 1-pass)

### 🔧 Code:

```java
public int maxDepth(String s) {
    int cnt = 0, max = Integer.MIN_VALUE;
    for (char c : s.toCharArray()) {
        if (c == '(')
            cnt++;
        max = Math.max(max, cnt);
        if (c == ')')
            cnt--;
    }
    return max;
}
```

### 🧠 Dry Run Example:

Input: `(1+(2*3)+((8)/4))+1`
Trace of `cnt`: 0 → 1 → 2 → 1 → 2 → 3 → 2 → 1 → 0
✔ Max depth = **3**

---

## 📊 Complexity

| Metric    | Value |
| --------- | ----- |
| **Time**  | O(n)  |
| **Space** | O(1)  |

---

## 🚫 Brute Force?

No need. This 1-pass solution is already optimal in both time and space. Stack is unnecessary because parentheses are **balanced** and we only need the **max depth**.

---

## ✅ Summary

| Approach     | Time | Space | Notes                      |
| ------------ | ---- | ----- | -------------------------- |
| Greedy Count | O(n) | O(1)  | Best and cleanest approach |

---
