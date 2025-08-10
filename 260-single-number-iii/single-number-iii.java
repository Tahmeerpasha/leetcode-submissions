class Solution {
    public int[] singleNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int[] ans = new int[2];
        int i = 0;
        for (int key : map.keySet()) {
            if (map.get(key) == 1)
                ans[i++] = key;
        }
        return ans;
    }
}