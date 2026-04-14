# 🔥 493. Reverse Pairs — Notes

## 🧠 Problem

Count pairs `(i, j)` such that:

```
i < j AND nums[i] > 2 * nums[j]
```

---

# 🥉 1. Brute Force

### 💡 Idea

Check every pair.

### ⏱ Complexity

* Time: **O(n²)**
* Space: **O(1)**

### ✅ Code

```java
public int reversePairs(int[] nums) {
    int count = 0;
    int n = nums.length;

    for (int i = 0; i < n; i++) {
        for (int j = i + 1; j < n; j++) {
            if ((long) nums[i] > 2L * nums[j]) {
                count++;
            }
        }
    }
    return count;
}
```

### ⚠️ Key Point

* Always use `long` → avoids overflow when multiplying by 2.

---

# 🥈 2. Better (Using Sorting + Binary Search)

### 💡 Idea

For each element:

* Look at elements to the **right**
* Count how many satisfy condition using binary search

### 🚨 Problem

This approach is tricky because:

* You need sorted structure on the fly
* Leads to **O(n log n)** per insertion if not careful

### ⏱ Complexity

* Time: **O(n log n + n log n) ≈ O(n log n)**
* Space: depends (TreeMap / List)

### 🧠 Intuition

* Maintain sorted list of elements seen so far (from right to left)
* For each `nums[i]`, find count of values `< nums[i]/2`

👉 This is rarely preferred in interviews due to complexity of implementation.

---

# 🥇 3. Optimal (Merge Sort Based)

### 💡 Core Insight

While merging two sorted halves:

* Left half: `low → mid`
* Right half: `mid+1 → high`

You can efficiently count valid pairs.

---

## ⚙️ Algorithm Flow

1. Divide array (merge sort)
2. Before merging:

   * Count reverse pairs
3. Merge normally

---

## 🔑 Counting Logic

For each `i` in left:

```
Find first index j in right such that:
nums[i] <= 2 * nums[j]
```

All elements before that `j` are valid.

---

## ⏱ Complexity

* Time: **O(n log n)**
* Space: **O(n)** (merge array)

---

## ✅ Clean Code (Fixing Your Version)

Your mistake:
👉 You tried counting again inside `merge()` — **WRONG**

All counting must happen in `countPairs()`.

---

### ✔️ Correct Code

```java
class Solution {
    public int reversePairs(int[] nums) {
        return mergeSort(nums, 0, nums.length - 1);
    }

    int mergeSort(int[] a, int low, int high) {
        if (low >= high) return 0;

        int mid = (low + high) / 2;
        int count = 0;

        count += mergeSort(a, low, mid);
        count += mergeSort(a, mid + 1, high);
        count += countPairs(a, low, mid, high);

        merge(a, low, mid, high);

        return count;
    }

    int countPairs(int[] a, int low, int mid, int high) {
        int right = mid + 1;
        int count = 0;

        for (int i = low; i <= mid; i++) {
            while (right <= high && (long) a[i] > 2L * a[right]) {
                right++;
            }
            count += right - (mid + 1);
        }
        return count;
    }

    void merge(int[] a, int low, int mid, int high) {
        int left = low, right = mid + 1;
        List<Integer> temp = new ArrayList<>();

        while (left <= mid && right <= high) {
            if (a[left] <= a[right]) {
                temp.add(a[left++]);
            } else {
                temp.add(a[right++]);
            }
        }

        while (left <= mid) temp.add(a[left++]);
        while (right <= high) temp.add(a[right++]);

        for (int i = low; i <= high; i++) {
            a[i] = temp.get(i - low);
        }
    }
}
```

---

# 🧠 How to Remember in Interview (Important)

When stuck, think:

> “This looks like inversion count… but condition is modified”

Then recall:

* Inversion → `nums[i] > nums[j]`
* This → `nums[i] > 2 * nums[j]`

👉 Same merge sort trick, just tweak comparison.
