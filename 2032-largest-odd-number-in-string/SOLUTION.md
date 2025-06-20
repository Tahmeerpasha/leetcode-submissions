## 🔍 Problem Summary

**Given:** A string `num` representing a **non-negative integer**.
**Task:** Return the **largest-valued odd-number substring** of `num` from **left to right**.
If no odd digit exists, return an empty string.

---

## ✅ Optimal Approach (Right-to-Left Scan)

### 🔧 Logic:

* A number is **odd** if its **last digit** is odd.
* To get the largest possible odd number from the original string, **remove trailing even digits**.
* Scan from **end to start**, and return the **longest prefix** ending at the **last odd digit**.

### ✅ Code:

```java
public String largestOddNumber(String num) {
    for (int i = num.length() - 1; i >= 0; i--) {
        int digit = num.charAt(i) - '0';
        if (digit % 2 == 1) {
            return num.substring(0, i + 1);
        }
    }
    return "";
}
```

### 🧠 Example:

```java
Input:  num = "3542706"
Output: "35427"

Input:  num = "4206"
Output: ""
```

---

## 🧱 Brute Force Approach (Not Efficient)

### 🔧 Logic:

* Generate all possible substrings from the left.
* Check if the number is odd and track the maximum.
* **Inefficient** for large input size `n > 10^5`.

### ❌ Time: `O(n^2)`

### ❌ Space: `O(1)`

Not suitable, hence skipped in practice.

---

## ⏱ Time & Space Complexity

| Complexity | Value  |
| ---------- | ------ |
| ⏱ Time     | `O(n)` |
| 📦 Space   | `O(1)` |

> Scanning from the back and returning a substring.

---

## ✅ Edge Cases

| Input         | Output        | Reason                    |
| ------------- | ------------- | ------------------------- |
| `"4206"`      | `""`          | No odd digit              |
| `"123456789"` | `"123456789"` | Already odd               |
| `"1248"`      | `""`          | All digits even           |
| `"35702468"`  | `"357"`       | Last odd digit at index 2 |

---

## 📝 Summary

| Approach                | Time     | Space  | Notes                                |
| ----------------------- | -------- | ------ | ------------------------------------ |
| Optimal (Reverse scan)  | `O(n)`   | `O(1)` | Simple, effective, handles all cases |
| Brute (Substring check) | `O(n^2)` | `O(1)` | Inefficient, not recommended         |

---
