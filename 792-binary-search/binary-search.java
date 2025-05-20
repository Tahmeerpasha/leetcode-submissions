class Solution {
    public int search(int[] nums, int target) {
        return binarySearch(nums, target, 0, nums.length - 1);
    }

    int binarySearch(int[] nums, int target, int start, int end) {
        int mid = (end + (end - start + 1)) / 2;
        if (start > end)
            return -1;
        if (nums[mid] == target)
            return mid;
        if (nums[mid] > target)
            return binarySearch(nums, target, 0, mid - 1);
        else
            return binarySearch(nums, target, mid + 1, end);
    }
}