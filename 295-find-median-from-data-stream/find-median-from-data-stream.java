class MedianFinder {
    // Brute force
    // List<Integer> list;

    // public MedianFinder() {
    //     this.list = new ArrayList<>();
    // }

    // public void addNum(int num) {
    //     list.add(num);
    //     Collections.sort(list);
    // }

    // public double findMedian() {
    //     int mid = list.size() / 2;
    //     if (list.size() % 2 == 0) {
    //         return (double) (list.get(mid) + list.get(mid - 1)) / 2;
    //     } else
    //         return (double) list.get(mid);
    // }

    // Optimal 
    PriorityQueue<Integer> smallHeap; // max heap
    PriorityQueue<Integer> largeHeap; // min heap

    public MedianFinder() {
        this.smallHeap = new PriorityQueue<>(Collections.reverseOrder());
        this.largeHeap = new PriorityQueue<>();
    }

    public void addNum(int num) {
        // Step 1: Add to smallHeap
        smallHeap.offer(num);

        // Step 2: Move the largest of smallHeap to largeHeap
        largeHeap.offer(smallHeap.poll());

        // Step 3: Balance the heaps if needed
        if (largeHeap.size() > smallHeap.size()) {
            smallHeap.offer(largeHeap.poll());
        }
    }

    public double findMedian() {
        // If equal sizes, return average
        if (largeHeap.size() == smallHeap.size()) {
            return (largeHeap.peek() + smallHeap.peek()) / 2.0;
        }

        // Otherwise, return top of smallHeap
        return smallHeap.peek();
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */