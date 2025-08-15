class Solution {
    public int[][] merge(int[][] intervals) {
        int n = intervals.length;
        List<int[]> answer = new ArrayList<>();
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        // Brute force -> Time = O(2n) && Space = O(n)
        // for(int i=0; i<n; i++){
        // select an interval:
        //     int start = intervals[i][0];
        //     int end = intervals[i][1];
        //Skip all the merged intervals:
        //     if(!answer.isEmpty() && end <= answer.get(answer.size()-1)[1])
        //         continue;
        //check the rest of the intervals:
        //     for(int j=i+1; j<n; j++){
        //         if(intervals[j][0] <= end)
        //             end = Math.max(end, intervals[j][1]);
        //         else break;
        //     }
        //     answer.add(new int[]{start,end});
        // }

        // Optimal -> Time = O(n) && Space -> O(n)
        for (int i = 0; i < n; i++) {
            // if the current interval does not
            // lie in the last interval:
            if (answer.isEmpty() || intervals[i][0] > answer.get(answer.size() - 1)[1])
                answer.add(intervals[i]);
            else
                // if the current interval
                // lies in the last interval:
                answer.get(answer.size() - 1)[1] = Math.max(answer.get(answer.size() - 1)[1], intervals[i][1]);
        }

        return answer.toArray(new int[answer.size()][]);
    }
}