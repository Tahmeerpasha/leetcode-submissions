## ✅ Problem: Rotate Image (Leetcode 48)

### 🧠 Problem Statement:
Given an `n x n` 2D matrix representing an image, **rotate the image by 90 degrees (clockwise)** in-place.

---

## 🔍 Constraints:
- Modify the matrix **in-place** (i.e., without using another matrix for transformation).

---

## 💡 Approaches:

---

### ❌ Brute Force Approach

#### 🛠️ Idea:
- Create a new matrix.
- For every element at position `(i, j)`, place it in position `(j, n-i-1)` in the new matrix.

#### ✅ Steps:
1. Create a new `rotatedMatrix`.
2. Copy transformed elements to it.
3. Copy `rotatedMatrix` back to `matrix`.

#### ⏱️ Time: `O(n^2)`  
#### 📦 Space: `O(n^2)`

---

### ✅ Optimal Approach (In-Place)

#### 🛠️ Idea:
To rotate the matrix **in-place**, follow these 2 steps:
1. **Transpose the matrix**: swap `matrix[i][j]` with `matrix[j][i]` (only upper triangle).
2. **Reverse each row**: reverse the 1D array row by row.

#### ✅ Steps:
1. Loop `row` from `0` to `n-1`, and `col` from `row+1` to `n-1`, and swap `matrix[row][col]` and `matrix[col][row]`.
2. Then, reverse each row.

---

### ✅ Code:
```java
public void rotate(int[][] matrix) {
    int n = matrix.length;

    // Step 1: Transpose the matrix
    for(int row = 0; row < n; row++) {
        for(int col = row; col < n; col++) {
            if(row != col) {
                swap(matrix, row, col);
            }
        }
    }

    // Step 2: Reverse each row
    for(int row = 0; row < n; row++) {
        reverse(matrix[row]);
    }
}

void swap(int[][] matrix, int row, int col) {
    int temp = matrix[row][col];
    matrix[row][col] = matrix[col][row];
    matrix[col][row] = temp;
}

void reverse(int[] arr) {
    int i = 0, j = arr.length - 1;
    while(i < j) {
        int temp = arr[i];
        arr[i++] = arr[j];
        arr[j--] = temp;
    }
}
```

---

## 📌 Intuition:
- **Transpose** swaps across the diagonal.
- **Reversing rows** simulates the 90° clockwise rotation.

---

## 📈 Time & Space Complexity:
- **Time**: `O(n^2)`
- **Space**: `O(1)` (in-place)
---

### 🎯 Input Matrix:

```
matrix =
[
 [1, 2, 3],
 [4, 5, 6],
 [7, 8, 9]
]
```

---

### 🔁 Step 1: Transpose the matrix (swap `matrix[i][j]` with `matrix[j][i]` for `i < j`)

We swap:
- `matrix[0][1]` with `matrix[1][0]` → swap 2 and 4
- `matrix[0][2]` with `matrix[2][0]` → swap 3 and 7
- `matrix[1][2]` with `matrix[2][1]` → swap 6 and 8

#### Result after transpose:

```
[
 [1, 4, 7],
 [2, 5, 8],
 [3, 6, 9]
]
```

---

### 🔄 Step 2: Reverse each row

- Reverse `[1, 4, 7]` → `[7, 4, 1]`
- Reverse `[2, 5, 8]` → `[8, 5, 2]`
- Reverse `[3, 6, 9]` → `[9, 6, 3]`

#### ✅ Final Rotated Matrix (90° clockwise):

```
[
 [7, 4, 1],
 [8, 5, 2],
 [9, 6, 3]
]
```
