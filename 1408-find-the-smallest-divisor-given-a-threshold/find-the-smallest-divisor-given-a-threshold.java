class Solution {
    public int smallestDivisor(int[] nums, int threshold) {
        int low = 1, high = max(nums);
        // Brute => Time=O(N * (high-low+1)) && Space = O(1)
        // for(int i=low; i<=high; i++){
        //     int sum = sumOfDivision(nums, i);
        //     if(sum <= threshold)return i;
        // }

        // Optimal 
        int ans = high;
        while(low <= high){
            int mid = (low+high)/2;
            int sum = sumOfDivision(nums, mid);
            if(sum <= threshold){
                ans = mid;
                high = mid-1;
            }
            else low = mid+1;
        }
        return ans;
    }
    int max(int nums[]){
        int max = Integer.MIN_VALUE;
        for(int num:nums){
            max = Math.max(max, num);
        }
        return max;
    }
    int sumOfDivision(int[] nums, int divisor){
        int sum=0;
        for(int num:nums){
            sum += Math.ceil((double) num/ (double)divisor);
        }
        return sum;
    }
}