class Solution {
    public int majorityElement(int[] nums) {
        // Space -> O(n) and Time -> O(1)
//     Map<Integer, Integer> elementToTimesMap = new HashMap<>();
//     for (int num : nums) {
//       if (!elementToTimesMap.containsKey(num)) {
//         elementToTimesMap.put(num, 1);
//       } else {
//         elementToTimesMap.put(num, elementToTimesMap.get(num) + 1);
//       }
//     }
//     for (Map.Entry<Integer, Integer> entry : elementToTimesMap.entrySet()) {
//       if (entry.getValue() > nums.length / 2) {
//         return entry.getKey();
//       }
//     }
//     return 0;

        // Space -> O(1) and Time -> O(N)

    int count = 0;
    int candidate = 0;

    for(int num: nums){
        if(count == 0)
            candidate = num;

        if(num == candidate) count++;
        else count--;
    }
    return candidate;
  }
}