class Solution {
    public String minWindow(String s, String t) {
        int l = 0, r = 0, count = 0, minLen = Integer.MAX_VALUE;
        int[] hash = new int[256];
        int sIndex = -1;
        for (int i = 0; i < t.length(); i++)
            hash[t.charAt(i)]++;
        while (r < s.length()) {
            char ch = s.charAt(r);
            if (hash[ch] > 0)
                count++;
            hash[ch]--;
            while (count == t.length()) {
                if (r - l + 1 < minLen) {
                    minLen = r - l + 1;
                    sIndex = l;
                }
                hash[s.charAt(l)]++;
                if (hash[s.charAt(l)] > 0)
                    count--;
                l++;
            }
            r++;
        }
        return sIndex == -1 ? new String() : s.substring(sIndex, sIndex + minLen);
    }
}