class Solution {
    public String longestCommonPrefix(String[] strs) {
        // Brute => Time -> O(n*m) && Space -> O(1)
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

        // Optimal approach => Time -> O(n log(m+n)) && Space -> O(1)
        if (strs.length == 0)
            return "";
        if (strs.length == 1)
            return strs[0];

        Arrays.sort(strs);
        String first = strs[0];
        String last = strs[strs.length - 1];

        int i = 0;
        while (i < first.length() && i < last.length() && first.charAt(i) == last.charAt(i)) {
            i++;
        }
        return first.substring(0, i);
    }
}