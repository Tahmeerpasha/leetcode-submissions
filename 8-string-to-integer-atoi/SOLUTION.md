## 🔍 Problem Summary

**Task:** Convert a given string `s` to a 32-bit signed integer (just like the C/C++ `atoi` function).

### Rules:

1. **Ignore leading whitespace**
2. **Optional sign** before the number: `+` or `-`
3. **Read digits** until non-digit is found
4. **Clamp result** within 32-bit signed integer range:

   * `[−2³¹,  2³¹ − 1]` → `[−2147483648, 2147483647]`

---

## ✅ Optimal Solution (One-pass Parsing)

### 🔧 Java Code (Your Solution):

```java
public int myAtoi(String s) {
    int i = 0, n = s.length(), sign = 1;
    long ans = 0;

    // 1. Skip leading whitespace
    while (i < n && s.charAt(i) == ' ') i++;

    // 2. Sign check
    if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
        sign = s.charAt(i) == '-' ? -1 : 1;
        i++;
    }

    // 3. Read digits
    while (i < n && Character.isDigit(s.charAt(i))) {
        ans = ans * 10 + (s.charAt(i) - '0');

        // 4. Handle overflow early
        if (sign * ans >= Integer.MAX_VALUE) return Integer.MAX_VALUE;
        if (sign * ans <= Integer.MIN_VALUE) return Integer.MIN_VALUE;

        i++;
    }

    return (int)(sign * ans);
}
```

---

## ✅ Example Walkthrough

**Input:** `"   -42"`
→ `" -42"` → `-42`

**Input:** `"4193 with words"`
→ Read `4193`, stop at `' '`
→ Output: `4193`

**Input:** `"words and 987"`
→ No digits at start → Output: `0`

---

## 🧠 Edge Cases

| Input                   | Output       | Reason                   |
| ----------------------- | ------------ | ------------------------ |
| `"   +0 123"`           | `0`          | Stops at space after `0` |
| `"  -00123"`            | `-123`       | Leading zeroes ignored   |
| `"9223372036854775808"` | `2147483647` | Clamped to `Integer.MAX` |
| `"abc"`                 | `0`          | No digit starts          |

---

## 🧪 Alternate Approaches

### 🔹 Approach 1: Regex + Parsing (Not recommended for interviews)

```java
String trimmed = s.trim();
Pattern p = Pattern.compile("^[+-]?\\d+");
Matcher m = p.matcher(trimmed);
if (m.find()) {
    try {
        return Integer.parseInt(m.group());
    } catch (NumberFormatException e) {
        return trimmed.startsWith("-") ? Integer.MIN_VALUE : Integer.MAX_VALUE;
    }
}
return 0;
```

* ⚠️ Slower and uses libraries; not preferred for DSA interviews.

---

## 📊 Time and Space Complexity

| Metric    | Value |
| --------- | ----- |
| **Time**  | O(n)  |
| **Space** | O(1)  |

---

## ✅ Summary

| Step           | Action                                  |
| -------------- | --------------------------------------- |
| Trim           | Ignore leading spaces                   |
| Sign           | Detect `+` or `-`                       |
| Convert Digits | Read until non-digit                    |
| Overflow Check | If `> INT_MAX` or `< INT_MIN`, clamp it |
| Return         | Final result with sign                  |

---
