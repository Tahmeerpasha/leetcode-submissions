## 🧠 Problem Statement:

Given an integer array `nums`, return all possible **subsets** (the power set).
The solution set **must not** contain duplicate subsets.

---

## ✅ Constraints:

* `1 <= nums.length <= 10`
* `-10 <= nums[i] <= 10`

---

## 🔍 Brute Force:

### 🔹 Idea:

* Generate **all combinations** of elements (using binary representation).
* For every number from `0` to `2^n - 1`, treat its binary representation as an inclusion/exclusion flag for elements in `nums`.

### 🔹 Code:

```java
public List<List<Integer>> subsets(int[] nums) {
    int n = nums.length;
    List<List<Integer>> result = new ArrayList<>();
    int total = 1 << n; // 2^n subsets

    for (int i = 0; i < total; i++) {
        List<Integer> subset = new ArrayList<>();
        for (int j = 0; j < n; j++) {
            if ((i & (1 << j)) != 0) {
                subset.add(nums[j]);
            }
        }
        result.add(subset);
    }

    return result;
}
```

### 🔹 Time Complexity:

* **O(n × 2ⁿ)** — 2ⁿ subsets, each may take up to `n` time to build.

### 🔹 Space Complexity:

* **O(n × 2ⁿ)** — Output list.

---

## 🔁 Better Approach:

### 🔹 Idea:

* Use **recursive backtracking**.
* At each index, decide to **include** or **exclude** the current element.

### 🔹 Code:

```java
void generateSubSets(int[] nums, int index, List<List<Integer>> result, List<Integer> currList) {
    if (index == nums.length) {
        result.add(new ArrayList<>(currList));
        return;
    }

    // Include
    currList.add(nums[index]);
    generateSubSets(nums, index + 1, result, currList);

    // Backtrack
    currList.remove(currList.size() - 1);

    // Exclude
    generateSubSets(nums, index + 1, result, currList);
}

public List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    generateSubSets(nums, 0, result, new ArrayList<>());
    return result;
}
```

### 🔹 Time Complexity:

* **O(n × 2ⁿ)** — Still generates 2ⁿ subsets, each taking O(n) time.

### 🔹 Space Complexity:

* **O(n)** — Recursion stack and temp list.

---

## ⚡ Optimal (Same as Better):

For this problem, **recursive backtracking** and **bitmasking** are **both optimal** in terms of time and space.

You can pick either based on what’s asked or what you’re comfortable with.

---

## 🧾 Summary Table:

| Approach       | Time Complexity | Space Complexity | Method       |
| -------------- | --------------- | ---------------- | ------------ |
| Brute Force    | O(n × 2ⁿ)       | O(n × 2ⁿ)        | Bitmasking   |
| Better         | O(n × 2ⁿ)       | O(n)             | Backtracking |
| Optimal (same) | O(n × 2ⁿ)       | O(n)             | Backtracking |

---
