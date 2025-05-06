### ✅ Problem Statement:

Given an integer array `nums` of size *n*, find all elements that appear **more than ⌊n/3⌋ times**.

You may return the answer in **any order**.

---

### 🧠 Key Observations:

* A number must appear **more than n/3** times to be considered a majority element in this problem.
* There can be **at most 2 majority elements** satisfying this condition.

  * Why? Because if we had three or more elements each occurring more than n/3 times, then the total count would exceed `n`.

---

### 🔍 Brute Force Approach:

* Use a **HashMap** to count occurrences.
* Traverse the map and collect elements with count > n/3.

**Time Complexity:** `O(n)`
**Space Complexity:** `O(n)`

---
### 🐢 Brute-Force Code (Using HashMap)

```java
class Solution {
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> result = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();

        // Count frequencies
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // Add elements that appear more than n/3 times
        int threshold = nums.length / 3;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > threshold) {
                result.add(entry.getKey());
            }
        }

        return result;
    }
}
```
---

### ✅ Optimal Solution – Boyer-Moore Voting Algorithm (Extended):

#### ✨ Core Idea:

* Track **at most 2 potential candidates** using Boyer-Moore Voting.
* Use two counters and two variables to track these candidates.
* First pass: Identify candidates.
* Second pass: Validate counts.

#### 🧾 Steps:

1. Initialize two potential candidates (`ele1`, `ele2`) and their counts (`count1`, `count2`) to zero.
2. Traverse the array:

   * If `count1 == 0` and current element ≠ `ele2`, assign it to `ele1`.
   * Else if `count2 == 0` and current element ≠ `ele1`, assign it to `ele2`.
   * If element matches `ele1` or `ele2`, increment their respective count.
   * Otherwise, decrement both counts.
3. Reinitialize counts.
4. Traverse the array again to count actual occurrences.
5. If a candidate appears more than `n/3` times, add it to result.

---

### ✅ Time & Space Complexity:

* **Time:** O(n) — Two passes through the array.
* **Space:** O(1) — Constant space (no extra map or list used for counting).

---

### 📌 Edge Cases:

* Array is empty → return empty list.
* All elements are the same.
* No element occurs more than `n/3` times → return empty list.

---
### ✅ **Optimal Code (Boyer-Moore Voting Algorithm)**

```java
class Solution {
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> result = new ArrayList<>();

        // Step 1: Find potential candidates
        int count1 = 0, count2 = 0;
        int candidate1 = Integer.MIN_VALUE, candidate2 = Integer.MIN_VALUE;

        for (int num : nums) {
            if (candidate1 == num) {
                count1++;
            } else if (candidate2 == num) {
                count2++;
            } else if (count1 == 0 && num != candidate2) {
                candidate1 = num;
                count1 = 1;
            } else if (count2 == 0 && num != candidate1) {
                candidate2 = num;
                count2 = 1;
            } else {
                count1--;
                count2--;
            }
        }

        // Step 2: Count actual occurrences
        count1 = 0;
        count2 = 0;
        for (int num : nums) {
            if (num == candidate1) count1++;
            else if (num == candidate2) count2++;
        }

        // Step 3: Check if they are valid majority elements
        int threshold = nums.length / 3;
        if (count1 > threshold) result.add(candidate1);
        if (count2 > threshold) result.add(candidate2);

        return result;
    }
}
```
---

### 🧪 Example:

**Input:** `nums = [3,2,3]`
**Output:** `[3]`
Because 3 appears 2 times, and `n = 3`, `n/3 = 1`.
