## 🔍 Problem Summary

**Given:** A Roman numeral string `s` (e.g., `"MCMXCIV"`).
**Goal:** Convert it to its integer representation.

---

## 🔠 Roman Numeral Mapping

| Roman | Integer |
| ----- | ------- |
| I     | 1       |
| V     | 5       |
| X     | 10      |
| L     | 50      |
| C     | 100     |
| D     | 500     |
| M     | 1000    |

Some numerals are subtracted if a smaller numeral appears before a larger one (e.g., IV = 4).

---

## ✅ Optimal Solution (One-pass from Left to Right)

### 🔧 Code (Provided):

```java
public int romanToInt(String s) {
    int num = giveNumFromChar(s.charAt(0));
    for (int i = 1; i < s.length(); i++) {
        if (giveNumFromChar(s.charAt(i - 1)) < giveNumFromChar(s.charAt(i)))
            num += giveNumFromChar(s.charAt(i)) - 2 * giveNumFromChar(s.charAt(i - 1));
        else
            num += giveNumFromChar(s.charAt(i));
    }
    return num;
}
```

### 🔹 Explanation:

* Iterate the string from **left to right**.
* If a **smaller** value appears **before** a larger value → **subtraction case**.
* Otherwise, it's just an **addition**.

#### Example:

**Input:** `"MCMXCIV"`
**Output:** `1994`
Breakdown:
M = 1000
CM = 900
XC = 90
IV = 4
Sum = 1000 + 900 + 90 + 4 = 1994 ✅

---

## 🔁 Alternate Approach (Right to Left)

Go from **right to left** and subtract when current < previous.

### Code:

```java
public int romanToInt(String s) {
    int n = s.length();
    int ans = giveNumFromChar(s.charAt(n - 1));
    for (int i = n - 2; i >= 0; i--) {
        int curr = giveNumFromChar(s.charAt(i));
        int next = giveNumFromChar(s.charAt(i + 1));
        if (curr < next)
            ans -= curr;
        else
            ans += curr;
    }
    return ans;
}
```

---

## 📊 Time and Space Complexity

| Metric    | Value |
| --------- | ----- |
| **Time**  | O(n)  |
| **Space** | O(1)  |

---

## 🧠 Summary

| Approach      | Time | Space | Notes                                       |
| ------------- | ---- | ----- | ------------------------------------------- |
| Left-to-Right | O(n) | O(1)  | Subtract when smaller numeral before larger |
| Right-to-Left | O(n) | O(1)  | Cleaner logic, often used in interviews     |

Both are optimal. You can choose based on readability or preference.

---
