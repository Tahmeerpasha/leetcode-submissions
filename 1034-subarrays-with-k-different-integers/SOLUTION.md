## 🔹 Problem Statement

Given an array `nums` and an integer `k`, return the number of subarrays with **exactly k distinct integers**.

---

## 🔸 Brute Force (O(n²·k))

* **Idea**: Enumerate all possible subarrays, insert elements into a **Set**, and check if size = k.
* **Steps**:

  1. For every `i`, expand `j` to the right.
  2. Insert into a `Set`.
  3. If size = k, increment count.
* **Code snippet**:

  ```java
  int count = 0;
  for (int i = 0; i < nums.length; i++) {
      Set<Integer> set = new HashSet<>();
      for (int j = i; j < nums.length; j++) {
          set.add(nums[j]);
          if (set.size() == k) count++;
      }
  }
  return count;
  ```
* **Complexity**:

  * **Time**: O(n²·k) (building set for each subarray)
  * **Space**: O(k)

✅ Works but too slow for large input.

---

## 🔸 Better (O(n²))

* **Idea**: Use a frequency **HashMap** instead of `Set`, so we don’t rebuild distinct count from scratch.
* **Steps**:

  1. For each starting index `i`, expand `j` to the right.
  2. Maintain frequency count in a map.
  3. Track `distinct` count as you go (increment when freq=1, decrement when freq=0).
  4. Stop early if `distinct > k`.
* **Complexity**:

  * **Time**: O(n²)
  * **Space**: O(n)

❌ Still quadratic, but avoids repeated set constructions.

---

## 🔸 Optimal (O(n))

* **Key Trick**:
  Count subarrays with **at most K distinct** and subtract those with **at most (K-1) distinct**:

  ```
  exactlyK = atMost(K) – atMost(K-1)
  ```

* **Reason**:

  * Subarrays with at most `K` distinct include both those with exactly `K` and with fewer.
  * Removing at most `K-1` leaves exactly `K`.

* **`atMost` Function (Sliding Window)**:

  * Maintain frequency map.
  * Expand `r`, add nums\[r].
  * If map.size() > K, shrink from `l` until valid.
  * For each window `[l..r]`, number of new subarrays ending at `r` = `(r - l + 1)`.

* **Code**:

  ```java
  public int subarraysWithKDistinct(int[] nums, int k) {
      return atMost(nums, k) - atMost(nums, k - 1);
  }

  private int atMost(int[] nums, int k) {
      int l = 0, count = 0;
      Map<Integer, Integer> map = new HashMap<>();
      for (int r = 0; r < nums.length; r++) {
          map.put(nums[r], map.getOrDefault(nums[r], 0) + 1);
          while (map.size() > k) {
              map.put(nums[l], map.get(nums[l]) - 1);
              if (map.get(nums[l]) == 0) map.remove(nums[l]);
              l++;
          }
          count += r - l + 1;
      }
      return count;
  }
  ```

* **Complexity**:

  * **Time**: O(2n) ≈ O(n) (each element enters & leaves window once)
  * **Space**: O(k)

---

## 🔹 Tips & Tricks

1. **Exactly K trick**: Always remember

   ```
   ExactlyK = AtMost(K) – AtMost(K-1)
   ```

   Works for “exactly k distinct / odd / even / sum ≤ k” type problems.
2. **Sliding Window Pattern**:

   * Grow window by moving `r`.
   * Shrink with `l` if constraint violated.
   * Add `(r - l + 1)` to answer → subarrays ending at `r`.
3. **HashMap vs Array**:

   * If input range is small → use int\[] freq instead of HashMap for faster code.
4. **Early break** in brute/better approach when distinct > k.

---

✅ **Final Recommendation**: Use **Optimal O(n)** sliding window with `atMost` trick.

---
