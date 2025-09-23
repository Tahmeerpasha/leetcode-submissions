class Solution {
    public int characterReplacement(String s, int k) {
        // Brute: TC=> O(n^2) && SC=> O(n)
        // int maxLen = 0;
        // for (int i = 0; i < s.length(); i++) {
        //     int[] hash = new int[26];
        //     int maxF = 0;
        //     for (int j = i; j < s.length(); j++) {
        //         hash[s.charAt(j) - 'A']++;
        //         maxF = Math.max(maxF, hash[s.charAt(j) - 'A']);
        //         int changes = j - i + 1 - maxF;
        //         if (changes <= k) {
        //             maxLen = Math.max(maxLen, j - i + 1);
        //         } else
        //             break;
        //     }
        // }
        // return maxLen;

        // Better: TC=>O(n*n) && SC=>O(26)
        // int l = 0, r = 0, maxLen = 0, maxF = 0;
        // int[] hash = new int[26];
        // while (r < s.length()) {
        //     hash[s.charAt(r) - 'A']++;
        //     maxF = Math.max(maxF, hash[s.charAt(r) - 'A']);
        //     while (r - l + 1 - maxF > k) {
        //         hash[s.charAt(l) - 'A']--;
        //         l++;
        //     }
        //     maxLen = Math.max(maxLen, r - l + 1);
        //     r++;
        // }
        // return maxLen;

        // Optimal: TC=>O(n) && SC=>O(26)
        int l = 0, r = 0, maxLen = 0, maxF = 0;
        int[] hash = new int[26];
        while (r < s.length()) {
            hash[s.charAt(r) - 'A']++;
            maxF = Math.max(maxF, hash[s.charAt(r) - 'A']);
            int changes = r - l + 1 - maxF;
            if (changes <= k) {
                maxLen = Math.max(maxLen, r - l + 1);
            } else {
                hash[s.charAt(l) - 'A']--;
                l++;
            }
            r++;
        }
        return maxLen;
    }
}