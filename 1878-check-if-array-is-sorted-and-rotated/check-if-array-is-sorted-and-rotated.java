class Solution {
    public boolean check(int[] nums) {
        int sorted = 0;
        if (nums[nums.length - 1] > nums[0])
            sorted++;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1])
                sorted++;
        }
        return sorted <= 1;
    }
}