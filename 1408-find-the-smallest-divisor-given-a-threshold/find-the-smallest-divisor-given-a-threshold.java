class Solution {
    public int smallestDivisor(int[] nums, int threshold) {
        int low = 1, high = findMax(nums);
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (isSmallDivisor(nums, threshold, mid))
                high = mid - 1;
            else
                low = mid + 1;
        }
        return low;
    }

    boolean isSmallDivisor(int[] nums, int threshold, int div) {
        int sum = 0;
        for (int num : nums) {
            sum += (int) Math.ceil((double) num / (double) div);
        }
        return sum <= threshold;
    }

    int findMax(int[] nums) {
        int max = Integer.MIN_VALUE;
        for (int num : nums)
            max = Math.max(max, num);
        return max;
    }
}