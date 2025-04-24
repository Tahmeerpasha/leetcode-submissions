class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] answer = new int[n];
        // // brute force O(n^2)
        // for(int i=0; i < nums.length; i++){
        //     int product = 1;
        //     for(int j=0; j < nums.length; j++){
        //         if(j != i){
        //             product *= nums[j];
        //         }
        //     }
        //     answer[i] = product;
        // }
        // return answer;

        // Another approach O(n) would be to multiple the entire array and divide by nums[i] to get answer[i] but it is not permissible according to the question.

        //    Better approach || Space -> O(n) || Time -> O(n)
        // Need to optimise for O(1) space
            // int[] prefix = new int[n];
            // int[] suffix = new int[n];
            // for(int i = 0 ; i < n ; i++){
            //     prefix[i] = 1;
            //     suffix[i] = 1;
            // }
            // for (int i = 1; i < n; i++) {
            //     prefix[i] = prefix[i - 1] * nums[i-1];
            // }
            // for (int i = n - 2; i >= 0; i--) {
            //     suffix[i] = suffix[i + 1] * nums[i+1];
            // }
            // for(int i=0; i < n ; i++){
            //     answer[i] = prefix[i] * suffix[i];
            // }
            // return answer;

            // Optimised approach || Space -> O(1) || Time -> O(n)
            int suffix = 1;
            for(int i = 0 ; i < n ; i++){
                answer[i] = 1;
            }
            for (int i = 1; i < n; i++) {
                answer[i] = answer[i - 1] * nums[i-1];
            }
            for (int i = n - 2; i >= 0; i--) {
                suffix *= nums[i+1];
                answer[i] *= suffix;
            }
            return answer;
    }
}