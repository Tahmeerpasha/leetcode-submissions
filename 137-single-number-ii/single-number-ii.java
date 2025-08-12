class Solution {
    public int singleNumber(int[] nums) {
        // Brute - Using map
        // Better 
        // int ans = 0;
        // for (int bitIndex = 0; bitIndex <= 31; bitIndex++) {
        //     int count = 0;
        //     for (int i = 0; i < nums.length; i++) {
        //         if ((nums[i] & (1 << bitIndex)) != 0)
        //             count++;
        //     }
        //     if (count % 3 == 1)
        //         ans = (ans | (1 << bitIndex));
        // }
        // return ans;

        // Optimal
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i += 3) {
            if (nums[i - 1] != nums[i])
                return nums[i - 1];
        }
        return nums[nums.length - 1];
    }
}