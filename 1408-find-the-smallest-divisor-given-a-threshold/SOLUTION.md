## ✅ Problem Summary:

> Given an integer array `nums` and an integer `threshold`, return the **smallest divisor** such that the **sum of division results (each rounded up)** is less than or equal to `threshold`.

🔸 **Rounded up** division: ⌈`nums[i] / divisor`⌉

---

## 🔎 Constraints:

* The result of each division is rounded **up**.
* Divisor must be a **positive integer ≥ 1**.
* If `nums.length > threshold`, it’s **impossible**, return `-1`.

---

## 💡 Approaches:

### 1. 🐢 Brute Force

* Try all divisors from 1 to `max(nums)`, compute the sum.
* Return the first divisor for which the total sum is ≤ threshold.
* **Time**: `O(n * max(nums))`
* **Space**: `O(1)`

```java
for(int i = 1; i <= max(nums); i++) {
    if(sumOfDivision(nums, i) <= threshold) return i;
}
```

---

### 2. ⚡ Optimal — Binary Search on Answer ✅

* Search in the range `[1, max(nums)]`.
* For each mid, check if the total rounded-up sum ≤ `threshold`.
* Narrow down search space to find **minimum valid divisor**.

#### Time & Space:

* **Time**: `O(n * log(max(nums)))`
* **Space**: `O(1)`

---

### ✅ Code:

```java
public int smallestDivisor(int[] nums, int threshold) {
    int low = 1, high = max(nums);
    if (nums.length > threshold) return -1;

    while (low <= high) {
        int mid = (low + high) / 2;
        int sum = sumOfDivision(nums, mid);
        if (sum <= threshold)
            high = mid - 1;
        else
            low = mid + 1;
    }
    return low;
}

int max(int[] nums) {
    int max = Integer.MIN_VALUE;
    for (int num : nums)
        max = Math.max(max, num);
    return max;
}

int sumOfDivision(int[] nums, int divisor) {
    int sum = 0;
    for (int num : nums)
        sum += Math.ceil((double) num / divisor);
    return sum;
}
```

---

## ✅ Summary Table:

| Approach      | Time Complexity         | Space  | Notes                                 |
| ------------- | ----------------------- | ------ | ------------------------------------- |
| Brute Force   | `O(n * max(nums))`      | `O(1)` | Tries all possible divisors           |
| Binary Search | `O(n * log(max(nums)))` | `O(1)` | Efficient — search over divisor range |

---
