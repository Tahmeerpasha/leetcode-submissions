class Solution {
    public int longestConsecutive(int[] nums) {
        int count=0, maxLength=0, lastSmaller=Integer.MIN_VALUE;
        Arrays.sort(nums);
        for(int i=0; i<nums.length; i++){
            if(nums[i]-1 == lastSmaller){
                count++;
                lastSmaller = nums[i];
            }else if(nums[i] != lastSmaller){ // if it's equal do nothing
                count = 1;
                lastSmaller = nums[i];
            }
            maxLength = Math.max(maxLength, count);
        }
        return maxLength;
    }
}