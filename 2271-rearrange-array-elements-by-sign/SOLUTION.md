### 🔍 Problem Summary:
You're given an integer array `nums` of **even length**, where **half of the integers are positive and the other half are negative**. You must rearrange the array such that:
- Positive numbers appear at **even indices**
- Negative numbers appear at **odd indices**

---

### ✅ Constraints:
- The array will always have the same number of positive and negative numbers.
- The relative order of positives or negatives doesn't need to be preserved.

---

### ✅ Optimal Solution (O(n) Time, O(n) Space):

```java
class Solution {
    public int[] rearrangeArray(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        int pos = 0, neg = 1;
        for (int i = 0; i < n; i++) {
            if (nums[i] < 0) {
                result[neg] = nums[i];
                neg += 2;
            } else {
                result[pos] = nums[i];
                pos += 2;
            }
        }
        return result;
    }
}
```

---

### ✅ Brute Force (Separate Positives and Negatives):
1. Store all positive numbers in one array.
2. Store all negative numbers in another.
3. Merge by placing positives at even and negatives at odd indices.

**Time:** O(n)  
**Space:** O(n)  

---

### ✅ Edge Case:
No edge cases since input guarantees equal number of positives and negatives.

---

### 📊 Dry Run Example:

Input: `nums = [3,1,-2,-5,2,-4]`  
Output: `[3, -2, 1, -5, 2, -4]`

**Index Assignment:**
- Positives: 3 → index 0, 1 → index 2, 2 → index 4
- Negatives: -2 → index 1, -5 → index 3, -4 → index 5

---

### 🧠 Intuition:
Since we know the number of positives and negatives is equal and the array length is even, we can confidently assign:
- Even indices → positive
- Odd indices → negative

---
