class Solution {
    public int largestRectangleArea(int[] heights) {
        // Brute -> Time = O(5N) && Space = O(5N)
        // Time -> O(2N) && Space -> O(2N)
        // int[] pse = findPSEE(heights);
        // // Time -> O(2N) && Space -> O(2N)
        // int[] nse = findNSE(heights);
        // int max = Integer.MIN_VALUE;
        // // Time -> O(N) && Space -> O(N)
        // for (int i = 0; i < heights.length; i++) {
        //     int value = heights[i] * (nse[i] - pse[i] - 1);
        //     max = Math.max(max, value);
        // }
        // return max;

        // Optimal -> Time = O(n) && Space -> O(n)
        int n = heights.length;
        int maxArea = Integer.MIN_VALUE;
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && heights[st.peek()] > heights[i]) {
                int currEle = st.pop();
                int pse = st.isEmpty() ? -1 : st.peek();
                int nse = i;
                maxArea = Math.max(maxArea, (heights[currEle] * (nse - pse - 1)));
            }
            st.push(i);
        }

        while (!st.isEmpty()) {
            int currEle = st.pop();
            int pse = st.isEmpty() ? -1 : st.peek();
            int nse = n;
            maxArea = Math.max(maxArea, (heights[currEle] * (nse - pse - 1)));
        }
        return maxArea;
    }

    int[] findNSE(int[] nums) {
        // Time -> O(2N) && Space -> O(2N)
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
        // Time -> O(2N) && Space -> O(2N)
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