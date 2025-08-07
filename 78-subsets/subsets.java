class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        // Time and Space -> O(n * 2^n)
        return generateSubSetsUsingBitM(nums, result);
        // Time and Space -> O(n * 2^n)
        // generateSubSetsUsingRecursion(nums, 0, result, new ArrayList<>());
        // return result;
    }

    List<List<Integer>> generateSubSetsUsingBitM(int[] nums, List<List<Integer>> result) {
        int n = nums.length;
        int subsets = 1 << n;

        for (int i = 0; i < subsets; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) // Check if kth bit is set
                    list.add(nums[j]);
            }
            result.add(list);
        }
        return result;
    }

    void generateSubSetsUsingRecursion(int[] nums, int index, List<List<Integer>> result, List<Integer> currList) {
        // Base case
        if (index == nums.length) {
            result.add(new ArrayList<>(currList)); // Deep copy
            return;
        }

        // Left recursive call
        currList.add(nums[index]);
        generateSubSetsUsingRecursion(nums, index + 1, result, currList);

        // Backtrack step
        currList.remove(currList.size() - 1);

        // Right recursive call
        generateSubSetsUsingRecursion(nums, index + 1, result, currList);
    }
}