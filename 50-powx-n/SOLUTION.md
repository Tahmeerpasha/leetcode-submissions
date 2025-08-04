## 🔢 Problem Statement

Implement the function `pow(x, n)` which calculates `x` raised to the power `n` (i.e., $x^n$).

* You must not use built-in library functions like `Math.pow`.
* Handle edge cases like negative powers and integer overflow (`n = Integer.MIN_VALUE`).

---

## 🔨 Brute Force Approach

### 💡 Idea

Multiply `x` with itself `n` times.

* If `n` is negative, invert `x` and make `n` positive.

### 🧠 Logic

* $x^n = x \times x \times \dots \times x$ (n times)
* If `n` < 0 → use `1/x` and `-n`

### 🧾 Code

```java
public double myPow(double x, int n) {
    long nn = n;
    if (nn < 0) {
        x = 1 / x;
        nn = -nn;
    }
    double ans = 1.0;
    for (long i = 0; i < nn; i++) {
        ans *= x;
    }
    return ans;
}
```

### ⏱️ Time Complexity

* **O(n)**

### 🗂️ Space Complexity

* **O(1)**

---

## ⚡ Better Approach: Recursive Fast Exponentiation (Exponentiation by Squaring)

### 💡 Idea

Use divide and conquer:

* $x^n = (x^{n/2})^2$ if `n` is even
* $x^n = (x^{n/2})^2 \times x$ if `n` is odd

### 🧾 Code

```java
public double myPow(double x, int n) {
    long N = n;
    if (N < 0) {
        x = 1 / x;
        N = -N;
    }
    return fastPow(x, N);
}

private double fastPow(double x, long n) {
    if (n == 0) return 1;
    double half = fastPow(x, n / 2);
    if (n % 2 == 0)
        return half * half;
    else
        return half * half * x;
}
```

### ⏱️ Time Complexity

* **O(log n)**

### 🗂️ Space Complexity

* **O(log n)** (due to recursion stack)

---

## 🚀 Optimal Approach: Iterative Binary Exponentiation

### 💡 Idea

Same as the recursive approach but implemented iteratively to avoid recursion overhead and stack usage.

### 🧾 Code

```java
public double myPow(double x, int n) {
    if (n == 0) return 1;
    if (x == 0) return 0;

    long binForm = n;
    if (binForm < 0) {
        x = 1 / x;
        binForm = -binForm;
    }

    double ans = 1.0;
    while (binForm > 0) {
        if (binForm % 2 == 1)
            ans *= x;
        x *= x;
        binForm /= 2;
    }
    return ans;
}
```

### ⏱️ Time Complexity

* **O(log n)**

### 🗂️ Space Complexity

* **O(1)**

---

## 🌲 Recursion Tree Visualization (for Recursive Version)

Example: `x = 2, n = 5`

```
fastPow(2, 5)
├── fastPow(2, 2)
│   └── fastPow(2, 1)
│       └── fastPow(2, 0) → 1
│       → returns 1 * 1 * 2 = 2
│   → returns 2 * 2 = 4
→ returns 4 * 4 * 2 = 32
```

---

## 🧠 Key Notes

* Always convert `n` to `long` to avoid overflow (`-Integer.MIN_VALUE` overflows `int`).
* Use `1/x` when the power is negative.
* Multiplication-based squaring reduces complexity from **O(n)** to **O(log n)**.

---
