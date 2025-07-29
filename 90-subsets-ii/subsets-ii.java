class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        // Time -> O(2^n * k) && Space -> O(k*x)
        Arrays.sort(nums);
        result.add(new ArrayList<>());
        findSubSets(nums, 0, result, new ArrayList<>());
        return result;
    }

    void findSubSets(int[] nums, int index, List<List<Integer>> result, List<Integer> currList) {
        if (index == nums.length) {
            return;
        }

        for (int i = index; i < nums.length; i++) {
            if (i > index && nums[i] == nums[i - 1])
                continue;
            currList.add(nums[i]);
            result.add(new ArrayList<>(currList));
            findSubSets(nums, i + 1, result, currList);
            currList.remove(currList.size() - 1);
        }
    }
}