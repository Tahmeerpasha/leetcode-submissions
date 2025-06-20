## 🔍 Problem Summary

**Given:** Two strings `s` and `t`
**Goal:** Return `true` if `t` is an anagram of `s`.

✅ An anagram means both strings:

* Contain the **same characters**
* Have the **same frequency** for each character

---

## 🔨 Brute Force Approach

### 🔧 Logic:

* Sort both strings.
* If the sorted versions match, they are anagrams.

### 🧪 Code:

```java
public boolean isAnagram(String s, String t) {
    if (s.length() != t.length()) return false;
    char[] a = s.toCharArray();
    char[] b = t.toCharArray();
    Arrays.sort(a);
    Arrays.sort(b);
    return Arrays.equals(a, b);
}
```

### 📊 Time & Space:

* **Time:** O(n log n) for sorting both strings
* **Space:** O(n) to store sorted arrays

---

## ⚡ Optimal Approach – Using Frequency Array

### 🔧 Logic:

* Count the frequency of each character in `s`.
* Subtract the frequency for characters in `t`.
* If all counts return to 0, they are anagrams.

### ✅ Code:

```java
public boolean isAnagram(String s, String t) {
    if (s.length() != t.length()) return false;
    int[] freq = new int[26];
    for (int i = 0; i < s.length(); i++) {
        freq[s.charAt(i) - 'a']++;
        freq[t.charAt(i) - 'a']--;
    }
    for (int count : freq) {
        if (count != 0) return false;
    }
    return true;
}
```

### 📊 Time & Space:

* **Time:** O(n)
* **Space:** O(1) (fixed size array for lowercase letters)

---

## 📝 Summary

| Approach          | Time Complexity | Space Complexity | Remarks                     |
| ----------------- | --------------- | ---------------- | --------------------------- |
| ✅ Frequency Array | O(n)            | O(1)             | Best for lowercase only     |
| 🌀 Sorting        | O(n log n)      | O(n)             | Clean, built-in, but slower |

---

## 📦 Bonus: Unicode / Extended Character Set

If input contains **Unicode** (not just lowercase), use a `HashMap<Character, Integer>` instead of a fixed array.

```java
public boolean isAnagram(String s, String t) {
    if (s.length() != t.length()) return false;
    Map<Character, Integer> map = new HashMap<>();
    for (char c : s.toCharArray()) map.put(c, map.getOrDefault(c, 0) + 1);
    for (char c : t.toCharArray()) {
        if (!map.containsKey(c)) return false;
        map.put(c, map.get(c) - 1);
        if (map.get(c) == 0) map.remove(c);
    }
    return map.isEmpty();
}
```

---
