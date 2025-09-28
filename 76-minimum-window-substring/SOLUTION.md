# 🚀 76. Minimum Window Substring

### **Problem Recap**

Given two strings `s` and `t`, find the **minimum window substring** in `s` that contains all the characters of `t`.

* If no such substring exists, return `""`.
* The order of characters in `t` doesn’t matter, only counts matter.

---

## 1️⃣ Brute Force Approach

**Idea:**

* Generate **all substrings** of `s`.
* For each substring, check if it contains all characters of `t`.
* Track the minimum length substring that satisfies the condition.

**Code Sketch:**

```java
class Solution {
    public String minWindow(String s, String t) {
        int n = s.length();
        String ans = "";
        int minLen = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                String sub = s.substring(i, j + 1);
                if (containsAll(sub, t) && sub.length() < minLen) {
                    ans = sub;
                    minLen = sub.length();
                }
            }
        }
        return ans;
    }

    private boolean containsAll(String sub, String t) {
        int[] freq = new int[256];
        for (char c : sub.toCharArray()) freq[c]++;
        for (char c : t.toCharArray()) {
            if (--freq[c] < 0) return false;
        }
        return true;
    }
}
```

**Complexity:**

* Generating substrings: **O(n²)**
* Checking each substring: **O(n + m)**
* **Total:** **O(n³)** (too slow for large strings).

---

## 2️⃣ Better Approach (Sliding Window + Validation per Window)

**Idea:**

* Use a sliding window (`l` and `r`).
* Expand `r` until window contains all chars of `t`.
* Then shrink from `l` while still valid.
* Keep checking validity by comparing window frequency map with `t`’s frequency.

**Code Sketch:**

```java
class Solution {
    public String minWindow(String s, String t) {
        int[] target = new int[256];
        for (char c : t.toCharArray()) target[c]++;

        int[] window = new int[256];
        int l = 0, minLen = Integer.MAX_VALUE, start = -1;

        for (int r = 0; r < s.length(); r++) {
            window[s.charAt(r)]++;

            while (isValid(window, target)) {
                if (r - l + 1 < minLen) {
                    minLen = r - l + 1;
                    start = l;
                }
                window[s.charAt(l)]--;
                l++;
            }
        }
        return start == -1 ? "" : s.substring(start, start + minLen);
    }

    private boolean isValid(int[] window, int[] target) {
        for (int i = 0; i < 256; i++) {
            if (window[i] < target[i]) return false;
        }
        return true;
    }
}
```

**Complexity:**

* For each `r`, worst-case check is **O(256)** = constant.
* But many unnecessary checks → still inefficient.
* **Time:** ~ **O(256·n) ≈ O(n)** (but slower than optimal).
* **Space:** O(256).

---

## 3️⃣ Optimal Approach (Sliding Window + Count Tracking ✅)

**Idea (used in your code):**

* Use one frequency map `hash[]` for `t`.
* Maintain `count` = number of characters matched so far.
* Expand `r` → decrease `hash[ch]`. If `hash[ch] > 0`, means that char was needed, so increase `count`.
* When `count == t.length()`, shrink `l` to minimize window.
* Update minimum length answer on the way.

**Code (Final Optimal):**

```java
class Solution {
    public String minWindow(String s, String t) {
        int l = 0, r = 0, count = 0, minLen = Integer.MAX_VALUE;
        int[] hash = new int[256];
        int start = -1;

        // fill frequency for t
        for (char c : t.toCharArray()) hash[c]++;

        while (r < s.length()) {
            char ch = s.charAt(r);
            if (hash[ch] > 0) count++;
            hash[ch]--;

            // valid window → try shrink
            while (count == t.length()) {
                if (r - l + 1 < minLen) {
                    minLen = r - l + 1;
                    start = l;
                }
                hash[s.charAt(l)]++;
                if (hash[s.charAt(l)] > 0) count--;
                l++;
            }
            r++;
        }

        return start == -1 ? "" : s.substring(start, start + minLen);
    }
}
```

**Complexity:**

* **Time:** O(n + m)

  * O(m) to fill hash for `t`.
  * O(n) for traversing `s` with `l` and `r`.
* **Space:** O(256) (constant).
* ✅ Best possible solution.

---

## 💡 Tips & Tricks

1. **Sliding window template**:

   * Expand `r` until valid.
   * Shrink `l` while still valid.
   * Update best answer during shrinking.

2. **Hashing with ASCII size (256)** is common for character frequency.
   For lowercase only → size `26`.

3. **Key trick:** Instead of checking full validity every time, maintain a `count` of how many characters of `t` are currently satisfied.

4. **Interview tip:**

   * Start brute → improve to better with sliding window → finally optimize with count-tracking.
   * Show interviewer you know how to **reduce redundant validity checks**.

---
