### 🔢 Problem:

Partition a string `s` such that every substring of the partition is a **palindrome**. Return all possible palindrome partitionings.

---

### 🧠 Brute Force Approach:

#### 🔍 Idea:

* Generate **all possible partitions** of the string.
* For each partition, **check whether all substrings are palindromes**.

#### 🧾 Steps:

1. Generate all partitions using recursion or bitmasking.
2. For each partition, validate if each part is a palindrome.
3. Add to result if valid.

#### ⏱ Time Complexity:

* Generating all partitions: **O(2^n)**
* Validating each partition: O(n^2) worst-case
* Total: **O(2^n \* n^2)**

#### 📦 Space Complexity:

* **O(n)** recursion stack
* **O(k \* x)** for storing `k` partitions of average length `x`

#### ❌ Drawbacks:

* Redundant palindrome checks
* High time cost

---

### ⚙️ Better Approach: Backtracking + On-the-fly Palindrome Check

#### 🔍 Idea:

* Use backtracking to build substrings from left to right.
* For each substring, check palindrome on-the-fly (using `isPalindrome`).
* If it's a palindrome, recurse on the rest of the string.

#### ✅ Code:

✅ Your provided code is already this approach.

```java
class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        backtrack(s, 0, new ArrayList<>(), result);
        return result;
    }

    void backtrack(String s, int index, List<String> path, List<List<String>> result) {
        if (index == s.length()) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = index; i < s.length(); ++i) {
            if (isPalindrome(s, index, i)) {
                path.add(s.substring(index, i + 1));
                backtrack(s, i + 1, path, result);
                path.remove(path.size() - 1);
            }
        }
    }

    boolean isPalindrome(String s, int start, int end) {
        while (start < end) {
            if (s.charAt(start++) != s.charAt(end--)) return false;
        }
        return true;
    }
}
```

#### ⏱ Time Complexity:

* Backtracking paths: **O(2^n)** (each character can start or not start a new partition)
* Palindrome check per substring: **O(n)**
* Total: **O(2^n \* n)** (average)

#### 📦 Space Complexity:

* **O(k \* x)** for result storage
* **O(n)** recursion stack

---

### 🚀 Optimal Approach: Backtracking + DP Memoization (Precompute Palindromes)

#### 🔍 Idea:

* Precompute a DP table `dp[i][j]` = true if `s[i...j]` is a palindrome.
* Avoid repeated palindrome checks inside backtracking.

#### 🧾 Steps:

1. Build a 2D DP table to store palindrome info.
2. Use the same backtracking logic but refer to the `dp` table for palindrome checks.

#### ✅ Improvements:

* Palindrome check becomes O(1)
* Preprocessing: O(n^2)

#### ⏱ Time Complexity:

* Backtracking paths: **O(2^n)**
* Preprocessing palindrome DP: **O(n^2)**
* Total: **O(2^n + n^2)** → better in practice than rechecking every substring.

#### 📦 Space Complexity:

* **O(n^2)** for DP table
* **O(n)** recursion stack

---

### ✅ Summary Table

| Approach               | Time Complexity | Space Complexity   | Notes                                |
| ---------------------- | --------------- | ------------------ | ------------------------------------ |
| Brute Force            | O(2^n \* n^2)   | O(k \* x)          | Generates all partitions, checks all |
| Better (BT + PalCheck) | O(2^n \* n)     | O(k \* x) + O(n)   | Efficient, used most commonly        |
| Optimal (BT + DP)      | O(2^n + n^2)    | O(n^2) + O(k \* x) | Fastest due to memoization           |

---
