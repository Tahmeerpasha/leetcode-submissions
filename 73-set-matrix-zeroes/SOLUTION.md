## ✅ Problem: Set Matrix Zeroes (Leetcode 73)

### 🧠 Problem Statement:
Given an `m x n` matrix, if any element is `0`, set its **entire row and column to 0**, in-place.

---

## 🔍 Constraints:
- Do it **in-place** (i.e., modify the original matrix).
- Try to achieve **constant space** complexity.

---

## 💡 Approaches:

---

### ❌ Brute Force Approach (using extra space)

#### 🛠️ Idea:
1. Store all positions `(i,j)` where `matrix[i][j] == 0`.
2. Iterate through the list and zero out the corresponding row and column.

#### ⏱️ Time: `O(m*n)`  
#### 📦 Space: `O(k)` → number of zero elements (worst case O(m*n))

#### Example Code (Using `Position` class):
```java
List<Position> positions = new ArrayList<>();
for(int i=0; i<matrix.length; i++){
    for(int j=0; j<matrix[0].length; j++){
        if(matrix[i][j] == 0)
            positions.add(new Position(i, j));
    }
}
for(Position pos : positions){
    for(int j=0; j<matrix[0].length; j++) matrix[pos.row][j] = 0;
    for(int i=0; i<matrix.length; i++) matrix[i][pos.col] = 0;
}
```

---

### ✅ Optimal Approach (In-Place, O(1) Space)

#### 🛠️ Idea:
Use the **first row and first column** of the matrix as markers to keep track of rows/columns that need to be zeroed.

#### 🔑 Trick:
- Use `matrix[0][j]` to mark column `j`
- Use `matrix[i][0]` to mark row `i`
- Use extra variable `col0` to track whether the **first column** should be zeroed (since `matrix[0][0]` is shared)

#### ✅ Steps:
1. **Mark** rows and columns to be zeroed.
2. **Zero out** the required cells from `(1,1)` to `(n-1, m-1)`.
3. Handle **first row** and **first column** separately at the end.

---

#### ⏱️ Time: `O(m*n)`  
#### 📦 Space: `O(1)`

---

### ✅ Code:
```java
public void setZeroes(int[][] matrix) {
    int n = matrix.length, m = matrix[0].length;
    int col0 = 1;

    // Step 1: Mark rows and columns
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            if (matrix[i][j] == 0) {
                matrix[i][0] = 0;
                if (j == 0) col0 = 0;
                else matrix[0][j] = 0;
            }
        }
    }

    // Step 2: Set matrix cells to zero based on markers
    for (int i = 1; i < n; i++) {
        for (int j = 1; j < m; j++) {
            if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                matrix[i][j] = 0;
            }
        }
    }

    // Step 3: Zero out first row if needed
    if (matrix[0][0] == 0) {
        for (int j = 0; j < m; j++) matrix[0][j] = 0;
    }

    // Step 4: Zero out first column if needed
    if (col0 == 0) {
        for (int i = 0; i < n; i++) matrix[i][0] = 0;
    }
}
```

---

## 🧠 Intuition Recap:
- Use the matrix itself as a marker to save space.
- Handle the first row and column carefully.
