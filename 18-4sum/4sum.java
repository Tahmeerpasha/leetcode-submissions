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

        // Better = Time -> O(n^3) && Space -> O(1)
        // for(int i=0; i<n; i++){
        //     for(int j=i+1; j<n; j++){
        //         Set<Long> set = new HashSet<>();
        //         for(int k=j+1; k<n; k++){
        //             long sum = nums[i] + nums[j];
        //             sum += nums[k];
        //             long fourth = target - sum;
        //             if(set.contains(fourth)){
        //                 List<Integer> temp = Arrays.asList(nums[i], nums[j], nums[k], (int)fourth);
        //                 temp.sort(null);
        //                 result.add(temp);
        //             }
        //             set.add((long)nums[k]);
        //         }
        //     }
        // }

        Arrays.sort(nums);
        for(int i=0; i<n; i++){
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            for(int j=i+1; j<n; j++){
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                int k=j+1, l=n-1;
                while(k<l){
                    long sum = nums[i];
                    sum += nums[j];
                    sum += nums[k];
                    sum += nums[l];
                    if(sum < target)k++;
                    else if(sum > target)l--;
                    else{
                        List<Integer> temp = Arrays.asList(nums[i], nums[j], nums[k], nums[l]);
                        temp.sort(null);
                        result.add(temp);
                        k++;
                        l--;
                        while(k<l && nums[k] == nums[k-1])k++;
                        while(k<l && nums[l] == nums[l+1])l--;
                    }
                }
            }
        }
        return new ArrayList<>(result);
    }
}