class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(k, n, result, new ArrayList<>(), 1, 0);
        return result;
    }

    void backtrack(int arraySize, int target, List<List<Integer>> result, List<Integer> currList, int index, int sum) {
        if (sum > target || currList.size() > arraySize)
            return; // prune invalid branches

        if (sum == target && currList.size() == arraySize) {
            result.add(new ArrayList<>(currList));
            return;
        }

        if (index > 9)
            return; // we only use digits 1-9

        currList.add(index);
        backtrack(arraySize, target, result, currList, index + 1, sum + index);
        currList.remove(currList.size() - 1);
        backtrack(arraySize, target, result, currList, index + 1, sum);
    }
}