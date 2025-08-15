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

        // Better -> O(n^2) & Space -> O(N) + O(2x(no.of.triplets))
        // for(int i=0; i<n; i++){
        //     Set<Integer> set = new HashSet<>();
        //     for(int j=i+1; j<n; j++){
        //         List<Integer> triplets = new ArrayList<>(3);
        //         int third = nums[i] + nums[j];
        //         if(set.contains(-third)){
        //             triplets.add(nums[i]);
        //             triplets.add(nums[j]);
        //             triplets.add(-third);        
        //             triplets.sort(null);
        //             result.add(triplets);
        //         }
        //         set.add(nums[j]);
        //     }
        // }

        // Optimal -> Time => O(n^2) && Space => O(1)
        Arrays.sort(nums);
        if (n < 3)
            return new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (i > 0 && nums[i] == nums[i - 1])
                continue;
            int j = i + 1, k = n - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == 0) {
                    List<Integer> triplets = new ArrayList<>(3);
                    triplets.add(nums[i]);
                    triplets.add(nums[j]);
                    triplets.add(nums[k]);
                    result.add(triplets);
                    j++;
                    k--;
                    while (j < k && nums[j] == nums[j - 1])
                        j++;
                    while (j < k && nums[k] == nums[k + 1])
                        k--;
                } else if (sum < 0)
                    j++;
                else
                    k--;
            }
        }
        return new ArrayList<>(result);
    }
}