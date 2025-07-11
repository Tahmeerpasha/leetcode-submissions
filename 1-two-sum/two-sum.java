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
        for (int i = 0; i < nums.length; i++) {
            int more = target - nums[i];
            if (map.containsKey(more)) {
                return new int[] { i, map.get(more) };
            }
            map.put(nums[i], i);
        }

/**
There is a more optimal solution where we can sort the array and use two pointers, But if we've been asked to return the indexes then using hashmaps is the best approach else we can use the two pointers if we don't have to return indexes.
*/
        return new int[] { -1, -1 };

    }
}