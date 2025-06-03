## ✅ Problem Summary:

You are given an `m x n` matrix where:

* Each **row is sorted in ascending order** (left to right),
* Each **column is sorted in ascending order** (top to bottom).

**Goal**: Determine whether a given `target` exists in the matrix.

---

## 🧠 Key Insight:

The matrix is **sorted row-wise and column-wise**, so at each position, you can **eliminate one row or column** in `O(1)` time.

---
### 🧱 **Brute Force Approach**

#### 🔍 Logic:

Scan every element in the matrix one by one and compare it to the target.

#### 💻 Code:

```java
public boolean searchMatrix(int[][] matrix, int target) {
    for (int i = 0; i < matrix.length; i++) {
        for (int j = 0; j < matrix[0].length; j++) {
            if (matrix[i][j] == target) return true;
        }
    }
    return false;
}
```

#### ⏱ Time: `O(m * n)`

#### 📦 Space: `O(1)`

#### 😩 Cons:

* Doesn’t use the sorted property
* Inefficient for large matrices

---

### 🔁 **Better Approach: Binary Search Row-wise**

#### 🔍 Logic:

Since each **row is sorted**, do a **binary search** in every row.

#### 💻 Code:

```java
public boolean searchMatrix(int[][] matrix, int target) {
    for (int i = 0; i < matrix.length; i++) {
        if (binarySearch(matrix[i], target)) return true;
    }
    return false;
}

private boolean binarySearch(int[] row, int target) {
    int low = 0, high = row.length - 1;
    while (low <= high) {
        int mid = (low + high) / 2;
        if (row[mid] == target) return true;
        else if (row[mid] < target) low++;
        else high--;
    }
    return false;
}
```

#### ⏱ Time: `O(m * log n)`

#### 📦 Space: `O(1)`

#### 😐 Cons:

* Uses only row-wise sorting
* Doesn’t leverage the column-wise sorting

---

### 🚀 **Optimal Approach: Top-Right Search**

#### 🔍 Logic:

Start from **top-right corner**:

* If value == target → return true
* If value > target → move left
* If value < target → move down

#### 💻 Code:

```java
public boolean searchMatrix(int[][] matrix, int target) {
    int row = 0, col = matrix[0].length - 1;
    while (row < matrix.length && col >= 0) {
        if (matrix[row][col] == target) return true;
        else if (matrix[row][col] < target) row++;
        else col--;
    }
    return false;
}
```

#### ⏱ Time: `O(m + n)`

#### 📦 Space: `O(1)`

#### ✅ Pros:

* Utilizes both **row and column sorting**
* Most efficient for large matrices

---

### 📊 Summary Table

| Approach      | Time Complexity | Space  | Logic Used             |
| ------------- | --------------- | ------ | ---------------------- |
| Brute         | `O(m * n)`      | `O(1)` | No sorting used        |
| Better        | `O(m * log n)`  | `O(1)` | Row-wise binary search |
| **Optimal** ✅ | `O(m + n)`      | `O(1)` | Row & column traversal |

---
