class Solution {
    public int numberOfSubstrings(String s) {
        // Brute: TC => O(n^2) && SC => O(1)
        // int count = 0;
        // for (int i = 0; i < s.length(); i++) {
        //     int a = 0, b = 0, c = 0;
        //     for (int j = i; j < s.length(); j++) {
        //         char ch = s.charAt(j);
        //         if (ch == 'a')
        //             a++;
        //         if (ch == 'b')
        //             b++;
        //         if (ch == 'c')
        //             c++;
        //         if (a >= 1 && b >= 1 && c >= 1) {
        //             count += s.length() - j;
        //             break;
        //         }
        //     }
        // }
        // return count;

        // Better: TC => O(N) && SC => O(1)
        int l = 0, r = 0, maxCount = 0;
        int[] count = new int[3]; // counts for 'a', 'b', 'c'
        while (r < s.length()) {
            count[s.charAt(r) - 'a']++;
            // shrink window from left while all characters are present
            while (count[0] > 0 && count[1] > 0 && count[2] > 0) {
                count[s.charAt(l) - 'a']--;
                l++;
            }
            // all substrings ending at 'right' and starting before 'left' are valid
            maxCount += l;
            r++;
        }
        return maxCount;

        // Optimal: TC => O(N) && SC => O(1)
        // int[] lastSeen = new int[] { -1, -1, -1 };
        // int count = 0;
        // for (int i = 0; i < s.length(); i++) {
        //     lastSeen[s.charAt(i) - 'a'] = i;
        //     if (lastSeen[0] != -1 && lastSeen[1] != -1 && lastSeen[2] != -1)
        //         count += 1 + Math.min(Math.min(lastSeen[0], lastSeen[1]), lastSeen[2]);
        // }
        // return count;
    }
}