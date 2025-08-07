## ✅ Problem: **Number of 1 Bits**

**Leetcode 191**
Given an unsigned integer `n`, return the **number of 1 bits** it has (also known as the **Hamming weight**).

---

## 🧠 Intuition

We need to count how many bits are set to `1` in the binary representation of `n`.

---

## 🔍 Example

**Input:** `n = 00000000000000000000000000001011`
**Output:** `3`
Explanation: The input has three `1`s in its binary form.

---

## ✅ Approaches:

---

### 📌 1. Brute Force: Divide by 2

**Idea:** Use `% 2` to check last bit and `/ 2` to shift right.

```java
while (n > 0) {
    if (n % 2 == 1)
        count++;
    n = n / 2;
}
```

🕒 **Time Complexity:** `O(log n)`
📦 **Space Complexity:** `O(1)`
⚠️ Works only for **positive `n`**

---

### 📌 2. Bit Manipulation (Right Shift `>>`)

**Idea:** Use `n & 1` to check LSB, then right-shift.

```java
while (n > 0) {
    count += n & 1;
    n = n >> 1;
}
```

🕒 **Time Complexity:** `O(log n)`
📦 **Space Complexity:** `O(1)`
✅ Handles bits more directly
⚠️ Still not ideal for **negative values** due to sign bit

---

### ✅ 3. Optimal: `n & (n - 1)` Trick

**Idea:**
Each time you do `n = n & (n - 1)`, it removes the **rightmost set bit**. Repeat until `n` becomes 0.

```java
while (n != 0) {
    n = n & (n - 1);
    count++;
}
```

🔁 Example:

```
n = 13 (1101)  
Step 1: n = 1101 -> 1100  
Step 2: n = 1100 -> 1000  
Step 3: n = 1000 -> 0000 (done)
Count = 3
```

🟢 **Best Performance**
🕒 **Time Complexity:** `O(k)` where `k = number of set bits`
📦 **Space Complexity:** `O(1)`
✅ Works for **negative numbers** too (with unsigned input)

---

## ✅ Final Code (Optimal)

```java
class Solution {
    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            n = n & (n - 1);
            count++;
        }
        return count;
    }
}
```

---

## 💡 Fun Fact

The trick `n & (n - 1)` is widely used in bit manipulation to:

* Count set bits
* Check if number is power of 2
* Optimize binary problems

---
