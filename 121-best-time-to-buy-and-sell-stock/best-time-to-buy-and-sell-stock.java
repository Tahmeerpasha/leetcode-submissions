class Solution {
    public int maxProfit(int[] prices) {
        int buyPrice = Integer.MAX_VALUE, profit = Integer.MIN_VALUE;

        for(int currPrice: prices){
            if(currPrice < buyPrice)
                buyPrice = currPrice;
            if(currPrice - buyPrice > profit)
                profit = currPrice - buyPrice;
        }

        return profit;
    }
}