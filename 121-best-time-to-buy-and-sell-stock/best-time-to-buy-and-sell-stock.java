class Solution {
    public int maxProfit(int[] prices) {
        int buyPrice=prices[0];
        int profit=0;

        for(int currentPrice: prices) {
            if (currentPrice < buyPrice)
                buyPrice = currentPrice;
            if (currentPrice-buyPrice > profit)
                profit = currentPrice-buyPrice;
        }
        return profit;
    }
}