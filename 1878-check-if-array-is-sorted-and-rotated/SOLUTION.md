## ✅ Problem:  
Given an array `nums`, return `true` **if it is a rotation of a sorted array**, otherwise `false`.

---

## 🟠 Brute Force Approach  
### 🔹 Logic:
- Try all possible rotations of the array.
- For each rotated version, check if it is sorted.

### 💡 Code:
```java
class Solution {
    public boolean check(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            boolean isSorted = true;
            for (int j = 0; j < n - 1; j++) {
                int a = nums[(i + j) % n];
                int b = nums[(i + j + 1) % n];
                if (a > b) {
                    isSorted = false;
                    break;
                }
            }
            if (isSorted) return true;
        }
        return false;
    }
}
```

### ⏱️ Time Complexity:  
- **O(n²)** – Checking all `n` rotations and each rotation costs `O(n)`  
- **Space:** O(1)

---

## 🟢 Optimized Approach (Linear Time)

### 🔹 Logic:
- Count how many times `nums[i] < nums[i-1]`
- If this count is more than once, array isn't sorted and rotated.

### ✅ Code:
```java
class Solution {
    public boolean check(int[] nums) {
        int n = nums.length;
        int count = 0;
        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[i - 1]) count++;
        }
        if (nums[0] < nums[n - 1]) count++;
        return count <= 1;
    }
}
```

### ⏱️ Time and Space:
- **Time:** O(n)  
- **Space:** O(1)

---

## 🎯 Dry Run

### Input: `nums = [3, 4, 5, 1, 2]`

- `3 < 4` – ok  
- `4 < 5` – ok  
- `5 > 1` – 🔴 count = 1  
- `1 < 2` – ok  
- `nums[0] = 3 < nums[4] = 2` → **false** → don't increment count  

✅ Final `count = 1` → Return `true`

---

## 🖼️ Visual Illustration

Here's a **visual explanation** of how the optimized logic works (one drop point):

- Array is **rotated at the drop** between 5 → 1
- Before and after the drop, the array is non-decreasing
