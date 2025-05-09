class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> result = new HashSet<>();
        int n = nums.length;

        // Brute -> O(n^3) & Space -> O(2x(size of triplets))
        // for(int i=0; i<n; i++){
        //     for(int j=i+1; j<n; j++){
        //         for(int k=j+1; k<n; k++){
        //             if(i!=j && i!=k && j!=k){
        //                 if(nums[i]+nums[j]+nums[k] == 0){
        //                     List<Integer> list = new ArrayList<>(3);
        //                     list.add(nums[i]);
        //                     list.add(nums[j]);
        //                     list.add(nums[k]);
        //                     list.sort(null);
        //                     result.add(list);
        //                 }
        //             }
        //         }
        //     }
        // }
       
        // Better -> O(n^2) & Space -> O(1)
        for(int i=0; i<n; i++){
            Set<Integer> set = new HashSet<>();
            for(int j=i+1; j<n; j++){
                List<Integer> triplets = new ArrayList<>(3);
                int third = nums[i] + nums[j];
                if(set.contains(-third)){
                    triplets.add(nums[i]);
                    triplets.add(nums[j]);
                    triplets.add(-third);        
                    triplets.sort(null);
                    result.add(triplets);
                }
                set.add(nums[j]);
            }
        }
        return new ArrayList<>(result);
    }
}