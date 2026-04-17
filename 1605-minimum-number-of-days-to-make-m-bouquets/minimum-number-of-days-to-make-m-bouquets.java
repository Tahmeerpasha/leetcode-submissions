class Solution {
    public int minDays(int[] bloomDay, int m, int k) {
        if (bloomDay.length < m * k)
            return -1;
        int low = findMin(bloomDay), high = findMax(bloomDay);
        int answer = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (isBouquetFormed(bloomDay, mid, m, k)) {
                answer = mid;
                high = mid - 1;
            } else
                low = mid + 1;
        }
        return answer;
    }

    boolean isBouquetFormed(int[] bloomDay, int day, int m, int k) {
        int cnt = 0;
        for (int i = 0; i < bloomDay.length; i++) {
            if (bloomDay[i] <= day) {
                cnt++;
            } else {
                cnt = 0;
            }
            if (cnt == k) {
                cnt = 0;
                m--;
            }

        }
        return m <= 0;
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