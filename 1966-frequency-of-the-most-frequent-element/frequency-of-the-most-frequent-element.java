class Solution {
    public int maxFrequency(int[] nums, int k) {
        int frequency = 1, n = nums.length, left = 0, right = 0;
        long total = 0;
        Arrays.sort(nums);
        while(right < n){
            total += nums[right];
            while((long) nums[right] * (right-left+1) > total + k){
                total -= nums[left];
                left++;
            }
            frequency = Math.max(frequency,(right-left+1));
            right++;
        }
        return frequency;
    }
}