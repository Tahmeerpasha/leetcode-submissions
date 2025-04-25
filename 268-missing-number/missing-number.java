class Solution {
    public int missingNumber(int[] nums) {
        int sum = 0, n = nums.length;
        for(int value: nums){
            sum += value;
        }
        return (n * (n+1))/2 - sum;
    }
}