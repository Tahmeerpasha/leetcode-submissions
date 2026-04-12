class Solution {
    public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE, cnt = 0;
        for (int num : nums) {
            if (cnt < 0)
                cnt = 0;
            cnt += num;
            max = Math.max(max, cnt);
        }
        return max;
    }
}