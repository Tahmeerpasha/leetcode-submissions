# 📘 Problem:

Leetcode **1004. Max Consecutive Ones III**
Given a binary array `nums` and integer `k`, flip at most `k` zeros to maximize consecutive 1s.

---

## 1. Brute Force (Check All Subarrays)

👉 Try every possible subarray and count zeros.
👉 If zeros ≤ k, update max length.

```java
class Solution {
    public int longestOnes(int[] nums, int k) {
        int n = nums.length, maxLen = 0;
        for (int i = 0; i < n; i++) {
            int zeroCount = 0;
            for (int j = i; j < n; j++) {
                if (nums[j] == 0) zeroCount++;
                if (zeroCount > k) break;
                maxLen = Math.max(maxLen, j - i + 1);
            }
        }
        return maxLen;
    }
}
```

**Complexity:**

* Time: **O(n²)**
* Space: **O(1)**

---

## 2. Better (Sliding Window with `while` loop → O(2N))

👉 Use two pointers `l` and `r`.
👉 Move `r` and count zeros.
👉 If `zeroCount > k`, shrink from left (`l++`) in a `while` loop until valid.
👉 Each element enters/exits window at most once → **2N moves**.

```java
class Solution {
    public int longestOnes(int[] nums, int k) {
        int l = 0, r = 0, zeroCount = 0, maxLen = 0;
        while (r < nums.length) {
            if (nums[r] == 0) zeroCount++;
            while (zeroCount > k) {   // keep shrinking until valid
                if (nums[l] == 0) zeroCount--;
                l++;
            }
            maxLen = Math.max(maxLen, r - l + 1);
            r++;
        }
        return maxLen;
    }
}
```

**Complexity:**

* Time: **O(2N)** (each index handled twice: once by `r`, once by `l`)
* Space: **O(1)**

---

## 3. Optimal (Sliding Window with `if` instead of `while` → O(N))

👉 Instead of looping multiple times to shrink, just adjust `l` **once per step** when window becomes invalid.
👉 This ensures total operations remain linear.

```java
class Solution {
    public int longestOnes(int[] nums, int k) {
        int l = 0, r = 0, zeroCount = 0, max = 0;
        while (r < nums.length) {
            if (nums[r] == 0) zeroCount++;
            if (zeroCount > k) {
                if (nums[l] == 0) zeroCount--;
                l++;
            }
            max = Math.max(max, r - l + 1);
            r++;
        }
        return max;
    }
}
```

**Complexity:**

* Time: **O(N)**
* Space: **O(1)**

---

# ✨ Tips & Tricks

1. **When you see “longest subarray with at most k violations” → think sliding window.**
2. **At most K vs Exactly K:**

   * “At most K” → sliding window
   * “Exactly K” → reduce to `atMost(K) - atMost(K-1)`
3. Each element in sliding window problems is processed at most twice → O(2N).
4. Interview strategy:

   * First explain brute force.
   * Then sliding window with while (better).
   * Then your O(N) optimized version.

---

### 🔑 Quick Comparison Table

| Approach | Idea                                                | Time  | Space |
| -------- | --------------------------------------------------- | ----- | ----- |
| Brute    | Check all subarrays                                 | O(N²) | O(1)  |
| Better   | Sliding window with while loop (shrink until valid) | O(2N) | O(1)  |
| Optimal  | Sliding window with single shift                    | O(N)  | O(1)  |

---
