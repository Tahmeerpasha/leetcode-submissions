class Solution {
    public int[] twoSum(int[] nums, int target) {
        // Brute force -> Time=O(n^2)
        int n = nums.length;
        int[] answer = new int[2];
        for(int i=0; i<n ; i++){
            for(int j=1; j<n; j++){
                if(nums[i]+nums[j] == target && i!=j){
                    answer[0] = i;
                    answer[1] = j;
                    return answer;
                } 
            }
        }
        return answer;
    }
}