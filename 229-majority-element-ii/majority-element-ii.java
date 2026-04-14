class Solution {
    public List<Integer> majorityElement(int[] nums) {
        int cnt1 = 0, cnt2 = 0, cand1 = 0, cand2 = 0;
        int n = nums.length;
        ArrayList<Integer> result = new ArrayList<>();
        for (int num : nums) {
            if (cnt1 == 0 && cand2 != num) {
                cand1 = num;
                cnt1 = 1;
            } else if (cnt2 == 0 && cand1 != num) {
                cand2 = num;
                cnt2 = 1;
            } else if (cand1 == num)
                cnt1++;
            else if (cand2 == num)
                cnt2++;
            else {
                cnt1--;
                cnt2--;
            }
        }
        cnt1 = 0;
        cnt2 = 0;
        for (int num : nums) {
            if (num == cand1)
                cnt1++;
            else if (num == cand2)
                cnt2++;
        }
        if (n / 3 < cnt1)
            result.add(cand1);
        if (n / 3 < cnt2)
            result.add(cand2);
        return result;
    }
}