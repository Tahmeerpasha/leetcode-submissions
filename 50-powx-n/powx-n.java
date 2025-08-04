public class Solution {
    // Iterative implementation of x^n (x raised to power n) => Time -> O(logn)
    // Binary Exponentiation
    // Watch to understand approach -> https://youtu.be/WBzZCm46mFo?si=EkRgBQtmBL12BnKf
    public double myPow(double x, int n) {
        if (n == 0)
            return 1;
        if (x == 0)
            return 0;
        long binForm = n;
        if (binForm < 0) {
            x = 1 / x;
            binForm = -binForm;
        }
        double ans = 1.0;
        while (binForm > 0) {
            if (binForm % 2 == 1) { // for 5 -> 1 0 1 (binary representation of 1)
                ans = ans * x;
            }
            x = x * x;
            binForm /= 2;
        }
        return ans;
    }

    // Recursive implementation of x^n (x raised to power n) => Time -> O(logn)
    // Fast Exponentiation (a.k.a Exponentiation by Squaring)
    // Watch to understand approach -> https://youtu.be/g9YQyYi4IQQ?si=0TewIEUkZeDH0scR
    // public double myPow(double x, int n) {
    //     // Convert n to long to safely handle edge case like Integer.MIN_VALUE,
    //     // because -Integer.MIN_VALUE overflows int range
    //     long N = n;

    //     // If power is negative, invert the base and make power positive
    //     if (N < 0) {
    //         x = 1 / x; // e.g., 2^-3 => (1/2)^3
    //         N = -N;
    //     }

    //     // Call helper to compute x^N using fast exponentiation
    //     return fastPow(x, N);
    // }

    // private double fastPow(double x, long n) {
    //     // Base case: anything raised to power 0 is 1
    //     if (n == 0)
    //         return 1;

    //     // Base case: 0 raised to any power is 0
    //     if (x == 0)
    //         return 0;

    //     // Recursively compute half power: x^(n/2)
    //     double half = fastPow(x, n / 2);

    //     // If power is even: x^n = (x^(n/2)) * (x^(n/2)) => half * half
    //     if (n % 2 == 0) {
    //         return half * half;
    //     }
    //     // If power is odd: x^n = (x^(n/2)) * (x^(n/2)) * x => half * half * x
    //     else {
    //         return half * half * x;
    //     }
    // }

    // Iterative solution for the recursive solution intuition
    // public double myPow(double x, int n) {
    //     double ans = 1.0;
    //     // We use long because INT_MIN will overflow if converted to positive number
    //     long nn = n;
    //     if (nn < 0)
    //         nn = -1 * nn;
    //     while (nn > 0) {
    //         if (nn % 2 == 1) {
    //             // If power is odd then subtract by 1
    //             ans = ans * x;
    //             nn = nn - 1;
    //         } else {
    //             // If power is even then divide by 2
    //             x = x * x;
    //             nn = nn / 2;
    //         }
    //     }
    //     // Finally if the power was negative then answer should be 1/ans
    //     if (n < 0)
    //         ans = (double) (1.0) / (double) (ans);

    //     return ans;
    // }

}

// \U0001f332 Recursion Tree (Example: x = 2, n = 5)
// We’re computing: 2^5
// myPow(2, 5)
//  └── fastPow(2, 5)
//        ├── fastPow(2, 2)
//        │     ├── fastPow(2, 1)
//        │     │     ├── fastPow(2, 0) → returns 1
//        │     │     └── returns 1 * 1 * 2 = 2
//        │     └── returns 2 * 2 = 4
//        └── returns 4 * 4 * 2 = 32
// So:
// fastPow(2, 0) returns 1
// fastPow(2, 1) returns 1 * 1 * 2 = 2
// fastPow(2, 2) returns 2 * 2 = 4
// fastPow(2, 5) returns 4 * 4 * 2 = 32