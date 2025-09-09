class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] list = new int[n - k + 1];
        int p = 0;
        Deque<Integer> dq = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            // remove elements out of window
            if (!dq.isEmpty() && dq.peekFirst() <= i - k)
                dq.pollFirst();

            // maintain decreasing order in deque
            while (!dq.isEmpty() && nums[dq.peekLast()] < nums[i])
                dq.pollLast();

            dq.offer(i);

            // record max once window is valid
            if (i >= k - 1)
                list[p++] = nums[dq.peekFirst()];
        }

        return list;
    }
}