class Solution {
    public int[][] merge(int[][] intervals) {
        int n = intervals.length;
        List<int[]> answer = new ArrayList<>();
        Arrays.sort(intervals, (a,b) -> a[0]-b[0]);
        // Brute force -> O(2n)
        // for(int i=0; i<n; i++){
        //     int start = intervals[i][0];
        //     int end = intervals[i][1];

        //     if(!answer.isEmpty() && end <= answer.get(answer.size()-1)[1])
        //         continue;
            
        //     for(int j=i+1; j<n; j++){
        //         if(intervals[j][0] <= end)
        //             end = Math.max(end, intervals[j][1]);
        //         else break;
        //     }
        //     answer.add(new int[]{start,end});
        // }

        for(int i=0; i<n; i++){
            if(answer.isEmpty() || intervals[i][0] > answer.get(answer.size()-1)[1])
                answer.add(new int[]{intervals[i][0], intervals[i][1]});
            else
                answer.get(answer.size()-1)[1] = Math.max(answer.get(answer.size()-1)[1], intervals[i][1]);
        }

        return answer.toArray(new int[answer.size()][]);
    }
}