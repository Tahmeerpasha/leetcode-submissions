class Solution {

    // ------------------------------------------------------------
    // Brute Force
    // ------------------------------------------------------------
    // Idea:
    //   1. For each element in nums1, find its index in nums2.
    //   2. From that index, linearly scan to the right until you find
    //      a greater element (if any).
    //   3. If found, store it; otherwise store -1.
    //
    // Time Complexity: O(n1 * n2) ~ O(n^2)
    // Space Complexity: O(1)
    /*
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] result = new int[nums1.length];
        int k = 0;
    
        for (int i = 0; i < nums1.length; i++) {
            int num = nums1[i];
            int greater = -1;
    
            // Step 1 -> Find 'num' in nums2
            for (int j = 0; j < nums2.length; j++) {
                if (nums2[j] == num) {
                    // Step 2 -> Search to the right for next greater element
                    for (int l = j + 1; l < nums2.length; l++) {
                        if (nums2[l] > num) {
                            greater = nums2[l];
                            break;
                        }
                    }
                }
            }
            result[k++] = greater;
        }
        return result;
    }
    */

    // ------------------------------------------------------------
    // Better (still O(n^2), but uses a stack for each nums1 element)
    // ------------------------------------------------------------
    // Idea:
    //   1. For each element in nums1, simulate traversal of nums2
    //      using a stack to hold elements on the right.
    //   2. Pop elements smaller than current num1[i] until either
    //      stack becomes empty or a greater element is found.
    //
    // Time Complexity: O(n1 * n2) ~ O(n^2)
    // Space Complexity: O(n2) (stack)
    /*
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int[] result = new int[n1];
    
        for (int i = n1 - 1; i >= 0; i--) {
            Stack<Integer> st = new Stack<>();
    
            // Step 1 -> Push elements from nums2 until we find nums1[i]
            for (int j = nums2.length - 1; j >= 0 && nums2[j] != nums1[i]; j--) {
                st.push(nums2[j]);
            }
    
            // Step 2 -> If no elements on right, answer is -1
            if (st.isEmpty()) {
                result[i] = -1;
                continue;
            }
    
            // Step 3 -> Remove smaller elements
            while (!st.isEmpty() && st.peek() < nums1[i]) {
                st.pop();
            }
    
            // Step 4 -> If stack empty, no greater element exists
            result[i] = st.isEmpty() ? -1 : st.peek();
        }
        return result;
    }
    */

    // ------------------------------------------------------------
    // Optimal Solution -> Monotonic Stack + HashMap
    // ------------------------------------------------------------
    // Idea:
    //   1. Preprocess nums2 using a monotonic decreasing stack.
    //      - While stack top is <= current element, pop it.
    //      - The next greater for nums2[i] is either stack top or -1 if empty.
    //      - Store this info in a map.
    //   2. For nums1, simply lookup answers from the map.
    //
    // Time Complexity: O(n1 + n2)  (Each element is pushed/popped once)
    // Space Complexity: O(n2)      (Map + Stack)
    //
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> nextGreater = new HashMap<>();
        Stack<Integer> st = new Stack<>();

        // Step 1 -> Precompute next greater elements for nums2
        // Traverse nums2 from RIGHT to LEFT
        for (int i = nums2.length - 1; i >= 0; i--) {

            // Maintain monotonic decreasing stack
            // Pop all smaller or equal elements
            while (!st.isEmpty() && st.peek() <= nums2[i]) {
                st.pop();
            }

            // If stack empty -> no greater element
            // Else -> top of stack is next greater
            if (st.isEmpty()) {
                nextGreater.put(nums2[i], -1);
            } else {
                nextGreater.put(nums2[i], st.peek());
            }

            // Push current element into stack for future comparisons
            st.push(nums2[i]);
        }

        // Step 2 -> Build result for nums1 using precomputed map
        int[] result = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            result[i] = nextGreater.get(nums1[i]);
        }

        return result;
    }
}
