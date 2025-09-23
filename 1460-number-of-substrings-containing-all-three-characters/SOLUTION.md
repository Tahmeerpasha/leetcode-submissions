## **Problem**

> Given a string `s` consisting of only characters `'a'`, `'b'`, and `'c'`, return the number of substrings containing **at least one of each character**.

---

## **1️⃣ Brute Force Approach**

### **Idea**

* Check **all possible substrings**.
* Count the number of `'a'`, `'b'`, `'c'` in each substring.
* As soon as all three characters are present, **all remaining substrings starting from this `i` are valid**, so we can add `s.length() - j`.

### **Code**

```java
int count = 0;
for (int i = 0; i < s.length(); i++) {
    int a = 0, b = 0, c = 0;
    for (int j = i; j < s.length(); j++) {
        char ch = s.charAt(j);
        if (ch == 'a') a++;
        if (ch == 'b') b++;
        if (ch == 'c') c++;
        if (a >= 1 && b >= 1 && c >= 1) {
            count += s.length() - j;
            break;
        }
    }
}
return count;
```

### **Complexity**

* **Time:** O(N²) — because we check all substrings starting at each index.
* **Space:** O(1) — only counters for `a, b, c`.

### **Pros & Cons**

* ✅ Easy to understand.
* ❌ Too slow for `N ~ 10^5`.

---

## **2️⃣ Better Approach (Sliding Window / Two Pointers)**

### **Idea**

* Maintain a **sliding window** `[l, r]` that always contains all 3 characters.
* Expand `r` until all three are present.
* The number of valid substrings ending at `r` is `(l + 1)` because any substring starting from `0..l` to `r` is valid.
* Move `l` to shrink window and continue.

> This reduces redundant counting compared to brute force.

### **Code**
```java
class Solution {
    public int numberOfSubstrings(String s) {
        int[] count = new int[3]; // counts for 'a', 'b', 'c'
        int left = 0, result = 0;
        
        for (int right = 0; right < s.length(); right++) {
            count[s.charAt(right) - 'a']++;
            
            // shrink window from left while all characters are present
            while (count[0] > 0 && count[1] > 0 && count[2] > 0) {
                count[s.charAt(left) - 'a']--;
                left++;
            }
            
            // all substrings ending at 'right' and starting before 'left' are valid
            result += left;
        }
        
        return result;
    }
}

```
### **Complexity**

* **Time:** O(2N) ≈ O(N) — each pointer moves at most N times.
* **Space:** O(1) — count array for 3 characters.

---

## **3️⃣ Optimal Approach (Last Seen Indices)**

### **Idea**

* Track **last seen indices** of `'a'`, `'b'`, `'c'`.

* For each index `i`:

  1. Update `lastSeen` for `s[i]`.
  2. If all characters have appeared at least once, the number of valid substrings ending at `i` = `1 + min(lastSeen[0], lastSeen[1], lastSeen[2])`.

* **Why it works:**
  Any substring ending at `i` and starting **anywhere ≤ minimum last seen index** contains all 3 characters.

### **Code**

```java
int[] lastSeen = new int[] { -1, -1, -1 };
int count = 0;
for (int i = 0; i < s.length(); i++) {
    lastSeen[s.charAt(i) - 'a'] = i;
    if (lastSeen[0] != -1 && lastSeen[1] != -1 && lastSeen[2] != -1)
        count += 1 + Math.min(Math.min(lastSeen[0], lastSeen[1]), lastSeen[2]);
}
return count;
```

### **Complexity**

* **Time:** O(N) — single pass through string.
* **Space:** O(1) — fixed-size array of length 3.

---

## **Tips & Tricks**

1. **Brute → Optimal Pattern**

   * Many substring problems can be reduced using **sliding window** or **last seen positions**.
2. **Counting Substrings**

   * If a substring ending at index `r` is valid, **all substrings ending at `r` and starting before the leftmost valid index** are also valid.
3. **Sliding Window vs Last Seen**

   * Sliding window → good for generic multiple characters.
   * Last seen → works when character set is small and fixed (`a`, `b`, `c`).

---

✅ **Key Takeaway:**
When the number of unique elements is small (like `'a'`, `'b'`, `'c'`), **tracking last seen indices is often the fastest way to count valid substrings**.

---
