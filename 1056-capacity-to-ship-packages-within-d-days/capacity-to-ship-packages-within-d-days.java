class Solution {
    public int shipWithinDays(int[] weights, int days) {
        int low = findMax(weights), high = findSum(weights);
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (canShipWithCapacity(weights, days, mid))
                high = mid - 1;
            else
                low = mid + 1;
        }
        return low;
    }

    boolean canShipWithCapacity(int[] weights, int days, int capacity) {
        int cnt = 1, sum = 0;
        for (int num : weights) {
            sum += num;
            if (sum > capacity) {
                sum = num;
                cnt++;
            }
        }
        return cnt <= days;
    }

    int findMax(int[] weights) {
        int max = Integer.MIN_VALUE;
        for (int num : weights)
            max = Math.max(max, num);
        return max;
    }

    int findSum(int[] weights) {
        int sum = 0;
        for (int num : weights)
            sum += num;
        return sum;
    }
}