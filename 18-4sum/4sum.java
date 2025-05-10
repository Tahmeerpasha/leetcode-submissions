class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        int n = nums.length;
        Set<List<Integer>> result = new HashSet<>();
        if(n < 4)return new ArrayList<>();

        // Brute = Time -> O(n^4) && Space -> O(1)
        // for(int i=0; i<n; i++){
        //     for(int j=i+1; j<n; j++){
        //         for(int k=j+1; k<n; k++){
        //             for(int l=k+1; l<n; l++){
        //                 int sum = nums[i] + nums[j] + nums[k] + nums[l];
        //                 if(sum == target){
        //                     List<Integer> temp = Arrays.asList(nums[i], nums[j], nums[k], nums[l]);
        //                     temp.sort(null);
        //                     result.add(temp);
        //                 }
        //             }
        //         }
        //     }
        // }

        for(int i=0; i<n; i++){
            for(int j=i+1; j<n; j++){
                Set<Long> set = new HashSet<>();
                for(int k=j+1; k<n; k++){
                    long sum = nums[i] + nums[j];
                    sum += nums[k];
                    long fourth = target - sum;
                    if(set.contains(fourth)){
                        List<Integer> temp = Arrays.asList(nums[i], nums[j], nums[k], (int)fourth);
                        temp.sort(null);
                        result.add(temp);
                    }
                    set.add((long)nums[k]);
                }
            }
        }


        return new ArrayList<>(result);
    }
}