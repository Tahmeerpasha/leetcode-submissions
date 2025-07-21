class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int maxCount = 0;

        for (int num : nums) {
            set.add(num);
        }

        for (int curr : set) {
            if (!set.contains(curr - 1)) {
                int initVal = curr, count = 1;
                while (set.contains(initVal + 1)) {
                    initVal += 1;
                    count += 1;
                }
                maxCount = Math.max(maxCount, count);
            }
        }

        return maxCount;
    }
}