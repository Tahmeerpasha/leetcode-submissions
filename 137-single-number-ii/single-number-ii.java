class Solution {
    public int singleNumber(int[] nums) {
        int ans = 0;
        for (int bitIndex = 0; bitIndex <= 31; bitIndex++) {
            int count = 0;
            for (int i = 0; i < nums.length; i++) {
                if ((nums[i] & (1 << bitIndex)) != 0)
                    count++;
            }
            if (count % 3 == 1)
                ans = (ans | (1 << bitIndex));
        }
        return ans;
    }
}