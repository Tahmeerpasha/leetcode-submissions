### 🔍 Problem: **51. N-Queens**

**Goal:** Place `n` queens on an `n x n` chessboard so that no two queens attack each other. Return all distinct solutions.

---

### 🧠 Intuition

The N-Queens problem is a classic **backtracking** problem. We need to explore all valid queen placements column by column and **backtrack** when we hit invalid positions.

---

### 🐌 Brute Force

#### 🔸 Idea:

Try all possible placements recursively, and for each placement, **validate the board** to check if it's safe.

#### 🔸 Approach:

* For each column, try placing a queen in every row.
* Use a helper function `validate()` to check if the position is safe by checking:

  * Left row
  * Upper left diagonal
  * Lower left diagonal
* Backtrack after each recursive call.

#### 🔸 Code Highlight:

```java
if (validate(board, row, col)) {
    board[row][col] = 'Q';
    dfs(col + 1, board, res);
    board[row][col] = '.';
}
```

#### 🔸 Time Complexity:

* **O(N! \* N)**

  * There are N choices for the first column, (N-1) for the second, and so on — factorial.
  * `O(N)` for each validation (row & diagonals).

#### 🔸 Space Complexity:

* **O(N²)**

  * Due to board and recursion stack.

---

### ⚙️ Better Approach (Minor Optimization)

#### 🔸 Idea:

Instead of validating every time, **precompute and update** whether a row/diagonal is safe using arrays.

#### 🔸 Arrays Used:

* `leftRow[n]`: Checks if a row has a queen.
* `upperDiagonal[2n-1]`: Tracks diagonals from top-left to bottom-right.
* `lowerDiagonal[2n-1]`: Tracks diagonals from bottom-left to top-right.

#### 🔸 Approach:

* No need for `validate()` function.
* Just check and update arrays during backtracking.
* Faster decision-making with constant-time checks.

#### 🔸 Code Highlight:

```java
class Solution {
    public List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                board[i][j] = '.';
        List<List<String>> res = new ArrayList<List<String>>();
        // Brute - Time -> O(N! * N) && Space -> O(n^2)
        // dfs(0, board, res);
        // Optimal - Time -> O(N! * N) && Space -> O(n)
        int leftRow[] = new int[n];
        int upperDiagonal[] = new int[2 * n - 1];
        int lowerDiagonal[] = new int[2 * n - 1];
        solve(0, board, res, leftRow, lowerDiagonal, upperDiagonal);
        return res;
    }

    void solve(int col, char[][] board, List<List<String>> res, int leftRow[], int lowerDiagonal[],
            int upperDiagonal[]) {
        if (col == board.length) {
            res.add(construct(board));
            return;
        }

        for (int row = 0; row < board.length; row++) {
            if (leftRow[row] == 0 && lowerDiagonal[row + col] == 0
                    && upperDiagonal[board.length - 1 + col - row] == 0) {
                board[row][col] = 'Q';
                leftRow[row] = 1;
                lowerDiagonal[row + col] = 1;
                upperDiagonal[board.length - 1 + col - row] = 1;
                solve(col + 1, board, res, leftRow, lowerDiagonal, upperDiagonal);
                // Backtrack
                board[row][col] = '.';
                leftRow[row] = 0;
                lowerDiagonal[row + col] = 0;
                upperDiagonal[board.length - 1 + col - row] = 0;
            }
        }
    }

    void dfs(int col, char[][] board, List<List<String>> res) {
        if (col == board.length) {
            res.add(construct(board));
            return;
        }

        for (int row = 0; row < board.length; row++) {
            if (validate(board, row, col)) {
                board[row][col] = 'Q';
                dfs(col + 1, board, res);
                board[row][col] = '.';
            }
        }
    }

    boolean validate(char[][] board, int row, int col) {
        int duprow = row;
        int dupcol = col;
        while (row >= 0 && col >= 0) {
            if (board[row][col] == 'Q')
                return false;
            row--;
            col--;
        }

        row = duprow;
        col = dupcol;
        while (col >= 0) {
            if (board[row][col] == 'Q')
                return false;
            col--;
        }

        row = duprow;
        col = dupcol;
        while (col >= 0 && row < board.length) {
            if (board[row][col] == 'Q')
                return false;
            col--;
            row++;
        }
        return true;
    }

    List<String> construct(char[][] board) {
        List<String> res = new LinkedList<String>();
        for (int i = 0; i < board.length; i++) {
            String s = new String(board[i]);
            res.add(s);
        }
        return res;
    }
}
```

#### 🔸 Time Complexity:

* **O(N! \* N)**
  Same number of recursive calls as brute, but faster safety check (O(1)) instead of O(N).

#### 🔸 Space Complexity:

* **O(N)**
  Only auxiliary arrays and recursion stack used (board itself can be reused).

---

### 🚀 Optimal Approach

#### ✅ You are already using the optimal approach:

* Precomputing safe positions via arrays.
* Constant-time validation.
* Efficient backtracking with pruning.

No further asymptotic improvement is possible, but implementation tricks (bitmasking, symmetry) can help for larger `N` in practical scenarios.

---

### 📌 Summary Table

| Approach  | Time Complexity | Space Complexity | Key Idea                       |
| --------- | --------------- | ---------------- | ------------------------------ |
| Brute     | O(N! \* N)      | O(N²)            | Validate all queen placements  |
| Better    | O(N! \* N)      | O(N²)            | Slight pruning or early return |
| ✅ Optimal | O(N! \* N)      | O(N)             | Use arrays for O(1) validation |

---

### ✅ Final Notes:

* Use precomputed **row/diagonal tracking** arrays for efficient pruning.
* Recursive backtracking is key.
* Valid board construction is handled via helper method `construct()`.
