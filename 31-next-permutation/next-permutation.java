class Solution {
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        int i = 0;
        for (i = n - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1])
                break;
        }
        if (i < 0) {
            reverse(nums, 0, n - 1);
            return;
        }
        int j = 0;
        for (j = n - 1; j >= 0; j--) {
            if (nums[j] > nums[i])
                break;
        }
        swap(nums, i, j);
        reverse(nums, i + 1, n - 1);
    }

    void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }

    void swap(int[] nums, int start, int end) {
        int temp = nums[start];
        nums[start] = nums[end];
        nums[end] = temp;
    }
}