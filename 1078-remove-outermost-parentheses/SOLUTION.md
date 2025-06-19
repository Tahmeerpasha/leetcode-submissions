## 🔍 Problem Summary

Given a valid parentheses string `s`, your task is to **remove the outermost parentheses** of every primitive substring.

> A **primitive** string is a non-empty valid parentheses string that **cannot be divided** into two non-empty valid parentheses strings.

---

## 🧱 Brute Force (Conceptual)

### 🔧 Logic:

* Track balance of parentheses using a counter.
* Keep splitting the string into **primitive substrings**.
* For each primitive, remove the first and last character and append to result.

### ⛔ Downsides:

* Requires substring tracking and additional complexity for splitting.
* Not efficient for large strings.

### ⏱ Time: `O(n)`

### 📦 Space: `O(n)`

*(Extra overhead due to multiple substrings)*

---

## 🚀 Optimal Solution (Used in Your Code)

### 🔧 Logic:

* Use a **counter `cnt`** to track the level of nested parentheses.
* For every character:

  * If `'('`, increment `cnt`.
  * If `cnt > 1`, it's not the outermost opening → **add to result**.
  * If `')'`, decrement `cnt`.
  * If `cnt > 0`, it's not the outermost closing → **add to result**.

> Skip characters when `cnt == 0` before `'('` or `cnt == 1` before `')'`.

### ✅ Key Insight:

* `cnt == 1` just before closing means you're at the **outermost** closing bracket.
* `cnt == 0` just before opening means you're at the **outermost** opening bracket.

---

### ✅ Code (Same as yours):

```java
class Solution {
    public String removeOuterParentheses(String s) {
        int cnt = 0;
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ')') cnt--;
            if (cnt != 0) ans.append(s.charAt(i));
            if (s.charAt(i) == '(') cnt++;
        }
        return ans.toString();
    }
}
```

---

## 🧠 Dry Run Example

Input: `"(()())(())"`

Breakdown:

* Primitive 1: `"(()())"` → Remove outer: `()()`
* Primitive 2: `"(())"` → Remove outer: `()`
* Final: `"()()()"`

Output: `"()()()"`

---

## 📊 Complexity Analysis

| Metric    | Value  | Explanation                   |
| --------- | ------ | ----------------------------- |
| **Time**  | `O(n)` | One pass through the string   |
| **Space** | `O(n)` | For storing the result string |

---

## 📝 Summary Table

| Approach  | Time   | Space  | Notes                              |
| --------- | ------ | ------ | ---------------------------------- |
| Brute     | `O(n)` | `O(n)` | Conceptually splits primitives     |
| ✅ Optimal | `O(n)` | `O(n)` | Counter-based single pass solution |

---
