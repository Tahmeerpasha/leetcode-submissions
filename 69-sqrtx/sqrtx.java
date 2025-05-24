class Solution {
    public int mySqrt(int x) {
        int ans = 0;
        // Brute => Time -> O(n)
        // for(int i=1; i<=x; i++){
        //     if((long) i*i <= x)ans = i;
        //     else break;
        // }
        
        // Optimal - Using binary search
        int low = 1, high = x;
        while(low <= high){
            int mid = low + (high-low)/2;
            if((long)mid * mid <= x){
                ans = mid;
                low = mid+1;
            }else high = mid-1;
        }
        return ans;
    }
}