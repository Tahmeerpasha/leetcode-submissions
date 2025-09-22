# **904. Fruit Into Baskets** 🍎🍊

**Problem Type:** Sliding Window / Longest Subarray with At Most K Distinct Elements (here `k=2`).

---

## **Brute Force Approach**

* **Idea:**
  Try every subarray, check if it has at most 2 distinct fruits.
* **Steps:**

  1. Start with every index `i`.
  2. Keep inserting elements into a `Set`.
  3. If `Set.size() > 2`, stop.
  4. Track the max length valid subarray.
* **Code Idea:**

  ```java
  int maxLen = 0;
  for (int i = 0; i < fruits.length; i++) {
      Set<Integer> set = new HashSet<>();
      for (int j = i; j < fruits.length; j++) {
          set.add(fruits[j]);
          if (set.size() > 2) break;
          maxLen = Math.max(maxLen, j - i + 1);
      }
  }
  return maxLen;
  ```
* **Complexity:**

  * **Time:** O(n²)
  * **Space:** O(n) (for `Set` in worst case).

---

## **Better Approach (Two Pointers but with checks)**

* **Idea:**
  Expand with `r` pointer, shrink with `l` pointer whenever you cross more than 2 distinct fruits.
* **Steps:**

  1. Maintain a frequency map of fruits.
  2. Expand `r`.
  3. If `map.size() > 2`, shrink from `l`.
  4. Track window length.
* **Complexity:**

  * **Time:** O(2n) (in worst case each element is added and removed once).
  * **Space:** O(3) → At most 3 keys in the map.

---

## **Optimal Approach (Clean Sliding Window)**

* **Idea:**
  Same as “Longest Substring with At Most K Distinct Characters” with `k=2`.
* **Code:**

  ```java
  int l = 0, maxLen = 0;
  Map<Integer, Integer> map = new HashMap<>();
  for (int r = 0; r < fruits.length; r++) {
      map.put(fruits[r], map.getOrDefault(fruits[r], 0) + 1);
      
      while (map.size() > 2) {
          map.put(fruits[l], map.get(fruits[l]) - 1);
          if (map.get(fruits[l]) == 0) map.remove(fruits[l]);
          l++;
      }
      maxLen = Math.max(maxLen, r - l + 1);
  }
  return maxLen;
  ```
* **Complexity:**

  * **Time:** O(n) (each element processed at most twice).
  * **Space:** O(3) = O(1).

---

## **Tips & Tricks** ✅

1. **Pattern Recognition:**

   * This is the classic **at most K distinct elements in a subarray** problem (here K=2).
   * Similar to: *Longest Substring Without Repeating Characters*, *Longest Subarray with At Most K Distinct Numbers*.

2. **When to use Sliding Window?**

   * If the problem asks for "longest subarray/substring with at most/exactly k distinct" → **always sliding window.**

3. **Optimization Insight:**

   * You only need to store counts of fruits (map size ≤ 3).
   * Space is O(1), not O(n), since only 2–3 types of fruits matter.

4. **Trick to Remember:**

   * **Left pointer moves only when invalid.**
   * **Right pointer always moves forward.**
   * Update result whenever valid.

---
