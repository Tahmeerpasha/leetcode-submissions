# 130. Surrounded Regions

## Problem Summary

You’re given a 2D board of `'X'` and `'O'`.

* An `'O'` is **captured** if it is **completely surrounded by `'X'`** (up, down, left, right).
* Any `'O'` connected to the **boundary** can **never** be captured.

👉 Convert all **captured `'O'` → `'X'`**.

---

## Core Insight (MOST IMPORTANT)

> **Don’t try to find surrounded regions.
> Instead, find regions that are NOT surrounded.**

* Any `'O'` connected to a **boundary `'O'`** is **safe**
* All other `'O'` are **trapped** → flip them

This flips the problem from **hard → trivial**.

---

## 1️⃣ Brute Force (Naive – Not Recommended)

### Idea

For **every `'O'`**:

* Run DFS/BFS
* Check if this region touches the boundary
* If not → flip all cells in this region

### Why it’s bad

* Same cells revisited multiple times
* Exponential feeling on large grids

### Complexity

* **Time:** `O((n*m)^2)`
* **Space:** `O(n*m)` recursion/queue

🚫 **Interview red flag** unless stated constraints are tiny.

---

## 2️⃣ Better Solution (Marking Safe Cells) ✅

### Idea

1. Start DFS/BFS from **boundary `'O'` cells**
2. Mark all reachable `'O'` as **safe**
3. Traverse board:

   * Unvisited `'O'` → flip to `'X'`

This avoids repeated work.

### Complexity

* **Time:** `O(n*m)`
* **Space:** `O(n*m)` (visited + recursion stack)

✔ Acceptable
✔ Clean
✔ Easy to explain

---

## 3️⃣ Optimal Solution (Same Logic, Space-Optimized) 🔥

### Idea

Same as Better, but:

* Instead of `vis[][]`, **modify board in-place**
* Temporarily mark safe `'O'` as `'#'`

### Steps

1. DFS from boundary `'O'` → mark as `'#'`
2. Traverse board:

   * `'O'` → `'X'`
   * `'#'` → `'O'`

### Complexity

* **Time:** `O(n*m)`
* **Space:** `O(1)` extra (excluding recursion stack)

🔥 **This is the interviewer favorite**

---

## ✅ Your Provided Code (Better Solution – DFS)

> This is **correct, clean, and interview-safe**.
> You used a visited array instead of modifying the board directly.

```java
class Solution {
    public void solve(char[][] board) {
        int n = board.length;
        int m = board[0].length;
        int[] delrow = { -1, 0, 1, 0 };
        int[] delcol = { 0, 1, 0, -1 };
        int[][] vis = new int[n][m];

        // Top & Bottom boundary
        for (int j = 0; j < m; j++) {
            if (vis[0][j] == 0 && board[0][j] == 'O')
                dfs(0, j, vis, board, delrow, delcol);
            if (vis[n - 1][j] == 0 && board[n - 1][j] == 'O')
                dfs(n - 1, j, vis, board, delrow, delcol);
        }

        // Left & Right boundary
        for (int i = 0; i < n; i++) {
            if (vis[i][0] == 0 && board[i][0] == 'O')
                dfs(i, 0, vis, board, delrow, delcol);
            if (vis[i][m - 1] == 0 && board[i][m - 1] == 'O')
                dfs(i, m - 1, vis, board, delrow, delcol);
        }

        // Flip unvisited O's
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (vis[i][j] == 0 && board[i][j] == 'O')
                    board[i][j] = 'X';
            }
        }
    }

    void dfs(int row, int col, int[][] vis, char[][] board, int[] delrow, int[] delcol) {
        vis[row][col] = 1;
        int n = board.length;
        int m = board[0].length;

        for (int i = 0; i < 4; i++) {
            int nrow = row + delrow[i];
            int ncol = col + delcol[i];

            if (nrow >= 0 && nrow < n && ncol >= 0 && ncol < m &&
                vis[nrow][ncol] == 0 && board[nrow][ncol] == 'O') {
                dfs(nrow, ncol, vis, board, delrow, delcol);
            }
        }
    }
}
```

---

## Interview Tips (Read This Carefully)

### 🧠 How to Explain in Interview

Say this verbatim (simplified):

> “Any region touching the boundary cannot be surrounded.
> So I first mark all boundary-connected O’s using DFS,
> then flip the remaining O’s.”

That’s it. You’ll sound solid.

---

### ⚠️ Common Traps

* ❌ Forgetting **all 4 boundaries**
* ❌ Trying DFS from **every `'O'`**
* ❌ Confusing diagonal connections (NOT allowed)

---

### 🔄 BFS vs DFS?

* Both are fine
* DFS is simpler
* BFS avoids stack overflow for very large grids

---

### 🧩 Pattern Recognition

This problem is:

* **Grid DFS/BFS**
* **Boundary-based traversal**
* **Connected components with constraints**

Same pattern as:

* Number of Islands (boundary variation)
* Flood Fill
* Rotting Oranges (multi-source BFS)

---
