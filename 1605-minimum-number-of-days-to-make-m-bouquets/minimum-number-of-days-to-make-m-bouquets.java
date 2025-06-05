class Solution {
    public int minDays(int[] bloomDay, int m, int k) {
        int n = bloomDay.length;
        if ((long) n < (long) m * k)
            return -1;
        int low = min(bloomDay);
        int high = max(bloomDay);

        // Brute force ->Time => O((high-low+1)*N) && Space -> O(1)
        // for (int i = low; i <= high; i++) {
        //     if (isPossible(bloomDay, i, m, k))
        //         return i;
        // }

        // Optimal -> Time => O(N * log(high-low+1))
        while (low <= high) {
            int mid = (low + high) / 2;
            if (isPossible(bloomDay, mid, m, k))
                high = mid - 1;
            else
                low = mid + 1;
        }
        return low;
    }

    int min(int[] bloomDay) {
        int min = Integer.MAX_VALUE;
        for (int num : bloomDay)
            min = Math.min(min, num);
        return min;
    }

    int max(int[] bloomDay) {
        int max = Integer.MIN_VALUE;
        for (int num : bloomDay)
            max = Math.max(max, num);
        return max;
    }

    boolean isPossible(int[] bloomDays, int day, int m, int k) {
        int noOfBouquets = 0, count = 0;
        for (int val : bloomDays) {
            if (val <= day)
                count++;
            else {
                noOfBouquets += count / k;
                count = 0;
            }
        }
        noOfBouquets += count / k;
        return noOfBouquets >= m;
    }
}