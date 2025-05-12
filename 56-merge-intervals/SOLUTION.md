# 🧠 Problem: **Merge Intervals**

**Link**: [LeetCode 56 - Merge Intervals](https://leetcode.com/problems/merge-intervals/)
**Given**: A collection of `intervals`, where each interval is `[start, end]`
**Goal**: Merge all overlapping intervals and return the merged list.

---

## ✅ Constraints

* `1 <= intervals.length <= 10⁴`
* `intervals[i].length == 2`
* `0 <= intervals[i][0] <= intervals[i][1] <= 10⁴`

---

## 🔍 Observation

* Intervals overlap **if** the start of the current interval is ≤ end of the previous one.
* Sorting the intervals by their start time helps process them sequentially.

---

## 1. 💥 Brute Force Approach

### 🔧 Idea:

* Sort intervals by start time.
* For each interval:

  * Skip if already merged.
  * Find all intervals that overlap with it.
  * Merge all overlapping intervals into one.
* Add the merged interval to the result.

### 💻 Pseudocode:

```java
sort intervals by start time
for each interval i:
    skip if already part of a previous merge
    set start = intervals[i][0], end = intervals[i][1]
    for j from i+1 to n:
        if intervals[j][0] <= end:
            end = max(end, intervals[j][1])
        else break
    add [start, end] to result
```

### ⏱ Time: `O(n log n + 2n)` = `O(n log n)` (dominant term: sorting)

### 📦 Space: `O(n)` for result

---

## 2. 🚀 Optimal Approach (Greedy + Sorting)

### 🔧 Idea:

* Sort intervals by start time
* Initialize an answer list with the first interval
* For every interval:

  * If it **does not overlap** with the last interval in result, add it
  * If it **does overlap**, merge it with the last interval in result by updating the end time

### 💻 Code:

```java
Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
for (int[] interval : intervals) {
    if (answer.isEmpty() || interval[0] > answer.get(answer.size() - 1)[1])
        answer.add(interval);
    else
        answer.get(answer.size() - 1)[1] = Math.max(answer.get(answer.size() - 1)[1], interval[1]);
}
```

### 🧠 Why this works:

* Intervals are sorted, so overlapping ones are adjacent
* Merge decision is just based on comparing with the last merged interval

### ⏱ Time: `O(n log n)` (due to sorting)

### 📦 Space: `O(n)` (for result list)

---

## 📌 Summary Table

| Approach         | Time Complexity | Space Complexity | Sorting Required | Duplicates? | Notes                               |
| ---------------- | --------------- | ---------------- | ---------------- | ----------- | ----------------------------------- |
| Brute Force      | O(n log n + 2n) | O(n)             | ✅                | ❌           | Merges manually, extra inner loop   |
| Optimal (Greedy) | O(n log n)      | O(n)             | ✅                | ❌           | Efficient, scalable, clean solution |

---
![image](https://github.com/user-attachments/assets/70fa6458-4168-446d-97e8-f0b5e8edf391)
---
## 🔚 Final Output

Return the list of merged intervals as a 2D array.

---
