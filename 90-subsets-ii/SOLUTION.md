### 🔍 **Problem Summary**

You are given an integer array `nums` that may contain **duplicates**, and you need to return **all possible subsets (the power set)** that are **unique**.

---

## 🧠 Brute Force (Backtracking without duplicate handling)

### ✅ Idea:

* Generate **all** `2^n` subsets using recursion.
* Store each subset in a `Set<List<Integer>>` to eliminate duplicates.

### ⚠️ Issues:

* Comparing and storing lists in a set is **costly**.
* **Inefficient** memory-wise.

### 💻 Pseudocode:

```java
Set<List<Integer>> set = new HashSet<>();
generate(nums, index, currList, set);
```

### 🕒 Time Complexity: `O(2^n * n)`

(Generating all subsets + copying to set)

### 📦 Space Complexity: `O(2^n * n)`

(Set + recursion stack)

---

## 🔁 Better (Use sorting + HashSet with deduplication logic)

### ✅ Idea:

* **Sort** the array to bring duplicates together.
* Use recursion and skip elements if they are **same as previous** and **not first element of recursion branch**.
* Still store results in a **HashSet** to prevent duplicates.

### ⚠️ Still using a set → extra memory and processing

### 🕒 Time Complexity: `O(2^n * n)`

### 📦 Space Complexity: `O(2^n * n)`

---

## 🚀 Optimal (No HashSet, Skip Duplicates via Smart Recursion)

### ✅ Idea:

* **Sort** input to group duplicates.
* In recursive loop, **skip duplicates** using:

  ```java
  if (i > index && nums[i] == nums[i - 1]) continue;
  ```
* This ensures that for each recursion branch, **only the first occurrence of a duplicate is considered**.

### ✅ Java Code:

```java
public List<List<Integer>> subsetsWithDup(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    Arrays.sort(nums);
    backtrack(nums, 0, new ArrayList<>(), result);
    return result;
}

private void backtrack(int[] nums, int index, List<Integer> curr, List<List<Integer>> result) {
    result.add(new ArrayList<>(curr));
    for (int i = index; i < nums.length; i++) {
        if (i > index && nums[i] == nums[i - 1]) continue;
        curr.add(nums[i]);
        backtrack(nums, i + 1, curr, result);
        curr.remove(curr.size() - 1);
    }
}
```

### 🕒 Time Complexity: `O(2^n * n)`

(Each subset is copied; pruning duplicates avoids extra branches)

### 📦 Space Complexity: `O(2^n * n)`

(For storing subsets and recursion stack)

---

### 📝 Summary Table:

| Approach  | Strategy                          | Duplicate Handling | Time         | Space        |
| --------- | --------------------------------- | ------------------ | ------------ | ------------ |
| Brute     | All subsets + HashSet             | After generation   | `O(2^n * n)` | `O(2^n * n)` |
| Better    | Sorted input + HashSet            | During recursion   | `O(2^n * n)` | `O(2^n * n)` |
| ✅ Optimal | Sorted + Skip duplicates in-place | Smart recursion    | `O(2^n * n)` | `O(2^n * n)` |

---
