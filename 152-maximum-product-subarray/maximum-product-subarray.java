class Solution {
    public int maxProduct(int[] nums) {
        int max = Integer.MIN_VALUE;
        // Brute -> Time = O(n^2) && Space = O(1)
        // for(int i=0; i<nums.length; i++){
        //     int product = 1;
        //     for(int j=i; j<nums.length; j++){
        //         product *= nums[j];
        //         max = Math.max(max, product);
        //     }
        // }

        // Optimal -> Time = O(n) && Space = O(1)
        int prefix=1, suffix=1;
        for(int i=0; i<nums.length; i++){
            if(prefix == 0) prefix = 1;
            if(suffix == 0) suffix = 1;

            prefix *= nums[i];
            suffix *= nums[nums.length-i-1];

            max = Math.max(max, Math.max(prefix, suffix));
        }
        return max;
    }
}