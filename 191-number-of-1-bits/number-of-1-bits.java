class Solution {
    public int hammingWeight(int n) {
        int count = 0;
        // normal method
        // while (n > 0) {
        //     if (n % 2 == 1)
        //         count++;
        //     n = n / 2;
        // }

        // Faster method using bits
        while (n > 0) {
            count += n & 1;
            n = n >> 1;
        }
        return count;
    }
}