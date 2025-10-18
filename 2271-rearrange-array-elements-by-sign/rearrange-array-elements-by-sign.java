class Solution {
    public int[] rearrangeArray(int[] nums) {
        // Time -> O(n) and Space -> O(n)
        // int n = nums.length;
        // int[] positive = new int[n/2];
        // int[] negative = new int[n/2];
        // int[] result = new int[n];
        // int j=0,k=0;
        // for(int i = 0; i < n; i++){
        //     if(nums[i] >= 0)
        //         positive[j++] = nums[i];
        //     else negative[k++] = nums[i];    
        // }
        // k=0;
        // j=0;
        // for(int i=0; i<n; i++){
        //     if(i%2 == 0)
        //         result[i] = positive[j++];
        //     else 
        //         result[i] = negative[k++];
        // }
        // return result;
        // }

        // Time -> O(n) and Space -> O(n)
        int n = nums.length;
        int[] result = new int[n];
        int pos = 0, neg = 1;
        for (int i = 0; i < n; i++) {
            if (nums[i] < 0) {
                result[neg] = nums[i];
                neg += 2;
            } else {
                result[pos] = nums[i];
                pos += 2;
            }
        }
        return result;
    }
}