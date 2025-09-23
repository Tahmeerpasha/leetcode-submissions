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

        // Optimal: TC => O(N) && SC => O(1)
        int[] lastSeen = new int[] { -1, -1, -1 };
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            lastSeen[s.charAt(i) - 'a'] = i;
            if (lastSeen[0] != -1 && lastSeen[1] != -1 && lastSeen[2] != -1)
                count += 1 + Math.min(Math.min(lastSeen[0], lastSeen[1]), lastSeen[2]);
        }
        return count;
    }
}