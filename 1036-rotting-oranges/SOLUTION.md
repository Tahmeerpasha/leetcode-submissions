# 994. Rotting Oranges — Notes

## Problem Summary

* `0` → empty cell
* `1` → fresh orange
* `2` → rotten orange
* Every minute, **rotten oranges rot adjacent fresh ones (up/down/left/right)**
* Return **minimum time** to rot all oranges, else `-1`

---

## Pattern Identification

**Multi-source BFS on a grid**

> Anytime you see:
>
> * “spread in all 4 directions”
> * “minimum time / steps”
> * “multiple starting points”
>   → **BFS**

---

## 🔴 Brute Force (Naive Simulation)

### Idea

* For every minute:

  * Scan the whole grid
  * Mark fresh oranges that should rot
* Repeat until no changes occur

### Why it’s bad

* You scan the grid again and again
* No guarantee of optimal timing

### Complexity

* **Time:** `O((R*C)^2)`
* **Space:** `O(1)` (or extra grid)

### Verdict

❌ **Never do this in interviews**
Mention it only to reject it quickly.

---

## 🟡 Better Approach (Single-source BFS – Incorrect)

* Start BFS from **one** rotten orange
* Doesn’t work if there are **multiple rotten sources**

❌ Incorrect logic → **skip**

---

## 🟢 Optimal Solution (Multi-source BFS)

### Core Idea

* Push **all rotten oranges** into the queue initially
* BFS level by level (each level = 1 minute)
* Count how many fresh oranges get rotted

---

## Algorithm Steps

1. Count total **fresh oranges**
2. Push **all rotten oranges** into queue with time = 0
3. Run BFS:

   * From each rotten orange, rot adjacent fresh ones
   * Track time
4. If all fresh oranges rot → return time
   Else → return `-1`

---

## Code (Your Provided Solution — Keep This)

```java
class Pair {
    int row;
    int col;
    int time;

    public Pair(int row, int col, int time) {
        this.row = row;
        this.col = col;
        this.time = time;
    }
}

class Solution {
    public int orangesRotting(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        Queue<Pair> q = new LinkedList<>();
        int[][] vis = new int[row][col];
        int cntFresh = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 2) {
                    vis[i][j] = 2;
                    q.add(new Pair(i, j, 0));
                } else {
                    vis[i][j] = 0;
                }

                if (grid[i][j] == 1)
                    cntFresh++;
            }
        }

        int[] drow = { -1, 0, 1, 0 };
        int[] dcol = { 0, 1, 0, -1 };
        int cnt = 0, tm = 0;

        while (!q.isEmpty()) {
            int r = q.peek().row;
            int c = q.peek().col;
            int t = q.peek().time;
            q.remove();

            tm = Math.max(tm, t);

            for (int i = 0; i < 4; i++) {
                int nrow = r + drow[i];
                int ncol = c + dcol[i];

                if (nrow >= 0 && nrow < row && ncol >= 0 && ncol < col
                        && vis[nrow][ncol] == 0 && grid[nrow][ncol] == 1) {

                    q.add(new Pair(nrow, ncol, t + 1));
                    vis[nrow][ncol] = 2;
                    cnt++;
                }
            }
        }

        if (cnt != cntFresh)
            return -1;

        return tm;
    }
}
```

---

## Complexity Analysis

* **Time:** `O(R * C)`
  Every cell is processed at most once
* **Space:** `O(R * C)`
  Queue + visited array

---

## Interview Tips (Very Important)

### 1. Say this line confidently:

> “This is a **multi-source BFS**, since rotting starts from multiple cells simultaneously.”

That alone signals competence.

---

### 2. Why BFS and not DFS?

* BFS naturally models **time / levels**
* DFS can’t guarantee minimum time

---

### 3. Why count fresh oranges?

* To detect **impossible cases**
* If some fresh oranges never get rotted → return `-1`

---

### 4. Optimization Note

You **don’t actually need `vis[][]`**

* You can modify `grid[][]` directly
* But `vis` makes logic cleaner and safer in interviews

---

## One-liner Summary (Memorize)

> “Push all rotten oranges into the queue initially and perform BFS level by level to simulate minute-wise rotting.”

---
