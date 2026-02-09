class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int cnt = 0, max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 1)
                cnt = 0;
            else
                cnt++;
            max = Math.max(cnt, max);
        }
        return max;
    }
}