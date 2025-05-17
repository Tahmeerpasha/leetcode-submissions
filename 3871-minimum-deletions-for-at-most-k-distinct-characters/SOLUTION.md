## 📝 Problem: **3545. Minimum Deletions for At Most K Distinct Characters**

**Given:** A string `s` and an integer `k`.
**Task:** Delete the *minimum* number of characters from `s` so that there are **at most `k` distinct characters** in the string.

---

### 🧠 Understanding:

We want to reduce the number of **distinct characters** in the string to **≤ `k`** by deleting as *few* characters as possible.

---

## ✅ Your Solution (Optimal Approach)

### 🧾 Idea:

1. Count the frequency of each character.
2. If the number of distinct characters is already ≤ `k`, no need to delete.
3. Else, remove characters with the *smallest frequencies* (as they affect the string the least) until only `k` distinct characters remain.

### ✅ Time Complexity:

* Counting characters: `O(n)`
* Sorting frequencies: `O(d log d)` (where `d` = number of distinct characters, ≤ 26 for lowercase letters)

---

### ✅ Code:

```java
public int minDeletion(String s, int k) {
    HashMap<Character, Integer> map = new HashMap<>();
    for (int i = 0; i < s.length(); i++) {
        map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
    }
    if (map.size() <= k)
        return 0;
    return map.values().stream()
            .sorted()
            .limit(map.size() - k)
            .mapToInt(Integer::intValue)
            .sum();
}
```

---

### ✨ Dry Run Example:

**Input:** `s = "aaabbbccdde"`, `k = 2`
Character counts:

```
a -> 3  
b -> 3  
c -> 2  
d -> 2  
e -> 1  
```

Distinct chars = 5, need to remove `5 - 2 = 3` characters.

Remove smallest frequencies:

* Remove `e (1)`, `c (2)` → `1+2=3 deletions`

✅ Output = `3`

---

## 🐢 Brute Force Approach (for understanding)

### 🧾 Idea:

* Try removing every possible combination of characters to leave only `k` distinct ones.
* This is a combinatorial approach and **not feasible** for large strings.

### ❌ Time Complexity:

* Exponential in worst case. Very inefficient.

---

## ✅ Final Notes:

| Approach        | Time Complexity  | Space Complexity  | Feasible for large `s`? |
| --------------- | ---------------- | ----------------- | ----------------------- |
| Brute Force     | Exponential      | `O(n)`            | ❌                       |
| Optimal (Yours) | `O(n + d log d)` | `O(d)` (`d` ≤ 26) | ✅                       |

---
