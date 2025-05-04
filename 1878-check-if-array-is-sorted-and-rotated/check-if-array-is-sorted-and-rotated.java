class Solution {
    public boolean check(int[] nums) {
        int breakPoint = 0;
        int n = nums.length;

        if(nums[n-1] > nums[0]) breakPoint++;

        for(int i=0; i<n-1; i++){
            if(nums[i] > nums[i+1])
                breakPoint++;
        }
       return !(breakPoint>1);
    }
}