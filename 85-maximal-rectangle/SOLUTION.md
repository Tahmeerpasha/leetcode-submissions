# 📘 Problem: Maximal Rectangle

Given a binary matrix (`0` and `1`), find the area of the **largest rectangle containing only `1`s**.

---

## 1️⃣ Brute Force Approach

### Idea:

* Treat every cell with `1` as the **top-left corner** of a potential rectangle.
* Expand in all directions (rightward for width, downward for height) to check if rectangle remains valid.
* Track max area.

### Steps:

1. For each cell `(i, j)`:

   * If `matrix[i][j] == 1`:

     * Expand to the right for possible widths.
     * For each width, expand downwards to check full rectangle.
     * Compute area = width × height.
2. Return maximum area.

### Complexity:

* **Time:** `O((m × n)² × min(m, n))` → Very high, checking many submatrices.
* **Space:** `O(1)`.

⚠️ Works only for small grids.

---

## 2️⃣ Better Approach (Row-wise DP)

### Idea:

* Convert problem into **multiple Largest Rectangle in Histogram (84)**.
* For each row:

  * Treat it as the **base** of a histogram.
  * Build heights by counting consecutive 1s **vertically**.
  * Run `largestRectangleArea()` on this histogram.

### Steps:

1. Maintain a `heights[]` array of size `col`.
2. For each row:

   * If `matrix[row][col] == 1`, increment `heights[col]++`.
   * Else reset `heights[col] = 0`.
   * Call **Largest Rectangle in Histogram** on `heights`.
   * Update max area.

### Complexity:

* **Time:** `O(m × n)` → `m` rows × `O(n)` histogram area calc.
* **Space:** `O(n)` for heights + stack.

This is much better, but still recalculates each row from scratch.

---

## 3️⃣ Optimal Approach (Prefix Sum + Histogram)

### Idea:

* Precompute **prefix heights** for each column.
* For each row, use histogram logic **directly** (like you did in your code).
* This avoids recalculating heights repeatedly.

### Steps:

1. Build a `prefixSum[row][col]` matrix:

   * If `matrix[i][j] == 1` → add height from above.
   * Else reset to `0`.
2. For each row in `prefixSum`, run **Largest Rectangle in Histogram**.
3. Track max area.

### Complexity:

* **Time:** `O(m × n)` (each cell processed once + histogram logic).
* **Space:** `O(m × n)` for prefix matrix (can be optimized to `O(n)` by reusing a `heights[]` array instead of full prefix).

---

## ✅ Comparison Table

| Approach                     | Time Complexity           | Space Complexity   | Notes            |
| ---------------------------- | ------------------------- | ------------------ | ---------------- |
| Brute Force                  | `O((m × n)² × min(m, n))` | `O(1)`             | Too slow         |
| Better (Row DP)              | `O(m × n)`                | `O(n)`             | Good trade-off   |
| Optimal (Prefix + Histogram) | `O(m × n)`                | `O(n)` (optimized) | Best in practice |

---

## 💡 Tips

1. **Key Reduction:** Maximal Rectangle → Multiple **Largest Rectangle in Histogram** problems.
2. Always reuse the histogram logic (monotonic stack).
3. Instead of storing full `prefixSum`, just maintain a **single heights array** updated row by row → reduces space from `O(m × n)` → `O(n)`.
4. Practice problem connections:

   * Largest Rectangle in Histogram (84)
   * Maximal Square (221)
   * Rectangle Area problems (good geometry + DP practice).

---
