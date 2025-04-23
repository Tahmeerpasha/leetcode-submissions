## 🔁 Leetcode 121: **Best Time to Buy and Sell Stock**

### 📌 Problem:
You are given an array `prices` where `prices[i]` is the price of a given stock on day `i`. You want to maximize your profit by choosing a single day to buy and a different day in the future to sell.

Return the **maximum profit** you can achieve from this transaction. If no profit can be made, return 0.

---

## ✅ Optimized Approach (One Pass)

### 🔧 Java Code:
```java
class Solution {
    public int maxProfit(int[] prices) {
        int buyPrice = prices[0];  // The price at the start
        int profit = 0;            // Maximum profit initialized to 0

        for (int currentPrice : prices) {
            if (currentPrice < buyPrice)  // New low price to buy at
                buyPrice = currentPrice;
            if (currentPrice - buyPrice > profit)  // Update maximum profit
                profit = currentPrice - buyPrice;
        }
        return profit;  // Return the max profit
    }
}
```

---

### 🧠 Intuition:
- **Buy Low, Sell High**: At each step, we try to buy at the lowest possible price seen so far and calculate the profit if we sell at the current price.
- Keep track of the lowest buy price and the maximum profit.

---

### 📊 Time & Space Complexity:
- **Time Complexity:** O(n) — We only traverse the array once.
- **Space Complexity:** O(1) — Constant space.

---

## 🧩 Dry Run:

### 🔧 Input:
```java
prices = [7, 1, 5, 3, 6, 4]
```

| i | prices[i] | buyPrice | profit | Action                           | profit (Updated) |
|---|-----------|----------|--------|----------------------------------|------------------|
| 0 | 7         | 7        | 0      | Initial buy price                | 0                |
| 1 | 1         | 7        | 0      | New lower price → buy at 1       | 0                |
| 2 | 5         | 1        | 0      | Profit = 5 - 1 = 4 → update profit| 4                |
| 3 | 3         | 1        | 4      | No profit → continue             | 4                |
| 4 | 6         | 1        | 4      | Profit = 6 - 1 = 5 → update profit| 5                |
| 5 | 4         | 1        | 5      | No profit → continue             | 5                |

✅ Maximum Profit → `5` (Buy at price `1` and sell at price `6`)

---

## 🧱 Brute Force (for learning)

### 🛠️ Idea:
- Try all possible pairs of buy and sell days to find the maximum profit.
  
```java
class Solution {
    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                if (prices[j] > prices[i]) {
                    maxProfit = Math.max(maxProfit, prices[j] - prices[i]);
                }
            }
        }
        return maxProfit;
    }
}
```

- **Time Complexity:** O(n²) — We compare every pair of buy-sell days.
- **Space Complexity:** O(1) — Constant space.

---

## 🔄 Optimized Approach (One Pass) Explanation:

In the optimized approach, we only pass through the array once and track:
1. **buyPrice** — the lowest price encountered so far.
2. **profit** — the highest profit achieved from buying at **buyPrice** and selling at each encountered **currentPrice**.

As we traverse through the prices, we update:
- If we find a new **lower buyPrice**, we update it.
- If we find a **higher profit** (by selling at the current price after buying at **buyPrice**), we update **profit**.

This reduces time complexity from O(n²) (brute force) to O(n).

---
