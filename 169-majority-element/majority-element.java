class Solution {
    public int majorityElement(int[] nums) {
    Map<Integer, Integer> elementToTimesMap = new HashMap<>();
    for (int num : nums) {
      if (!elementToTimesMap.containsKey(num)) {
        elementToTimesMap.put(num, 1);
      } else {
        elementToTimesMap.put(num, elementToTimesMap.get(num) + 1);
      }
    }
    for (Map.Entry<Integer, Integer> entry : elementToTimesMap.entrySet()) {
      if (entry.getValue() > nums.length / 2) {
        return entry.getKey();
      }
    }
    return 0;
  }
}