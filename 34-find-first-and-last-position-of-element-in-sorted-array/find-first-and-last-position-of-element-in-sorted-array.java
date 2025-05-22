class Solution {
    public int[] searchRange(int[] nums, int target) {
        int n = nums.length;
        int low = 0, high = n-1;
        int first=-1, last=-1;
        while(low <= high){
            int mid = (low+high)/2;
            if(nums[mid] == target){
                int j=mid;
                while(j>=0 && nums[j] == target)j--;
                int k=mid;
                while(k <= n-1 && nums[k] == target)k++;
                first = j+1;
                last = k-1;
            }
            if(nums[mid] < target){
                low = mid+1;
            }else high = mid-1;
        }
        return new int[]{first, last};
    }
}