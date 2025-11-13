class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int n = intervals.length;
        int[][] result = new int[n + 1][2];
        int i = 0, j = 0;
        // Left non merging part
        while (i < n && intervals[i][1] < newInterval[0]) {
            result[j][0] = intervals[i][0];
            result[j][1] = intervals[i][1];
            i++;
            j++;
        }

        // Merging part
        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        result[j][0] = newInterval[0];
        result[j][1] = newInterval[1];
        j++;

        // Remaining non merging intervals in the right
        while (i < n) {
            result[j][0] = intervals[i][0];
            result[j][1] = intervals[i][1];
            i++;
            j++;
        }
        return Arrays.copyOf(result, j);
    }
}