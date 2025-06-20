class Solution {
    public String longestCommonPrefix(String[] strs) {
        String s = strs[0];
        int totalCnt = 0;
        for (int i = 0; i < s.length(); i++) {
            int cnt = 1;
            for (int j = 1; j < strs.length; j++) {
                if (strs[j].length() > i && strs[j].charAt(i) == s.charAt(i))
                    cnt++;
                else {
                    cnt = -1;
                    break;
                }
            }
            if(cnt == -1)break;
            if (cnt == strs.length)
                totalCnt++;
        }
        return s.substring(0, totalCnt);
    }
}