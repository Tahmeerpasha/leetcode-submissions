## **Problem Recap**

> Count the number of subarrays that contain **exactly `k` odd numbers**.

**Input:** `nums = [1,1,2,1,1], k = 3`
**Output:** `2` (subarrays `[1,1,2,1]` and `[1,2,1,1]`)

---

## **1️⃣ Brute Force**

**Idea:**
Check all possible subarrays and count the number of odd numbers. Increment result if it equals `k`.

```java
class Solution {
    public int numberOfSubarrays(int[] nums, int k) {
        int n = nums.length;
        int count = 0;

        for (int i = 0; i < n; i++) {
            int oddCount = 0;
            for (int j = i; j < n; j++) {
                if (nums[j] % 2 == 1) oddCount++;
                if (oddCount == k) count++;
                if (oddCount > k) break;
            }
        }

        return count;
    }
}
```

**Complexity:**

* **Time:** $O(N^2)$ (nested loops)
* **Space:** $O(1)$

**Tip:** Works for small arrays but will **TLE** for large arrays (`N ~ 10^5`).

---

## **2️⃣ Better Approach — Using Odd Indexes**

**Idea:**
Store the indexes of odd numbers. Count how many subarrays can be formed using the difference of indexes.

```java
class Solution {
    public int numberOfSubarrays(int[] nums, int k) {
        List<Integer> oddIndexes = new ArrayList<>();
        oddIndexes.add(-1); // boundary
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 == 1) oddIndexes.add(i);
        }
        oddIndexes.add(nums.length); // boundary

        int count = 0;
        for (int i = 1; i + k < oddIndexes.size(); i++) {
            int left = oddIndexes.get(i) - oddIndexes.get(i - 1);
            int right = oddIndexes.get(i + k) - oddIndexes.get(i + k - 1);
            count += left * right;
        }

        return count;
    }
}
```

**Complexity:**

* **Time:** $O(N)$
* **Space:** $O(N)$ (to store odd indexes)

**Tip:** This is intuitive once you realize a subarray is determined by the **first and last odd numbers** and the number of evens around them.

---

## **3️⃣ Optimal Approach — Sliding Window / At Most `k`**

**Idea:**
Use the trick:

> `exactly k` = `atMost(k)` − `atMost(k-1)`

Use a **sliding window** to calculate the number of subarrays with at most `k` odd numbers.

```java
class Solution {
    public int numberOfSubarrays(int[] nums, int k) {
        return atMost(nums, k) - atMost(nums, k - 1);
    }

    int atMost(int[] nums, int goal) {
        if (goal < 0) return 0;
        int l = 0, count = 0, sum = 0;

        for (int r = 0; r < nums.length; r++) {
            sum += nums[r] % 2;

            while (sum > goal) {
                sum -= nums[l] % 2;
                l++;
            }

            count += r - l + 1; // number of valid subarrays ending at r
        }

        return count;
    }
}
```

**Complexity:**

* **Time:** $O(N)$ (single pass, each element added/removed once)
* **Space:** $O(1)$

**Tip & Tricks:**

1. Converting `nums[i] % 2` simplifies even/odd checks.
2. Sliding window can often be used for “subarrays with at most X property”.
3. When asked **exactly k**, think:
   `exactly k = atMost(k) - atMost(k-1)`
4. Counting subarrays ending at `r` in a window: `r - l + 1`.

---

✅ **Summary Table**

| Approach       | Time Complexity | Space Complexity | Notes                                 |
| -------------- | --------------- | ---------------- | ------------------------------------- |
| Brute Force    | O(N²)           | O(1)             | TLE for large N                       |
| Odd Indexes    | O(N)            | O(N)             | More intuitive, uses index arithmetic |
| Sliding Window | O(N)            | O(1)             | Optimal, uses atMost trick            |

---
