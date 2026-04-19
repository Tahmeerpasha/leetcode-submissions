class Solution {
    public int splitArray(int[] nums, int k) {
        int low = findMax(nums), high = findSum(nums);
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (canSplitArray(nums, k, mid))
                high = mid - 1;
            else
                low = mid + 1;
        }
        return low;
    }

    boolean canSplitArray(int[] nums, int k, int limit) {
        int cnt = 1, sum = 0;
        for (int num : nums) {
            sum += num;
            if (sum > limit) {
                cnt++;
                sum = num;
            }
        }
        return cnt <= k;
    }

    int findMax(int[] nums) {
        int max = Integer.MIN_VALUE;
        for (int num : nums)
            max = Math.max(max, num);
        return max;
    }

    int findSum(int[] nums) {
        int sum = 0;
        for (int num : nums)
            sum += num;
        return sum;
    }
}