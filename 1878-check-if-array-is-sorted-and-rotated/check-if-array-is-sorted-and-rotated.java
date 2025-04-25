class Solution {
    public boolean check(int[] nums) {
        int n = nums.length;
        int once = 0;
        for(int i=1; i<n; i++){
            if(nums[i] < nums[i-1]) once++;
        }
        if(nums[0] < nums[n-1])once++;
        return once <= 1 ? true : false;
    }
}