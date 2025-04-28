class Solution {
    public void sortColors(int[] nums) {
        int low=0, mid=0, high = nums.length-1;
        while(mid<=high){
            if(nums[mid] == 0) swap(nums, low++, mid++);
            else if (nums[mid] == 2) swap(nums, mid, high--);
            else mid++;
        }
    }

    void swap(int[] nums, int fromIndex, int toIndex){
        if(fromIndex>toIndex) return;
        int temp = nums[fromIndex];
        nums[fromIndex] = nums[toIndex];
        nums[toIndex] = temp;
    }
}