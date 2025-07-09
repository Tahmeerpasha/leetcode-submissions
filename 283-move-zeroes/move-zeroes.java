class Solution {
    public void moveZeroes(int[] nums) {
        int zeros = 0, temp = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                temp = nums[i];
                nums[i] = nums[zeros];
                nums[zeros] = temp;
                zeros++;
            }
        }
    }
}