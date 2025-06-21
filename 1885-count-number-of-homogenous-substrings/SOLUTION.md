## 🔍 Problem Summary

**You are given a string `s` consisting of only lowercase letters.**
You need to **count the number of homogenous substrings** — substrings where all characters are the same.

✅ The result should be **modulo 10⁹ + 7**.

---

## 🧠 Key Insight

A group of consecutive same characters of length `n` contributes:

$$
\frac{n \cdot (n + 1)}{2}
$$

homogenous substrings.

### 🔸 Example:

`s = "abbcccaa"`

* `"a"` → count = 1 → `1`
* `"bb"` → count = 2 → `2 + 1 = 3`
* `"ccc"` → count = 3 → `3 + 2 + 1 = 6`
* `"aa"` → count = 2 → `3`

**Total = 1 + 3 + 6 + 3 = 13**

---

## ✅ Code Explanation

```java
public int countHomogenous(String s) {
    long count = 1, result = 0, mod = 1_000_000_007;

    for (int i = 1; i < s.length(); i++) {
        if (s.charAt(i) == s.charAt(i - 1)) {
            count++;
        } else {
            result = (result + (count * (count + 1) / 2)) % mod;
            count = 1;
        }
    }

    // Add final group
    result = (result + (count * (count + 1) / 2)) % mod;

    return (int) result;
}
```

---

## 🔄 Dry Run

**Input:** `"abbcccaa"`
**Groups:**

* `'a'` → `1` → 1
* `'bb'` → `2` → 3
* `'ccc'` → `3` → 6
* `'aa'` → `2` → 3

**Total:** `1 + 3 + 6 + 3 = 13`

---

## ⏱ Time & Space Complexity

| Complexity | Value |
| ---------- | ----- |
| **Time**   | O(n)  |
| **Space**  | O(1)  |

✅ Single pass and constant extra space.

---

## 🧪 Alternate Approaches

### ❌ Brute Force (Not feasible)

Generate all substrings and check if each is homogenous → **O(n²)** time and space — TLE.

---

## ✅ Summary

| Step            | Logic                                       |
| --------------- | ------------------------------------------- |
| Traverse string | Count groups of same characters             |
| For each group  | Use formula `n*(n+1)/2` to count substrings |
| Modulo          | Always take modulo `10⁹ + 7`                |

---
