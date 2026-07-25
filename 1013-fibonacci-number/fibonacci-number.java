class Solution {
    public int fib(int n) {
        // DP - Memoization | TC -> O(n) && SC -> O(n) + O(n)
        if (n <= 1)
            return n;
        int[] dp = new int[n + 1];
        // Arrays.fill(dp, -1);
        // return fibSeries(n, dp);

        // DP - Tabulation | TC -> O(n) && SC -> O(n)
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    int fibSeries(int n, int[] dp) {
        if (n <= 1)
            return n;
        if (dp[n] != -1)
            return dp[n];
        return dp[n] = fibSeries(n - 1, dp) + fibSeries(n - 2, dp);
    }
}