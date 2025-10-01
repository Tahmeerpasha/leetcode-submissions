class Solution {
    public int lengthOfLongestSubstring(String s) {
        int l = 0, r = 0, maxLen = 0;
        int[] map = new int[256];
        for (int i = 0; i < 256; i++)
            map[i] = -1;
        while (r < s.length()) {
            if (map[s.charAt(r)] != -1 && map[s.charAt(r)] >= l) {
                l = map[s.charAt(r)] + 1;
            }
            maxLen = Math.max(maxLen, r - l + 1);
            map[s.charAt(r)] = r;
            r++;
        }
        return maxLen;
    }
}