## 🔢 Problem: [37. Sudoku Solver](https://leetcode.com/problems/sudoku-solver/)

**Given**: A partially filled 9x9 Sudoku board.
**Goal**: Fill the board so that it becomes a valid Sudoku solution.

---

## 🧠 Brute Force Approach

### 🔍 Idea:

Try placing all digits `1-9` in each empty cell, and check if the entire board is valid after every placement.

### ❌ Problems:

* Does not backtrack.
* Keeps trying full board validation repeatedly.
* Extremely inefficient.

### ⏱️ Time Complexity:

* **O((9^n) \* n²)**
  `n` = number of empty cells
* Each cell tries 9 digits, and entire board might be validated repeatedly.

### 🧠 Space Complexity:

* O(1), assuming no additional storage.

---

## 🧠 Better Approach

### 🔍 Idea:

Use brute force but add **board validity check** for every move.
Still doesn’t **backtrack efficiently**—tries full combinations.

### ⏱️ Time:

Still exponential due to repeated work.

---

## ✅ Optimal Approach (Backtracking)

### 🔍 Idea:

Use **backtracking** to fill each cell. For every empty cell:

1. Try placing digits `1-9`.
2. For each digit, check if it is valid using row, column, and 3x3 grid.
3. If valid, place and recursively solve for the rest.
4. If stuck, **backtrack**.

---

### ✅ Code (Your Code):

```java
class Solution {
    public void solveSudoku(char[][] board) {
        solve(board);
    }

    boolean solve(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '.') {
                    for (char c = '1'; c <= '9'; c++) {
                        if (isValid(board, i, j, c)) {
                            board[i][j] = c;
                            if (solve(board))
                                return true;
                            else
                                board[i][j] = '.';
                        }
                    }
                    return false; // No valid digit found
                }
            }
        }
        return true; // Entire board filled
    }

    boolean isValid(char[][] board, int row, int col, char c) {
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == c) return false; // Column check
            if (board[row][i] == c) return false; // Row check
            if (board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == c) return false; // Box check
        }
        return true;
    }
}
```

---

### ⏱️ Time Complexity:

* **O(9^n)**
  Where `n` is number of empty cells.
  Each cell can try 9 digits, leading to exponential growth.

### 🧠 Space Complexity:

* **O(1)** extra space (board modified in-place)
* **O(k)** recursion stack depth (`k` = number of empty cells)

---

## 🧩 Dry Run Example

Given:

```
[
 ['5','3','.','.','7','.','.','.','.'],
 ['6','.','.','1','9','5','.','.','.'],
 ['.','9','8','.','.','.','.','6','.'],
 ...
]
```

The algorithm:

* Starts at cell \[0]\[2]
* Tries placing 1-9
* Validates using `isValid()`
* Moves forward recursively until solution is found
* If a dead-end is reached, backtracks

---

## ✅ Final Summary

| Approach  | Time Complexity   | Space Complexity | Key Technique              |
| --------- | ----------------- | ---------------- | -------------------------- |
| Brute     | (9^n) × n²        | O(1)             | Trial without pruning      |
| Better    | Still exponential | O(1)             | Board-level validity       |
| ✅ Optimal | **O(9^n)**        | O(k) recursion   | **Backtracking** + Pruning |

---
