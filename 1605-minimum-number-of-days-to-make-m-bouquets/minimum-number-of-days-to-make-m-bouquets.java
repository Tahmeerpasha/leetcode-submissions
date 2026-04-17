class Solution {
    public int minDays(int[] bloomDay, int m, int k) {
        if (bloomDay.length < (long) m * k)
            return -1;
        int low = 1, high = findMax(bloomDay);
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (canMakeBouquet(bloomDay, mid, m, k)) {
                high = mid - 1;
            } else
                low = mid + 1;
        }
        return low;
    }

    boolean canMakeBouquet(int[] bloomDay, int day, int m, int k) {
        int consequetiveFlowers = 0, bouquet = 0;
        for (int i = 0; i < bloomDay.length; i++) {
            if (bloomDay[i] <= day) {
                consequetiveFlowers++;
            } else {
                consequetiveFlowers = 0;
            }
            if (consequetiveFlowers == k) {
                consequetiveFlowers = 0;
                bouquet++;
            }

        }
        return bouquet >= m;
    }

    int findMin(int[] bloomDay) {
        int min = Integer.MAX_VALUE;
        for (int num : bloomDay)
            min = Math.min(min, num);
        return min;
    }

    int findMax(int[] bloomDay) {
        int max = Integer.MIN_VALUE;
        for (int num : bloomDay)
            max = Math.max(max, num);
        return max;
    }
}