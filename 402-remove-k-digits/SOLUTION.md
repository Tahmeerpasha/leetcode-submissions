# 🚀 402. Remove K Digits

### 🔹 Problem Restatement

Given a string `num` representing a non-negative integer and an integer `k`, remove `k` digits from the number so that the new number is the **smallest possible**.

Return the result as a string.

* If the result is empty, return `"0"`.
* Leading zeros must be removed.

---

## 1️⃣ Brute Force Approach

👉 **Idea**:

* Generate all possible numbers by removing exactly `k` digits (use recursion or backtracking).
* Compare all generated numbers and return the smallest.

👉 **Complexity**:

* Time: `O(C(n, k)) ≈ O(2^n)` (choose `k` digits out of `n`) → infeasible.
* Space: `O(n)` (recursion stack).

👉 **Code Sketch** (not practical):

```java
// Backtracking: try removing each digit and keep the minimum
```

⚠️ Works only for very small inputs → **not feasible for LeetCode constraints**.

---

## 2️⃣ Better Approach (Greedy with String Manipulation)

👉 **Idea**:

* Start with the original number.
* Repeat `k` times: remove one digit such that the resulting number is smallest.

  * Usually, remove the **first digit where `num[i] > num[i+1]`**.
* If no such digit exists, remove the last digit.
* Strip leading zeros at the end.

👉 **Complexity**:

* Time: `O(k * n)` (worst-case: each removal scans string).
* Space: `O(n)`.

👉 **Example**:
`num = "1432219", k = 3`

* Remove `4` → `"132219"`
* Remove `3` → `"12219"`
* Remove `2` → `"1219"` ✅

---

## 3️⃣ Optimal Approach (Monotonic Stack) ✅

👉 **Core Insight**:

* We want the **smallest number**, so we should remove digits that "peak" before smaller digits.
* Use a **monotonic increasing stack** to greedily build the result.
* While the current digit `c` is smaller than the top of stack and we still have `k` removals left → pop stack.
* Push current digit.
* After processing all digits, if `k > 0`, pop from the end.
* Finally, strip leading zeros.

👉 **Complexity**:

* Time: `O(n)` (each digit pushed/popped at most once).
* Space: `O(n)` (stack + result).

👉 **Code (your optimal version)**:

```java
class Solution {
    public String removeKdigits(String num, int k) {
        Stack<Character> st = new Stack<>();

        for (char c : num.toCharArray()) {
            while (!st.isEmpty() && k > 0 && st.peek() > c) {
                st.pop();
                k--;
            }
            st.push(c);
        }

        // If still have removals left
        while (k > 0 && !st.isEmpty()) {
            st.pop();
            k--;
        }

        // Build the number
        StringBuilder sb = new StringBuilder();
        for (char c : st)
            sb.append(c);

        // Remove leading zeros
        while (sb.length() > 0 && sb.charAt(0) == '0')
            sb.deleteCharAt(0);

        return sb.length() == 0 ? "0" : sb.toString();
    }
}
```

---

## 🔑 Key Tips

* **Greedy removal**: always remove the "peak" digit before a smaller digit.
* **Leading zeros** must be explicitly stripped.
* **Edge cases**:

  * If `k == num.length()` → return `"0"`.
  * If the number is already increasing → just remove last `k` digits.
  * If the number has many leading zeros after removals → trim them.

---

✅ Example Walkthrough:
`num = "10200", k = 1`

* Stack = \[`1`]
* Next digit `0` → pop `1` (k=0), push `0`.
* Push remaining digits → `0, 2, 0, 0`.
* Result → `"0200"` → trim zeros → `"200"` ✅

---
