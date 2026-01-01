# 66. Plus One

## Problem Summary

You’re given a non-negative integer represented as an array of digits.
Each element contains a single digit. Increment the number by **1** and return the resulting array.

---

## Brute Force Approach ❌ (Conceptual – NOT recommended)

### Idea

* Convert digits → number
* Add 1
* Convert number back to digit array

### Pseudocode

```
num = convert digits to number
num = num + 1
convert num back to array
```

### Why it’s bad

* Integer overflow for large inputs
* Violates problem intent
* Fails hidden test cases

### Complexity

* **Time:** O(n)
* **Space:** O(n)

### Interview Verdict

🚫 **Never use this**
If you do this in an interview, expect rejection.

---

## Better Approach ❌ (Not needed here)

There is **no meaningful “better” approach** distinct from the optimal one.

Reason:

* You must handle carry from right to left
* Any correct solution ends up being the optimal approach

So we jump straight to the correct one.

---

## Optimal Approach ✅ (Carry Simulation)

### Key Insight

* Addition happens from **right to left**
* Only digits `9` create carry
* Stop early once carry is resolved

---

### Steps

1. Traverse from last index to first
2. If digit < 9 → increment and return
3. If digit == 9 → set to 0 and continue
4. If all digits were 9 → create new array with leading `1`

---

### Code (KEEP THIS)

```java
class Solution {
    public int[] plusOne(int[] digits) {
        int n = digits.length;

        for (int i = n - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0; // carry forward
        }

        // If all digits were 9
        int[] result = new int[n + 1];
        result[0] = 1;
        return result;
    }
}
```

---

### Complexity

* **Time:** O(n)
* **Space:** O(1) extra space
  (Output array doesn’t count)

---

## Edge Cases to Mention in Interview

* `[1,2,3] → [1,2,4]`
* `[9] → [1,0]`
* `[9,9,9] → [1,0,0,0]`
* Large digit arrays (why brute fails)

---

## Interview Tips 🔥

* **Never convert digits to a number**
* Think in terms of **manual addition**
* Early return improves efficiency
* Output array creation only happens in **all-9s case**
* Mention why space is O(1)

---

## Pattern Mapping

* **Array + Carry Simulation**
* Similar to:

  * Add Binary
  * Add Strings
  * Multiply Strings

---
