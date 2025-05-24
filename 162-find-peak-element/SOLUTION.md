### ✅ Problem: **162. Find Peak Element**

You're given an array `nums`, where a peak element is defined as an element that is **strictly greater than its neighbors**. Find any **one** peak element in **O(log n)** time.

---

## ✅ Problem Summary:

> Given an integer array `nums`, find a **peak element** and return its **index**.
> A **peak element** is an element that is **strictly greater** than its neighbors.
> You may assume that `nums[i] ≠ nums[i + 1]` for all valid `i`.
> **You must write an algorithm that runs in O(log n) time.**

---

## 💡 Approaches:

### 1. 🐢 Brute Force — **Linear Scan**

* **Idea**: Loop through the array, and check each element if it's greater than both its neighbors.
* **Time Complexity**: `O(n)`
* **Space Complexity**: `O(1)`

```java
public int findPeakElement(int[] nums) {
    int n = nums.length;
    for (int i = 0; i < n; i++) {
        boolean leftOK = (i == 0 || nums[i] > nums[i - 1]);
        boolean rightOK = (i == n - 1 || nums[i] > nums[i + 1]);
        if (leftOK && rightOK)
            return i;
    }
    return -1;
}
```

---


### 2. Optimal: **Binary Search Based on Slope**

---

### 🔍 Key Observations:

* A **peak** element is one where: `nums[i] > nums[i-1] && nums[i] > nums[i+1]`.
* Binary search can be applied by analyzing the **slope**:

  * If the mid element is **less than right neighbor**, the **peak lies right**.
  * If the mid element is **less than left neighbor**, the **peak lies left**.
  * If it is **greater than both**, it is a **peak**.

---

### 🧠 Algorithm:

```java
public int findPeakElement(int[] a) {
    int n = a.length;
    
    // Handle small and boundary arrays
    if (n == 1) return 0;
    if (a[0] > a[1]) return 0;
    if (a[n - 1] > a[n - 2]) return n - 1;

    int low = 1, high = n - 2;
    
    while (low <= high) {
        int mid = (low + high) / 2;
        
        // Found a peak
        if (a[mid] > a[mid - 1] && a[mid] > a[mid + 1])
            return mid;
        
        // Peak lies to the right
        else if (a[mid] < a[mid + 1])
            low = mid + 1;
        
        // Peak lies to the left
        else
            high = mid - 1;
    }
    
    return -1; // Should never hit due to constraints
}
```

---

### ⚡ Time & Space Complexity:

| Metric           | Value         |
| ---------------- | ------------- |
| Time Complexity  | `O(log n)`    |
| Space Complexity | `O(1)`        |
| Pattern          | Binary Search |

---

### ✅ Edge Case Handling:

| Case                 | Output     |
| -------------------- | ---------- |
| `[1]`                | `0`        |
| `[3, 2, 1]`          | `0`        |
| `[1, 2, 3]`          | `2`        |
| `[1, 3, 2, 1]`       | `1`        |
| `[2, 1, 3, 5, 6, 4]` | `4` or `0` |

---

### ✨ Insight:

Binary search is valid here because **at least one peak must exist** due to the nature of element comparisons — either a peak is at the edges or one exists between increasing and decreasing patterns.

---

### ✅ Summary Table:

| Approach         | Time       | Space  | Notes                          |
| ---------------- | ---------- | ------ | ------------------------------ |
| Brute Force      | `O(n)`     | `O(1)` | Simple scan                    |
| Optimal (Binary) | `O(log n)` | `O(1)` | Uses slope-based binary search |

---
