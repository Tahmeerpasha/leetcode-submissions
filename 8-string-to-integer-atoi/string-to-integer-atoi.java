class Solution {
    public int myAtoi(String s) {
        int i = 0, n = s.length(), sign = 1;
        long ans = 0;

        // Step 1: skip leading spaces
        while (i < n && s.charAt(i) == ' ') {
            i++;
        }

        // Step 2: check for sign
        if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
            sign = s.charAt(i) == '-' ? -1 : 1;
            i++;
        }

        // Step 3: read digits
        while (i < n && Character.isDigit(s.charAt(i))) {
            ans = ans * 10 + (s.charAt(i) - '0');

            // Step 4: handle overflow
            if (sign * ans >= Integer.MAX_VALUE)
                return Integer.MAX_VALUE;
            if (sign * ans <= Integer.MIN_VALUE)
                return Integer.MIN_VALUE;

            i++;
        }

        return (int) (sign * ans);
    }
}
