class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> resultSet = new HashSet<>();
        for (int num : nums) {
            if (!set.add(num))
                resultSet.add(num);
        }
        return resultSet.stream().toList();
    }
}