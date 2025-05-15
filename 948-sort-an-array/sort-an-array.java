class Solution {
    public int[] sortArray(int[] nums) {
        mergeSort(nums, 0, nums.length-1);
        return nums;
    }

    void mergeSort(int[] nums, int low, int high){
        if(low == high)return;
        int mid = low + (high - low) / 2;
        mergeSort(nums, low, mid);
        mergeSort(nums, mid+1, high);
        merge(nums, low, mid, high);
    }

    void merge(int[] nums, int low, int mid, int high){
        int left = low;
        int right = mid+1;
        int temp[] = new int[high-low+1];
        int i=0;
        while(left <= mid && right <= high){
            if(nums[left] <= nums[right]){
                temp[i] = nums[left];
                left++;
                i++;
            }else{
                temp[i] = nums[right];
                right++;
                i++;
            }
        }

        while(left <= mid){
            temp[i++] = nums[left++];
        }

        while(right <= high){
            temp[i++] = nums[right++];
        }

        for(int j=low; j<=high; j++){
            nums[j] = temp[j-low];
        }
    }
}