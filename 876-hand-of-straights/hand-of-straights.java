class Solution {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        // if (hand.length % groupSize != 0)
        //     return false;
        // Map<Integer, Integer> freq = new HashMap<>();
        // for (int num : hand)
        //     freq.put(num, freq.getOrDefault(num, 0) + 1);

        // PriorityQueue<Integer> pq = new PriorityQueue<>();
        // for (int num : hand)
        //     pq.add(num);

        // while (!pq.isEmpty()) {
        //     int top = pq.poll();
        //     if (freq.get(top) <= 0)
        //         continue;
        //     for (int i = 1; i < groupSize; i++) {
        //         if (freq.get(top + i) == null || freq.get(top + i) <= 0)
        //             return false;
        //         else if (freq.get(top + i) > 0)
        //             freq.put(top + i, freq.getOrDefault(top + i, 0) - 1);
        //     }
        //     freq.put(top, freq.getOrDefault(top, 0) - 1);
        // }
        // return true;

        if (hand.length % groupSize != 0)
            return false;

        TreeMap<Integer, Integer> freq = new TreeMap<>();
        for (int x : hand)
            freq.put(x, freq.getOrDefault(x, 0) + 1);

        while (!freq.isEmpty()) {
            int start = freq.firstKey();
            int count = freq.get(start);

            // try to take 'count' groups starting at start
            for (int v = start; v < start + groupSize; v++) {
                Integer f = freq.get(v);
                // If f < count, means that we will have smallest more than bigger numbers and we cannot form the groups
                if (f == null || f < count)
                    return false;
                // Used up all the values so remove
                if (f == count)
                    freq.remove(v);
                // There is still remaining to use so put back to the map
                else
                    freq.put(v, f - count);
            }
        }
        return true;
    }
}