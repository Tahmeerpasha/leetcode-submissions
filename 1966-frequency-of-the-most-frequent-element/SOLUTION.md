### 🔍 Problem Summary:
You're given an integer array `nums` and an integer `k`. You can increment any element by `1` any number of times (max total increments = `k`).

**Goal:** Return the **maximum frequency** of an element that can be achieved by performing at most `k` operations.

---
### 🐢 Brute Force Approach:
**Idea:**  
For each element in the array, try to make it the **most frequent element** by **increasing smaller elements** using at most `k` operations.

---

### 🔁 Steps:

1. For every index `i` in `nums`:
   - Try to increase as many elements before `i` as possible so that they equal `nums[i]`.
   - Keep track of the total cost.
   - If the total cost ≤ `k`, update the max frequency.

2. Sort the array to make comparisons easier.

---

### 🧠 Key Point:
To make `nums[j]` equal to `nums[i]`, you need `(nums[i] - nums[j])` operations.

---

### 🧪 Example:

```java
nums = [1, 2, 4], k = 5
Sorted => [1, 2, 4]
Try making all elements equal to 4
Cost = (4-1) + (4-2) = 3 + 2 = 5 (valid)
```

---

### 🧾 Brute Force Code:

```java
public int maxFrequency(int[] nums, int k) {
    Arrays.sort(nums);
    int maxFreq = 0;

    for (int i = nums.length - 1; i >= 0; i--) {
        long ops = 0;
        int freq = 1;

        for (int j = i - 1; j >= 0; j--) {
            ops += nums[i] - nums[j];
            if (ops > k) break;
            freq++;
        }

        maxFreq = Math.max(maxFreq, freq);
    }

    return maxFreq;
}
```

---

### ⏱️ Time Complexity:

| Part          | Complexity    |
|---------------|---------------|
| Sorting       | O(n log n)    |
| Nested loops  | O(n²) worst   |
| Overall       | O(n²)         |

---

### ❌ Why not good for Leetcode?
- Time limit will exceed for large arrays (n ~ 10⁵).
- Only good for small inputs or to understand problem.

---
## OPTIMAL APPROACH
### ✅ Intuition:
To maximize the frequency of a number:
- **Choose a number and try to make other numbers equal to it** using the allowed operations (`+1`).
- Best strategy is to **pick the largest number** and try to increase smaller numbers to match it.
- Sorting helps: it allows us to efficiently work with increasing values.

---

### ✅ Approach: Sliding Window + Sorting

1. **Sort** the array to align elements for easier manipulation.
2. Use a **sliding window**:
   - The window `[left...right]` represents the subarray we are trying to make all elements equal to `nums[right]`.
   - We check: Can we increase all elements in the window to `nums[right]` with ≤ `k` total increments?
3. If not, **shrink** the window from the left until the condition holds.
4. Track the **maximum size** of a valid window.

---

### ✅ Core Condition:
At any point, to make all elements in window `[left...right]` equal to `nums[right]`:
```java
(nums[right] * (right - left + 1)) <= total + k
```
Where `total` is the **sum of elements in the current window**.

If this condition fails, we reduce the window by moving `left++`.

---

### ✅ Code:

```java
public int maxFrequency(int[] nums, int k) {
    Arrays.sort(nums);
    int left = 0, right = 0, maxFreq = 1;
    long total = 0;

    while (right < nums.length) {
        total += nums[right];
        
        // While we can't convert all values in the window to nums[right]
        while ((long) nums[right] * (right - left + 1) > total + k) {
            total -= nums[left];
            left++;
        }

        maxFreq = Math.max(maxFreq, right - left + 1);
        right++;
    }

    return maxFreq;
}
```

---

### 📊 Dry Run:
Input: `nums = [1, 2, 4], k = 5`

- After sorting: `[1, 2, 4]`
- Try to make all elements in `[1, 2, 4]` equal to `4`
  - Cost = (4*3) - (1+2+4) = 12 - 7 = 5
- Since 5 ≤ k, max frequency = 3 ✅

---

### ⏱️ Time & Space Complexity:

| Complexity | Value         |
|------------|---------------|
| Time       | O(n log n)    |
| Space      | O(1)          |

- Sorting → O(n log n)
- Sliding window traversal → O(n)

---

### 🧠 Summary:

- Sort the array.
- Use sliding window to keep checking largest valid subarray.
- Use total sum and math to validate whether we can equalize with ≤ `k` increments.
