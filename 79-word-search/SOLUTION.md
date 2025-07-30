### 🔍 Problem: **79. Word Search**

**Given:** A 2D board and a word.
**Task:** Check if the word exists in the grid by moving horizontally or vertically (not diagonally), and without revisiting any cell.

---

### 🧠 Intuition

This is a **backtracking** problem. We need to try all possible paths starting from every cell that matches the first character of the word.

---

### 💪 Brute Force

#### 🔸 Idea:

* Try starting from every cell in the grid.
* From each cell, explore all 4 directions recursively to match the next character in the word.
* Do not revisit any cell.

#### 🔸 Approach:

* Use a recursive DFS from each cell.
* Maintain a visited matrix to prevent cycles.

#### 🔸 Time Complexity:

* **O(N \* M \* 4^L)**
  Where `N x M` is the size of the board and `L` is the length of the word.

#### 🔸 Space Complexity:

* **O(L)** — recursion stack (depth = word length)
* **O(N\*M)** — visited matrix

#### 🔸 Code:

```java
class Solution {
    static boolean[][] visited;

    public boolean exist(char[][] board, String word) {
        int row = board.length;
        int col = board[0].length;
        visited = new boolean[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (word.charAt(0) == board[i][j] && search(board, word, 0, i, j))
                    return true;
            }
        }
        return false;
    }

    boolean search(char[][] board, String word, int index, int i, int j) {
        if (index == word.length())
            return true;

        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length ||
            board[i][j] != word.charAt(index) || visited[i][j])
            return false;

        visited[i][j] = true;
        boolean found = search(board, word, index + 1, i - 1, j) ||
                        search(board, word, index + 1, i + 1, j) ||
                        search(board, word, index + 1, i, j - 1) ||
                        search(board, word, index + 1, i, j + 1);
        visited[i][j] = false;
        return found;
    }
}
```

---

### ⚡ Better Approach

#### 🔸 Optimization:

* Instead of using an external `visited` matrix, we can **modify the board in-place** by replacing characters temporarily.
* This reduces extra space.

#### 🔸 Time Complexity:

* Still **O(N \* M \* 4^L)**
  No pruning improvement.

#### 🔸 Space Complexity:

* **O(L)** — for recursion stack
  Eliminated visited matrix space.

#### 🔸 Tip:

> In-place modification needs **careful restoration** of the board state (backtracking).

---

### 🚀 Optimal Thoughts (Heuristics)

Even though the time complexity doesn’t reduce drastically, you can optimize practically by:

* **Pruning**: If the count of a character in the board is less than in the word, immediately return false.
* **Frequency Map**: Precompute board frequency vs. word frequency.

This is mostly helpful when used in interview constraints or large test cases.

---

### ✅ Final Takeaway

* The main idea is **backtracking + marking visited**.
* Optimal path depends on **efficient pruning** and **space-saving tricks** (in-place modification).

---
