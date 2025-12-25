# 542. 01 Matrix

## Problem Summary

Given a binary matrix, return a matrix where each cell contains the **distance to the nearest `0`**.

Distance = number of moves (up, down, left, right).

---

## 🔴 Brute Force (Naive)

### Idea

For **every `1`**, run a BFS/DFS to find the nearest `0`.

### Steps

1. For each cell with value `1`
2. Run BFS until you hit a `0`
3. Store minimum distance

### Complexity

* **Time:** `O((n*m) * (n*m))` → worst-case BFS for every cell
* **Space:** `O(n*m)`

### Verdict

❌ **Instant rejection** if you propose this in an interview.

---

## 🟡 Better Solution (BFS from every 1 — still bad)

### Idea

Still BFS from each `1`, but reuse visited arrays smartly.

### Complexity

* **Time:** Still ~`O((n*m)^2)`
* **Space:** `O(n*m)`

### Verdict

⚠️ Slightly better thinking, but **still unacceptable**

---

## 🟢 Optimal Solution — Multi-Source BFS ✅

This is what interviewers EXPECT.

---

## Key Insight (This is the money line)

> **Distance from 1 to nearest 0 = shortest path in an unweighted graph**

Instead of BFS from every `1`, do this:

* Push **ALL `0`s into the queue at once**
* Run BFS outward
* First time you reach a cell → shortest distance guaranteed

---

## Why Multi-Source BFS Works

* All `0`s start at distance `0`
* BFS expands level-by-level
* First visit = minimum distance (unweighted graph property)

---

## Algorithm

1. Initialize:

   * `queue` → store `(row, col, steps)`
   * `vis[][]`
   * `dist[][]`
2. Push **all `0` cells** into queue with `steps = 0`
3. Mark them visited
4. BFS in 4 directions
5. For each unvisited neighbor:

   * mark visited
   * push with `steps + 1`
6. Store distance when popped

---

## ✅ Your Code (KEEP THIS – IT’S PERFECT)

```java
class Node {
    int row;
    int col;
    int steps;

    Node(int r, int c, int s) {
        row = r;
        col = c;
        steps = s;
    }
}

class Solution {
    public int[][] updateMatrix(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        int[][] dist = new int[n][m];
        int[][] vis = new int[n][m];
        Queue<Node> q = new LinkedList<>();

        // Multi-source initialization
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 0) {
                    vis[i][j] = 1;
                    q.add(new Node(i, j, 0));
                } else {
                    vis[i][j] = 0;
                }
            }
        }

        int[] delrow = { -1, 0, 1, 0 };
        int[] delcol = { 0, 1, 0, -1 };

        while (!q.isEmpty()) {
            Node node = q.poll();
            int row = node.row;
            int col = node.col;
            int steps = node.steps;

            dist[row][col] = steps;

            for (int i = 0; i < 4; i++) {
                int nrow = row + delrow[i];
                int ncol = col + delcol[i];

                if (nrow >= 0 && nrow < n && ncol >= 0 && ncol < m
                        && vis[nrow][ncol] == 0) {
                    vis[nrow][ncol] = 1;
                    q.add(new Node(nrow, ncol, steps + 1));
                }
            }
        }
        return dist;
    }
}
```

---

## Complexity (Optimal)

* **Time:** `O(n * m)`
  Each cell is visited once
* **Space:** `O(n * m)`
  Queue + visited + distance

---

## Interview Tips (VERY IMPORTANT)

### 🔑 Pattern Recognition

Say this clearly:

> “This is a **multi-source BFS** problem on a grid.”

Instant brownie points.

---

### ❌ Common Mistakes

* BFS from every `1`
* Using DFS (distance not guaranteed shortest)
* Forgetting to mark visited early → duplicates in queue
* Confusing this with DP first-pass-only solution

---

### 🧠 Related Problems (Same Pattern)

* Rotting Oranges
* Nearest Exit in Maze
* Walls and Gates
* Flood Fill (variation)

If you master **this pattern**, half of graph/grid problems become trivial.

---
