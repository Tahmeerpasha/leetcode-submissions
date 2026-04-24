class Solution {
    public int maxProduct(int[] nums) {
        int n = nums.length;
        if (n == 1)
            return nums[0];
        int[] prefix = new int[n];
        int product = 1;
        for (int i = 0; i < n; i++) {
            if (product == 0)
                product = 1;
            product *= nums[i];
            prefix[i] = product;
        }
        int[] suffix = new int[n];
        product = 1;
        for (int i = n - 1; i >= 0; i--) {
            if (product == 0)
                product = 1;
            product *= nums[i];
            suffix[i] = product;
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, Math.max(prefix[i], suffix[i]));
        }
        return max;
    }
}
/**
[2,3,-2,4]
[2, 6, -12, -24] = prefix
[-48,-24,-8,4] = suffix
[2, 6, -8, 4] = ans
max = 6
 */