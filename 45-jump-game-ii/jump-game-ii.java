class Solution {
    public int jump(int[] nums) {
        int l = 0, r = 0, jumps = 0;
        while (r < nums.length - 1) {
            int farthest = Integer.MIN_VALUE;
            for (int i = l; i <= r; i++) {
                farthest = Math.max(farthest, i + nums[i]);
            }
            l = r + 1;
            r = farthest;
            jumps += 1;
        }
        return jumps;
    }
}