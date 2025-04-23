## 🌀 LeetCode 189: Rotate Array (Right Rotation)

### ✅ Problem
Given an integer array `nums`, rotate the array to the right by `k` steps, where `k` is non-negative.

---

### 💡 Intuition
To rotate the array to the right by `k` steps, we use **array reversal**. The idea is to reverse parts of the array strategically so the final array is rotated without using extra space.

---

### 🔁 Step-by-Step Logic

1. **Reverse the first part:** From index `0` to `len - k - 1`
2. **Reverse the second part:** From index `len - k` to `len - 1`
3. **Reverse the entire array:** From index `0` to `len - 1`

This gives us the rotated array in-place.

---

### 🧪 Example

```java
nums = [1, 2, 3, 4, 5, 6, 7]
k = 3
```

#### Step 1: Reverse first part
Reverse from index `0` to `3`  
Result: `[4, 3, 2, 1, 5, 6, 7]`

#### Step 2: Reverse second part
Reverse from index `4` to `6`  
Result: `[4, 3, 2, 1, 7, 6, 5]`

#### Step 3: Reverse entire array
Reverse from index `0` to `6`  
Final Result: `[5, 6, 7, 1, 2, 3, 4]`

---

### 💻 Java Code

```java
class Solution {
    public void rotate(int[] nums, int k) {
        if (k > nums.length)
            k = k % nums.length;

        reverseArray(nums, 0, nums.length - k - 1);   // Step 1
        reverseArray(nums, nums.length - k, nums.length - 1);   // Step 2
        reverseArray(nums, 0, nums.length - 1);   // Step 3
    }

    void reverseArray(int[] nums, int startIndex, int endIndex) {
        while (startIndex < endIndex) {
            int temp = nums[startIndex];
            nums[startIndex] = nums[endIndex];
            nums[endIndex] = temp;
            startIndex++;
            endIndex--;
        }
    }
}
```

---

### ⏱️ Time & Space Complexity

- **Time Complexity:** `O(n)` – Each reversal takes linear time.
- **Space Complexity:** `O(1)` – In-place reversal, no extra space used.
