class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        findSubsets(nums, 0, result, new ArrayList<>());
        return result;
    }

    void findSubsets(int[] nums, int index, List<List<Integer>> result, List<Integer> curr) {
        if (index >= nums.length) {
            result.add(new ArrayList<>(curr));
            return;
        }
        curr.add(nums[index]);
        findSubsets(nums, index + 1, result, curr);
        curr.remove(curr.size() - 1);
        findSubsets(nums, index + 1, result, curr);
    }
}