class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int n : nums) {
            int num = Math.abs(n);
            if (nums[num - 1] < 0)
                list.add(num);
            else
                nums[num - 1] = -nums[num - 1];
        }
        return list;
    }
}