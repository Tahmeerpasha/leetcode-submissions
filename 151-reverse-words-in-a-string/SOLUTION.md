## 🔍 Problem Summary

**Given** a string `s`, reverse the **order of words**.
Words are separated by **one or more spaces**, and the result must:

* Remove **leading and trailing spaces**
* Ensure only **single spaces** between words

---

## 🧱 Brute Force Approach

### 🔧 Logic:

* Split the string using regular expression `" +"` (one or more spaces)
* Reverse the array of words
* Rebuild the string with exactly one space between words

### ✅ Java Code:

```java
public String reverseWords(String s) {
    String[] words = s.trim().split(" +");
    StringBuilder ans = new StringBuilder();
    for (int i = words.length - 1; i >= 0; i--) {
        ans.append(words[i]).append(" ");
    }
    return ans.toString().trim();
}
```

### ⏱ Time Complexity: `O(n)`

### 📦 Space Complexity: `O(n)`

(for storing words and result)

✅ Your code already uses this optimal brute-force approach.

---

## 🔁 Better Approach (Using Stack)

### 🔧 Logic:

* Manually parse the string, collect each word
* Push each word into a **stack**
* Pop from stack to reverse order

### ✅ Java Code:

```java
public String reverseWords(String s) {
    Stack<String> stack = new Stack<>();
    String[] words = s.trim().split(" +");
    for (String word : words) {
        stack.push(word);
    }

    StringBuilder result = new StringBuilder();
    while (!stack.isEmpty()) {
        result.append(stack.pop()).append(" ");
    }
    return result.toString().trim();
}
```

### ⏱ Time: `O(n)`

### 📦 Space: `O(n)`

(for stack and result)

🟡 More intuitive but not more efficient than the brute.

---

## 🚀 Optimal In-Place Approach (For Interviews / C++ / Python)

> Java strings are immutable, so this approach is more common in languages where strings can be modified in-place like C++ or Python (with lists).

### 🔧 Steps:

1. Remove extra spaces (leading, trailing, multiple in between)
2. Reverse the entire string
3. Reverse each word in the reversed string

### ✨ Conceptual Example:

```
Input:  "  the sky  is  blue  "
Step 1: "the sky is blue"
Step 2: "eulb si yks eht"
Step 3: "blue is sky the"
```

This avoids using extra space for stack or arrays.

---

## 📝 Summary Table

| Approach              | Time   | Space  | Notes                                    |
| --------------------- | ------ | ------ | ---------------------------------------- |
| Brute (split + trim)  | `O(n)` | `O(n)` | Clean and effective, works well in Java  |
| Stack-based           | `O(n)` | `O(n)` | More intuitive for beginners             |
| In-place (conceptual) | `O(n)` | `O(1)` | Optimal for in-place mutation (not Java) |

---

## 📦 Edge Case Handling

* Multiple spaces between words → `"a   b"` → `"b a"`
* Leading/trailing spaces → `"  hello world  "` → `"world hello"`
* Empty or only spaces → `""` or `"   "` → `""`

---
