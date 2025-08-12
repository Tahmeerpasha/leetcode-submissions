## **Problem Recap**

Given `n`, count the number of **prime numbers** strictly less than `n`.

---

## **1. Brute Force**

**Idea:**
For each number `x` in range `[2, n)`, check if it’s prime by trying division up to `x-1`.

**Steps:**

1. Loop `i = 2` to `n-1`.
2. Check if divisible by any `j = 2` to `i-1`.
3. Count if prime.

**Complexity:**

* **Time:** `O(N²)`
* **Space:** `O(1)`

---

## **2. Better (Trial Division √N)**

**Idea:**
Instead of checking divisibility till `i-1`, only check up to `√i`.

**Steps:**

1. Loop `i = 2` to `n-1`.
2. Check divisibility for `j = 2` to `√i`.

**Complexity:**

* **Time:** `O(N√N)`
* **Space:** `O(1)`

---

## **3. Optimal (Sieve of Eratosthenes)** ✅

**Idea:**

* Use a boolean array to mark primes.
* Initially assume all numbers are prime.
* For each prime `p`, mark all multiples starting from `p²` as not prime.

**Steps:**

1. Create boolean/int array `primes[n]` and mark all `1` except index `0` & `1`.
2. For `i` in `2` to `√n`:

   * If prime, mark all multiples of `i` starting from `i*i` as 0.
3. Count remaining `1`s.

**Example:** n = 10

* Initial: `[1,1,1,1,1,1,1,1,1,1]` (index from 0)
* After i=2: mark 4,6,8
* After i=3: mark 9
* Remaining primes < 10 → 2,3,5,7 → count = 4.

**Complexity:**

* **Time:** `O(N log log N)`
* **Space:** `O(N)`

---

**Code (Optimal):**

```java
class Solution {
    public int countPrimes(int n) {
        if (n <= 2) return 0;

        int[] primes = new int[n];
        for (int i = 2; i < n; i++) primes[i] = 1;

        for (int i = 2; i * i < n; i++) {
            if (primes[i] == 1) {
                for (int j = i * i; j < n; j += i) {
                    primes[j] = 0;
                }
            }
        }

        int count = 0;
        for (int i = 2; i < n; i++) 
            if (primes[i] == 1) count++;

        return count;
    }
}
```

---
