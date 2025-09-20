## 3. Longest Substring Without Repeating Characters

You’re given a string `s`. Find the **length of the longest substring** (continuous segment) that has **all unique characters**.

Example:
`s = "abcabcbb"` → Longest substring = `"abc"` → length = 3

---

## 1️⃣ Brute Force Approach

**Idea**: Generate all substrings and check if each substring has unique characters.

**Steps**:

1. Loop over all substrings using 2 loops (start `i`, end `j`).
2. For each substring `s[i..j]`, check if it contains all unique characters using a `Set`.
3. Track the maximum length.

**Code Sketch (Brute Force)**:

```java
int n = s.length();
int maxLen = 0;
for (int i = 0; i < n; i++) {
    Set<Character> set = new HashSet<>();
    for (int j = i; j < n; j++) {
        if (set.contains(s.charAt(j))) break;
        set.add(s.charAt(j));
        maxLen = Math.max(maxLen, j - i + 1);
    }
}
return maxLen;
```

**Complexity**:

* Generating substrings = O(n²)
* Checking uniqueness (with set) = O(n)
* **Time: O(n³)** (with naive checking), improved to **O(n²)** with HashSet.
* **Space: O(min(n, charset))**

---

## 2️⃣ Better Approach (Sliding Window with Set)

**Idea**: Use a **sliding window** to avoid recomputation. Expand right pointer (`r`), shrink left pointer (`l`) when duplicate occurs.

**Steps**:

1. Maintain a `HashSet` of current characters in the window.
2. Move `r` forward: if character not in set → add it, update maxLen.
3. If duplicate occurs, remove characters from left until duplicate is removed.

**Code Sketch**:

```java
Set<Character> set = new HashSet<>();
int l = 0, r = 0, maxLen = 0;
while (r < s.length()) {
    if (!set.contains(s.charAt(r))) {
        set.add(s.charAt(r));
        maxLen = Math.max(maxLen, r - l + 1);
        r++;
    } else {
        set.remove(s.charAt(l));
        l++;
    }
}
return maxLen;
```

**Complexity**:

* Each character is added & removed at most once.
* **Time: O(n)**
* **Space: O(min(n, charset))**

---

## 3️⃣ Optimal Approach (Sliding Window with Index Map ✅)

**Idea**: Instead of removing characters one by one, directly jump the left pointer past the duplicate’s last position.

**Steps**:

1. Use an array `map[256]` or HashMap to store the **last index** of each character.
2. If the current char was seen inside the window (`map[ch] >= l`), move `l = map[ch] + 1`.
3. Update maxLen and update `map[ch] = r`.

**Code (Optimal)** 
```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int l = 0, r = 0, maxLen = 0;
        int[] map = new int[256];
        for (int i = 0; i < 256; i++)
            map[i] = -1;
        while (r < s.length()) {
            if (map[s.charAt(r)] != -1 && map[s.charAt(r)] >= l) {
                l = map[s.charAt(r)] + 1;
            }
            maxLen = Math.max(maxLen, r - l + 1);
            map[s.charAt(r)] = r;
            r++;
        }
        return maxLen;
    }
}
```

**Complexity**:

* **Time: O(n)** (each char processed once)
* **Space: O(min(n, charset))**

---

## 💡 Tips for Interviews

* **Brute first**: Always explain brute force → interviewer knows you understand basics.
* **Move to sliding window**: Mention how to optimize substring checks with a sliding window.
* **Optimal improvement**: Instead of removing one by one, jump directly using last seen index (saves unnecessary work).
* **Charset assumption**: If only lowercase letters, use `int[26]`. If ASCII, use `int[256]`. If Unicode, prefer `HashMap<Character, Integer>`.
* **Edge cases**:

  * Empty string → return 0
  * String with all unique chars (`"abcdef"`) → return `n`
  * String with all same chars (`"aaaa"`) → return 1

---

✅ **Final Answer**:

* **Brute**: O(n²)/O(n³) with Set
* **Better**: O(n) with sliding window + HashSet
* **Optimal**: O(n) with sliding window + last index map

---
