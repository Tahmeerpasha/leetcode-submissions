# 961. N-Repeated Element in Size 2N Array

## Problem Recap

* Array size = `2n`
* Total unique elements = `n + 1`
* **Exactly one element appears `n` times**
* All other elements appear **once**
* Return the repeated element

---

## 1️⃣ Brute Force Approach

### Idea

For every element, count its occurrences by scanning the entire array.

### Approach

* Pick an element
* Count how many times it appears
* If count == `n`, return it

### Complexity

* **Time:** `O(n²)`
* **Space:** `O(1)`

### Why it’s bad

* Repeated counting
* Completely ignores constraints
* Won’t pass large inputs efficiently

---

## 2️⃣ Better Approach (HashMap Counting) ✅

### Idea

Count frequencies using a map and return the element with maximum frequency.

### Why it works

* One element appears `n` times
* All others appear once
* Max frequency element is the answer

### Your Code (kept as-is)

```java
class Solution {
    public int repeatedNTimes(int[] nums) {
        int len = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int maxNum = 0, count = 0;
        for (int num : map.keySet()) {
            if (count < map.get(num)) {
                maxNum = num;
                count = map.get(num);
            }
        }
        return maxNum;
    }
}
```

### Complexity

* **Time:** `O(n)`
* **Space:** `O(n)`

### Interview Notes

* Correct and easy to explain
* Acceptable for most interviews
* But **not optimal** due to extra space

---

## 3️⃣ Optimal Approach (Constant Space) ⭐

### Key Observation

The repeated element appears **n times in an array of size 2n**.

This guarantees:

* The repeated element **must appear at least twice within distance ≤ 2**
* It is **impossible** for all occurrences to be far apart

This is a **distribution guarantee**, not a counting problem.

---

### Optimal Idea

While traversing the array:

* If `nums[i] == nums[i+1]` → answer
* If `nums[i] == nums[i+2]` → answer

The repeated element **must collide locally**.

### Complexity

* **Time:** `O(n)`
* **Space:** `O(1)`

### Why this is optimal

* Uses constraints intelligently
* No extra memory
* Early exit
* Strong interview signal

---

## ❌ Why XOR Does NOT Work (Important)

XOR only works when:

* All elements appear **exactly twice** except one

Here:

* One element appears `n` times
* Others appear once
* Mixed parity → no reliable cancellation

👉 XOR gives **incorrect results**

---

## Interview Tips & Tricks 🎯

### What interviewers want to see

* You **noticed the frequency distribution**
* You didn’t blindly use HashMap
* You used **problem guarantees**

### How to explain optimally

> “Since the repeated element occupies half the array, it must appear at least twice within a very small window, so we can detect it without extra space.”

### Order of presenting solutions

1. HashMap (safe baseline)
2. Then explain **why it’s not optimal**
3. Derive the **constant-space solution**

That shows maturity.

---

## Final Comparison Summary

| Approach     | Time  | Space | Interview Quality |
| ------------ | ----- | ----- | ----------------- |
| Brute Force  | O(n²) | O(1)  | ❌ Poor            |
| HashMap      | O(n)  | O(n)  | ✅ Acceptable      |
| Window Check | O(n)  | O(1)  | ⭐ Excellent       |

---

### Takeaway

Your solution is **correct**.
The optimal one shows **constraint awareness**.

This problem is a **pattern**:

> “High frequency element → local collision guaranteed”

Lock that pattern in your head — it comes back in interviews.
