class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int low = 0, high = max(piles);
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int totalH = calTotalHrs(piles, mid);
            if (totalH <= h)
                high = mid - 1;
            else
                low = mid + 1;
        }
        return low;
    }

    int calTotalHrs(int[] piles, int hr) {
        int total = 0;
        for (int val : piles)
            total += Math.ceil((double) val / (double) hr);
        return total;
    }

    int max(int[] piles) {
        int max = Integer.MIN_VALUE;
        for (int val : piles)
            max = Math.max(max, val);
        return max;
    }
}