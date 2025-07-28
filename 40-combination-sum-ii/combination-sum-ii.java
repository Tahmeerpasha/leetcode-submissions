class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        // Brute force
        // Set<List<Integer>> result = new HashSet<>();
        // Time -> O(2^t * k) where t is how many times we picked an element
        // Arrays.sort(candidates);
        // findCS2(candidates, target, 0, result, new ArrayList<>());
        // return new ArrayList(result);

        // Optimal
        List<List<Integer>> result = new ArrayList<>();
        // Time -> O(2^n * k) && Space -> O(k*x)
        Arrays.sort(candidates);
        findCS2Optimal(candidates, target, 0, result, new ArrayList<>());
        return result;
    }

    // void findCS2(int[] candidates, int target, int index, Set<List<Integer>> result, List<Integer> currList) {
    //     if (index == candidates.length) {
    //         if (target == 0)
    //             result.add(new ArrayList<>(currList));
    //         return;
    //     }

    //     if (candidates[index] <= target) {
    //         // Pick the number
    //         currList.add(candidates[index]);
    //         findCS2(candidates, target - candidates[index], index + 1, result, currList);

    //         // Bactrack step - remove the number
    //         currList.remove(currList.size() - 1);
    //     }
    //     // Don't pick the number
    //     findCS2(candidates, target, index + 1, result, currList);
    // }

    void findCS2Optimal(int[] candidates, int target, int index, List<List<Integer>> result, List<Integer> currList) {
        if (target == 0) {
            result.add(new ArrayList<>(currList));
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            if (i > index && candidates[i] == candidates[i - 1])
                continue;
            if (candidates[i] > target)
                break;
            currList.add(candidates[i]);
            findCS2Optimal(candidates, target - candidates[i], i + 1, result, currList);
            currList.remove(currList.size() - 1);
        }
    }
}