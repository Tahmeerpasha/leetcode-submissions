class Solution {
    public int[] singleNumber(int[] nums) {
        // Brute - Using hashmaps
        // Optimal - Time => O(2N) && Space -> O(1)
        int b1 = 0, b2 = 0;
        long xor = 0;
        for (int num : nums)
            xor ^= num;
        int rightmost = (int) ((xor & xor - 1) ^ xor);
        for (int num : nums) {
            if ((num & rightmost) != 0)
                b1 ^= num;
            else
                b2 ^= num;
        }
        return new int[] { b1, b2 };
    }
}