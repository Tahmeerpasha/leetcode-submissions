class Solution {
    public int minDays(int[] bloomDay, int m, int k) {
        int n = bloomDay.length;
        int min = min(bloomDay);
        int max = max(bloomDay);
        if ((long) n < (long) m * k)
            return -1;
        // Brute force ->Time => O(n^2) && Space -> O(1)
        // for (int i = min; i <= max; i++) {
        //     if (isPossible(bloomDay, i, m, k))
        //         return i;
        // }

        int low = min, high = max, ans=0;
        while(low <= high){
            int mid = (low+high)/2;
            if(isPossible(bloomDay, mid, m, k)){
                ans = mid;
                high = mid-1;
            }else low = mid+1;
        }
        return ans;
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