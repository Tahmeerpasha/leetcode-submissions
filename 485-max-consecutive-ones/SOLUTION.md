## 🔹 Problem:
> Given a binary array `nums`, return the maximum number of consecutive 1s in the array.

---

## 💪 Brute Force Approach

### 🔸 Logic:
Check for every subarray, count the 1s, and track the longest streak.

### 🧾 Code:
```java
class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        for(int i = 0; i < nums.length; i++) {
            int count = 0;
            for(int j = i; j < nums.length; j++) {
                if(nums[j] == 1) count++;
                else break;
            }
            max = Math.max(max, count);
        }
        return max;
    }
}
```

### ⏱ Time: O(n²)  
### 📦 Space: O(1)

---

## 🚀 Optimized Approach (Your Code)

### 🔸 Logic:
- Keep a running count of consecutive 1s.
- Reset the counter when a 0 is encountered.
- Track the maximum during the process.

### ✅ Code:
```java
class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0, ones = 0;
        for(int num : nums) {
            if(num == 1)
                ++ones;
            else 
                ones = 0;
            max = Math.max(max, ones);
        }
        return max;
    }
}
```

### ⏱ Time: O(n)  
### 📦 Space: O(1)

---

## 🔍 Dry Run

### Input: `nums = [1, 1, 0, 1, 1, 1]`

| Index | num | ones | max |
|-------|-----|------|-----|
| 0     | 1   | 1    | 1   |
| 1     | 1   | 2    | 2   |
| 2     | 0   | 0    | 2   |
| 3     | 1   | 1    | 2   |
| 4     | 1   | 2    | 2   |
| 5     | 1   | 3    | 3 ✅|

🔚 Output: `3`

---

## 🧠 Visual Representation:

```
Input: [1, 1, 0, 1, 1, 1]
          ↑  ↑     ↑  ↑  ↑
         streak: 2   3 ← max
```

🟩 = 1s streaks, 🔴 = reset on 0.

---
