class Solution {
    public void rotate(int[] nums, int k) {
        if(k > nums.length)
            k = k % nums.length;
        reverseArray(nums,0,nums.length-k-1);
        reverseArray(nums,nums.length-k,nums.length-1);
        reverseArray(nums,0,nums.length-1);
    }

    void reverseArray(int[] nums, int startIndex, int endIndex){
        while(startIndex < endIndex){
            int temp = nums[startIndex];
            nums[startIndex] = nums[endIndex];
            nums[endIndex] = temp;
            startIndex++;
            endIndex--;
        }
    }
}