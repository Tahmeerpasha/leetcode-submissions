class Solution {
    public int climbStairs(int n) {
        if (n <= 1)
            return 1;
        int[] dp = new int[n + 1];
        // Arrays.fill(dp, -1);
        // return climbStairsWithDp(n, dp);

        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    int climbStairsWithDp(int n, int[] dp) {
        if (n <= 1)
            return 1;
        if (dp[n] != -1)
            return dp[n];
        return dp[n] = climbStairsWithDp(n - 1, dp) + climbStairsWithDp(n - 2, dp);
    }
}