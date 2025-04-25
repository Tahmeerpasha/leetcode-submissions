**Leetcode 268 – Missing Number**
## ✅ Problem Statement

You're given an array `nums` containing `n` distinct numbers taken from `0` to `n`. Return the number that's **missing** from the array.

---

## 🟤 Brute Force Approach

### 🔹 Logic:
- For each number from `0 to n`, check if it exists in the array.

### 💡 Code:
```java
class Solution {
    public int missingNumber(int[] nums) {
        int n = nums.length;
        for (int i = 0; i <= n; i++) {
            boolean found = false;
            for (int num : nums) {
                if (num == i) {
                    found = true;
                    break;
                }
            }
            if (!found) return i;
        }
        return -1;
    }
}
```

### ⏱️ Time: O(n²)  
### 📦 Space: O(1)

---

## 🟡 Better Approach (Using HashSet)

### 🔹 Logic:
- Put all elements into a set.
- Check which number from `0 to n` is missing.

### 💡 Code:
```java
class Solution {
    public int missingNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) set.add(num);
        for (int i = 0; i <= nums.length; i++) {
            if (!set.contains(i)) return i;
        }
        return -1;
    }
}
```

### ⏱️ Time: O(n)  
### 📦 Space: O(n)

---

## 🟢 Optimized Approach (Sum Formula – Your Code)

### 🔹 Logic:
- Expected sum = `n * (n + 1) / 2`
- Subtract actual sum from it

### ✅ Code:
```java
class Solution {
    public int missingNumber(int[] nums) {
        int sum = 0, n = nums.length;
        for (int value : nums) sum += value;
        return (n * (n + 1)) / 2 - sum;
    }
}
```

### ⏱️ Time: O(n)  
### 📦 Space: O(1)

---

## 🧠 Dry Run

### Input: `nums = [3, 0, 1]`

- `n = 3`
- Expected sum = `3 * (3 + 1) / 2 = 6`
- Actual sum = `3 + 0 + 1 = 4`
- Missing number = `6 - 4 = 2`

✅ Output: `2`

---

## 🖼️ Visual Illustration

```text
Index:     0   1   2   3
Nums:      3   0   1   ?

Range:     [0, 1, 2, 3]
Present:   [✓, ✓, ✗, ✓] → 2 is missing
```

> Total Expected Sum = 0 + 1 + 2 + 3 = 6  
> Actual Sum = 0 + 1 + 3 = 4  
> Missing = 6 - 4 = **2**

---
## 🧩 XOR Approach – Missing Number

### 🔹 Logic:
- XOR of a number with itself is 0: `x ^ x = 0`
- XOR of a number with 0 is the number itself: `x ^ 0 = x`
- XOR all indices `0 to n` and all numbers in `nums`.
- The result will be the missing number.

Why?  
Because all numbers in range `0...n` cancel out with the ones in `nums`, except the missing number.

---

### ✅ Code:
```java
class Solution {
    public int missingNumber(int[] nums) {
        int xor = 0;
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            xor ^= i;        // XOR with indices
            xor ^= nums[i];  // XOR with array values
        }

        xor ^= n; // XOR with the last number (since loop goes to n-1)
        return xor;
    }
}
```

---

### ⏱️ Time: O(n)  
### 📦 Space: O(1)

---

## 🧠 Dry Run

### Input: `nums = [3, 0, 1]`

- `n = 3`
- Indices: 0 ^ 1 ^ 2 ^ 3 = `0 ^ 1 = 1`, `1 ^ 2 = 3`, `3 ^ 3 = 0`
- nums: 3 ^ 0 ^ 1 = `3 ^ 0 = 3`, `3 ^ 1 = 2`
- Final XOR = `0 ^ 2 = 2`

✅ Output: `2`

---

## 🧠 Visual Thought:
```
XOR of full range (0 to 3)    = 0 ^ 1 ^ 2 ^ 3 = X1
XOR of array [3, 0, 1]        = 3 ^ 0 ^ 1     = X2
Missing Number = X1 ^ X2
```

👉 All common elements cancel out due to XOR, missing one remains.
