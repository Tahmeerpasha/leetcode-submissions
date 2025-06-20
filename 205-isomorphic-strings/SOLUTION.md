## 🔍 Problem Summary

**Given:** Two strings `s` and `t`.
**Goal:** Determine if `s` and `t` are **isomorphic**.

**Definition:** Two strings are isomorphic if the characters in `s` can be **replaced** to get `t`, with:

* A one-to-one character mapping
* No two characters from `s` mapping to the same character in `t`

---

## ✅ Optimal Approach (Dual HashMaps)

### 🔧 Logic:

* Use **two HashMaps**:

  * `mapST`: maps characters from `s → t`
  * `mapTS`: maps characters from `t → s`
* For every index `i`:

  * If `s[i]` already maps to some character, check if it’s equal to `t[i]`
  * If not mapped yet, check if `t[i]` is already mapped from some other character (to ensure one-to-one)

### ✅ Code:

```java
public boolean isIsomorphic(String s, String t) {
    if (s.length() != t.length()) return false;

    Map<Character, Character> mapST = new HashMap<>();
    Map<Character, Character> mapTS = new HashMap<>();

    for (int i = 0; i < s.length(); i++) {
        char chS = s.charAt(i);
        char chT = t.charAt(i);

        if (mapST.containsKey(chS)) {
            if (mapST.get(chS) != chT) return false;
        } else {
            if (mapTS.containsKey(chT)) return false;

            mapST.put(chS, chT);
            mapTS.put(chT, chS);
        }
    }
    return true;
}
```

---

## 🧱 Brute Force Approach

### 🔧 Logic:

* For every pair `(i, j)`, if `s[i] == s[j]` then check `t[i] == t[j]` (and vice versa).
* Time consuming and redundant for large strings.

### ❌ Time: `O(n^2)`

### ❌ Space: `O(1)`

(Not efficient and not used in practice.)

---

## 🧠 Alternate Optimal (Using Arrays Instead of HashMap)

Useful if only lowercase or ASCII characters:

```java
public boolean isIsomorphic(String s, String t) {
    int[] mapS = new int[256];
    int[] mapT = new int[256];

    for (int i = 0; i < s.length(); i++) {
        if (mapS[s.charAt(i)] != mapT[t.charAt(i)]) return false;
        mapS[s.charAt(i)] = i + 1;
        mapT[t.charAt(i)] = i + 1;
    }
    return true;
}
```

* Uses integer arrays to track last seen positions
* Much faster due to no hash operations

---

## 📊 Complexity Analysis

| Aspect        | Value                                    |
| ------------- | ---------------------------------------- |
| 🕒 Time       | `O(n)`                                   |
| 💾 Space      | `O(1)` (if arrays), `O(n)` (if HashMaps) |
| 🧪 Input Size | Up to `10^5` chars                       |

---

## 🧪 Examples

| s         | t         | Output  | Reason                               |
| --------- | --------- | ------- | ------------------------------------ |
| `"egg"`   | `"add"`   | ✅ true  | e → a, g → d                         |
| `"foo"`   | `"bar"`   | ❌ false | f → b, o → a (conflict)              |
| `"paper"` | `"title"` | ✅ true  | p → t, a → i, e → l, r → e           |
| `"ab"`    | `"aa"`    | ❌ false | a → a, b → a (b maps to same as a ❌) |

---

## 📝 Summary

| Approach               | Time  | Space | Notes                         |
| ---------------------- | ----- | ----- | ----------------------------- |
| ✅ HashMap Dual Mapping | O(n)  | O(n)  | Clean and general approach    |
| 🔁 Brute Force         | O(n²) | O(1)  | Inefficient, not used in prod |
| ⚡ Char Index Array     | O(n)  | O(1)  | Fastest for ASCII characters  |

---
