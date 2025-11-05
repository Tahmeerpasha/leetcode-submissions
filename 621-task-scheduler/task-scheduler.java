class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] map = new int[26];
        for (char task : tasks)
            map[task - 'A']++;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int time = 0;
        for (int i = 0; i < 26; i++) {
            if (map[i] > 0)
                pq.add(map[i]);
        }

        while (!pq.isEmpty()) {
            List<Integer> temp = new ArrayList<>();
            for (int i = 1; i <= n + 1; i++) {
                if (!pq.isEmpty()) {
                    int freq = pq.poll();
                    freq--;
                    temp.add(freq);
                }
            }
            for (int num : temp)
                if (num > 0)
                    pq.add(num);

            if (pq.isEmpty())
                time += temp.size();
            else
                time += n + 1;
        }
        return time;
    }
}