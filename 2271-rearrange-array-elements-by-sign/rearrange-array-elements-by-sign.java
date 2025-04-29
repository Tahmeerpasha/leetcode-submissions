class Solution {
    public int[] rearrangeArray(int[] nums) {
    //     int positive=0, negative=1, normal=0;
    //     if(nums.length < 2)return nums;
    //     while(normal < nums.length){
    //         if(nums[normal] >= 0 && normal != positive && positive < nums.length && nums[positive] < 0){
    //             swap(nums, normal, positive);
    //             positive += 2; 
    //         } else if (positive < nums.length && nums[positive] >= 0) positive+=2;
    //         else if(nums[normal] < 0 && normal != negative && negative < nums.length && nums[negative] >= 0){
    //             swap(nums, normal, negative);
    //             negative += 2;
    //         } else if(negative < nums.length && nums[negative] < 0 )negative += 2;
    //         normal++;
    //     }
    //     return nums;
    // }

    // void swap(int[] nums, int start, int end){
    //     int temp = nums[start];
    //     nums[start] = nums[end];
    //     nums[end] = temp;
    // }
    int n = nums.length;
    int[] positive = new int[n/2];
    int[] negative = new int[n/2];
    int[] result = new int[n];
    int j=0,k=0;
    for(int i = 0; i < n; i++){
        if(nums[i] >= 0)
            positive[j++] = nums[i];
        else negative[k++] = nums[i];    
    }
    k=0;
    j=0;
    for(int i=0; i<n; i++){
        if(i%2 == 0)
            result[i] = positive[j++];
        else 
            result[i] = negative[k++];
    }
    return result;
    }
}