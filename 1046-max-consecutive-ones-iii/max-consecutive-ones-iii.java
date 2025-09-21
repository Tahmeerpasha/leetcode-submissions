class Solution {
    public int longestOnes(int[] nums, int k) {
        int l = 0, r = 0, max = 0, zeroCount = 0;
        while (r < nums.length) {
            if (nums[r] == 0)
                zeroCount++;
            if (zeroCount > k) {
                if (nums[l] == 0)
                    zeroCount--;
                l++;
            }
            if (zeroCount <= k)
                max = Math.max(max, r - l + 1);
            r++;
        }
        return max;
    }
}