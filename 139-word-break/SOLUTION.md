## 🔒 Problem: 139. Word Break

Given a string `s` and a dictionary of strings `wordDict`, return `true` if `s` can be segmented into a space-separated sequence of one or more dictionary words.

---

### ✅ Constraints

* `1 <= s.length <= 300`
* `1 <= wordDict.length <= 1000`
* `1 <= wordDict[i].length <= 20`
* `s` and `wordDict[i]` consist of lowercase English letters.

---

## ✅ Brute Force (Recursion)

### 🧠 Idea:

Try breaking the string from every possible index and check if the prefix is in the dictionary. If yes, recursively solve the suffix.

### 🧾 Code:

```java
boolean solve(Set<String> dict, String s, int index) {
    if (index == s.length())
        return true;

    StringBuilder temp = new StringBuilder();
    for (int i = index; i < s.length(); i++) {
        temp.append(s.charAt(i));
        if (dict.contains(temp.toString())) {
            if (solve(dict, s, i + 1))
                return true;
        }
    }
    return false;
}
```

### 🧮 Complexity:

* **Time:** Exponential `O(2^n)` — each character may or may not split
* **Space:** `O(n)` recursive stack

---

## ♻️ Better (Top-down Memoization)

### 🧠 Idea:

Same as brute, but avoid recomputation using a memoization map (`index -> boolean`).

### 🧾 Code:

```java
boolean solve(Set<String> dict, String s, int index, Map<Integer, Boolean> memo) {
    if (index == s.length())
        return true;
    if (memo.containsKey(index))
        return memo.get(index);

    StringBuilder temp = new StringBuilder();
    for (int i = index; i < s.length(); i++) {
        temp.append(s.charAt(i));
        if (dict.contains(temp.toString())) {
            if (solve(dict, s, i + 1, memo)) {
                memo.put(index, true);
                return true;
            }
        }
    }

    memo.put(index, false);
    return false;
}
```

### 🧮 Complexity:

* **Time:** `O(n²)` — for each index, explore all substrings, memo saves repeated work
* **Space:** `O(n)` memo + recursion stack

---

## 🚀 Optimal (Tabulation - Bottom-Up)

### 🧠 Idea:

Use a DP array `dp[i]` = true if `s[0...i)` can be broken into words. Build the solution iteratively.

### 🧾 Code:

```java
public boolean wordBreak(String s, List<String> wordDict) {
    Set<String> dict = new HashSet<>(wordDict);
    boolean[] dp = new boolean[s.length() + 1];
    dp[0] = true;

    for (int i = 1; i <= s.length(); i++) {
        for (int j = 0; j < i; j++) {
            if (dp[j] && dict.contains(s.substring(j, i))) {
                dp[i] = true;
                break;
            }
        }
    }
    return dp[s.length()];
}
```

### 🧮 Complexity:

* **Time:** `O(n²)` — two nested loops
* **Space:** `O(n)` — DP array

---

## 🧪 Final Code (Combined Brute + Memo):

```java
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        // Brute
        // return solve(new HashSet<>(wordDict), s, 0);
        // Optimal with DP
        return solve(new HashSet<>(wordDict), s, 0, new HashMap<>());
    }

    boolean solve(Set<String> dict, String s, int index) {
        if (index == s.length())
            return true;

        StringBuilder temp = new StringBuilder();
        for (int i = index; i < s.length(); i++) {
            temp.append(s.charAt(i));
            if (dict.contains(temp.toString())) {
                if (solve(dict, s, i + 1))
                    return true;
            }
        }
        return false;
    }

    boolean solve(Set<String> dict, String s, int index, Map<Integer, Boolean> memo) {
        if (index == s.length())
            return true;
        if (memo.containsKey(index))
            return memo.get(index);

        StringBuilder temp = new StringBuilder();
        for (int i = index; i < s.length(); i++) {
            temp.append(s.charAt(i));
            if (dict.contains(temp.toString())) {
                if (solve(dict, s, i + 1, memo)) {
                    memo.put(index, true);
                    return true;
                }
            }
        }

        memo.put(index, false);
        return false;
    }
}
```

---
