class Solution {
    public int repeatedNTimes(int[] nums) {
        int len = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int maxNum = 0, count = 0;
        for (int num : map.keySet()) {
            if (count < map.get(num)) {
                maxNum = num;
                count = map.get(num);
            }
        }
        return maxNum;
    }
}