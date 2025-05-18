class Solution {
    public int longestConsecutive(int[] nums) {
        if(nums.length==0)return 0;
        
        // Better => Time -> O(nlogn) && Space -> O(1)
        // Arrays.sort(nums);
        // int count=1, maxCount=1;
        // for(int i=0; i<nums.length-1; i++){
        //     if(nums[i+1]-nums[i] == 1){
        //         count++;
        //         maxCount = Math.max(maxCount, count);
        //     }
        //     else if(nums[i+1]-nums[i] == 0)continue;
        //     else count=1;
        // }
        // return maxCount;

        HashSet<Integer> set = new HashSet<>();
        int maxCount = 0;
        for(int num:nums){
            set.add(num);
        }
        for(int currentElement: set){
            if(!set.contains(currentElement-1)){
                int count = 1;
                int initialValue = currentElement;
                while(set.contains(initialValue + 1)){
                    initialValue += 1;
                    count += 1;
                }
                maxCount = Math.max(maxCount, count);
            }
        }
        return maxCount;
    }
}