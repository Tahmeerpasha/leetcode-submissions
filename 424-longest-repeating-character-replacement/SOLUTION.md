## 🔹 Problem Recap

You are given a string `s` consisting of only uppercase English letters and an integer `k`.
You can replace at most `k` characters in the string with any letter.
Return the **length of the longest substring** containing the same letter after performing at most `k` replacements.

---

## 🔹 Brute Force Approach

**Idea**:
Try all substrings, count the frequency of each character, and check how many replacements are needed. If replacements ≤ `k`, update max length.

**Steps**:

1. Fix left pointer `i`.
2. Use right pointer `j` to expand substring.
3. Maintain frequency count of characters.
4. Track the maximum frequency (`maxF`) of any character inside substring.
5. Calculate required replacements = `(substring length) - maxF`.
   If replacements ≤ `k`, valid substring.

**Code sketch**:

```java
int maxLen = 0;
for (int i = 0; i < s.length(); i++) {
    int[] freq = new int[26];
    int maxF = 0;
    for (int j = i; j < s.length(); j++) {
        freq[s.charAt(j) - 'A']++;
        maxF = Math.max(maxF, freq[s.charAt(j) - 'A']);
        int changes = (j - i + 1) - maxF;
        if (changes <= k)
            maxLen = Math.max(maxLen, j - i + 1);
        else
            break; // further expansion not possible
    }
}
return maxLen;
```

**Complexity**:

* Time: **O(n²)** (double loop, frequency update inside)
* Space: **O(26) ≈ O(1)**

---

## 🔹 Better Approach

**Idea**:
Use **sliding window** but **recompute `maxF` every time** when window changes.
This avoids checking all substrings separately.

**Steps**:

1. Maintain sliding window `[l, r]`.
2. Maintain frequency of characters inside window.
3. Compute `maxF` = maximum frequency character inside window (loop through 26 each time).
4. If `(window length - maxF) > k`, shrink window from left.
5. Else, update answer.

**Code sketch**:

```java
int l = 0, r = 0, maxLen = 0;
int[] freq = new int[26];
while (r < s.length()) {
    freq[s.charAt(r) - 'A']++;
    // recompute maxF each time
    int maxF = 0;
    for (int f : freq) maxF = Math.max(maxF, f);

    int changes = (r - l + 1) - maxF;
    if (changes > k) {
        freq[s.charAt(l) - 'A']--;
        l++;
    }
    maxLen = Math.max(maxLen, r - l + 1);
    r++;
}
return maxLen;
```

**Complexity**:

* Time: **O(26·n) ≈ O(n)**
  (since maxF recalculation takes O(26) for each step)
* Space: **O(26)**

---

## 🔹 Optimal Approach

**Idea**:
Still sliding window, but **don’t recompute `maxF` every time**.
Instead, keep a running maximum frequency as window expands.
This works because even if `maxF` is slightly outdated, window validity check `(len - maxF > k)` ensures correctness.

**Steps**:

1. Expand right pointer, update freq and `maxF`.
2. If `(window length - maxF) > k`, shrink window.
3. Keep track of maximum window length.

**Code** (your optimal version):

```java
int l = 0, r = 0, maxLen = 0, maxF = 0;
int[] freq = new int[26];
while (r < s.length()) {
    freq[s.charAt(r) - 'A']++;
    maxF = Math.max(maxF, freq[s.charAt(r) - 'A']);
    int changes = (r - l + 1) - maxF;

    if (changes > k) {
        freq[s.charAt(l) - 'A']--;
        l++;
    }
    maxLen = Math.max(maxLen, r - l + 1);
    r++;
}
return maxLen;
```

**Complexity**:

* Time: **O(n)**
* Space: **O(26)**

---

## 🔹 Tips & Tricks

1. **Key Formula** →
   `changes = window_size - maxF`
   (all non-max characters need replacement).

2. **Why maxF works** →
   The substring can only become valid if we make all other characters equal to the most frequent one.

3. **Better vs Optimal**:

   * Better → recalc `maxF` inside loop (safe & intuitive).
   * Optimal → maintain running `maxF` (more efficient).
   * Both give same correctness, but optimal avoids the O(26) scan.

4. **Common Sliding Window Pattern**:

   * Expand `r`, track condition.
   * If condition breaks, shrink `l`.
   * Update answer.

---

✅ **Final Recommendation for Interviews**:

* Explain **Brute → Better → Optimal** transition clearly.
* Write the **Optimal** solution, but mention **Better** (with recalculating maxF) if interviewer asks about correctness guarantees.

---
