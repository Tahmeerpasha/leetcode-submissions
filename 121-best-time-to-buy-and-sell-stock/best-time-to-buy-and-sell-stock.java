class Solution {
    public int maxProfit(int[] prices) {
        int minBuy = Integer.MAX_VALUE, maxProfit = Integer.MIN_VALUE;
        for (int num : prices) {
            minBuy = Math.min(minBuy, num);
            maxProfit = Math.max(maxProfit, num - minBuy);
        }
        return maxProfit;
    }
}