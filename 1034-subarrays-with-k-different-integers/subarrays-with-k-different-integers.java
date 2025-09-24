class Solution {
    public int subarraysWithKDistinct(int[] nums, int k) {
        // Brute: TC => O(n^2) && SC => O(n)
        // int count = 0;
        // for (int i = 0; i < nums.length; i++) {
        //     Set<Integer> set = new HashSet<>();
        //     for (int j = i; j < nums.length; j++) {
        //         set.add(nums[j]);
        //         if (set.size() == k)
        //             count++;
        //     }
        // }
        // return count;

        // Optimal: TC => O(2n) && SC => O(1)
        return atMost(nums, k) - atMost(nums, k - 1);
    }

    int atMost(int[] nums, int k) {
        int l = 0, r = 0, count = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        while (r < nums.length) {
            map.put(nums[r], map.getOrDefault(nums[r], 0) + 1);
            while (map.size() > k) {
                map.put(nums[l], map.get(nums[l]) - 1);
                if (map.get(nums[l]) <= 0)
                    map.remove(nums[l]);
                l++;
            }
            count += r - l + 1;
            r++;
        }
        return count;
    }
}