class Solution {
    public int maxProfit(int[] prices) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int num : prices) {
            min = Math.min(min, num);
            max = Math.max(max, num - min);
        }
        return max;
    }
}