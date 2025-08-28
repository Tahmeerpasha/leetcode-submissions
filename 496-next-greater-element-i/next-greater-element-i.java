class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int result[] = new int[nums1.length];
        int k = 0;
        for (int i = 0; i < nums1.length; i++) {
            int num = nums1[i];
            for (int j = 0; j < nums2.length; j++) {
                if (nums2[j] == num) {
                    int grt = -1;
                    for (int l = j + 1; l < nums2.length; l++) {
                        if (l < nums2.length && nums2[l] > num) {
                            grt = nums2[l];
                            break;
                        }
                    }
                    result[k++] = grt;
                }
            }
        }
        return result;
    }
}