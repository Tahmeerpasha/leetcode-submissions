class Solution {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        if (hand.length % groupSize != 0)
            return false;
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : hand)
            freq.put(num, freq.getOrDefault(num, 0) + 1);

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int num : hand)
            pq.add(num);

        while (!pq.isEmpty()) {
            int top = pq.poll();
            if(freq.get(top) <=0)continue;
            for (int i = 1; i < groupSize; i++) {
                if (freq.get(top + i) == null || freq.get(top + i) <= 0)
                    return false;
                else if(freq.get(top+i) > 0)
                    freq.put(top + i, freq.getOrDefault(top + i, 0) - 1);
            }
            freq.put(top, freq.getOrDefault(top, 0) - 1);
        }
        return true;
    }
}