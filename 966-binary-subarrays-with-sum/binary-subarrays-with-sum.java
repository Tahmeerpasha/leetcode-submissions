class Solution {
    public int numSubarraysWithSum(int[] nums, int goal) {

        // Brute: TC=> O(n^2) && SC => O(1)
        // int count = 0;
        // for (int i = 0; i < nums.length; i++) {
        //     int sum = 0;
        //     for (int j = i; j < nums.length; j++) {
        //         sum += nums[j];
        //         if (sum == goal) {
        //             count++;
        //         } else if (sum > goal)
        //             break;
        //     }
        // }
        // return count;

        // Optimal: TC => O(2* 2n) && SC => O(1)
        return func(nums, goal) - func(nums, goal - 1);

    }

    // TC - O(2N) && SC - O(1)
    int func(int[] nums, int goal) {
        if (goal < 0)
            return 0;
        int l = 0, r = 0, count = 0, sum = 0;
        while (r < nums.length) {
            sum += nums[r];
            while (sum > goal) {
                sum -= nums[l];
                l++;
            }
            count += r - l + 1;
            r++;
        }
        return count;
    }
}