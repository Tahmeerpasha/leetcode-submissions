class Solution {
    public int sumSubarrayMins(int[] arr) {
        long sum = 0;
        // Brute -> time => O(n^2) && Space -> O(1)
        // for (int i = 0; i < arr.length; i++) {
        //     int min = Integer.MAX_VALUE;
        //     for (int j = i; j < arr.length; j++) {
        //         min = Math.min(min, arr[j]);
        //         sum = (sum + min) % 1000000007;
        //     }
        // }

        // Optimal 
        int[] nse = findNSE(arr);
        int[] pse = findPSE(arr);
        int MOD = 1000000007;
        for (int i = 0; i < arr.length; i++) {
            int left = i - pse[i];
            int right = nse[i] - i;
            sum = (sum + (right * left * 1L * arr[i]) % MOD) % MOD;
        }
        return (int) sum;
    }

    int[] findNSE(int[] arr) {
        Stack<Integer> st = new Stack<>();
        int[] nse = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            while (!st.isEmpty() && arr[st.peek()] >= arr[i])
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
            while (!st.isEmpty() && arr[st.peek()] > arr[i])
                st.pop();
            pse[i] = st.isEmpty() ? -1 : st.peek();
            st.push(i);
        }
        return pse;
    }
}