class Solution {
    public int longestConsecutive(int[] nums) {
        int count=0, maxLength=0, lastSmaller=Integer.MIN_VALUE;
        // O(nlogn)
        // Arrays.sort(nums);
        // for(int i=0; i<nums.length; i++){
        //     if(nums[i]-1 == lastSmaller){
        //         count++;
        //         lastSmaller = nums[i];
        //     }else if(nums[i] != lastSmaller){ // if it's equal do nothing
        //         count = 1;
        //         lastSmaller = nums[i];
        //     }
        //     maxLength = Math.max(maxLength, count);
        // }
        // return maxLength;

        // O(n)
        Set<Integer> set = new HashSet<Integer>();
        for(int i=0; i<nums.length; i++){
            set.add(nums[i]);
        }
        
        for(int it: set){
            if(!set.contains(it-1)){
                int cnt = 1;
                int x = it;
                while(set.contains(x+1)){
                    x += 1;
                    cnt += 1;
                }
                maxLength = Math.max(maxLength, cnt);
            }
        }
        return maxLength;
    }
}