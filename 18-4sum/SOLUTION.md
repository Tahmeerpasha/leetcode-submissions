# 🧠 Problem: **4Sum**

**Link**: [LeetCode 18 - 4Sum](https://leetcode.com/problems/4sum/)
**Given**: An integer array `nums` and an integer `target`
**Goal**: Find all unique quadruplets `(a, b, c, d)` such that `a + b + c + d == target`.

---

## ✅ Constraints

* 1 ≤ nums.length ≤ 200
* -10⁹ ≤ nums\[i] ≤ 10⁹
* -10⁹ ≤ target ≤ 10⁹
* The result must not contain duplicate quadruplets

---

## 1. 💥 Brute Force Approach

### 🔧 Idea:

* Use **4 nested loops** to check every combination of 4 numbers
* If their sum is equal to `target`, store the sorted list in a `Set` to avoid duplicates

### 💻 Code Snippet:

```java
for(int i=0; i<n; i++)
  for(int j=i+1; j<n; j++)
    for(int k=j+1; k<n; k++)
      for(int l=k+1; l<n; l++)
        if(nums[i] + nums[j] + nums[k] + nums[l] == target)
          add sorted quadruplet to set
```

### ⏱ Time: `O(n⁴)`

### 📦 Space: `O(k)` (for storing unique quadruplets)

---

## 2. ⚡ Better Approach (3 Loops + HashSet)

### 🔧 Idea:

* Fix two elements (`i`, `j`)
* Use a **HashSet** to find the remaining two numbers that sum to the required value
* Store sorted quadruplets in a `Set` to avoid duplicates

### 🧠 Why Long?

To avoid integer overflow when dealing with very large values.

### 💻 Code Snippet:

```java
for(int i=0; i<n; i++)
  for(int j=i+1; j<n; j++) {
    Set<Long> seen = new HashSet<>();
    for(int k=j+1; k<n; k++) {
      long sum = (long)nums[i] + nums[j] + nums[k];
      long fourth = target - sum;
      if(seen.contains(fourth))
        add sorted quadruplet to set
      seen.add((long)nums[k]);
    }
  }
```

### ⏱ Time: `O(n³)`

### 📦 Space: `O(n)` for HashSet

---

## 3. 🚀 Optimal Approach (Sorting + Two Pointers)

### 🔧 Idea:

* **Sort** the array
* Use **2 nested loops (`i`, `j`)**, and **two pointers (`k`, `l`)** from both ends
* Skip duplicates at all levels
* Use `long` to prevent overflow

### 💻 Code Snippet:

```java
Arrays.sort(nums);
for (int i = 0; i < n; i++) {
    if (i > 0 && nums[i] == nums[i - 1]) continue;

    for (int j = i + 1; j < n; j++) {
        if (j > i + 1 && nums[j] == nums[j - 1]) continue;

        int k = j + 1, l = n - 1;
        while (k < l) {
            long sum = (long)nums[i] + nums[j] + nums[k] + nums[l];
            if (sum < target) k++;
            else if (sum > target) l--;
            else {
                add quadruplet to set
                move k and l, skipping duplicates
            }
        }
    }
}
```

### ⏱ Time: `O(n³)`

### 📦 Space: `O(k)` for result storage, otherwise `O(1)`

---

## 🔍 Why This Optimal is Better:

* Sorting enables efficient pointer movement
* Duplicate skipping avoids repeat work
* Time complexity is best possible for this problem (`O(n³)`)

---

## 📌 Summary Table

| Approach    | Time Complexity | Space Complexity     | Handles Duplicates?  | Notes                            |
| ----------- | --------------- | -------------------- | -------------------- | -------------------------------- |
| Brute Force | O(n⁴)           | O(k)                 | ✔️ via `Set`         | Very slow, only for small inputs |
| Better      | O(n³)           | O(n) per loop        | ✔️ via `Set`         | Faster, uses HashSet             |
| Optimal     | O(n³)           | O(1) (except result) | ✔️ via pointer logic | Best performance, scalable       |

---
![image](https://github.com/user-attachments/assets/b8627d69-3dc9-4025-ae81-66a2df0d840d)
