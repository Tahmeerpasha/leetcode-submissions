class Solution {
    public int removeDuplicates(int[] nums) {
        int j=0;
        for(int i = 0; i< nums.length; i++){
            if(nums[j] != nums[i]){
                j++;
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }
        return j+1;
    }
}