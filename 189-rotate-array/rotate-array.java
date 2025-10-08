class Solution {
    public void rotate(int[] nums, int k) {

        // Brute : Time => O(n * k) && Space => O(1)
        // for (int i = 1; i <= k; i++) {
        //     shiftRight(nums);
        // }
        // Optimal: Time => O(n) && Space => O(1)
        int n = nums.length;
        if (n <= 1)
            return;
        k = k % n;
        reverse(nums, 0, n - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);
    }

    void shiftRight(int[] nums) {
        int temp = nums[nums.length - 1];
        for (int i = nums.length - 2; i >= 0; i--) {
            nums[i + 1] = nums[i];
        }
        nums[0] = temp;
    }

    void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}

// [1,2,3,4,5,6,7] - reverse the entire array
// [7,6,5,4,3,2,1] - reverse 0 to k-1
// [5,6,7,4,3,2,1] - reverse k to n-1

// [5,6,7,1,2,3,4]