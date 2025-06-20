## 🔍 Problem Summary

**Given:** An array of strings `strs[]`.
**Goal:** Return the **longest common prefix** among all strings.
If there’s no common prefix, return `""`.

---

## ✅ Optimal Approach (Sort and Compare First & Last)

### 🔧 Logic:

* **Sort** the array lexicographically.
* Only the **first and last** strings can be the most different after sorting.
* Compare characters one-by-one between these two to find the common prefix.

### ✅ Code:

```java
public String longestCommonPrefix(String[] strs) {
    if (strs.length == 0) return "";
    if (strs.length == 1) return strs[0];

    Arrays.sort(strs);
    String first = strs[0];
    String last = strs[strs.length - 1];

    int i = 0;
    while (i < first.length() && i < last.length() && first.charAt(i) == last.charAt(i)) {
        i++;
    }
    return first.substring(0, i);
}
```

### ✅ Time: `O(n * log n + m)`

(Sorting + linear scan of first and last strings, where `n = strs.length`, `m = length of prefix`)

### ✅ Space: `O(1)` (ignoring sort space)

---

## 🧱 Brute Force Approach

### 🔧 Logic:

* Pick the first string `s` as the base.
* For each character in `s`, check if that character exists in the same position in **all other strings**.
* Keep counting matched characters until mismatch.

### 🔁 Time: `O(n * m)`

(where `n = number of strings`, `m = length of smallest string`)

### 💾 Space: `O(1)`

### 🧪 Example:

```java
Input: ["flower","flow","flight"]
Output: "fl"
```

---

## 🧠 Other Approaches

### 🔹 **Vertical Scanning**:

* Check each character of each string one by one (column-wise).
* Break as soon as a mismatch is found.

```java
public String longestCommonPrefix(String[] strs) {
    if (strs == null || strs.length == 0) return "";
    for (int i = 0; i < strs[0].length(); i++) {
        char c = strs[0].charAt(i);
        for (int j = 1; j < strs.length; j++) {
            if (i >= strs[j].length() || strs[j].charAt(i) != c)
                return strs[0].substring(0, i);
        }
    }
    return strs[0];
}
```

**Time:** `O(n * m)`, where `m = min string length`.

---

## 📌 Edge Cases

| Input                     | Output | Reason                    |
| ------------------------- | ------ | ------------------------- |
| `["dog","racecar","car"]` | `""`   | No common prefix          |
| `[""]`                    | `""`   | Empty input               |
| `["a"]`                   | `"a"`  | Single string             |
| `["ab", "abc"]`           | `"ab"` | First is prefix of second |

---

## 📝 Summary

| Approach                | Time         | Space  | Notes                          |
| ----------------------- | ------------ | ------ | ------------------------------ |
| ✅ Optimal (Sort)        | `O(n log n)` | `O(1)` | Compare only first and last    |
| 🔁 Brute (Char-by-char) | `O(n * m)`   | `O(1)` | Check each char in all strings |
| 🔍 Vertical Scan        | `O(n * m)`   | `O(1)` | Best for short strings         |

---
