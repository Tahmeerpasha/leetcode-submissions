## 🔢 Problem Summary

You're given a list of **non-overlapping intervals** sorted by start time, and a **new interval** that you must insert into the list.
After insertion, **merge all overlapping intervals** and return the resulting list.

### Example:

```java
Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
Output: [[1,5],[6,9]]
```

---

## 🧠 Intuition

We’re basically **merging a new interval** into a sorted list of **non-overlapping intervals**.
The three possible regions:

1. **Left part:** intervals ending before newInterval starts
2. **Merge part:** overlapping intervals with newInterval
3. **Right part:** intervals starting after newInterval ends

---

# 🪜 Approaches

---

## 1️⃣ **Brute Force Approach**

### **Idea:**

* Insert `newInterval` into the `intervals` array.
* Sort all intervals by their start time.
* Merge overlapping intervals (like in “Merge Intervals” problem).

### **Steps:**

1. Create a new list including all intervals + `newInterval`.
2. Sort by start time.
3. Merge overlapping intervals:

   * If `current.start <= prev.end` → merge.
   * Else, push current interval to result.

### **Code:**

```java
class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> list = new ArrayList<>(Arrays.asList(intervals));
        list.add(newInterval);
        list.sort((a, b) -> a[0] - b[0]);
        
        List<int[]> merged = new ArrayList<>();
        for (int[] interval : list) {
            if (merged.isEmpty() || merged.get(merged.size() - 1)[1] < interval[0]) {
                merged.add(interval);
            } else {
                merged.get(merged.size() - 1)[1] =
                    Math.max(merged.get(merged.size() - 1)[1], interval[1]);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }
}
```

### **Complexity:**

* **Time:** O(N log N) (due to sorting)
* **Space:** O(N)

✅ **Works fine but sorting is unnecessary** since intervals are already sorted.

---

## 2️⃣ **Better Approach (Direct merge simulation)**

### **Idea:**

* You can avoid sorting because the input is already sorted.
* Just simulate how you’d “insert” in three stages:

  1. Add all intervals before the new one.
  2. Merge overlapping intervals.
  3. Add all intervals after.

### **Code:**

```java
class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> res = new ArrayList<>();
        int i = 0, n = intervals.length;

        // 1️⃣ Add all intervals ending before newInterval starts
        while (i < n && intervals[i][1] < newInterval[0]) {
            res.add(intervals[i++]);
        }

        // 2️⃣ Merge overlapping intervals
        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        res.add(newInterval);

        // 3️⃣ Add remaining intervals
        while (i < n) {
            res.add(intervals[i++]);
        }

        return res.toArray(new int[res.size()][]);
    }
}
```

### **Complexity:**

* **Time:** O(N)
* **Space:** O(N)

✅ Already optimal for this problem in both logic and complexity.

---

## 3️⃣ **Optimal Approach (In-place / minimal space)**

You can slightly optimize space (constant) if allowed to modify the array in place.
But in Java, since arrays are fixed-size, we usually go with the **Better (O(N))** version above.

So in practical terms:

> The “Better” approach **is the Optimal one** for this problem.

---

# 💡 Interview Tips & Tricks

✅ **Pattern Recognition:**
This problem is a variation of the **“Merge Intervals”** pattern.
If you recognize that pattern early, you immediately know:

* Sort (if not sorted)
* Merge overlapping intervals linearly

✅ **Edge Cases to Discuss:**

* Empty input: `intervals = []`
* New interval before all existing ones: `[0,1]` with `[[3,5]]`
* New interval after all existing ones
* Complete overlap: `[[1,5]]` with `[2,3]`
* Multiple overlaps

✅ **Don’t Overcomplicate:**
If input is sorted, **do not sort again** — this is a common mistake in interviews.

✅ **Communication in interviews:**
Explain that the merging logic works because:

* Intervals are non-overlapping and sorted.
* So, linear traversal ensures correctness.

✅ **Follow-up Variations:**

1. Merge two lists of intervals (calendar meeting type)
2. Insert multiple intervals (batch insert)
3. Count merged interval lengths (union length)

---

# 🧩 Summary Table

| Approach | Sorting Needed | Time       | Space | Key Idea                          |
| -------- | -------------- | ---------- | ----- | --------------------------------- |
| Brute    | ✅ Yes          | O(N log N) | O(N)  | Merge after sorting all intervals |
| Better   | ❌ No           | O(N)       | O(N)  | Linearly merge in 3 stages        |
| Optimal  | ❌ No           | O(N)       | O(1)* | Same logic, in-place if possible  |

*(In Java, array resizing prevents true O(1) space, so O(N) effectively.)*

---
