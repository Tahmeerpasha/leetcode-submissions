class Solution {
    public int candy(int[] ratings) {
        int n = ratings.length;
        int sum = 1, i = 1;
        while (i < n) {
            if (ratings[i] == ratings[i - 1]) {
                sum += 1;
                i++;
                continue;
            }

            // Increasing slope
            int up = 1;
            while (i < n && ratings[i] > ratings[i - 1]) {
                // Increment first since the current child has a higher rating 
                // and must get one more candy than the previous child
                up++;
                sum += up;
                i++;
            }

            // Decreasing slope
            int down = 1;
            while (i < n && ratings[i] < ratings[i - 1]) {
                // Add current down value first since the first child on the 
                // downward slope gets exactly 1 candy less than the peak
                sum += down;
                down++;
                i++;
            }

            // Adjust for peak double-count: if the downhill is longer than the uphill,
            // add the difference to correct the overlap at the peak
            if (down > up)
                sum += down - up;
        }
        return sum;
    }
}