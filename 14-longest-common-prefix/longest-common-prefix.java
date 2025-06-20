class Solution {
    public String longestCommonPrefix(String[] strs) {
        // Brute => Time -> O(n^2) && Space -> O(1)
        // String s = strs[0];
        // int totalCnt = 0;
        // for (int i = 0; i < s.length(); i++) {
        //     int cnt = 1;
        //     for (int j = 1; j < strs.length; j++) {
        //         if (strs[j].length() > i && strs[j].charAt(i) == s.charAt(i))
        //             cnt++;
        //         else {
        //             cnt = -1;
        //             break;
        //         }
        //     }
        //     if (cnt == -1)
        //         break;
        //     if (cnt == strs.length)
        //         totalCnt++;
        // }

        // Optimal approach => Time -> O(nlogn) && Space -> O(1)
        if (strs.length < 2)
            return strs[0];
        Arrays.sort(strs);
        String str1 = strs[0];
        String str2 = strs[strs.length - 1];
        int i = 0;
        if (str1.equals(str2))
            return str1;
        int min = Math.min(str1.length(), str2.length());
        while (i < min && str1.charAt(i) == str2.charAt(i)) {
            i++;
        }
        return str1.substring(0, i);
    }
}