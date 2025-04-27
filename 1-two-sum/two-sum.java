class Solution {
    public int[] twoSum(int[] nums, int target) {
        int n = nums.length;
        int[] answer = new int[2];
        
        // Brute force -> Time=O(n^2)
        // for(int i=0; i<n ; i++){
        //     for(int j=1; j<n; j++){
        //         if(nums[i]+nums[j] == target && i!=j){
        //             answer[0] = i;
        //             answer[1] = j;
        //             return answer;
        //         } 
        //     }
        // }
        // return answer;

        // 
       HashMap<Integer, Integer> remainingSum = new HashMap<Integer, Integer>();
        for(int i=0; i<n; i++){
        int diff = target - nums[i];
        if(remainingSum.containsKey(diff)){
            answer[0] = i;
            answer[1] = remainingSum.get(diff);
            return answer;
        }else{
            remainingSum.put(nums[i], i);
        }
        }
        return answer;
    }
}