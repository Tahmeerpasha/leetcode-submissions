class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        // Heap method: TC = O(n log k) && SC = O(n+k)
        // Step 1: Count frequencies
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // // Step 2: Use a min-heap to keep top k frequent elements
        // PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((a, b) -> a.getValue() - b.getValue());

        // for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
        //     pq.offer(entry);
        //     if (pq.size() > k)
        //         pq.poll();
        // }

        // // Step 3: Extract keys from heap
        // int[] result = new int[k];
        // int i = 0;
        // while (!pq.isEmpty()) {
        //     result[i++] = pq.poll().getKey();
        // }

        // return result;

        // Bucket sort method: TC = O(n) && SC = O(n)
        // Step 1: Create bucket array where index = frequency
        List<List<Integer>> bucket = new ArrayList<>(nums.length + 1);
        for (int i = 0; i <= nums.length; i++) {
            bucket.add(new ArrayList<>()); // initialize empty lists
        }

        // Step 2: Fill the buckets
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int freq = entry.getValue();
            bucket.get(freq).add(entry.getKey());
        }

        // Step 3: Collect top k frequent elements
        int[] result = new int[k];
        int j = 0;
        for (int i = nums.length; i >= 0 && j < k; i--) {
            if (!bucket.get(i).isEmpty()) {
                for (int num : bucket.get(i)) {
                    result[j++] = num;
                    if (j == k)
                        break;
                }
            }
        }

        return result;
    }
}