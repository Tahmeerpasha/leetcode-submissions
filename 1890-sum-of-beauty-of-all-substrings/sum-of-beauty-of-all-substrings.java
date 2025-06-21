class Solution {
    public int beautySum(String s) {
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            int[] freq = new int[26];
            for (int j = i; j < s.length(); j++) {
                freq[s.charAt(j) - 'a']++;
                int beauty = getMaxCount(freq) - getMinCount(freq);
                result += beauty;
            }
        }
        return result;
    }

    int getMaxCount(int[] freq) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < freq.length; i++) {
            max = Math.max(max, freq[i]);
        }
        return max;
    }

    int getMinCount(int[] freq) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < freq.length; i++) {
            if (freq[i] != 0)
                min = Math.min(min, freq[i]);
        }
        return min;
    }
}