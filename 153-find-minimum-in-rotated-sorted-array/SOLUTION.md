## 📝 Problem: **153. Find Minimum in Rotated Sorted Array**

### 🔍 Given:

* A rotated sorted array `a` (distinct integers, no duplicates)
* Initially sorted in ascending order and rotated at some pivot

### 🎯 Goal:

Find and return the **minimum** element.

---

## ✅ Approach: Modified Binary Search

### 💡 Intuition:

In a rotated sorted array without duplicates, one part is always sorted.
The **minimum** is the only point where the **next element is smaller**, or it’s the smallest among the sorted halves.

---

## 🔁 Steps:

1. **Initialize Pointers**: `low = 0`, `high = a.length - 1`, `min = ∞`

2. **Binary Search Loop**:

   * Compute `mid = (low + high) / 2`
   * If `a[low] <= a[mid]`, the **left half is sorted**:

     * The smallest must be in the left part or after `mid`
     * Update `min = min(min, a[low])`
     * Move to right half: `low = mid + 1`
   * Else, the **right half is sorted**:

     * Update `min = min(min, a[mid])`
     * Move to left half: `high = mid - 1`

3. **Return** the minimum value

---

## ✅ Time & Space Complexity:

| Type      | Complexity |
| --------- | ---------- |
| **Time**  | `O(log n)` |
| **Space** | `O(1)`     |

---

## 🔍 Example:

```java
a = [4,5,6,7,0,1,2]
low = 0, high = 6
mid = 3 → a[3]=7 → left sorted → min = 4
move right: low = 4
mid = 5 → a[5]=1 → right sorted → min = 0
```

---

## ⚠️ Edge Cases:

| Case                      | Result |
| ------------------------- | ------ |
| `[1,2,3,4,5]`             | `1`    |
| `[2,1]`                   | `1`    |
| `[1]`                     | `1`    |
| `[3,4,5,1,2]`             | `1`    |
| Already sorted input      | Works  |
| Minimum at index 0 or n-1 | Works  |

---

## ✅ Summary

| Feature       | Detail                                     |
| ------------- | ------------------------------------------ |
| Technique     | Binary Search                              |
| Sorted Halves | One half is always sorted                  |
| Optimization  | Compare and shrink based on sorted portion |
| Time          | `O(log n)`                                 |

---
