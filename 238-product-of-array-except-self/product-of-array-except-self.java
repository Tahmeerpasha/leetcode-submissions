class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] prefix = new int[n];
        int[] suffix = new int[n];
        int[] answer = new int[n];
        int product = 1;
        for (int i = 0; i < n; i++) {
            product *= nums[i];
            prefix[i] = product;
        }
        product = 1;
        for (int i = n - 1; i >= 0; i--) {
            product *= nums[i];
            suffix[i] = product;
        }
        answer[0] = suffix[1];
        answer[n - 1] = prefix[n - 2];
        for (int i = 1; i < n - 1; i++) {
            answer[i] = suffix[i + 1] * prefix[i - 1];
        }
        return answer;
    }
}