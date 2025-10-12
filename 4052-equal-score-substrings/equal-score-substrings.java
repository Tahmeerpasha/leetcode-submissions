class Solution {
    public boolean scoreBalance(String s) {
        // Brute: Time => O(n^2) && Space => O(1)
        // int n = s.length();
        // for (int i = 0; i < n; i++) {
        //     String s1 = s.substring(0, i);
        //     String s2 = s.substring(i, n);
        //     int sum1 = 0;
        //     for (int j = 0; j < s1.length(); j++) {
        //         sum1 += s1.charAt(j) - 'a' + 1;
        //     }
        //     int sum2 = 0;
        //     for (int j = 0; j < s2.length(); j++) {
        //         sum2 += s2.charAt(j) - 'a' + 1;
        //     }
        //     if (sum1 == sum2)
        //         return true;
        // }
        // return false;

        // Optimal approach
        int n = s.length();
        int total = 0;

        // Step 1: compute total score
        for (char ch : s.toCharArray())
            total += ch - 'a' + 1;

        int leftSum = 0;

        // Step 2: check prefix sum balance
        for (int i = 0; i < n - 1; i++) { // split before last char
            leftSum += s.charAt(i) - 'a' + 1;
            int rightSum = total - leftSum;

            if (leftSum == rightSum)
                return true;
        }

        return false;
    }
}