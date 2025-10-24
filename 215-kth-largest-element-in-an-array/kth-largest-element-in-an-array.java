class Solution {
    public int findKthLargest(int[] nums, int k) {
        // Brute: Time => O(n logn) && Space => O(1)
        // Sorting approach
        // Arrays.sort(nums);
        // return nums[nums.length-k];

        // Better: Time => O(n + k log n && Space => O(n)
        // Max heap approach
        // PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        // for (int num : nums) {
        //     maxHeap.add(num);
        // }
        // for (int i = 0; i < k - 1; i++) {
        //     maxHeap.poll();
        // }
        // return maxHeap.peek();

        // Optimal 1: Time => O(n log k) && Space => O(k)
        // Min heap approach
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int num : nums) {
            pq.add(num);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        return pq.poll();

        // Optimal 2: Time => Average: O(n) && Worst case (bad pivots): O(n²) && Space => O(1)
        // Quick Select approach. Just mention this in interview
        // int left = 0, right = nums.length - 1;
        // int target = nums.length - k; // kth largest index (0-based)

        // while (true) {
        //     int pivotIndex = partition(nums, left, right);
        //     if (pivotIndex == target)
        //         return nums[pivotIndex];
        //     else if (pivotIndex < target)
        //         left = pivotIndex + 1;
        //     else
        //         right = pivotIndex - 1;
        // }
    }

    private int partition(int[] nums, int left, int right) {
        int pivot = nums[right];
        int i = left;
        for (int j = left; j < right; j++) {
            if (nums[j] <= pivot) {
                swap(nums, i, j);
                i++;
            }
        }
        swap(nums, i, right);
        return i;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}