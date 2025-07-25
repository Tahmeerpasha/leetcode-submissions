### 🔍 **Problem Statement**

Given `n` pairs of parentheses, generate all combinations of **well-formed (balanced)** parentheses.

---

## ✅ 1. **Brute Force Approach**

### 🔸 **Idea**:

Generate **all possible combinations** of `2n` characters using `'('` and `')'`, and then **filter** only valid ones.

### 🔸 **Steps**:

1. Use recursion to generate all strings of length `2n`.
2. For each string, check if it's a valid parentheses sequence.
3. If valid, add it to the result.

### 🔸 Java Code:

```java
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        generateAll("", n * 2, result);
        return result;
    }

    private void generateAll(String curr, int maxLength, List<String> result) {
        if (curr.length() == maxLength) {
            if (isValid(curr))
                result.add(curr);
            return;
        }

        generateAll(curr + "(", maxLength, result);
        generateAll(curr + ")", maxLength, result);
    }

    private boolean isValid(String s) {
        int balance = 0;
        for (char ch : s.toCharArray()) {
            if (ch == '(') balance++;
            else balance--;
            if (balance < 0) return false;
        }
        return balance == 0;
    }
}
```

### 🔸 Time Complexity:

* Generating all strings: `O(2^(2n))`
* Validating each string: `O(n)`
* **Total:** `O(n * 2^(2n))`

### 🔸 Space Complexity: `O(n)` for recursion + output

---

## ⚡ 2. **Better Approach (Backtracking without filter)**

### 🔸 Idea:

Avoid generating invalid sequences in the first place.

Use **backtracking** by:

* Adding `'('` if `open < n`
* Adding `')'` if `close < open`

### 🔸 Java Code:

```java
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        generate(result, n, 0, 0, "");
        return result;
    }

    void generate(List<String> result, int n, int open, int close, String currStr) {
        if (currStr.length() == n * 2) {
            result.add(currStr);
            return;
        }

        if (open < n)
            generate(result, n, open + 1, close, currStr + "(");
        if (close < open)
            generate(result, n, open, close + 1, currStr + ")");
    }
}
```

### 🔸 Time Complexity:

* Total valid sequences are Catalan number: `C(n) = (2n)! / (n! * (n+1)!)`
* Each string is length `2n`
* **Total:** `O(C(n) * 2n)` ≈ `O(4^n / sqrt(n))`

### 🔸 Space Complexity: `O(n)` for recursion + output

---

## 🚀 3. **Optimal Approach (Backtracking with StringBuilder)**

### 🔸 Optimization:

Use a **mutable** object like `StringBuilder` instead of `String` to avoid new object creation at every step.

### 🔸 Java Code:

```java
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(new StringBuilder(), result, 0, 0, n);
        return result;
    }

    private void backtrack(StringBuilder sb, List<String> result, int open, int close, int n) {
        if (sb.length() == 2 * n) {
            result.add(sb.toString());
            return;
        }

        if (open < n) {
            sb.append("(");
            backtrack(sb, result, open + 1, close, n);
            sb.deleteCharAt(sb.length() - 1); // backtrack
        }

        if (close < open) {
            sb.append(")");
            backtrack(sb, result, open, close + 1, n);
            sb.deleteCharAt(sb.length() - 1); // backtrack
        }
    }
}
```

### 🔸 Time Complexity:

Same as better approach:

* **O(C(n) \* 2n)**

### 🔸 Space Complexity:

* `O(n)` for the StringBuilder and recursion
* Less overhead compared to immutable string

---

## ✅ Summary Table

| Approach              | Time Complexity             | Space Complexity | Notes                             |
| --------------------- | --------------------------- | ---------------- | --------------------------------- |
| Brute Force           | O(n \* 2^(2n))              | O(n)             | Inefficient                       |
| Better (Backtracking) | O(C(n) \* 2n) ≈ O(4^n / √n) | O(n)             | Efficient & Common                |
| Optimal (SB)          | O(C(n) \* 2n)               | O(n)             | Slightly faster due to mutability |

---
