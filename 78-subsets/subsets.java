class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        generateSubSets(nums, 0, result, new ArrayList<>());
        return result;
    }

    void generateSubSets(int[] nums, int index, List<List<Integer>> result, List<Integer> currList) {
        // Base case
        if (index == nums.length) {
            result.add(new ArrayList<>(currList));
            return;
        }
        // Left recursive call
        currList.add(nums[index]);
        generateSubSets(nums, index + 1, result, currList);
        // Right recursive call
        currList.remove(currList.size() - 1);
        generateSubSets(nums, index + 1, result, currList);
    }

}