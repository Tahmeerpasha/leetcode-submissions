public class Solution {

    // Iterative solution
    public static double myPow(double x, int n) {
        double ans = 1.0;
        // We use long because INT_MIN will overflow if converted to positive number
        long nn = n;
        if (nn < 0)
            nn = -1 * nn;
        while (nn > 0) {
            if (nn % 2 == 1) {
                // If power is odd then subtract by 1
                ans = ans * x;
                nn = nn - 1;
            } else {
                // If power is even then divide by 2
                x = x * x;
                nn = nn / 2;
            }
        }
        // Finally if the power was negative then answer should be 1/ans
        if (n < 0)
            ans = (double) (1.0) / (double) (ans);
        
        return ans;
    }

    // Recursive
    // public double myPow(double x, int n) {
    //     if (n == 0)
    //         return 1;

    //     // Handle negative power safely
    //     // We use long because INT_MIN will overflow if converted to positive number
    //     long N = n;
    //     if (N < 0) {
    //         x = 1 / x;
    //         N = -N;
    //     }

    //     return fastPow(x, N);
    // }

    private double fastPow(double x, long n) {
        if (n == 0)
            return 1;
        double half = fastPow(x, n / 2);

        if (n % 2 == 0) {
            return half * half;
        } else {
            return half * half * x;
        }
    }
}
