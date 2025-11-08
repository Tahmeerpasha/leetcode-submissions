class KthLargest {
    PriorityQueue<Integer> pq;
    int size;

    public KthLargest(int k, int[] nums) {
        this.pq = new PriorityQueue<>();
        this.size = k;
        for (int num : nums) {
            pq.add(num);
            while (pq.size() > k)
                pq.poll();
        }
    }

    public int add(int val) {
        pq.add(val);
        while (pq.size() > size)
            pq.poll();
        return pq.peek();
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */