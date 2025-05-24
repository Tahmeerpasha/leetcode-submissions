class Solution {
    public int mySqrt(int x) {
        // Brute => Time -> O(n)
        int ans = 0;
        for(int i=1; i<=x; i++){
            if((long) i*i <= x)ans = i;
            else break;
        }
        return ans;
    }
}