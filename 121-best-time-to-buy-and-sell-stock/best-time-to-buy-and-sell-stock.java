class Solution {
    public int maxProfit(int[] prices) {
        int buyPrice=prices[0];
        int profit=0;

        for(int currentPrice: prices) {
            buyPrice = Math.min(buyPrice, currentPrice);
            profit = Math.max(profit, currentPrice-buyPrice);
        }
        return profit;
    }
}