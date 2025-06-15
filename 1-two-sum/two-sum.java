class Solution {
    public int[] twoSum(int[] nums, int target) {
        // Brute -> Time = O(n^2) && Space = O(1)
        // for (int i = 0; i < nums.length; i++) {
        //     for (int j = i + 1; j < nums.length; j++) {
        //         if (nums[i] + nums[j] == target)
        //             return new int[] { i, j };
        //     }
        // }

        // Optimal -> Hashing || Time = O(n * logn) && Space = O(n)
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<nums.length; i++){
            int more = target - nums[i];
            if(map.containsKey(more)){
                return new int[]{i, map.get(more)};
            }
            map.put(nums[i],i);
        }

        return new int[] { -1, -1 };

    }
}