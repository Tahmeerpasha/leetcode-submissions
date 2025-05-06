class Solution {
    public List<Integer> majorityElement(int[] nums) {
        Map<Integer, Integer> elementCountMap = new HashMap<>();
        List<Integer> resultantArray = new ArrayList<>();
        for(int i = 0; i < nums.length; i++){
            elementCountMap.put(nums[i], elementCountMap.getOrDefault(nums[i],0)+1);
        }
        elementCountMap.forEach((key, value) -> {
        if(value > nums.length/3){
            resultantArray.add(key);
        }
        });
        return resultantArray;
    }
}