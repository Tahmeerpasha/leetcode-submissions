## 📝 Problem: **33. Search in Rotated Sorted Array**

### 🔍 Given:

* A rotated sorted array `a` (originally increasing, rotated at an unknown pivot)
* An integer `x` (target)

### 🎯 Goal:

Find the index of `x` in `a`. If not found, return `-1`.

---

## ✅ Approach: Modified Binary Search

### 🔧 Intuition:

In a rotated sorted array, at least **one half is always sorted**. Use this to decide whether to go left or right in the binary search.

### 🔁 Steps:

1. **Binary Search Loop:**

   * Compute `mid = (low + high) / 2`
   * If `a[mid] == x`, return `mid`

2. **Left half is sorted** if `a[low] <= a[mid]`

   * If `x` lies between `a[low]` and `a[mid]`, narrow search to the left half (`high = mid - 1`)
   * Otherwise, go to the right half (`low = mid + 1`)

3. **Right half is sorted** if `a[mid] < a[low]`

   * If `x` lies between `a[mid]` and `a[high]`, search in right half (`low = mid + 1`)
   * Otherwise, search in left half (`high = mid - 1`)

---

### ✅ Time Complexity: `O(log n)`

* Each iteration reduces the search space by half.
* Efficient even in rotated arrays.

### ✅ Space Complexity: `O(1)`

* No extra memory used.

---

### 🔁 Example Trace:

```java
a = [4,5,6,7,0,1,2], x = 0

low = 0, high = 6
mid = 3 → a[3] = 7

Left half sorted (a[0] = 4 <= a[3] = 7)
→ x = 0 is not in [4, 7] → search right

low = 4, high = 6
mid = 5 → a[5] = 1

Right half sorted (a[5] = 1 <= a[6] = 2)
→ x = 0 is not in [1, 2] → search left

low = 4, high = 4
mid = 4 → a[4] = 0 → found!
```

---

### ⚠️ Edge Cases:

| Case                                      | Output     |
| ----------------------------------------- | ---------- |
| `[]` (empty array)                        | `-1`       |
| `[1]`, target not present                 | `-1`       |
| No rotation (`[1,2,3,4]`)                 | Works fine |
| Fully rotated (`[1,2,3,4]` → `[4,1,2,3]`) | Works fine |

---

## ✅ Summary

| Feature          | Detail                             |
| ---------------- | ---------------------------------- |
| Strategy         | Modified Binary Search             |
| Time Complexity  | `O(log n)`                         |
| Space Complexity | `O(1)`                             |
| Key Insight      | At least one half is always sorted |

---
