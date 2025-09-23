# 📘 930. Binary Subarrays With Sum

We are asked to count the number of **subarrays** whose sum equals `goal`.
Input is a **binary array** (`nums[i] ∈ {0, 1}`).

---

## 1️⃣ Brute Force

👉 Enumerate all subarrays, compute their sum, and check if it equals `goal`.

```java
int count = 0;
for (int i = 0; i < nums.length; i++) {
    int sum = 0;
    for (int j = i; j < nums.length; j++) {
        sum += nums[j];
        if (sum == goal) count++;
        else if (sum > goal) break; // optimization since nums[i] >= 0
    }
}
return count;
```

* **Time Complexity**: `O(n^2)`
* **Space Complexity**: `O(1)`
* ✅ Works, but too slow for large arrays.

---

## 2️⃣ Better Approach (Hashing / Prefix Sum Frequency)

👉 Use **prefix sums** + hashmap.
We know:
`sum(i..j) = prefix[j] - prefix[i-1]`
For each index `j`, we want `prefix[j] - goal` to exist in hashmap.

```java
public int numSubarraysWithSum(int[] nums, int goal) {
    Map<Integer, Integer> map = new HashMap<>();
    map.put(0, 1); // base case
    int sum = 0, count = 0;

    for (int num : nums) {
        sum += num;
        if (map.containsKey(sum - goal)) {
            count += map.get(sum - goal);
        }
        map.put(sum, map.getOrDefault(sum, 0) + 1);
    }
    return count;
}
```

* **Time Complexity**: `O(n)`
* **Space Complexity**: `O(n)` (hashmap storage)
* ✅ Straightforward, efficient.
* ⚡ Works for all arrays (not just binary).

---

## 3️⃣ Optimal Approach (Sliding Window Trick with AtMost)

👉 Use **atMost(K)** technique:

* Count subarrays with sum ≤ `goal`
* Count subarrays with sum ≤ `goal - 1`
* Answer = difference = `atMost(goal) - atMost(goal-1)`

⚠️ Works because array is **binary (non-negative)**, so sliding window is valid.

```java
public int numSubarraysWithSum(int[] nums, int goal) {
    return atMost(nums, goal) - atMost(nums, goal - 1);
}

private int atMost(int[] nums, int goal) {
    if (goal < 0) return 0;
    int l = 0, sum = 0, count = 0;
    for (int r = 0; r < nums.length; r++) {
        sum += nums[r];
        while (sum > goal) {
            sum -= nums[l++];
        }
        count += (r - l + 1); // all subarrays ending at r
    }
    return count;
}
```

* **Time Complexity**: `O(2n)` → `O(n)`
* **Space Complexity**: `O(1)`
* ✅ Most efficient, uses sliding window.
* ⚡ But works only when array has **non-negative numbers** (here binary).

---

# 💡 Tips & Tricks

1. **Sliding window with AtMost trick** →
   Useful when elements are non-negative. Count `= atMost(K) - atMost(K-1)` is a standard trick for “exact sum subarrays”.

2. **Prefix sum + hashmap** →
   More general, works for arrays with negative numbers too.

3. **Early break in brute force** →
   Since `nums[i] ∈ {0,1}`, once sum > goal, no need to expand further.

4. **Edge cases to test**:

   * `goal = 0` (all subarrays of zeros should be handled correctly)
   * Array full of ones (e.g., `[1,1,1,1]`)
   * Large input size (`10^5` elements)

---

✅ **Final Recommendation**:

* For interviews → first explain brute, then hashmap method (clear & general), and finally the sliding window trick (optimal for binary).

---
