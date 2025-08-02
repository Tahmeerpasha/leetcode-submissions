### 🧠 Problem: **Expression Add Operators**

Given a string `num` that contains only digits and an integer `target`, return all valid expressions that can be built by inserting the operators '+', '-', and '\*' between the digits so that the resultant expression evaluates to the target value.

---

## 🧪 Brute Force:

### ✅ Idea:

* Try inserting all combinations of '+', '-', and '\*' at every position between digits.
* Evaluate each expression using a **standard parser** or **expression evaluator** (like `eval()`).
* Add it to the result if the evaluated value matches the `target`.

### ❌ Limitations:

* Very inefficient due to generating all combinations without any pruning or mathematical optimization.
* Using `eval()` can be unsafe and slow.

### ⏱️ Time Complexity:

* **O(4ⁿ × n)** → 3 operators + empty space at each of (n-1) positions.
* For each expression, evaluating it takes **O(n)** time.

### 🧠 Space Complexity:

* **O(n)** recursion stack per path.

---

## 🧪 Better Approach:

### ✅ Idea:

* Instead of generating full expressions and then evaluating them,
* Maintain a path string, an evaluation (`eval`), and the previous operand (`prev`).
* As you build the string recursively, calculate intermediate results to avoid parsing.

### ❌ Limitations:

* Still exponential due to recursive exploration of all operator insertions.
* Doesn't handle overflow or memoization, so not optimal.

### ⏱️ Time Complexity:

* Still **O(4ⁿ)** in worst-case due to multiple splits and recursive calls.

---

## 🚀 Optimal Approach (Backtracking with correct operator precedence):

### ✅ Idea:

* Use backtracking with these parameters:

  * `path` = current expression string
  * `eval` = running evaluation of the expression
  * `prev` = last multiplied value to handle multiplication precedence
* Try placing each operator between substrings recursively.
* **Avoid numbers with leading zeros** (e.g., "05").

### ✅ Java Code (Your code):

```java
class Solution {
    public List<String> addOperators(String num, int target) {
        List<String> result = new ArrayList<>();
        backtrack(result, "", num, target, 0, 0, 0);
        return result;
    }

    private void backtrack(List<String> result, String path, String num, int target, int index, long eval, long prev) {
        if (index == num.length()) {
            if (eval == target) {
                result.add(path);
            }
            return;
        }

        for (int i = index; i < num.length(); i++) {
            if (i != index && num.charAt(index) == '0') break; // skip leading zeros

            String currStr = num.substring(index, i + 1);
            long currNum = Long.parseLong(currStr);

            if (index == 0) {
                // First number, no operator
                backtrack(result, currStr, num, target, i + 1, currNum, currNum);
            } else {
                // +
                backtrack(result, path + "+" + currStr, num, target, i + 1, eval + currNum, currNum);
                // -
                backtrack(result, path + "-" + currStr, num, target, i + 1, eval - currNum, -currNum);
                // *
                backtrack(result, path + "*" + currStr, num, target, i + 1,
                          eval - prev + prev * currNum, prev * currNum);
            }
        }
    }
}
```

### ⏱️ Time Complexity:

* **O(4ⁿ)** → At each position, we can either insert '+', '-', '\*', or do nothing.
* For each call, we try different splits of the remaining digits and recurse.

### 🧠 Space Complexity:

* **O(n)** recursion depth × **O(n)** for string building → effectively **O(n²)** total.

---

### 🔑 Key Concepts:

* Use backtracking to **build strings and compute values simultaneously**.
* Multiplication needs special care due to operator precedence, which is handled via the `prev` parameter.
* **Avoid leading zeros** when splitting strings (`"05"` is not valid).

---
