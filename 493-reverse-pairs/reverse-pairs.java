class Solution {
    public int reversePairs(int[] nums) {
        // int count = 0;
        // Brute -> Time => O(n^2) && Space => O(1)
        // for(int i = 0; i<nums.length; i++){
        //     for(int j=i+1; j<nums.length; j++){
        //         if((long) nums[i] >(long) 2*nums[j])
        //             count++;
        //     }
        // }      
        // return count++;

        // Optimal -> Time => O(2nlogn) && Space => O(1)
        return mergeSort(nums, 0, nums.length-1);  
    }

    int mergeSort(int[] a, int low, int high){
        int count = 0;
        if(low>=high)return count;
        int mid = (low + high) / 2;
        count += mergeSort(a, low, mid);
        count += mergeSort(a, mid+1, high);
        count += countPairs(a, low, mid, high);
        merge(a, low, mid, high);
        return count;
    }

    int countPairs(int[] a, int low, int mid, int high){
        int right = mid+1;
        int count = 0;
        for(int i=low; i<=mid; i++){
            while(right <= high && (long) a[i] > (long) 2*a[right])right++;
            count += right - (mid + 1);
        }
        return count;
    }
    int merge(int[] a, int low, int mid, int high){
        int count = 0;
        int left = low, right = mid+1;
        int dummy[] = new int[high-low+1];
        int k = 0;
        while(left <= mid && right <= high){
            if(a[left] <= a[right])
                dummy[k++] = a[left++];
            else{
                if(a[right] > 2*a[left])
                    count += 1;
                dummy[k++] = a[right++];
            }
        }

        while(left <= mid)
            dummy[k++] = a[left++];
        
        while(right <= high)
            dummy[k++] = a[right++];

        for(int i=low; i<=high; i++){
            a[i] = dummy[i-low];
        }

        return count;
    }
}