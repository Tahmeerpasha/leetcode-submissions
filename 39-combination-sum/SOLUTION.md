### 🔢 Problem: 39. Combination Sum

**Given** an array of distinct integers `candidates` and a target integer `target`, return all **unique combinations** of candidates where the chosen numbers sum to `target`. You may **reuse** the same number **unlimited** times.

---

## ✅ Brute Force

### ✅ Idea:

* Try **all possible combinations** using each number any number of times.
* Use a recursive function to explore all paths.
* At each index, either **pick** the element (can pick again) or **skip** it.

### ✅ Code Logic:

```java
void findCombinations(int[] arr, int index, int target, List<List<Integer>> res, List<Integer> temp) {
    if (index == arr.length) {
        if (target == 0) res.add(new ArrayList<>(temp));
        return;
    }

    // Pick current number
    if (arr[index] <= target) {
        temp.add(arr[index]);
        findCombinations(arr, index, target - arr[index], res, temp);
        temp.remove(temp.size() - 1); // Backtrack
    }

    // Skip current number
    findCombinations(arr, index + 1, target, res, temp);
}
```

### ⏱️ Time Complexity:

* **O(2^t \* k)**

  * `t = target value`, maximum depth of recursion
  * `k = average length of each combination`

### 🧠 Space Complexity:

* **O(k \* x)** where `x` is the number of combinations returned

### ⚠️ Limitations:

* Works fine, but might explore many unnecessary paths when input is large.

---

## 💡 Better / Optimal (Backtracking with pruning) ✅

This is what you're already using in your code — a **backtracking solution** with pruning.

### ✅ Optimizations:

* Backtrack if `target < 0` (though not in your code; it's safe to add).
* Avoid duplicate combinations by always starting from the current index (`index`) — ensures non-descending combinations.
* Use the same index to allow unlimited reuse of the same number.

### ✅ Code:

```java
public List<List<Integer>> combinationSum(int[] candidates, int target) {
    List<List<Integer>> result = new ArrayList<>();
    backtrack(candidates, target, 0, new ArrayList<>(), result);
    return result;
}

void backtrack(int[] candidates, int target, int index, List<Integer> currList, List<List<Integer>> result) {
    if (target == 0) {
        result.add(new ArrayList<>(currList));
        return;
    }
    if (target < 0 || index >= candidates.length) return;

    // Include current element
    currList.add(candidates[index]);
    backtrack(candidates, target - candidates[index], index, currList, result);
    currList.remove(currList.size() - 1); // Backtrack

    // Exclude current element
    backtrack(candidates, target, index + 1, currList, result);
}
```

### ✅ Time Complexity:

* **O(2^t \* k)**
  Same as brute, but in practice much faster due to pruning and avoiding invalid branches early.

### ✅ Space Complexity:

* **O(k \* x)**

---

## 📌 Summary Table

| Approach     | Time Complexity | Space Complexity | Notes                            |
| ------------ | --------------- | ---------------- | -------------------------------- |
| Brute Force  | O(2^t \* k)     | O(k \* x)        | Tries all combinations           |
| Optimal (BT) | O(2^t \* k)     | O(k \* x)        | Efficient with pruning and reuse |

---
