class Solution {
    public int climbStairs(int n) {
        if (n <= 1)
            return 1;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        return climbStairsWithDp(n, dp);
    }

    int climbStairsWithDp(int n, int[] dp) {
        if (n <= 1)
            return 1;
        if (dp[n] != -1)
            return dp[n];
        return dp[n] = climbStairsWithDp(n - 1, dp) + climbStairsWithDp(n - 2, dp);
    }
}