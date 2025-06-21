## 🔍 Problem Summary

Given a string `s`, find the **sum of beauty of all substrings** of `s`.

**Beauty of a substring** is defined as:

> Maximum frequency of a character - Minimum frequency of a character (excluding 0s)

---

## 🔸 Example

### Input:

`s = "aabcb"`

### Substrings and Beauties:

* `"a"` → 0
* `"aa"` → 1 (`a=2`)
* `"aab"` → `a=2, b=1` → `2-1=1`
* `"aabc"` → `a=2, b=1, c=1` → `2-1=1`
* `"aabcb"` → `a=2, b=2, c=1` → `2-1=1`
  ... and so on.

### Total sum = `5`

---

## ✅ Code Explanation

```java
public int beautySum(String s) {
    int result = 0;
    for (int i = 0; i < s.length(); i++) {
        int[] freq = new int[26];
        for (int j = i; j < s.length(); j++) {
            freq[s.charAt(j) - 'a']++;
            int beauty = getMaxCount(freq) - getMinCount(freq);
            result += beauty;
        }
    }
    return result;
}
```

### Helper Functions

```java
int getMaxCount(int[] freq) {
    int max = Integer.MIN_VALUE;
    for (int f : freq) max = Math.max(max, f);
    return max;
}

int getMinCount(int[] freq) {
    int min = Integer.MAX_VALUE;
    for (int f : freq)
        if (f != 0) min = Math.min(min, f);
    return min;
}
```

---

## 🔄 Dry Run

`s = "aab"`

* `"a"` → beauty = 0
* `"aa"` → `a=2` → 2-2 = 0
* `"aab"` → `a=2, b=1` → 2-1 = 1
* `"ab"` → `a=1, b=1` → 1-1 = 0
* `"b"` → 0

Total: `0 + 0 + 1 + 0 + 0 = 1`

---

## ⏱ Complexity Analysis

| Type      | Complexity         |
| --------- | ------------------ |
| **Time**  | O(n² × 26) ≈ O(n²) |
| **Space** | O(26) = O(1)       |

Where `n` is the length of the string. The inner loop takes O(26) per substring due to the character frequency array.

---

## 🧪 Alternate Approaches

### ❌ Brute Force:

Generate all substrings, compute character counts from scratch for each → **O(n³)** — inefficient.

### ✅ Optimized (Current Approach):

Build frequency array incrementally and use it to calculate beauty → **O(n²)** — acceptable for constraints.

### 🔮 Advanced:

For very long strings (e.g. > 10⁴), you can experiment with **segment trees or prefix frequency arrays** to speed up frequency queries, but it's likely overkill unless constraints are tight.

---

## ✨ Summary

| Step             | Approach                           |
| ---------------- | ---------------------------------- |
| Count substrings | Use nested loop for all substrings |
| Track frequency  | Maintain and update `freq[26]`     |
| Get beauty       | `max(freq) - min(freq ≠ 0)`        |
| Time Complexity  | O(n²)                              |
| Space            | O(1) (ignoring output)             |

---
