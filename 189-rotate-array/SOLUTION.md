## 🔁 LeetCode 189: Rotate Array

### ✅ Problem Statement  
Given an array, rotate the array to the right by `k` steps, where `k` is non-negative.

---

## 💡 Approaches

---

### 1. 🔨 Brute Force Approach

#### 🧠 Logic:
- Rotate the array one step to the right `k` times.
- For each rotation, move the last element to the front by shifting all elements.

#### 💻 Java Code:
```java
class Solution {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;

        for (int i = 0; i < k; i++) {
            int last = nums[n - 1];
            for (int j = n - 1; j > 0; j--) {
                nums[j] = nums[j - 1];
            }
            nums[0] = last;
        }
    }
}
```

#### ⏱️ Complexity:
- **Time:** O(n * k)
- **Space:** O(1)

---

### 2. 🔧 Better Approach (Using Extra Array)

#### 🧠 Logic:
- Create a new array `res` of size `n`.
- Place each element at its new rotated index: `res[(i + k) % n] = nums[i]`.

#### 💻 Java Code:
```java
class Solution {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        int[] res = new int[n];
        k = k % n;

        for (int i = 0; i < n; i++) {
            res[(i + k) % n] = nums[i];
        }

        // Copy back to original array
        for (int i = 0; i < n; i++) {
            nums[i] = res[i];
        }
    }
}
```

#### ⏱️ Complexity:
- **Time:** O(n)
- **Space:** O(n)

---

### 3. ⚡ Optimized Approach (In-place Reversal)

#### 🧠 Logic:
Use the reversal trick:
1. Reverse the first part: `0` to `n - k - 1`
2. Reverse the second part: `n - k` to `n - 1`
3. Reverse the whole array: `0` to `n - 1`

This reorders elements as if the array was rotated.


#### 📐 Dry Run Example

### 🔧 Input:
nums = [1, 2, 3, 4, 5, 6, 7], k = 3

### 📌 Dry Run Steps:

Let `n = 7`, so:

#### Step 1: Reverse from `0 to n-k-1` → `0 to 3`
```java
Before: [1, 2, 3, 4, 5, 6, 7]
After:  [4, 3, 2, 1, 5, 6, 7]
```

#### Step 2: Reverse from `n-k to n-1` → `4 to 6`
```java
Before: [4, 3, 2, 1, 5, 6, 7]
After:  [4, 3, 2, 1, 7, 6, 5]
```

#### Step 3: Reverse the entire array from `0 to n-1`
```java
Before: [4, 3, 2, 1, 7, 6, 5]
After:  [5, 6, 7, 1, 2, 3, 4]
```

✅ Final Rotated Array → `[5, 6, 7, 1, 2, 3, 4]`

#### 💻 Java Code:
```java
class Solution {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;

        reverse(nums, 0, n - k - 1);
        reverse(nums, n - k, n - 1);
        reverse(nums, 0, n - 1);
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start++] = nums[end];
            nums[end--] = temp;
        }
    }
}
```

#### ⏱️ Complexity:
- **Time:** O(n)
- **Space:** O(1)

---

### 🧪 Example

```java
Input: nums = [1,2,3,4,5,6,7], k = 3
Output: [5,6,7,1,2,3,4]
```
