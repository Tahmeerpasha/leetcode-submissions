## 733. Flood Fill

**Pattern:** Graph traversal on grid (DFS / BFS)
**Graph type:** Implicit grid graph (4-directional)

![Image](https://favtutor.com/resources/images/uploads/mceu_75315074111692767932262.png)

![Image](https://www.thecshandbook.com/public_html/img/uploads/floodfill.png)

---

## Problem Restatement (Interview Style)

You’re given a 2D grid (`image`).
Starting from `(sr, sc)`, **change the color of all connected pixels** having the **same initial color** to a new `color`.

Connectivity = **up, right, down, left**.

---

## Key Insight (This is what interviewers want)

* This is **connected component traversal**
* Condition to move:

  1. Inside bounds
  2. Same initial color
  3. Not already recolored

---

## Brute Force Approach ❌ (Theoretical)

**Idea:**
Repeatedly scan the grid and recolor matching neighbors until no changes occur.

**Why it's bad:**

* Multiple full grid scans
* No traversal structure

**Time Complexity:** `O((m*n)^2)`
**Space Complexity:** `O(1)`

⚠️ **Never propose this in interviews** unless asked explicitly.

---

## Better Approach – DFS (Recursive) ✅

This is what you already wrote. It’s **correct and optimal**.

### Approach

* Start DFS from `(sr, sc)`
* Store initial color
* Recursively visit all 4-directional neighbors
* Recolor as you visit

---

### ✅ Your Code (Keep This)

```java
class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int[][] ans = image;
        int iniClr = image[sr][sc];
        int[] drow = { -1, 0, 1, 0 };
        int[] dcol = { 0, 1, 0, -1 };
        dfs(ans, image, drow, dcol, iniClr, color, sr, sc);
        return ans;
    }

    public void dfs(int[][] ans, int[][] image, int[] drow, int[] dcol,
                    int iniClr, int nClr, int sr, int sc) {

        ans[sr][sc] = nClr;
        int row = image.length;
        int col = image[0].length;

        for (int i = 0; i < 4; i++) {
            int nrow = sr + drow[i];
            int ncol = sc + dcol[i];

            if (nrow >= 0 && nrow < row && ncol >= 0 && ncol < col
                    && image[nrow][ncol] == iniClr
                    && ans[nrow][ncol] != nClr) {

                dfs(ans, image, drow, dcol, iniClr, nClr, nrow, ncol);
            }
        }
    }
}
```

---

### Complexity

* **Time:** `O(m * n)`
  (Each cell is visited once)
* **Space:** `O(m * n)` worst case (recursive stack)

---

## Optimal Approach – BFS (Iterative) ✅

Same efficiency, **safer for large grids** (no stack overflow).

### When to prefer BFS?

* Large grid
* Language has small recursion stack (Java interviews → BFS is safer)

---

### BFS Logic (Explain, don’t overcode in interview)

1. Push `(sr, sc)` into queue
2. Recolor it
3. While queue not empty:

   * Pop cell
   * Explore 4 neighbors
   * If valid → recolor + push

---

### BFS Complexity

* **Time:** `O(m * n)`
* **Space:** `O(m * n)` (queue)

---

## Important Edge Case (VERY IMPORTANT)

```java
if (image[sr][sc] == color) return image;
```

Why?

* Otherwise you’ll recurse forever / do useless work

🔥 **This is a common interview trap**

---

## Interview Tips & Tricks (Memorize These)

* Call it **DFS/BFS on implicit graph**
* Say **“connected component traversal”**
* Always mention:

  * Boundary check
  * Initial color comparison
  * Visited / recolored condition
* If asked “why not visited array?” →

  > Recoloring itself acts as visited marking

---

## Pattern Mapping (For Revision)

This same pattern appears in:

* Number of Islands
* Max Area of Island
* Rotting Oranges
* Surrounded Regions
* Number of Provinces (matrix version)

If you master Flood Fill, **half of graph grid problems become trivial**.

---
