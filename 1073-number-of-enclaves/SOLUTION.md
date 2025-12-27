# 1020. Number of Enclaves

## Problem Restatement (1 line)

Count land cells (`1`) that **cannot reach the boundary** of the grid by moving 4-directionally.

---

## Key Insight (Core Idea)

👉 **Any land connected to the boundary is NOT an enclave.**
So:

1. Eliminate all boundary-connected land.
2. Count remaining land.

This is **reverse thinking** — instead of finding enclaves directly, remove non-enclaves.

---

## 🟥 Brute Force (Conceptual – NOT recommended)

### Idea

For **every land cell**, run DFS/BFS to check:

* If during traversal you hit the boundary → **not enclave**
* Else → enclave

### Steps

1. For each cell `(i,j)` where `grid[i][j] == 1`
2. Run DFS/BFS
3. Track if boundary is reached

### Complexity

* **Time:** `O((N*M) * (N*M))` ❌
* **Space:** `O(N*M)` recursion / queue

### Why it’s bad

* Repeating traversals for same components
* Will TLE for large grids

**Interview verdict:** ❌ Don’t even mention unless asked

---

## 🟨 Better Approach (DFS from boundary)

### Idea

1. Start DFS from **all boundary land cells**
2. Mark everything reachable as `visited`
3. Count land cells not visited

### Steps

1. Traverse first row, last row, first col, last col
2. DFS from boundary `1`s
3. Count unvisited `1`s

### Complexity

* **Time:** `O(N*M)`
* **Space:** `O(N*M)` (recursion stack worst case)

### Risk

* DFS stack overflow for large grids

---

## 🟩 Optimal Approach (BFS from boundary) ✅

### Why BFS?

* No recursion stack risk
* Clean, level-order flood fill

### Strategy

1. Push **all boundary land cells** into queue
2. BFS to mark all reachable land
3. Remaining unvisited land = enclaves

---

## ✅ Optimal Code (Your Code – Correct & Interview Ready)

```java
class Pair {
    int row;
    int col;

    Pair(int r, int c) {
        row = r;
        col = c;
    }
}

class Solution {
    public int numEnclaves(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] vis = new int[n][m];
        Queue<Pair> q = new LinkedList<>();
        int[] delrow = { -1, 0, 1, 0 };
        int[] delcol = { 0, 1, 0, -1 };

        // Boundary rows
        for (int j = 0; j < m; j++) {
            if (grid[0][j] == 1) {
                vis[0][j] = 1;
                q.add(new Pair(0, j));
            }
            if (grid[n - 1][j] == 1) {
                vis[n - 1][j] = 1;
                q.add(new Pair(n - 1, j));
            }
        }

        // Boundary columns
        for (int i = 0; i < n; i++) {
            if (grid[i][0] == 1) {
                vis[i][0] = 1;
                q.add(new Pair(i, 0));
            }
            if (grid[i][m - 1] == 1) {
                vis[i][m - 1] = 1;
                q.add(new Pair(i, m - 1));
            }
        }

        // BFS
        while (!q.isEmpty()) {
            int row = q.peek().row;
            int col = q.peek().col;
            q.remove();

            for (int i = 0; i < 4; i++) {
                int nrow = row + delrow[i];
                int ncol = col + delcol[i];

                if (nrow >= 0 && nrow < n &&
                    ncol >= 0 && ncol < m &&
                    grid[nrow][ncol] == 1 &&
                    vis[nrow][ncol] == 0) {

                    vis[nrow][ncol] = 1;
                    q.add(new Pair(nrow, ncol));
                }
            }
        }

        // Count enclaves
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1 && vis[i][j] == 0)
                    cnt++;
            }
        }
        return cnt;
    }
}
```

---

## Complexity (Optimal)

* **Time:** `O(N*M)` ✅
  (each cell visited once)
* **Space:** `O(N*M)`
  (`visited` + queue in worst case)

---

## Interview Tips (IMPORTANT)

### 🔥 Pattern Name

> **Boundary BFS / Flood Fill from Boundary**

Say this explicitly.

---

### 🔥 One-liner Explanation (Use This)

> “Any land connected to the boundary cannot be an enclave, so I eliminate all boundary-reachable land using BFS, then count the rest.”

---

### 🔥 Common Mistakes

❌ Forgetting corner cells
❌ Running BFS from every land cell
❌ Counting before eliminating boundary land

---

### 🔥 Related Problems (Same Pattern)

* Surrounded Regions (130)
* Number of Closed Islands (1254)
* Flood Fill
* Rotting Oranges (multi-source BFS)

---

### 🔥 Optimization Thought

You **don’t need `vis[][]`** — you can mark `grid[i][j] = 0` directly to save space (mention if interviewer asks).

---
