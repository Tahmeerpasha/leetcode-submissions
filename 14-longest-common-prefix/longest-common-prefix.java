class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 1) {
            return strs[0];
        }
        Arrays.sort(strs);
        int size1 = strs[0].length();
        int size2 = strs[strs.length - 1].length();
        int size = Math.min(size1, size2);
        for (int i = 0; i < size; i++) {
            if (strs[0].charAt(i) != strs[strs.length - 1].charAt(i)) {
                return strs[0].substring(0, i);
            }
        }
        return strs[0].substring(0, size);
    }
}