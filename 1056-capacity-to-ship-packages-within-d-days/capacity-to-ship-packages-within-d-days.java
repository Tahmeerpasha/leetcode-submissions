class Solution {
    public int shipWithinDays(int[] weights, int days) {
        int low = max(weights), high = sumOfAllWeights(weights);
        // for(int i=low; i<=high; i++){
        //     if(canShipAllWithCapacity(weights,i,days))return i;
        // }

        while(low <= high){
            int mid = (low+high)/2;
            if(canShipAllWithCapacity(weights,mid,days))high = mid-1;
            else low = mid+1;
        }
        return low;
    }

    int max(int[] weights){
        int max = Integer.MIN_VALUE;
        for(int num: weights){
            max = Math.max(max, num);
        }
        return max;
    }

    int sumOfAllWeights(int[] weights){
        int sum = 0;
        for(int num: weights)
            sum += num;
        return sum;
    }
    boolean canShipAllWithCapacity(int[] weights, int capacity, int days){
        int sum=0, totalDays=1;
        for(int num:weights){
            sum += num;
            if(sum > capacity){
                totalDays++;
                sum=num;
            }
        }
        // if(sum > capacity)totalDays++;
        return totalDays <= days;
    }
}