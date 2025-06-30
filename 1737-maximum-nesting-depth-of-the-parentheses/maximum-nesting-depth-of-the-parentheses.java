class Solution {
    public int maxDepth(String s) {
        int cnt = 0, max = Integer.MIN_VALUE;
        for (char c : s.toCharArray()) {
            if (c == '(')
                cnt++;
            if (c == ')')
                cnt--;
            max = Math.max(max, cnt);
        }
        return max;
    }
}