class Solution {
    public long maximumHappinessSum(int[] happiness, int k) {
        happiness = Arrays.stream(happiness)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .mapToInt(Integer::intValue)
                .toArray();
        int n = happiness.length;
        long result = 0;
        for (int i = 0; i < k; i++) {
            result += Math.max(happiness[i] - i, 0);
        }
        return result;
    }
}