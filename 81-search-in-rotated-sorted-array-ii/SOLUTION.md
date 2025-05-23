## 📝 Problem: **81. Search in Rotated Sorted Array II**

### 🔍 Given:

* A rotated sorted array `a` (can contain duplicates)
* An integer `x` (target)

### 🎯 Goal:

Return `true` if `x` exists in `a`, else return `false`.

---

## ✅ Approach: Modified Binary Search (Handling Duplicates)

### 🔧 Intuition:

Duplicates can obscure the sorted half of the array, so we may need to shrink the search space.

---

## 🔁 Steps:

1. **Initialize Pointers**: `low = 0`, `high = a.length - 1`

2. **Binary Search Loop**:

   * Compute `mid = (low + high) / 2`
   * If `a[mid] == x`, return `true`

3. **Handle Duplicates**:

   * If `a[low] == a[mid] && a[mid] == a[high]`, **can't decide** the sorted half, so:

     * `low++`
     * `high--`

4. **Determine Sorted Side**:

   * If `a[low] <= a[mid]` → **left half is sorted**

     * If `x` lies in `[a[low], a[mid]]`, go left (`high = mid - 1`)
     * Else, go right (`low = mid + 1`)
   * Else → **right half is sorted**

     * If `x` lies in `[a[mid], a[high]]`, go right (`low = mid + 1`)
     * Else, go left (`high = mid - 1`)

---

### ✅ Time Complexity:

* **Best/Average**: `O(log n)` (similar to binary search)
* **Worst Case (All Duplicates)**: `O(n)` — happens when duplicates force `low++`, `high--` repeatedly

### ✅ Space Complexity:

* `O(1)` – no extra space used

---

## 🧪 Example Trace:

```java
a = [2,5,6,0,0,1,2], x = 0

low = 0, high = 6
mid = 3 → a[3] = 0 → found!
```

---

## ⚠️ Edge Cases:

| Case                          | Output  |
| ----------------------------- | ------- |
| `[]`, x = any                 | `false` |
| `[1,1,1,1,1]`, x = 1          | `true`  |
| `[1,1,1,3,1]`, x = 3          | `true`  |
| `[1,1,1,3,1]`, x = 0          | `false` |
| No rotation, no duplicates    | Works   |
| Full rotation with duplicates | Handled |

---

## ✅ Summary

| Feature          | Detail                              |
| ---------------- | ----------------------------------- |
| Strategy         | Binary Search with Duplicate Check  |
| Time Complexity  | `O(log n)` on average, `O(n)` worst |
| Space Complexity | `O(1)`                              |
| Special Case     | Shrink range on duplicate ambiguity |

---
