## ✅ Problem Summary:

Given an `m x n` matrix where:

* Each row is sorted in ascending order.
* The **first element of each row is greater than the last element of the previous row**.

> You must search for a given `target` value in the matrix with **O(log(m \* n))** time complexity.

---

## 💡 Key Insight:

The matrix behaves like a **flattened sorted 1D array**, so we can perform a **binary search** directly on it using index mapping:

* 1D index → 2D position:

  * `row = index / col`
  * `col = index % col`

---

## 🔍 Approaches:

### 1. 🚫 Brute Force (TLE)

* Iterate through each cell: `O(m * n)` time

---

### 2. ⚡ Better – Row-wise Binary Search

* For each row, check if the target lies within its bounds.
* If yes, perform binary search on that row.
* **Time**: `O(m + log n)` in the worst case
* **Still not optimal for large matrices**

---

### 3. ✅ Optimal – Binary Search Over Virtual 1D Array

#### ✅ Logic:

* Treat the matrix as a **sorted 1D array** of length `m * n`.
* Perform binary search over `[0, m * n - 1]`.
* Convert mid index to `(row, col)` as:
  `row = mid / n`, `col = mid % n`.

#### ⏱ Time:

* **Time**: `O(log(m * n))`
* **Space**: `O(1)`

---

## ✅ Code Explanation:

```java
public boolean searchMatrix(int[][] matrix, int target) {
    int row = matrix.length;
    int col = matrix[0].length;
    int low = 0, high = row * col - 1;

    while (low <= high) {
        int mid = (low + high) / 2;
        int r = mid / col;
        int c = mid % col;

        if (matrix[r][c] == target)
            return true;
        else if (matrix[r][c] > target)
            high = mid - 1;
        else
            low = mid + 1;
    }
    return false;
}
```

---

### Optional Helper – For Better Approach

```java
public boolean binarySearch(int[] a, int x) {
    int low = 0, high = a.length - 1;
    while (low <= high) {
        int mid = (low + high) / 2;
        if (a[mid] == x)
            return true;
        else if (a[mid] < x)
            low = mid + 1;
        else
            high = mid - 1;
    }
    return false;
}
```

---

## 📝 Summary Table:

| Approach                | Time Complexity | Space  | Notes                             |
| ----------------------- | --------------- | ------ | --------------------------------- |
| Brute Force             | `O(m * n)`      | `O(1)` | Linear scan                       |
| Row Binary Search       | `O(m + log n)`  | `O(1)` | Fast row identification           |
| Binary Search on Matrix | `O(log(m * n))` | `O(1)` | Best, optimal, uses index mapping |

---
