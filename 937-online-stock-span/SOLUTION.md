# 📘 Online Stock Span (LeetCode 901) – Notes

## 🔹 Problem Statement

You are given a stream of daily stock prices. For each day’s price, return the **span** of that price, i.e. the number of consecutive days (including today) where the price was **less than or equal** to today’s price.

---

## 🔹 Example

```
Input:  prices = [100, 80, 60, 70, 60, 75, 85]
Output: [1,    1,   1,  2,  1,  4,  6]
```

---

## 🔹 Approaches

### 1. Brute Force (Naive)

* For each price, look **backward** until you find a higher price.
* Count how many consecutive smaller/equal prices there are.

#### Pseudocode

```java
for each new price:
    span = 1
    for j = i-1 → 0:
        if prices[j] <= price: span++
        else break
    return span
```

#### Complexity

* **Time**: O(n²) in worst case (if prices are strictly increasing, every day looks back at all previous days).
* **Space**: O(1).

✅ Works, but **too slow** for large input (n up to 10⁵).

---

### 2. Better Approach (Using Index Stack)

* Maintain a **monotonic decreasing stack** of `(price, index)` pairs.
* For each price:

  * Pop while stack top price ≤ current price.
  * Span = `currentIndex - (stack.isEmpty() ? -1 : stack.peek().index)`.
  * Push `(price, index)`.

#### Complexity

* **Time**: O(n) amortized (each element is pushed/popped once).
* **Space**: O(n).

⚠️ Slightly more complex because you track indices.

---

### 3. Optimal Approach (Using Span Stack ✅)

* Maintain a **stack of (price, span)** instead of indices.
* For each price:

  * Initialize span = 1.
  * While stack top price ≤ current price, pop and **add its span**.
  * Push `(price, span)` back.
  * Return span.

#### Code

```java
class StockSpanner {
    Stack<int[]> st; // each element is [price, span]

    public StockSpanner() {
        st = new Stack<>();
    }

    public int next(int price) {
        int span = 1;
        while (!st.isEmpty() && st.peek()[0] <= price) {
            span += st.pop()[1];
        }
        st.push(new int[]{price, span});
        return span;
    }
}
```

#### Complexity

* **Time**: O(1) amortized per query (each element is pushed and popped at most once).
* **Space**: O(n).

This is the **cleanest and most efficient solution**.

---

## 🔹 Tips & Insights

1. **Think Monotonic Stack**: This is a classic "nearest greater element to the left" variation.
2. **Span vs Index**:

   * `(price, index)` → need subtraction to get span.
   * `(price, span)` → span is precomputed, cleaner & faster.
3. **Amortized O(1)**: Each element is popped once only → total work across `n` prices is O(n).
4. **Common Mistake**: Forgetting to add popped spans → leads to wrong answers.
5. **Real-world analogy**: Span tells you "how many days in a row including today the stock has been bullish or non-decreasing compared to today’s price".

---

✅ **Final Takeaway**:

* Start with brute force to understand.
* Move to index-based stack.
* Master the span-based stack for interviews — it’s the cleanest and most optimal solution.

---
