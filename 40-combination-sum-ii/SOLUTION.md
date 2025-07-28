### 🔍 Problem Summary:

* Given a list of **candidates** (may contain duplicates) and a **target** sum.
* Find **all unique combinations** where each number can be used **at most once**.
* Combinations must not be repeated.

---

### ✅ Optimal Approach: Backtracking with Pruning

#### 🚀 Key Concepts:

* Use **backtracking** to explore all combinations.
* Sort the array to **easily skip duplicates**.
* Avoid picking the **same number at the same recursive level** to prevent duplicate combinations.

---

### 🧠 Time & Space Complexity:

* **Time:** `O(2^n * k)`

  * `n` = size of input array
  * `k` = average length of a combination
* **Space:** `O(k * x)`

  * `x` = number of valid combinations
  * Recursion stack space used for backtracking.

---

### 🧩 Algorithm Steps:

1. **Sort** the input array → to handle duplicates and enable early pruning.
2. Use a **for-loop** from `index` to `n` to iterate over choices.
3. **Skip duplicates**: If `i > index && candidates[i] == candidates[i - 1]` → continue.
4. **Early stopping**: If `candidates[i] > target`, break the loop (because array is sorted).
5. If valid, **include** the number in the combination and **recurse** with reduced target.
6. **Backtrack** by removing the last added number.

---

### 🧪 Example:

```java
Input: candidates = [10,1,2,7,6,1,5], target = 8
Sorted: [1,1,2,5,6,7,10]

Output: [
  [1,1,6],
  [1,2,5],
  [1,7],
  [2,6]
]
```

---

### ✅ Code Explanation:

```java
Arrays.sort(candidates);  // Step 1: Sort to handle duplicates

findCS2Optimal(candidates, target, 0, result, new ArrayList<>());
```

#### Inside `findCS2Optimal()`:

```java
for (int i = index; i < candidates.length; i++) {
    if (i > index && candidates[i] == candidates[i - 1]) continue;  // Step 2: Skip duplicates
    if (candidates[i] > target) break;  // Step 3: Early pruning

    currList.add(candidates[i]);  // Step 4: Pick
    findCS2Optimal(candidates, target - candidates[i], i + 1, result, currList);  // Step 5: Recurse
    currList.remove(currList.size() - 1);  // Step 6: Backtrack
}
```

---

### 📝 Tips:

* Always **sort** the array when duplicates are involved in backtracking.
* For problems where elements can be reused → start recursion from `i`.
* For **non-reusable** elements (like this one) → recurse with `i + 1`.

---
