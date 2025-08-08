class Solution {
    public int divide(int dividend, int divisor) {
        if (dividend == divisor)
            return 1;

        // Handle sign
        boolean sign = (dividend >= 0) == (divisor >= 0);

        // Convert to long before abs to avoid overflow
        long n = Math.abs((long) dividend);
        long d = Math.abs((long) divisor);

        long quotient = 0;

        while (n >= d) {
            int cnt = 0;
            while (n >= (d << (cnt + 1))) {
                cnt++;
            }
            quotient += 1L << cnt;
            n -= d << cnt;
        }

        // Handle overflow
        if (quotient >= (1L << 31)) {
            return sign ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        }

        return sign ? (int) quotient : (int) -quotient;
    }
}