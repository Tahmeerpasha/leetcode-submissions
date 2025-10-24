class Solution {
    public int findKthLargest(int[] nums, int k) {
        // Brute: Time => O(n logn) && Space => O(1)
        // Arrays.sort(nums);
        // return nums[nums.length-k];

        // Better: Time => O(n + k log n && Space => O(n)
        // PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        // for (int num : nums) {
        //     maxHeap.add(num);
        // }
        // for (int i = 0; i < k - 1; i++) {
        //     maxHeap.poll();
        // }
        // return maxHeap.peek();

        // Optimal 1: Time => O(n log k) && Space => O(k)
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int num : nums) {
            pq.add(num);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        return pq.poll();
    }
}