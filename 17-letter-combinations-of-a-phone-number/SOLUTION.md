### 🔢 Problem: 17. Letter Combinations of a Phone Number

Given a string containing digits from 2-9, return all possible letter combinations that the number could represent.

📱 Digits map to letters like on a telephone keypad (e.g., '2' → "abc", '3' → "def", etc.).

---

## 🔨 Brute Force Approach

### Idea:

* Generate **all possible strings** of length equal to the number of digits.
* For each string, check if it maps to a valid combination based on digit-letter mapping.

### Time Complexity:

* Worst: O(4ⁿ \* n), where n = length of digits, since some digits map to 4 letters (like '7', '9').

### Space Complexity:

* O(4ⁿ \* n) for storing results and recursive stack.

### ❌ Why it’s not efficient?

* Generates even invalid combinations and filters later.
* No pruning or smart generation of combinations.

---

## ✅ Better Approach — Recursive Backtracking (used in code)

### Idea:

* Use backtracking to **build combinations incrementally**.
* For each digit, try every letter it maps to and move to the next digit.

### Code Summary:

```java
void backtrack(String digits, int index, String path, Map<Character, List<String>> map, List<String> result) {
    if (index == digits.length()) {
        result.add(path);
        return;
    }

    char digit = digits.charAt(index);
    for (String letter : map.get(digit)) {
        backtrack(digits, index + 1, path + letter, map, result);
    }
}
```

### Time Complexity:

* O(4ⁿ), where n = length of digits
  (Each digit can map up to 4 letters, e.g., '7' → 4 options)

### Space Complexity:

* O(n) for recursion stack
* O(4ⁿ) for storing final results

---

## 🚀 Optimal Approach

Same as the better approach (backtracking), but we can optimize:

### 🔧 Optimizations:

* Use `StringBuilder` instead of string concatenation (`path + letter`) to avoid extra string creation.
* Pass digit-letter mapping as a fixed array to reduce map lookup overhead.

### Final Optimized Time & Space:

* **Time:** O(4ⁿ)
* **Space:** O(4ⁿ) (result storage) + O(n) recursion stack

---

### ✅ Summary:

| Approach     | Time Complexity | Space Complexity | Notes                          |
| ------------ | --------------- | ---------------- | ------------------------------ |
| Brute Force  | O(4ⁿ \* n)      | O(4ⁿ \* n)       | Generates all, filters later   |
| Backtracking | O(4ⁿ)           | O(4ⁿ + n)        | Builds valid paths recursively |
| Optimal      | O(4ⁿ)           | O(4ⁿ + n)        | Uses `StringBuilder` for speed |

---
