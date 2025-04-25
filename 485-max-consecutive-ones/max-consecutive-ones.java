class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int max=0, ones=0;
        for(int num: nums){
            if(num == 1)
                ++ones;
            else 
                ones = 0;
            if(max<ones)
                max = ones;
        }
        return max;
    }
}