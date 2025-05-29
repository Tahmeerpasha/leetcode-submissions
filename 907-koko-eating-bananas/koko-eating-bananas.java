class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int low = 1, high = max(piles);
        while (low <= high) {
            int mid = (low + high) / 2;
            int totalH = findTotal(piles, mid);
            if (totalH <= h)
                high = mid - 1;
            else
                low = mid + 1;
        }
        return low;
    }

    int findTotal(int[] nums, int hour) {
        int total = 0;
        for (int num : nums)
            total += Math.ceil((double) num / (double) hour);
        return total;
    }

    int max(int[] nums) {
        int max = Integer.MIN_VALUE;
        for (int num : nums)
            max = Math.max(max, num);
        return max;
    }
}