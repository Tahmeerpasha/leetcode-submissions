**231. Power of Two – Notes**

---

### **Problem**

Check if an integer `n` is a power of two.

---

## **1️⃣ Brute Force**

**Idea:**
Repeatedly divide `n` by 2 until it becomes 1. If at any step it’s not divisible by 2, return false.

**Time:** O(log n)
**Space:** O(1)

---

## **2️⃣ Better**

**Idea:**
Use logarithms: `log₂(n)` must be an integer.
But this may suffer from floating-point precision errors.

**Time:** O(1)
**Space:** O(1)

---

## **3️⃣ Optimal (Bit Manipulation)**

**Idea:**

* Powers of two have exactly **one bit set** in binary.
* `n & (n - 1)` removes the lowest set bit.
* For powers of two, result is 0.

**Code:**

```java
class Solution {
    public boolean isPowerOfTwo(int n) {
        return n > 0 && ((n & (n - 1)) == 0);
    }
}
```

**Time:** O(1)
**Space:** O(1)

---
