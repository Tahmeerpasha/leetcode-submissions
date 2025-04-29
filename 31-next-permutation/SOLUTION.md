### 🔍 Problem Summary:
You are given an array of integers `nums`, representing a permutation of numbers. You need to modify the array **in-place** to the **next lexicographically greater permutation** of numbers.  
If no such arrangement exists (i.e., it's the last permutation), rearrange to the **lowest possible order** (i.e., sorted in ascending order).

---

### ✅ Constraints:
- Must be done **in-place** (i.e., O(1) extra space).
- Rearrangement should be the **next lexicographic permutation**.

---

### ✅ Step-by-Step Approach:

#### 1. **Find the Pivot:**
From the end, find the first index `i` such that:
```java
nums[i] < nums[i + 1]
```
This marks the position where the descending order from the end breaks.

#### 2. **If no pivot found:**
If no such index exists (`index == -1`), the array is in descending order, so:
```java
reverse the entire array to get the smallest permutation.
```

#### 3. **Find the Swap Element:**
If a pivot is found at `index = i`, then from the end, find the smallest number `nums[j]` **greater than** `nums[i]` and **swap them**.

#### 4. **Reverse the suffix:**
Reverse the subarray after index `i` (`index + 1 to end`) to get the smallest arrangement of that suffix.

---

### ✅ Code Recap:

```java
public void nextPermutation(int[] nums) {
    int index = -1;
    int n = nums.length;

    // Step 1: Find pivot
    for (int i = n - 2; i >= 0; i--) {
        if (nums[i] < nums[i + 1]) {
            index = i;
            break;
        }
    }

    // Step 2: Reverse whole array if no next permutation
    if (index == -1) {
        reverse(nums, 0, n - 1);
        return;
    }

    // Step 3: Find the next greater element to swap
    for (int i = n - 1; i > index; i--) {
        if (nums[i] > nums[index]) {
            swap(nums, i, index);
            break;
        }
    }

    // Step 4: Reverse the suffix
    reverse(nums, index + 1, n - 1);
}

void reverse(int[] nums, int i, int j) {
    while (i < j) {
        swap(nums, i++, j--);
    }
}

void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
}
```

---

### 📊 Dry Run Example:

Input: `[1, 2, 3]`  
1. Pivot = `1` at index 1 (`nums[1] < nums[2]`)
2. Swap with `3` (next greater)
3. Reverse suffix `[3]` → `[3]`

Output: `[1, 3, 2]` ✅

---

### 🧠 Intuition:
- Think of permutations as dictionary order.
- This method ensures that we're always moving to the next possible permutation.
- If you're at the end (largest permutation), just loop back to the beginning (smallest permutation).

---

### ⏱️ Time & Space Complexity:

- **Time:** O(n) — Two passes: one to find the pivot and one to reverse
- **Space:** O(1) — In-place modifications

---
