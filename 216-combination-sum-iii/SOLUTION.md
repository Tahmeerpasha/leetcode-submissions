### 🔢 Problem Statement:

Find all valid combinations of **k** numbers that add up to **n**, using numbers from **1 to 9**, **without repetition**.
Each combination should be unique, and numbers in each combination should be in ascending order.

---

### 🧠 Brute Force (Conceptual)

* **Idea:** Generate all subsets of numbers from 1 to 9, filter out subsets with size = `k` and sum = `n`.
* **Steps:**

  1. Generate all `2^9` subsets of numbers 1–9.
  2. For each subset, check if size = `k` and sum = `n`.
  3. If valid, add to result.
* **Time Complexity:** `O(2^9 * k)` → Because there are `2^9` subsets and for each we might iterate up to `k` elements for sum.
* **Space Complexity:** `O(2^9 * k)` for storing valid combinations.

---

### 🚀 Better Approach

* **Idea:** Use **combinations** by choosing `k` numbers out of 9 and summing them.

* **Steps:**

  1. Use backtracking to try all `k`-length combinations from 1 to 9.
  2. Check if the combination sums to `n`.

* **Still involves generating many paths**, though we skip many using length and sum conditions.

* **Time Complexity:** Roughly `O(9Ck)` (combinations), where each combination costs `O(k)` to create.

* **Space Complexity:** `O(k)` recursion stack + result storage.

---

### ✅ Optimal Approach (Used in Your Code)

* **Technique:** **Backtracking with pruning**
* **Steps:**

  1. Start from number `1` and try to pick it or skip it.
  2. Track current combination (`currList`), current sum, and current index.
  3. If `currList.size() > k` or `sum > target`, **prune** (terminate) that branch early.
  4. If `currList.size() == k` and `sum == target`, add to result.
  5. Only go till number 9.

```java
if (sum > target || currList.size() > k) return;
if (sum == target && currList.size() == k) result.add(new ArrayList<>(currList));
```

* **Time Complexity:**

  * Worst case: All combinations of 1 to 9 (i.e., 2⁹ = 512), but pruning significantly reduces it.
  * So approx **O(k \* 2^9)** or **bounded constant** for practical purposes since n ≤ 60.
* **Space Complexity:**

  * `O(k)` recursion depth + result space.

---

### 📝 Summary

| Approach    | Time Complexity | Space Complexity  | Notes                                    |
| ----------- | --------------- | ----------------- | ---------------------------------------- |
| Brute Force | O(2^9 \* k)     | O(2^9 \* k)       | Generate all subsets                     |
| Better      | O(9Ck \* k)     | O(k) + output     | Try all k-combinations                   |
| **Optimal** | **O(k \* 2^9)** | **O(k) + output** | Backtracking with pruning (used in code) |

---
