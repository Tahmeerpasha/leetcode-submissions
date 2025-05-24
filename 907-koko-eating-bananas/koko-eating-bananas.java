class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int low = 1, high = max(piles);
        while(low <= high){
            int mid = (low+high)/2;
            int totalH = calculateTotalHours(piles, mid);
            if(totalH <= h) high = mid-1;
            else low = mid+1;
        }
        return low;
    }
    int calculateTotalHours(int[] arr, int h){
        int total = 0;
        for(int i=0;i<arr.length;i++){
            total += Math.ceil((double)arr[i]/ (double)h);
        }
        return total;
    }
    int max(int[] arr){
        int ans = Integer.MIN_VALUE;
        for(int i=0; i< arr.length; i++){
            ans = Math.max(ans, arr[i]);
        }
        return ans;
    }
}