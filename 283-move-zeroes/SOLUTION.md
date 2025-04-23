## 🔄 LeetCode 283: Move Zeroes

### ✅ Problem Statement
Given an integer array `nums`, move all the `0`'s to the end of it while maintaining the **relative order** of the non-zero elements.

You must do this **in-place** without making a copy of the array.

---

### 💡 Intuition
We want to keep all non-zero elements at the front of the array and move all `0`s to the end, **without changing the order** of the non-zero elements.

---

### 🔁 Step-by-Step Logic

- Initialize a pointer `j = 0` to track the **position where the next non-zero element should go**.
- Loop through the array using index `i`:
  - If `nums[i]` is not `0`, swap `nums[i]` with `nums[j]` and increment `j`.
  - This way, all non-zero elements are moved to the front, and zeroes are pushed to the back.

---

### 🧪 Example

```java
Input:  [0, 1, 0, 3, 12]
Output: [1, 3, 12, 0, 0]
```

**Explanation:**

- i = 0 → nums[0] = 0 → skip  
- i = 1 → nums[1] = 1 → swap(nums[1], nums[0]) → [1, 0, 0, 3, 12], j = 1  
- i = 2 → nums[2] = 0 → skip  
- i = 3 → nums[3] = 3 → swap(nums[3], nums[1]) → [1, 3, 0, 0, 12], j = 2  
- i = 4 → nums[4] = 12 → swap(nums[4], nums[2]) → [1, 3, 12, 0, 0], j = 3  

---

### 💻 Java Code

```java
class Solution {
    public void moveZeroes(int[] nums) {
        int j = 0, temp = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                j++;
            }
        }
    }
}
```

---

### ⏱️ Time & Space Complexity

- **Time Complexity:** `O(n)` – Single pass through the array.
- **Space Complexity:** `O(1)` – In-place operations, no extra space used.
