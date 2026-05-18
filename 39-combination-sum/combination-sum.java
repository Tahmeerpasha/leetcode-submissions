class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        findCombinationSum(candidates, target, 0, result, new ArrayList<>());
        return result;
    }

    void findCombinationSum(int[] candidates, int target, int index, List<List<Integer>> result, List<Integer> curr) {
        if (index == candidates.length) {
            if (target == 0) {
                result.add(new ArrayList<>(curr));
            }
            return;
        }
        if (candidates[index] <= target) {
            curr.add(candidates[index]);
            findCombinationSum(candidates, target - candidates[index], index, result, curr);
            curr.remove(curr.size() - 1);
        }
        findCombinationSum(candidates, target, index + 1, result, curr);
    }
}