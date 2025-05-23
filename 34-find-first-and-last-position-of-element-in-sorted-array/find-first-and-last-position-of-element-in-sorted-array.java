class Solution {
    public int[] searchRange(int[] nums, int target) {
        int n = nums.length;
        int low = 0, high = n-1;
        int first=-1, last=-1;
        // Not optimal - Using loops. Time -> more than O(nlogn)
        // while(low <= high){
        //     int mid = (low+high)/2;
        //     if(nums[mid] == target){
        //         int j=mid;
        //         while(j>=0 && nums[j] == target)j--;
        //         int k=mid;
        //         while(k <= n-1 && nums[k] == target)k++;
        //         first = j+1;
        //         last = k-1;
        //     }
        //     if(nums[mid] < target){
        //         low = mid+1;
        //     }else high = mid-1;
        // }
        // return new int[]{first, last};

        // Using upper and lower bounds => Time -> O(nlogn)
        // int lower = lowerBound(nums, nums.length, target);
        // if(lower == nums.length || nums[lower] != target)return new int[]{-1,-1};
        // int upper = upperBound(nums, nums.length, target);
        // return new int[]{lower, upper-1};

        // Simple binary search for first and last
        // For first occurrence
        while(low <= high){
            int mid = (low+high)/2;
            if(nums[mid] == target){
                first = mid;
                high = mid-1;
            }else if(nums[mid] > target) high = mid-1;
            else low = mid+1;
        }
        low = 0;
        high = n-1;
        // For last occurrence
        while(low <= high){
            int mid = (low+high)/2;
            if(nums[mid] == target){
                last = mid;
                low = mid+1;
            }else if(nums[mid] > target) high = mid-1;
            else low = mid+1;
        }
        return new int[]{first, last};
    }

    public int lowerBound(int []arr, int n, int x) {
        int ans = n;
        int low = 0, high = n-1;
        while(low <= high){
            int mid = (low + high)/2;
            if(arr[mid] >= x){
                ans = mid;
                high = mid-1;
            }else low = mid+1;
        }
        return ans;
    }

    public int upperBound(int []arr, int n, int x) {
        int ans = n;
        int low = 0, high = n-1;
        while(low <= high){
            int mid = (low + high)/2;
            if(arr[mid] > x){
                ans = mid;
                high = mid-1;
            }else low = mid+1;
        }
        return ans;
    }
}