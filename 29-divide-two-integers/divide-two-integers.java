class Solution {
    public int divide(int dividend, int divisor) {
        if (dividend == divisor)
            return 1;
        if (divisor == 1)
            return dividend;

        // Handle sign
        boolean sign = (dividend >= 0) == (divisor >= 0);

        // Convert to long before abs to avoid overflow
        long n = Math.abs((long) dividend);
        long d = Math.abs((long) divisor);

        long quotient = 0;

        while (n >= d) { // run this till numerator is greater or equal to denominator
            int cnt = 0;
            // Checking for one step ahead so we can stop count at the exact step 
            while (n >= (d << (cnt + 1)))
                cnt++;
            quotient += 1L << cnt;
            n -= d << cnt; // Removing the (3 x 2^cnt) from n
        }

        // Handle overflow
        // If quotient is greater than 2^31 means overflow
        if (quotient >= (1L << 31))
            return sign ? Integer.MAX_VALUE : Integer.MIN_VALUE;

        return sign ? (int) quotient : (int) -quotient;
    }
}