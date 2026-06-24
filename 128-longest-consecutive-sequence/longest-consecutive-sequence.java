class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int max = 0;
        for (int num : nums)
            set.add(num);

        for (int num : set) {
            if (!set.contains(num - 1)) {
                int curr = num;
                int streak = 0;
                while (set.contains(curr)) {
                    curr++;
                    streak++;
                }
                max = Math.max(max, streak);
            }
        }
        return max;
    }
}