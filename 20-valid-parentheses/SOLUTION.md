# **20. Valid Parentheses**

### **Problem**

Given a string containing just `'(' , ')' , '{' , '}' , '[' , ']'`, determine if the string is valid.
A string is valid if:

1. Open brackets are closed by the same type of brackets.
2. Open brackets are closed in the correct order.
3. Every closing bracket has a corresponding opening bracket.

---

## **Brute Force Approach**

* Repeatedly **find and remove valid pairs** `"()"`, `"{}"`, `"[]"` from the string.
* Continue until no more pairs can be removed.
* If the final string is empty → valid, else → invalid.

**Complexity:**

* Time → O(N²) (since repeatedly scanning & replacing).
* Space → O(1).

---

## **Better / Optimal Approach (Using Stack)** ✅

* Use a **stack** to store opening brackets.
* Traverse string:

  * If opening bracket → push to stack.
  * If closing bracket → check top of stack:

    * If matches, pop.
    * Else return false.
* At the end, if stack empty → valid.

**Code:**

```java
class Solution {
    public boolean isValid(String s) {
        if (s.length() % 2 != 0) return false; // odd length can't be valid
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') stack.push(c);
            else if (stack.isEmpty()) return false;
            else {
                char top = stack.pop();
                if ((c == ')' && top != '(') || 
                    (c == '}' && top != '{') || 
                    (c == ']' && top != '[')) return false;
            }
        }
        return stack.isEmpty();
    }
}
```

**Complexity:**

* Time → O(N) (single pass).
* Space → O(N) (stack storage).

---

## **Optimized Approach (HashMap + Stack)**

* Use a **HashMap** to store mappings `{ ')' → '(', '}' → '{', ']' → '[' }`.
* Cleaner code with fewer conditions.

**Code Snippet:**

```java
Map<Character, Character> map = Map.of(')', '(', '}', '{', ']', '[');
Stack<Character> stack = new Stack<>();
for (char c : s.toCharArray()) {
    if (map.containsKey(c)) { // closing bracket
        char top = stack.isEmpty() ? '#' : stack.pop();
        if (top != map.get(c)) return false;
    } else {
        stack.push(c);
    }
}
return stack.isEmpty();
```

**Complexity:**

* Time → O(N)
* Space → O(N)

---

### **Summary**

* **Brute:** Remove pairs repeatedly → O(N²).
* **Better/Optimal:** Use stack to track open brackets → O(N).
* **Optimized (clean code):** Stack + HashMap → O(N).

---
