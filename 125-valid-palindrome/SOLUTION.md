## 📝 Problem: **Valid Palindrome**

**Given:** A string `s`
**Task:** Return `true` if `s` is a palindrome **considering only alphanumeric characters** and **ignoring cases**.

---

### 🧠 Key Concepts:

* A **palindrome** is a string that reads the same backward as forward.
* **Ignore** punctuation, spaces, and symbols.
* **Case-insensitive** comparison.

---

## ✅ Constraints:

* `1 <= s.length <= 2 * 10⁵`
* Contains ASCII characters

---

## ✅ Optimal Approach: **Two-Pointer Technique**

### 🔄 Logic:

* Initialize two pointers: `left = 0` and `right = s.length - 1`
* Move both inward:

  * Skip any character that's not a letter or digit
  * Compare characters in lowercase
* If any mismatch → return `false`
* If loop finishes without mismatch → return `true`

---

### ✅ Time and Space Complexity:

| Metric | Value  |                                     |
| ------ | ------ | ----------------------------------- |
| Time   | `O(n)` |                                     |
| Space  | `O(1)` | *(no extra space except variables)* |

---

### 🔧 Code Explanation:

```java
public boolean isPalindrome(String s) {
    int left = 0, right = s.length() - 1;

    while (left < right) {
        // Skip non-alphanumeric from left
        while (left < right && !Character.isLetterOrDigit(s.charAt(left))) left++;

        // Skip non-alphanumeric from right
        while (left < right && !Character.isLetterOrDigit(s.charAt(right))) right--;

        // Compare characters in lowercase
        if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
            return false;
        }

        left++;
        right--;
    }

    return true;
}
```

---

### 🔁 Example:

**Input:** `"A man, a plan, a canal: Panama"`
**Cleaned:** `"amanaplanacanalpanama"`
**Output:** `true`

---

## 🧪 Alternative Approaches:

### 1. **Recursive Approach** (less preferred for large strings):

```java
public boolean isPalindrome(String s) {
   return check(s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase().toCharArray(), 0);
}

boolean check(char[] s, int i) {
   if (i >= s.length / 2) return true;
   int j = s.length - i - 1;
   if (s[i] != s[j]) return false;
   return check(s, i + 1);
}
```

* **Time:** `O(n)`
* **Space:** `O(n)` due to recursive stack
* **Note:** Might cause stack overflow on long input strings

---

## 🏁 Final Notes:

* The **two-pointer approach** is the most efficient for this problem.
* It avoids unnecessary memory usage by skipping string reconstruction.
* Make sure to handle all non-alphanumeric characters carefully.
