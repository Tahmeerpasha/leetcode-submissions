class Solution {
    // public int[] nextGreaterElement(int[] nums1, int[] nums2) {
    //     int result[] = new int[nums1.length];
    //     int k = 0;
    //     for (int i = 0; i < nums1.length; i++) {
    //         int num = nums1[i];
    //         for (int j = 0; j < nums2.length; j++) {
    //             if (nums2[j] == num) {
    //                 int grt = -1;
    //                 for (int l = j + 1; l < nums2.length; l++) {
    //                     if (l < nums2.length && nums2[l] > num) {
    //                         grt = nums2[l];
    //                         break;
    //                     }
    //                 }
    //                 result[k++] = grt;
    //             }
    //         }
    //     }
    //     return result;
    // }

    // public int[] nextGreaterElement(int[] nums1, int[] nums2) {
    //     int n1 = nums1.length, k = n1 - 1;
    //     int n2 = nums2.length;
    //     int[] result = new int[n1];
    //     for (int i = n1 - 1; i >= 0; i--) {
    //         Stack<Integer> st = new Stack<>();
    //         for (int j = n2 - 1; j >= 0 && nums1[i] != nums2[j]; j--) {
    //             st.push(nums2[j]);
    //         }
    //         if (st.isEmpty() && k >= 0) {
    //             result[k--] = -1;
    //             continue;
    //         }
    //         while (!st.isEmpty() && st.peek() < nums1[i])
    //             st.pop();
    //         if (st.isEmpty())
    //             result[k--] = -1;
    //         else
    //             result[k--] = st.peek();
    //     }
    //     return result;
    // }

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> nextGreater = new HashMap<>();
        Stack<Integer> st = new Stack<>();
        int n1 = nums1.length, k = n1 - 1;
        int n2 = nums2.length;
        // Step 1 -> Traverse nums2 and fill nextGreater map
        for (int i = n2 - 1; i >= 0; i--) {
            while (st.size() > 0 && st.peek() <= nums2[i])
                st.pop();

            if (st.isEmpty()) {
                nextGreater.put(nums2[i], -1);
            } else {
                nextGreater.put(nums2[i], st.peek());
            }
            st.push(nums2[i]);
        }

        // Step 2 -> Build result for nums1
        int[] result = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            result[i] = nextGreater.get(nums1[i]);
        }

        return result;
    }
}