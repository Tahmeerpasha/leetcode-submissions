class Solution {
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        // Brute force -> time => O(n^n) & space => O(1)
        // for(int i=0; i<nums.length; i++){
        //     int sum=0;
        //     for(int j=i; j<nums.length; j++){
        //         sum += nums[j];
        //         if(sum == k){
        //             count++;
        //         }
        //     }
        // }

        // Time => O(n) & Space => O(n) [Prefix Sum method]
        Map<Integer, Integer> prefixSumMap = new HashMap<>();
        int prefixSum = 0;
        prefixSumMap.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            int remove = prefixSum - k;
            count += prefixSumMap.getOrDefault(remove, 0);
            prefixSumMap.put(prefixSum, prefixSumMap.getOrDefault(prefixSum, 0) + 1);
        }
        return count;
    }
}