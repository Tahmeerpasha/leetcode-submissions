class Solution {
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> resultantArray = new ArrayList<>();

        // Time -> O(n) & Space -> O(n)
        // Map<Integer, Integer> elementCountMap = new HashMap<>();
        // for(int i = 0; i < nums.length; i++){
        //     elementCountMap.put(nums[i], elementCountMap.getOrDefault(nums[i],0)+1);
        // }
        // elementCountMap.forEach((key, value) -> {
        // if(value > nums.length/3){
        //     resultantArray.add(key);
        // }
        // });

        // Optimal Approach -> Space = O(1) & Time = O(n)
        int ele1 = Integer.MIN_VALUE, ele2 = Integer.MIN_VALUE, count1 = 0, count2 = 0, n = nums.length;

        for (int i = 0; i < n; i++) {
            if (count1 == 0 && ele2 != nums[i]) {
                count1 = 1;
                ele1 = nums[i];
            } else if (count2 == 0 && ele1 != nums[i]) {
                count2 = 1;
                ele2 = nums[i];
            } else if (ele1 == nums[i])
                count1++;
            else if (ele2 == nums[i])
                count2++;
            else {
                count1--;
                count2--;
            }
        }
        count1 = 0;
        count2 = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == ele1)
                count1++;
            if (nums[i] == ele2)
                count2++;
        }
        if (n / 3 < count1)
            resultantArray.add(ele1);
        if (n / 3 < count2)
            resultantArray.add(ele2);
        return resultantArray;
    }
}