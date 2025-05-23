## 📝 Problem: **34. Find First and Last Position of Element in Sorted Array**

### 🔍 Given:

* A sorted integer array `nums`
* A target value `target`

### 🎯 Goal:

Find the **first and last position** of `target` in the array.
If not found, return `[-1, -1]`.

---

## ✅ Approach 1: Brute Force

**Logic:**

1. Do a linear scan from left to right to find the first occurrence.
2. Do another linear scan to find the last occurrence.

**Time Complexity:** `O(n)`
**Space Complexity:** `O(1)`
✅ Simple but inefficient on large arrays.

---

## ✅ Approach 2: Expand Around Mid (Not Optimal)

**Logic:**

1. Use binary search to find any one occurrence of the target.
2. Expand to the left and right to find the full range.

```java
while(low <= high){
    int mid = (low + high) / 2;
    if(nums[mid] == target){
        int j = mid;
        while(j >= 0 && nums[j] == target) j--;
        int k = mid;
        while(k < n && nums[k] == target) k++;
        return new int[]{j+1, k-1};
    } else if(nums[mid] < target){
        low = mid + 1;
    } else {
        high = mid - 1;
    }
}
```

**Time Complexity:** More than `O(log n)`
✅ Easier to implement, but not efficient.

---

## ✅ Approach 3: Optimal Using Binary Search (Your Final Approach)

### 🔧 Binary Search for First and Last Occurrence

```java
// Find first occurrence
while(low <= high){
    int mid = (low + high) / 2;
    if(nums[mid] == target){
        first = mid;
        high = mid - 1; // move left
    } else if(nums[mid] < target){
        low = mid + 1;
    } else {
        high = mid - 1;
    }
}
```

```java
// Find last occurrence
low = 0;
high = n - 1;
while(low <= high){
    int mid = (low + high) / 2;
    if(nums[mid] == target){
        last = mid;
        low = mid + 1; // move right
    } else if(nums[mid] < target){
        low = mid + 1;
    } else {
        high = mid - 1;
    }
}
```

### ✅ Time Complexity: `O(log n)`

### ✅ Space Complexity: `O(1)`

🔒 Uses two binary searches — fast and optimal.

---

## 🧠 Alternate Optimal (Lower & Upper Bound Helper Methods)

You also implemented:

* `lowerBound`: Finds the first index where `nums[i] >= target`
* `upperBound`: Finds the first index where `nums[i] > target`

These also yield `O(log n)` performance and work similarly to `std::lower_bound` and `std::upper_bound` in C++.

---

## ✅ Summary Table

| Approach               | Time     | Space | Notes                       |
| ---------------------- | -------- | ----- | --------------------------- |
| Brute Force            | O(n)     | O(1)  | Simple but slow             |
| Expand Around Mid      | > O(logn) | O(1)  | Uses binary search + expand |
| Binary Search (2 pass) | O(log n) | O(1)  | Fastest & cleanest          |
| Lower/Upper Bound      | O(log n) | O(1)  | C++ STL-like implementation |

---
