class Solution {
    public int countPrimes(int n) {
        // if (n <= 2)
        //     return 0;
        // int[] primes = new int[n];
        // for (int i = 2; i < primes.length; i++)
        //     primes[i] = 1;

        // for (int i = 2; i * i < n; i++) {
        //     if (primes[i] == 1) {
        //         for (int j = i * i; j < n; j = j + i) {
        //             primes[j] = 0;
        //         }
        //     }
        // }
        // int count = 0;
        // for (int i = 2; i < primes.length; i++)
        //     if (primes[i] == 1)
        //         count++;

        // return count;

        if (n < 2)
            return 0;

        boolean[] isPrime = new boolean[n];//creating an boolean array
        isPrime[0] = isPrime[1] = true;

        //iterating over the array from 2 to sqrt of n
        for (int i = 2; i * i < n; i++) {
            if (!isPrime[i]) {
                for (int j = i * i; j < n; j += i) {
                    isPrime[j] = true;
                }
            }
        }

        int cnt = 0;
        for (boolean b : isPrime) {
            if (!b)
                cnt++;
        }

        return cnt;
    }
}