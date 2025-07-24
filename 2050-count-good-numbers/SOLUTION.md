### 🔢 **Problem: 1922. Count Good Numbers**

You are given a number `n`. A number is called "good" if:

* The digits at **even indices** (0-based) are even digits (0, 2, 4, 6, 8) → 5 choices
* The digits at **odd indices** are prime digits (2, 3, 5, 7) → 4 choices

You must count the **total number of such n-digit good numbers** modulo `10^9 + 7`.

---

### ✅ **Optimized Approach (Exponentiation)**

We notice:

* Even indices → `ceil(n / 2)` positions → each has 5 options
* Odd indices → `floor(n / 2)` positions → each has 4 options

Hence, total count = `5^even * 4^odd`.

Instead of looping `n` times (TLE), use **fast modular exponentiation**.

---

### ✅ **Code Walkthrough**

```java
public int countGoodNumbers(long n) {
    long product = 1;
    int mod = 1_000_000_007;

    long odd = n / 2;
    long even = n / 2 + n % 2;

    return (int) ((fastPow(5, even) * fastPow(4, odd)) % mod);
}

private long fastPow(int x, long n) {
    int mod = 1_000_000_007;
    if (n == 0)
        return 1;
    long ans = fastPow(x, n / 2);
    ans *= ans;
    ans %= mod;
    if (n % 2 == 1)
        ans *= x;
    ans %= mod;
    return ans;
}
```

---

### 🧠 **Key Observations**

* Instead of constructing every number, compute the **count** using math.
* Use **modular exponentiation** to handle large powers (since `n` can be up to `10^15`).
* `(a * b) % mod = ((a % mod) * (b % mod)) % mod` ensures no overflow.

---

### 🧮 **Time and Space Complexity**

| Type             | Complexity                                |
| ---------------- | ----------------------------------------- |
| ⏱ Time (main)    | `O(log n)` – due to fast exponentiation   |
| 🧠 Space (stack) | `O(log n)` – recursive stack of `fastPow` |
| 💾 Extra Space   | `O(1)` – excluding recursion              |

---

### 📌 Example

If `n = 4`, even indices = 2, odd indices = 2
→ total = `5^2 * 4^2 = 25 * 16 = 400`

---

### ✅ Tips

* Always watch for modulo overflows when multiplying.
* Handle power operations via **binary exponentiation** for better performance.
