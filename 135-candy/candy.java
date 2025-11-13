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
                up++;
                sum += up;
                i++;
            }

            // Decreasing slope
            int down = 1;
            while (i < n && ratings[i] < ratings[i - 1]) {
                sum += down;
                down++;
                i++;
            }
            // down++;
            // Adjust peak overlap
            if (down > up)
                sum += down - up;
        }
        return sum;
    }
}