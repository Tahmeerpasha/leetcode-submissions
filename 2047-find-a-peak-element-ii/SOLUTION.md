## 🔍 Problem Summary

You are given a 2D matrix where every element has at most 4 neighbors. A **peak** element is one that is **strictly greater than all its adjacent elements** (top, bottom, left, right). Return the coordinates of any one peak.

---

## 🧱 Brute Force Approach

### 🔧 Logic:

Check **every element** in the matrix and compare it with its **up/down/left/right** neighbors to check if it's a peak.

### 💻 Code:

```java
public int[] findPeakGrid(int[][] mat) {
    int rows = mat.length, cols = mat[0].length;
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            int up = (i > 0) ? mat[i-1][j] : -1;
            int down = (i < rows-1) ? mat[i+1][j] : -1;
            int left = (j > 0) ? mat[i][j-1] : -1;
            int right = (j < cols-1) ? mat[i][j+1] : -1;

            if (mat[i][j] > up && mat[i][j] > down && mat[i][j] > left && mat[i][j] > right)
                return new int[] {i, j};
        }
    }
    return new int[] {-1, -1};
}
```

### ⏱ Time: `O(m * n)`

### 📦 Space: `O(1)`

---

## 🔁 Better Approach – Row-wise Peak Search

> Not widely used in this problem due to unstructured peak layout, so we jump to Optimal.

---

## 🚀 Optimal Approach – Binary Search on Columns

### 🔧 Logic:

Use **binary search on columns**:

1. Pick a **middle column**.
2. Find the **maximum element** in that column.
3. Compare it with its left and right neighbors:

   * If it is greater than both → it's a peak.
   * If the left is greater → go left half.
   * If the right is greater → go right half.

This works due to the **guaranteed existence** of a peak.

### 💻 Code:

```java
public int[] findPeakGrid(int[][] mat) {
    int row = mat.length;
    int col = mat[0].length;
    int low = 0, high = col - 1;

    while (low <= high) {
        int mid = (low + high) / 2;
        int maxRow = findMaxInColumn(mat, mid);

        int left = (mid > 0) ? mat[maxRow][mid - 1] : -1;
        int right = (mid < col - 1) ? mat[maxRow][mid + 1] : -1;

        if (mat[maxRow][mid] > left && mat[maxRow][mid] > right)
            return new int[] { maxRow, mid };
        else if (left > mat[maxRow][mid])
            high = mid - 1;
        else
            low = mid + 1;
    }

    return new int[] { -1, -1 };
}

private int findMaxInColumn(int[][] mat, int col) {
    int maxVal = -1, rowIdx = -1;
    for (int i = 0; i < mat.length; i++) {
        if (mat[i][col] > maxVal) {
            maxVal = mat[i][col];
            rowIdx = i;
        }
    }
    return rowIdx;
}
```

### ⏱ Time: `O(m * log n)`

### 📦 Space: `O(1)`

---

## 📊 Summary Table

| Approach      | Time Complexity | Space  | Notes                           |
| ------------- | --------------- | ------ | ------------------------------- |
| Brute         | `O(m * n)`      | `O(1)` | Checks all neighbors explicitly |
| Better        | `-`             | `-`    | Not commonly used here          |
| **Optimal** ✅ | `O(m * log n)`  | `O(1)` | Binary search on columns        |

---
