# 📘 Problem: Largest Rectangle in Histogram

You are given an array `heights[]`, where each element represents the height of a bar in a histogram.
You need to find the **largest rectangular area** that can be formed within the histogram.

---

## 1️⃣ Brute Force Approach

### Idea:

* For every bar, expand **left and right** until the height of bars becomes less than the current bar.
* Calculate the rectangle area with the current bar as the **minimum height**.

### Steps:

1. For each index `i`:

   * Move left until `heights[j] < heights[i]`.
   * Move right until `heights[j] < heights[i]`.
   * Width = `right - left - 1`.
   * Area = `heights[i] * width`.
2. Track the maximum area.

### Complexity:

* **Time:** `O(N^2)` → For each bar, left & right scans may take `O(N)`.
* **Space:** `O(1)`.

⚠️ Works fine for small `N`, but **TLE for large inputs** (N \~ 10^5).

---

## 2️⃣ Better Approach (Using PSE & NSE Precomputation)

### Idea:

* Precompute **Previous Smaller Element (PSE)** and **Next Smaller Element (NSE)** using monotonic stacks.
* For each bar, the max width = `(NSE[i] - PSE[i] - 1)`.

### Steps:

1. Compute `PSE[]`: index of previous smaller element for each bar.
2. Compute `NSE[]`: index of next smaller element for each bar.
3. For each index `i`:

   * Width = `NSE[i] - PSE[i] - 1`.
   * Area = `heights[i] * width`.
   * Update max area.

### Complexity:

* **Time:** `O(N)` → each index pushed & popped once in stack.
* **Space:** `O(2N)` (for PSE, NSE arrays + stack).

---

## 3️⃣ Optimal Approach (Single Pass Monotonic Stack)

### Idea:

* Instead of separately calculating PSE & NSE, compute the area **on the fly** using a stack.
* Maintain a **monotonic increasing stack** of indices:

  * When a new height is smaller than the top of the stack → keep popping & calculate area for popped bar using current index as `NSE`.
  * At the end, pop all remaining stack elements considering array end (`n`) as `NSE`.

### Steps:

1. Traverse through array:

   * While current height < height at top of stack:

     * Pop element.
     * Compute area with popped element as height.
     * Width = `(currentIndex - pse - 1)`.
   * Push current index.
2. After loop, process remaining stack with `n` as right boundary.

### Complexity:

* **Time:** `O(N)` → each bar pushed & popped once.
* **Space:** `O(N)` → stack only.

---

## ✅ Comparison Table

| Approach         | Time Complexity | Space Complexity | Notes             |
| ---------------- | --------------- | ---------------- | ----------------- |
| Brute Force      | `O(N^2)`        | `O(1)`           | Simple but slow   |
| Better (PSE+NSE) | `O(N)`          | `O(N)`           | Uses extra arrays |
| Optimal (Stack)  | `O(N)`          | `O(N)`           | Best in practice  |

---

## 💡 Tips

1. Histogram problems almost always reduce to **monotonic stack patterns**.
2. When you see "nearest smaller/greater to left/right" → think **stack**.
3. Don’t forget to process **remaining stack elements** after the loop (with `n` as right boundary).
4. Initialize `maxArea = 0` instead of `Integer.MIN_VALUE` in real code (since area is non-negative).
5. Practice similar problems:

   * Trapping Rain Water (42)
   * Maximal Rectangle (85)

---
