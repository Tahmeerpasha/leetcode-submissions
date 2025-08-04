class Solution {
    // public int myAtoi(String s) {
    //     int i = 0, n = s.length(), sign = 1;
    //     long ans = 0;

    //     // Step 1: skip leading spaces
    //     while (i < n && s.charAt(i) == ' ') {
    //         i++;
    //     }

    //     // Step 2: check for sign
    //     if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
    //         sign = s.charAt(i) == '-' ? -1 : 1;
    //         i++;
    //     }

    //     // Step 3: read digits
    //     while (i < n && Character.isDigit(s.charAt(i))) {
    //         ans = ans * 10 + (s.charAt(i) - '0');

    //         // Step 4: handle overflow
    //         if (sign * ans >= Integer.MAX_VALUE)
    //             return Integer.MAX_VALUE;
    //         if (sign * ans <= Integer.MIN_VALUE)
    //             return Integer.MIN_VALUE;

    //         i++;
    //     }

    //     return (int) (sign * ans);
    // }

    // Recursive solution
    public int myAtoi(String s) {
        s = s.trim(); // Remove leading/trailing whitespace
        if (s.isEmpty())
            return 0;

        int sign = 1;
        int index = 0;

        if (s.charAt(0) == '+' || s.charAt(0) == '-') {
            sign = (s.charAt(0) == '-') ? -1 : 1;
            index++;
        }

        return helper(s, index, 0, sign);
    }

    private int helper(String s, int index, int result, int sign) {
        // Base case: index out of bounds or non-digit character
        if (index >= s.length() || !Character.isDigit(s.charAt(index))) {
            return result * sign;
        }

        // Subtracting a digit in char with '0' will give that value in integer
        int digit = s.charAt(index) - '0';

        // Handle overflow cases
        /**
        Why this condition?? - result > (Integer.MAX_VALUE - digit) / 10
        1. result * 10 + digit
        -> We can't use this as it'll overflow so,
        2. result * 10 + digit > Integer.MAX_VALUE
        -> Same condition but in other form as above
        3. result > (Integer.MAX_VALUE - digit) / 10
         */
        if (result > (Integer.MAX_VALUE - digit) / 10) {
            return (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        }

        result = result * 10 + digit;

        return helper(s, index + 1, result, sign);
    }
}
