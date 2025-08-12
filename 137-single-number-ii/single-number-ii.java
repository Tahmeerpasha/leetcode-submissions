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

        // Optimal - Time=>O(nlogn + n/3) && Space => O(1)
        // Arrays.sort(nums);
        // for (int i = 1; i < nums.length; i += 3) {
        //     if (nums[i - 1] != nums[i])
        //         return nums[i - 1];
        // }
        // return nums[nums.length - 1];

        // Optimal 2 (Unintuitive so it's ok if you cannot come up with it in interview)
        // Time -> O(N) && Space -> O(1)
        int ones = 0, twos = 0;
        for (int i = 0; i < nums.length; i++) {
            ones = (nums[i] ^ ones) & ~twos;
            twos = (nums[i] ^ twos) & ~ones;
        }
        return ones;
    }
}