class Solution {
    public int numberOfSubarrays(int[] nums, int k) {
        return atMost(nums, k) - atMost(nums, k - 1);
    }

    // TC - O(2N) && SC - O(1)
    int atMost(int[] nums, int goal) {
        if (goal < 0)
            return 0;
        int l = 0, r = 0, count = 0, sum = 0;
        while (r < nums.length) {
            sum += nums[r] % 2;
            while (sum > goal) {
                sum -= nums[l] % 2;
                l++;
            }
            count += r - l + 1;
            r++;
        }
        return count;
    }
}