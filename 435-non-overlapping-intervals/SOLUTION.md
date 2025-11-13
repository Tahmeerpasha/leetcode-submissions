## 🔍 Problem Summary

You’re given an array of intervals `intervals[i] = [start_i, end_i]`.
You need to **remove the minimum number of intervals** so that the rest are **non-overlapping**.

Return **the minimum number of intervals to remove**.

---

## 🧠 Intuition

We want the **maximum number of non-overlapping intervals** — because removing the minimum number means keeping as many as possible.
This transforms the problem into a **classic activity selection / interval scheduling problem**.

---

## 🧩 Example

```
Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
Output: 1
Explanation: Remove [1,3] to make the rest non-overlapping.
```

---

## 🚀 Brute Force Approach

### Idea

Try **all subsets** of intervals and check which is the largest set of non-overlapping intervals.

### Steps

1. Generate all subsets of intervals.
2. For each subset, check if it’s non-overlapping.
3. Track the largest subset size.
4. Answer = total intervals − size of largest non-overlapping subset.

### Code Sketch

```java
// O(2^n * n) approach
// For each subset, check all pairs for overlap
```

### ⏱ Complexity

* **Time:** O(2^n * n)
* **Space:** O(n)

### ⚠️ Note

Exponential → impractical for n > 20.
Good only for **understanding the problem structure**.

---

## 💡 Better (Dynamic Programming) Approach

### Idea

This is similar to **Longest Non-overlapping Subsequence**:

1. Sort by start time.
2. Use DP to find max number of non-overlapping intervals.

### Steps

1. Sort by `start`.
2. For each interval `i`, check all previous `j < i`.
3. If `intervals[j][1] <= intervals[i][0]`, then `dp[i] = max(dp[i], dp[j] + 1)`.
4. Answer = n - max(dp[i]).

### Code Sketch

```java
Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
int n = intervals.length;
int[] dp = new int[n];
Arrays.fill(dp, 1);
int max = 1;

for (int i = 1; i < n; i++) {
    for (int j = 0; j < i; j++) {
        if (intervals[j][1] <= intervals[i][0]) {
            dp[i] = Math.max(dp[i], dp[j] + 1);
        }
    }
    max = Math.max(max, dp[i]);
}
return n - max;
```

### ⏱ Complexity

* **Time:** O(n²)
* **Space:** O(n)

### ⚙️ When to use

If interviewer asks for a **DP version** or you need to show **transition logic**.

---

## 🔥 Optimal Greedy Approach (used in your code)

### Idea

Classic **Activity Selection Problem** logic:

* Always pick the interval that **ends earliest**, because it leaves room for future intervals.

### Steps

1. Sort intervals by their **end time**.
2. Initialize `previousEnd = first interval’s end`.
3. Iterate through intervals:

   * If current start ≥ previousEnd → keep it, update `previousEnd`.
   * Else → overlap → remove.
4. Return totalRemoved = total - countKept.

### Code

✅ (Your version — correct and optimal)

```java
class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);
        int previousEnd = intervals[0][1], cnt = 1;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= previousEnd) {
                cnt++;
                previousEnd = intervals[i][1];
            }
        }
        return intervals.length - cnt;
    }
}
```

### ⏱ Complexity

* **Time:** O(n log n) — due to sorting
* **Space:** O(1)

### ✅ Example Dry Run

```
Intervals: [[1,2],[2,3],[3,4],[1,3]]
Sorted by end: [[1,2],[2,3],[1,3],[3,4]]
Pick [1,2], then [2,3], skip [1,3], pick [3,4].
Kept = 3 → remove = 4 - 3 = 1
```

---

## 🧩 Key Takeaways (for Interviews)

| Concept                           | Explanation                                                              |
| --------------------------------- | ------------------------------------------------------------------------ |
| **Core Problem Type**             | Greedy — Interval Scheduling                                             |
| **Sorting Basis**                 | By `end` time (not start)                                                |
| **Reason for Greedy correctness** | Choosing earliest end leaves max room for next intervals                 |
| **Edge Cases**                    | Single interval, overlapping boundaries, same end times                  |
| **Alternative View**              | Equivalent to finding *max set of non-overlapping intervals*             |
| **Common Variants**               | Activity Selection, Minimum Platforms, Merge Intervals, Meeting Rooms II |

---

## ⚔️ Interview Tips

1. **Always justify greedy:**
   Explain why picking earliest ending interval works — it ensures maximum possible future choices.

2. **Sort carefully:**
   Sorting by `start` leads to wrong answers — highlight that you must sort by **end time**.

3. **Dry run on small examples** to prove your understanding.

4. **Watch for inclusive vs exclusive overlaps**
   Some versions treat `[a,b]` and `[b,c]` as overlapping — clarify with interviewer.

5. **Be ready to write both DP and Greedy** — if they ask “Can we optimize further?”, show transition.

---

## 🧭 Summary Table

| Approach    | Idea                                | Time       | Space | Notes            |
| ----------- | ----------------------------------- | ---------- | ----- | ---------------- |
| Brute Force | Try all subsets                     | O(2^n)     | O(n)  | Concept only     |
| DP          | Longest non-overlapping subsequence | O(n²)      | O(n)  | Easier to reason |
| Greedy      | Sort by end, pick earliest end      | O(n log n) | O(1)  | Optimal ✅        |

---
