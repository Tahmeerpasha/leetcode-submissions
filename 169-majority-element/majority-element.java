class Solution {
    public int majorityElement(int[] nums) {
        // Map<Integer, Integer> map = new HashMap<>();
        // for (int num : nums) {
        //     map.put(num, map.getOrDefault(num, 0) + 1);
        // }
        // for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
        //     if (entry.getValue() > nums.length / 2) {
        //         return entry.getKey();
        //     }
        // }
        // return -1;
        int candidate = 0, count = 0;
        for (int num : nums) {
            if (count == 0) {
                candidate = num;
                count = 1;
            } else if (candidate == num)
                count++;
            else
                count--;
        }
        count = 0;
        for (int num : nums) {
            if (num == candidate)
                count++;
        }
        return count > nums.length / 2 ? candidate : -1;
    }
}