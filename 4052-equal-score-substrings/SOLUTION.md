##  Problem

You are given a string `s` consisting of lowercase English letters.
The **score** of a string is the sum of positions of its characters in the alphabet:
`a = 1, b = 2, ..., z = 26`.

We need to determine if there exists an index `i` such that the string can be split into **two non-empty substrings**:

```
s[0..i]  and  s[i+1..n-1]
```

that have **equal scores**.

---

## 🧩 Example

**Input:** `s = "adcb"`
**Output:** `true`

**Explanation:**
Split at `i = 1` →
Left = `"ad"` → (1 + 4 = 5)
Right = `"cb"` → (3 + 2 = 5)
✅ Equal scores → return true

---

## 🪓 Brute Force Approach

### 🔹 Idea

Try every possible split point `i`,
then calculate the **left** and **right** substring scores separately.

### 🔹 Code

```java
// Time: O(n^2), Space: O(1)
class Solution {
    public boolean scoreBalance(String s) {
        int n = s.length();

        for (int i = 0; i < n; i++) {
            String s1 = s.substring(0, i);
            String s2 = s.substring(i, n);

            int sum1 = 0, sum2 = 0;

            for (int j = 0; j < s1.length(); j++)
                sum1 += s1.charAt(j) - 'a' + 1;

            for (int j = 0; j < s2.length(); j++)
                sum2 += s2.charAt(j) - 'a' + 1;

            if (sum1 == sum2)
                return true;
        }

        return false;
    }
}
```

### 🔹 Complexity

* **Time:** O(n²) → recalculating sums for each split
* **Space:** O(1)

---

## ⚡ Better Approach (Prefix Sum Array)

### 🔹 Idea

Instead of recalculating sums every time,
precompute a **prefix sum array** that stores cumulative scores up to each index.
Then we can get any substring sum in O(1).

### 🔹 Code

```java
// Time: O(n), Space: O(n)
class Solution {
    public boolean scoreBalance(String s) {
        int n = s.length();
        int[] prefix = new int[n];
        
        prefix[0] = s.charAt(0) - 'a' + 1;
        for (int i = 1; i < n; i++)
            prefix[i] = prefix[i - 1] + (s.charAt(i) - 'a' + 1);

        int total = prefix[n - 1];

        for (int i = 0; i < n - 1; i++) {
            int leftSum = prefix[i];
            int rightSum = total - prefix[i];
            if (leftSum == rightSum)
                return true;
        }

        return false;
    }
}
```

### 🔹 Complexity

* **Time:** O(n)
* **Space:** O(n)

---

## 🚀 Optimal Approach (Prefix Sum + Constant Space)

### 🔹 Idea

We don’t need an array.
Just keep track of:

* **`total`** → total score of entire string
* **`leftSum`** → running sum while traversing

At each step:

```
rightSum = total - leftSum
if (leftSum == rightSum) → return true
```

### 🔹 Code

```java
// Time: O(n), Space: O(1)
class Solution {
    public boolean scoreBalance(String s) {
        int n = s.length();
        int total = 0;

        // Step 1: compute total score
        for (char ch : s.toCharArray())
            total += ch - 'a' + 1;

        int leftSum = 0;

        // Step 2: check prefix sum balance
        for (int i = 0; i < n - 1; i++) { // split before last char
            leftSum += s.charAt(i) - 'a' + 1;
            int rightSum = total - leftSum;

            if (leftSum == rightSum)
                return true;
        }

        return false;
    }
}
```

### 🔹 Complexity

* **Time:** O(n)
* **Space:** O(1)

---

## 🧠 Summary Table

| Approach    | Time  | Space | Technique          | Notes                  |
| ----------- | ----- | ----- | ------------------ | ---------------------- |
| **Brute**   | O(n²) | O(1)  | Recompute sums     | Simple but inefficient |
| **Better**  | O(n)  | O(n)  | Prefix sum array   | Avoids recomputation   |
| **Optimal** | O(n)  | O(1)  | Running prefix sum | Best — minimal space   |

---
