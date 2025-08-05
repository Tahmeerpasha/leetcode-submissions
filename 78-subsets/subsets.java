class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        // Time and Space -> O(n * 2^n)
        generateSubSets(nums, 0, result, new ArrayList<>());
        return result;
    }

    void generateSubSets(int[] nums, int index, List<List<Integer>> result, List<Integer> currList) {
        // Base case
        if (index == nums.length) {
            result.add(new ArrayList<>(currList)); // Deep copy
            return;
        }

        // Left recursive call
        currList.add(nums[index]);
        generateSubSets(nums, index + 1, result, currList);

        // Backtrack step
        currList.remove(currList.size() - 1);

        // Right recursive call
        generateSubSets(nums, index + 1, result, currList);
    }
}