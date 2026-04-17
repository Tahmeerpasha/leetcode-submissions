class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int low = 1, high = findMax(piles);
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (canFinishInTime(piles, mid, h))
                high = mid - 1;
            else
                low = mid + 1;
        }
        return low;
    }

    boolean canFinishInTime(int[] piles, int t, int h) {
        int totalH = 0;
        for (int num : piles) {
            totalH += Math.ceil((double) num / (double) t);
        }
        return totalH <= h;
    }

    int findMax(int[] nums) {
        int max = Integer.MIN_VALUE;
        for (int num : nums)
            max = Math.max(max, num);
        return max;
    }
}