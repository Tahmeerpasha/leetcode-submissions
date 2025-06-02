## ✅ Problem Summary:

> Given an array `nums` and an integer `k`, split the array into `k` or fewer **continuous** subarrays such that the **maximum sum among these subarrays is minimized**. Return this minimum possible value.

---

## 🧠 Observations:

* You can split the array into **exactly `k` subarrays**, not more.
* You must keep the **subarrays contiguous**.
* We are trying to **minimize the largest subarray sum** — this is a classic **binary search on answer** problem.

---

## 💡 Approaches:

### 1. 🐢 Brute Force (TLE for large inputs)

* Try all values of max subarray sum from `max(nums)` to `sum(nums)`.
* For each value, check if you can split array into at most `k` parts.
* **Time**: `O((high - low) * n)`
* **Space**: `O(1)`

```java
for (int i = max(nums); i <= sum(nums); i++) {
    if (numOfSubArrays(nums, i) == k) return i;
}
```

---

### 2. ⚡ Optimal — Binary Search on Answer ✅

#### ✅ Intuition:

* The answer lies between `max(nums)` (can't split an element) and `sum(nums)` (put everything in one subarray).
* Use binary search to find the smallest **maximum subarray sum** where `≤ k` subarrays are enough.

#### 🧮 Logic:

* If we can split into **more than k** subarrays, the `mid` value is **too small** → search right.
* If we can split into **k or fewer**, we might minimize further → search left.

#### Time & Space:

* **Time**: `O(n * log(sum - max))`
* **Space**: `O(1)`

---

## ✅ Code:

```java
public int splitArray(int[] nums, int k) {
    int low = max(nums), high = sum(nums);

    while (low <= high) {
        int mid = (low + high) / 2;
        if (numOfSubArrays(nums, mid) > k)
            low = mid + 1;
        else
            high = mid - 1;
    }
    return low;
}

int max(int[] nums) {
    int max = Integer.MIN_VALUE;
    for (int num : nums) max = Math.max(max, num);
    return max;
}

int sum(int[] nums) {
    int sum = 0;
    for (int num : nums) sum += num;
    return sum;
}

int numOfSubArrays(int[] nums, int maxAllowedSum) {
    int count = 1, sum = 0;
    for (int num : nums) {
        sum += num;
        if (sum > maxAllowedSum) {
            count++;
            sum = num;
        }
    }
    return count;
}
```

---

## 📝 Summary Table:

| Approach      | Time Complexity         | Space  | Notes                      |
| ------------- | ----------------------- | ------ | -------------------------- |
| Brute Force   | `O((sum-max) * n)`      | `O(1)` | Try every max sum          |
| Binary Search | `O(n * log(sum - max))` | `O(1)` | Efficient for large inputs |

---
