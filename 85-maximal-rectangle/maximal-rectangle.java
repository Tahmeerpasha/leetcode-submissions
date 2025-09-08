class Solution {
    public int maximalRectangle(char[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] prefixSum = new int[row][col];
        for (int i = 0; i < col; i++) {
            int sum = 0;
            for (int j = 0; j < row; j++) {
                sum += matrix[j][i] - '0';
                if (matrix[j][i] == '0')
                    sum = 0;
                prefixSum[j][i] = sum;
            }
        }
        int maxArea = Integer.MIN_VALUE;
        for (int i = 0; i < row; i++) {
            maxArea = Math.max(maxArea, largestRectangleArea(prefixSum[i]));
        }
        return maxArea;
    }

    public int largestRectangleArea(int[] heights) {
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