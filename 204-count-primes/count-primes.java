class Solution {
    public int countPrimes(int n) {
        if (n <= 2)
            return 0;
        int[] primes = new int[n];
        for (int i = 0; i < primes.length; i++)
            primes[i] = 1;

        for (int i = 2; i * i < n; i++) {
            if (primes[i] == 1) {
                for (int j = i * i; j < n; j = j + i) {
                    primes[j] = 0;
                }
            }
        }
        int count = 0;
        for (int i = 2; i < primes.length; i++)
            if (primes[i] == 1)
                count++;

        return count;
    }
}