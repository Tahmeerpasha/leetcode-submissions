class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        findCS(candidates, target, 0, result, new ArrayList<>());
        return result;
    }

    void findCS(int[] candidates, int target, int index, List<List<Integer>> result, List<Integer> currList) {

        if (target == 0) {
            result.add(new ArrayList<>(currList));
            return;
        }

        if (target >= 0 && index < candidates.length) {
            // Pick the number
            currList.add(candidates[index]);
            findCS(candidates, target - candidates[index], index, result, currList);

            // Don't pick the number
            currList.remove(currList.size() - 1);
            findCS(candidates, target, index + 1, result, currList);
        }
    }
}