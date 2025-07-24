### ✅ Problem:

Implement the function `myPow(x, n)` which calculates `x^n` (x raised to the power n), where:

* `x` is a double,
* `n` is an integer (can be negative),
* Avoid library functions like `Math.pow()`.

---

## ✅ **1. Iterative Approach**

### 🔍 Key Idea:

Use **Exponentiation by Squaring** to reduce the time complexity from O(n) to **O(log n)**.

### 🧠 Logic:

* If `n` is negative, invert `x` and take absolute of `n` using `long` to handle `Integer.MIN_VALUE`.
* Use a loop:

  * If `n` is odd: `ans = ans * x; n--`
  * If `n` is even: square `x` and halve `n`.

### 📦 Code Highlights:

```java
double ans = 1.0;
long nn = n;
if (nn < 0) {
    nn = -nn;
    x = 1 / x;
}
while (nn > 0) {
    if (nn % 2 == 1) {
        ans *= x;
        nn--;
    } else {
        x *= x;
        nn /= 2;
    }
}
```

### 🧮 Complexity:

* **Time:** `O(log n)`
* **Space:** `O(1)` (no recursion stack used)

---

## ✅ **2. Recursive Approach (Exponentiation by Squaring)**

### 🧠 Logic:

* Base case: If `n == 0`, return 1.
* Recursive step:

  * Compute `half = myPow(x, n/2)`
  * If `n` is even: return `half * half`
  * If `n` is odd: return `half * half * x`

### 🔁 Handles:

* Negative powers by converting `x` to `1/x` and `n` to `-n`.

### 📦 Code Highlights:

```java
if (n == 0) return 1;
long N = n;
if (N < 0) {
    x = 1 / x;
    N = -N;
}
return fastPow(x, N);

private double fastPow(double x, long n) {
    if (n == 0) return 1;
    double half = fastPow(x, n / 2);
    return (n % 2 == 0) ? half * half : half * half * x;
}
```

### 🧮 Complexity:

* **Time:** `O(log n)` — cuts power in half each step
* **Space:** `O(log n)` — due to recursive call stack

---

### ✅ Summary Table:

| Approach  | Time Complexity | Space Complexity | Notes                                 |
| --------- | --------------- | ---------------- | ------------------------------------- |
| Iterative | O(log n)        | O(1)             | Faster, avoids recursion              |
| Recursive | O(log n)        | O(log n)         | Elegant, but extra space due to stack |

---
