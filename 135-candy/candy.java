class Solution {
    public int candy(int[] ratings) {
        int sum = 1, i = 1, n = ratings.length;
        while (i < n) {
            if (ratings[i] == ratings[i - 1]) {
                sum += 1;
                i++;
                continue;
            }
            int peak = 1;
            while (i < n && ratings[i] > ratings[i - 1]) {
                peak++;
                i++;
                sum += peak;
            }
            int down = 0;
            while (i < n && ratings[i] < ratings[i - 1]) {
                down++;
                i++;
                sum += down;
            }
            down++;
            if (down > peak)
                sum += down - peak;
        }
        return sum;
    }
}