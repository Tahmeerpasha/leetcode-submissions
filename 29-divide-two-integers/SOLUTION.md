## ✅ 29. Divide Two Integers

> **Problem Statement:**
> Given two integers `dividend` and `divisor`, divide them **without using multiplication, division, and mod operator**.
> The result should be truncated toward **zero**. Handle overflow for edge cases like `Integer.MIN_VALUE / -1`.

---

### 🌟 Example

```text
Input: dividend = 10, divisor = 3
Output: 3  (because 10 / 3 = 3.333... → truncated to 3)

Input: dividend = 7, divisor = -3
Output: -2
```

---

## 🧠 Approaches

---

### 1️⃣ Brute Force – Repeated Subtraction

#### 🔹 Idea:

* Repeatedly subtract `divisor` from `dividend` until what's left is smaller than `divisor`.
* Keep a counter for the number of times you subtract.

#### ✅ Code:

```java
public int divide(int dividend, int divisor) {
    if (dividend == divisor) return 1;

    boolean sign = (dividend >= 0) == (divisor >= 0);
    long n = Math.abs((long) dividend);
    long d = Math.abs((long) divisor);
    long quotient = 0;

    while (n >= d) {
        n -= d;
        quotient++;
    }

    return sign ? (int) quotient : (int) -quotient;
}
```

#### ⏳ Time Complexity: `O(|quotient|)` → can be **very slow** if dividend is huge.

#### 🧠 Space Complexity: `O(1)`

---

### 2️⃣ Better – Exponential Doubling

#### 🔹 Idea:

* Instead of subtracting `divisor` one time per loop, double it (shift left) until it surpasses `n`.
* This reduces the number of subtractions significantly.

#### ✅ Code:

```java
public int divide(int dividend, int divisor) {
    if (dividend == divisor) return 1;

    boolean sign = (dividend >= 0) == (divisor >= 0);
    long n = Math.abs((long) dividend);
    long d = Math.abs((long) divisor);
    long quotient = 0;

    while (n >= d) {
        long temp = d;
        long multiple = 1;
        while (n >= (temp << 1)) {
            temp <<= 1;
            multiple <<= 1;
        }
        n -= temp;
        quotient += multiple;
    }

    if (quotient >= (1L << 31))
        return sign ? Integer.MAX_VALUE : Integer.MIN_VALUE;
    return sign ? (int) quotient : (int) -quotient;
}
```

#### ⏳ Time Complexity: `O(log(|quotient|))`

#### 🧠 Space Complexity: `O(1)`

#### 📌 Note: This is **much faster** than brute force because we jump in powers of 2.

---

### 3️⃣ Optimal – Bit Manipulation (Your Code)

#### 🔹 Idea:

* Works just like **long division** but using **bit shifts**.
* At each step, find the **largest power of two** multiple of divisor that fits into current dividend.
* Subtract that value and add the multiple to the quotient.
* Continue until dividend is less than divisor.

#### ✅ Code:

```java
class Solution {
    public int divide(int dividend, int divisor) {
        if (dividend == divisor) return 1;
        if (divisor == 1) return dividend;

        boolean sign = (dividend >= 0) == (divisor >= 0);
        long n = Math.abs((long) dividend);
        long d = Math.abs((long) divisor);
        long quotient = 0;

        while (n >= d) {
            int cnt = 0;
            while (n >= (d << (cnt + 1))) cnt++;
            quotient += 1L << cnt;
            n -= d << cnt;
        }

        if (quotient >= (1L << 31))
            return sign ? Integer.MAX_VALUE : Integer.MIN_VALUE;

        return sign ? (int) quotient : (int) -quotient;
    }
}
```

#### ⏳ Time Complexity: `O(log(|dividend|))`

Each inner loop finds the largest shift possible.

#### 🧠 Space Complexity: `O(1)`

---

## 📌 Summary Table

| Approach    | Technique              | Time Complexity | Space Complexity | Notes |     
| ----------- | ---------------------- | --------------- | ---------------- | ----- | 
| Brute Force | Repeated Subtraction   | O(quotient)      | O(1)            | Very slow for large inputs               |
| Better      | Doubling / Exponential | O(log(quotient)  | O(1)            | Faster by jumping powers of 2            |
| Optimal     | Bit Manipulation       | O(log(quotient)  | O(1)            | Directly works like binary long division |

---
