class Solution {
    public long subArrayRanges(int[] nums) {
        return sumSubarrayMaxs(nums) - sumSubarrayMins(nums);
    }

    public long sumSubarrayMaxs(int[] arr) {
        long sum = 0;
        int[] nge = findNGE(arr);
        int[] pge = findPGE(arr);
        for (int i = 0; i < arr.length; i++) {
            int left = i - pge[i];
            int right = nge[i] - i;
            sum = (sum + (right * left * 1L * arr[i]));
        }
        return sum;
    }

    public long sumSubarrayMins(int[] arr) {
        long sum = 0;
        int[] nse = findNSE(arr);
        int[] pse = findPSE(arr);
        for (int i = 0; i < arr.length; i++) {
            int left = i - pse[i];
            int right = nse[i] - i;
            sum = (sum + (right * left * 1L * arr[i]));
        }
        return sum;
    }

    int[] findNSE(int[] arr) {
        Stack<Integer> st = new Stack<>();
        int[] nse = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            while (!st.isEmpty() && arr[st.peek()] > arr[i])
                st.pop();
            nse[i] = st.isEmpty() ? arr.length : st.peek();
            st.push(i);
        }
        return nse;
    }

    int[] findPSE(int[] arr) {
        Stack<Integer> st = new Stack<>();
        int[] pse = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            while (!st.isEmpty() && arr[st.peek()] >= arr[i])
                st.pop();
            pse[i] = st.isEmpty() ? -1 : st.peek();
            st.push(i);
        }
        return pse;
    }

    int[] findNGE(int[] arr) {
        Stack<Integer> st = new Stack<>();
        int[] nge = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            while (!st.isEmpty() && arr[st.peek()] < arr[i])
                st.pop();
            nge[i] = st.isEmpty() ? arr.length : st.peek();
            st.push(i);
        }
        return nge;
    }

    int[] findPGE(int[] arr) {
        Stack<Integer> st = new Stack<>();
        int[] pge = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            while (!st.isEmpty() && arr[st.peek()] <= arr[i])
                st.pop();
            pge[i] = st.isEmpty() ? -1 : st.peek();
            st.push(i);
        }
        return pge;
    }
}