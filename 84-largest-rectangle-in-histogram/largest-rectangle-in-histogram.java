class Solution {
    public int largestRectangleArea(int[] heights) {
        int[] pse = findPSEE(heights);
        int[] nse = findNSE(heights);
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < heights.length; i++) {
            int value = heights[i] * (nse[i] - pse[i] - 1);
            max = Math.max(max, value);
        }
        return max;
    }

    int[] findNSE(int[] nums) {
        Stack<Integer> st = new Stack<>();
        int[] result = new int[nums.length];
        for (int i = nums.length - 1; i >= 0; i--) {
            while (!st.isEmpty() && nums[i] < nums[st.peek()])
                st.pop();
            result[i] = st.isEmpty() ? nums.length : st.peek();
            st.push(i);
        }
        return result;
    }

    int[] findPSEE(int[] nums) {
        Stack<Integer> st = new Stack<>();
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            while (!st.isEmpty() && nums[i] <= nums[st.peek()])
                st.pop();
            result[i] = st.isEmpty() ? -1 : st.peek();
            st.push(i);
        }
        return result;
    }
}