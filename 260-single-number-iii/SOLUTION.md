## **Problem Recap**

You’re given an integer array `nums` where **exactly two elements** appear only once and all the other elements appear exactly twice.
**Goal:** Return the two elements that appear once. Order doesn’t matter.

---

## **Approaches**

---

### **1. Brute Force (Using HashMap)**

**Idea:**

* Count frequency of each number.
* Return the two numbers with frequency = 1.

**Steps:**

1. Traverse array, store counts in `HashMap<Integer, Integer>`.
2. Collect numbers with frequency 1.

**Example:**
`nums = [1, 2, 1, 3, 2, 5]`
Map → `{1: 2, 2: 2, 3: 1, 5: 1}` → Output = `[3, 5]`

**Complexity:**

* **Time:** `O(N)`
* **Space:** `O(N)`

---

### **2. Better (Sort + Scan)**

**Idea:**

* Sort the array.
* Scan through: If current element != next, it’s a single occurrence.

**Steps:**

1. Sort array → `nums = [1, 1, 2, 2, 3, 5]`
2. Compare pairs, pick singles.

**Complexity:**

* **Time:** `O(N log N)` (sorting)
* **Space:** `O(1)` (ignoring sort space)

---

### **3. Optimal (Bit Manipulation)**

**Key Observations:**

* If we XOR all numbers, **pairs cancel out** (because `x ^ x = 0`), leaving `xor = a ^ b`, where `a` and `b` are the unique numbers.
* `a` and `b` must differ in at least one bit → that bit is set in `xor`.
* Use that set bit to **separate numbers** into two groups.
* XOR each group to get `a` and `b`.

---

**Steps:**

1. XOR all → result = `xor = a ^ b`.
2. Find **rightmost set bit**:

   * Formula: `(xor & (xor - 1)) ^ xor`
   * This bit distinguishes `a` from `b`.
3. Partition nums:

   * Group 1: That bit is **set** → XOR gives `a`.
   * Group 2: That bit is **unset** → XOR gives `b`.

---

**Example:**
`nums = [1, 2, 1, 3, 2, 5]`

Step 1: XOR all

```
1 ^ 2 ^ 1 ^ 3 ^ 2 ^ 5  
→ (1 ^ 1) ^ (2 ^ 2) ^ (3 ^ 5)  
→ 0 ^ 0 ^ 6  
→ xor = 6 (binary: 110)
```

Step 2: Find rightmost set bit

```
xor = 110  
xor - 1 = 101  
xor & (xor - 1) = 100  
(xor & (xor - 1)) ^ xor = 010  (decimal 2)
```

Mask = `010` means we’ll separate numbers based on the **2’s place** bit.

Step 3: Partition & XOR

* Group 1 (bit set at mask position): `2, 3, 2` → XOR = 3
* Group 2 (bit not set): `1, 1, 5` → XOR = 5

Output: `[3, 5]`

---

**Dry Run Table:**

| Num | Binary | Mask (010) Set? | Group |
| --- | ------ | --------------- | ----- |
| 1   | 001    | No              | G2    |
| 2   | 010    | Yes             | G1    |
| 1   | 001    | No              | G2    |
| 3   | 011    | Yes             | G1    |
| 2   | 010    | Yes             | G1    |
| 5   | 101    | No              | G2    |

Group 1 XOR = `2 ^ 3 ^ 2 = 3`
Group 2 XOR = `1 ^ 1 ^ 5 = 5`

---

**Complexity:**

* **Time:** `O(2N)` ≈ `O(N)` (One pass for `xor`, one for partition)
* **Space:** `O(1)`

---

**Code:**

```java
class Solution {
    public int[] singleNumber(int[] nums) {
        int xor = 0;
        for (int num : nums)
            xor ^= num; // xor = a ^ b

        int rightmost = (xor & (xor - 1)) ^ xor;

        int b1 = 0, b2 = 0;
        for (int num : nums) {
            if ((num & rightmost) != 0)
                b1 ^= num;
            else
                b2 ^= num;
        }
        return new int[] { b1, b2 };
    }
}
```

---
