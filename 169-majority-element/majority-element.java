class Solution {
    public int majorityElement(int[] nums) {
        int count = 0, val = 0;
        for (int i = 0; i < nums.length; i++) {
            if (count == 0)
                val = nums[i];
            if (nums[i] == val)
                count++;
            else
                count--;
        }
        return val;
    }
}