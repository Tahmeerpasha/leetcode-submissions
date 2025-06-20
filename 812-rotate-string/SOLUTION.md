## 🔍 Problem Summary

**Given:**

* Two strings `s` and `goal`.

**Task:**

* Return `true` if and only if `s` **can be rotated** some number of times (possibly 0) to become `goal`.

---

## ✅ Optimal Approach – *Concatenation Trick*

### 🔧 Logic:

* If rotating `s` can form `goal`, then `goal` must be a **substring** of `s + s`.

  * Why? Because every possible rotation of `s` will appear as a substring in `s + s`.

### ✅ Code:

```java
public boolean rotateString(String s, String goal) {
    if (s.length() != goal.length()) return false;
    String appendedString = s + s;
    return appendedString.contains(goal);
}
```

---

## 🧱 Brute Force Approach

### 🔧 Logic:

* Rotate the string `s` one character at a time and check if it equals `goal`.
* Repeat this process for `s.length()` times.

### 🧪 Code:

```java
public boolean rotateString(String s, String goal) {
    if (s.length() != goal.length()) return false;
    for (int i = 0; i < s.length(); i++) {
        s = s.substring(1) + s.charAt(0); // Rotate left
        if (s.equals(goal)) return true;
    }
    return false;
}
```

---

## 📊 Complexity Analysis

| Approach         | Time Complexity | Space Complexity | Notes                             |
| ---------------- | --------------- | ---------------- | --------------------------------- |
| Optimal (Concat) | O(n)            | O(n)             | Using `contains()` on `s + s`     |
| Brute Force      | O(n²)           | O(n)             | Creates new string on every shift |

Where `n = s.length()`

---

## 🧪 Examples

| s         | goal      | Output  | Explanation                  |
| --------- | --------- | ------- | ---------------------------- |
| `"abcde"` | `"cdeab"` | ✅ true  | Rotate 2 times left          |
| `"abc"`   | `"acb"`   | ❌ false | No rotation can match `goal` |
| `"a"`     | `"a"`     | ✅ true  | 0 rotations                  |
| `"ab"`    | `"ba"`    | ✅ true  | Rotate once                  |

---

## 📝 Summary

| Approach       | Time  | Space | Comment                             |
| -------------- | ----- | ----- | ----------------------------------- |
| ✅ Optimal      | O(n)  | O(n)  | Elegant and efficient using `s + s` |
| 🌀 Brute Force | O(n²) | O(n)  | Rotates string manually             |

---
