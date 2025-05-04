### 🔍 **Problem Statement (Leetcode 560)**

Given an integer array `nums` and an integer `k`, return the total number of **continuous subarrays** whose sum equals to `k`.

---

### ✅ **Optimized Solution (Prefix Sum + HashMap)**

#### 💡 **Idea**:

Maintain a running sum (`prefixSum`) and use a HashMap to store the frequency of each prefix sum.
If at index `i`, the running sum is `prefixSum`, and there's a previous prefix sum `prefixSum - k`, it means the subarray between that earlier index and `i` has sum `k`.

#### 🧠 **Key Formula**:

```text
If prefixSum[i] - prefixSum[j] == k,
Then subarray nums[j+1...i] has sum k.
```

#### 🔄 **Steps**:

1. Initialize `prefixSum = 0` and a map with `{0: 1}` to handle the case where subarray from index 0 has sum = k.
2. Iterate through the array:

   * Update `prefixSum`.
   * If `(prefixSum - k)` is present in map → add its frequency to the result.
   * Store/update current `prefixSum` in the map.

#### 🕒 Time: `O(n)`

#### 🧠 Space: `O(n)`

---

### 🐌 **Brute Force Approach**

#### 🔄 **Steps**:

* For each starting index `i`, calculate the sum of all subarrays starting from `i` to `j`.
* If the sum equals `k`, increment the count.

#### 🕒 Time: `O(n^2)`

#### 🧠 Space: `O(1)`

---

### 🔢 **Example**

```java
nums = [1, 2, 3], k = 3

Subarrays:
[1, 2] => 3 ✔️
[3] => 3 ✔️

Output: 2
```

---
