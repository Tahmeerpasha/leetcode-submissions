class Solution {
    public int countGoodNumbers(long n) {
        long product = 1;
        int mod = 1_000_000_007;
        // for (int i = 1; i <= n; i++) {
        //     if (i % 2 == 1)
        //         product *= 5;
        //     else
        //         product *= 4;
        //     product %= mod;
        // }
        long odd = n / 2;
        long even = n / 2 + n % 2;

        return (int) ((fastPow(5, even) * fastPow(4, odd)) % mod);
    }

    private long fastPow(int x, long n) {
        int mod = 1_000_000_007;
        if (n == 0)
            return 1;
        long ans = fastPow(x, n / 2);
        ans *= ans;
        ans %= mod;
        if (n % 2 == 1)
            ans *= x;
        ans %= mod;
        return ans;
    }
}