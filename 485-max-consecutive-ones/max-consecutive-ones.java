class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0, ones = 0;
        for (int num : nums) {
            if (num == 0)
                ones = 0;
            else
                ones++;
            max = Math.max(max, ones);
        }
        return max;
    }
}