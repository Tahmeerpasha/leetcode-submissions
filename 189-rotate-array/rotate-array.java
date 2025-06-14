class Solution {
    public void rotate(int[] nums, int k) {
        if(nums.length < 2)return;
        k =  k % nums.length;
        reverse(nums, 0, nums.length-1);
        reverse(nums, 0, k-1);
        reverse(nums, k, nums.length-1);
    }

    void reverse(int[] nums, int from, int to){
        while(from <= to){
            int temp = nums[from];
            nums[from] = nums[to];
            nums[to] = temp;
            from++;
            to--;
        }
    }
}