# 🧠 Problem: **3Sum**

**Link**: [LeetCode 15 - 3Sum](https://leetcode.com/problems/3sum/)
**Given**: An integer array `nums`
**Goal**: Find all unique triplets `(a, b, c)` such that `a + b + c == 0`.

---

## ✅ Constraints

* 0 ≤ nums.length ≤ 3000
* -10⁵ ≤ nums\[i] ≤ 10⁵
* Result must not contain duplicate triplets

---

## 1. 💥 Brute Force Approach

### 🔧 Idea:

* Use **3 nested loops** to generate all triplets
* Check if their sum is `0`
* Sort and store in a `Set` to avoid duplicates

### 💻 Code:

```java
for(int i=0; i<n; i++){
    for(int j=i+1; j<n; j++){
        for(int k=j+1; k<n; k++){
            if(nums[i] + nums[j] + nums[k] == 0){
                List<Integer> triplet = Arrays.asList(nums[i], nums[j], nums[k]);
                triplet.sort(null);
                result.add(triplet); // HashSet handles uniqueness
            }
        }
    }
}
```

### ⏱ Time: `O(n³)`

### 📦 Space: `O(k)` for storing unique triplets

---

## 2. ⚡ Better Approach (Using HashSet)

### 🔧 Idea:

* Fix one element at a time (`nums[i]`)
* Use a **HashSet** to find the other 2 elements that sum to `-nums[i]`
* Sort and store each valid triplet in a `Set` to avoid duplicates

### 💻 Code:

```java
for (int i = 0; i < n; i++) {
    Set<Integer> set = new HashSet<>();
    for (int j = i + 1; j < n; j++) {
        int third = -(nums[i] + nums[j]);
        if (set.contains(third)) {
            List<Integer> triplet = Arrays.asList(nums[i], nums[j], third);
            triplet.sort(null);
            result.add(triplet);
        }
        set.add(nums[j]);
    }
}
```

### ⏱ Time: `O(n²)`

### 📦 Space: `O(n)` (for the HashSet per iteration)

---

## 3. 🚀 Optimal Approach (Two Pointers + Sorting)

### 🔧 Idea:

* **Sort the array**
* Fix one element and use **two pointers** (`j`, `k`) to find the remaining two
* Move pointers while avoiding duplicates

### 💻 Code:

```java
Arrays.sort(nums);
for (int i = 0; i < nums.length; i++) {
    if (i > 0 && nums[i] == nums[i - 1]) continue; // Skip duplicates
    int j = i + 1, k = nums.length - 1;
    while (j < k) {
        int sum = nums[i] + nums[j] + nums[k];
        if (sum == 0) {
            result.add(Arrays.asList(nums[i], nums[j], nums[k]));
            j++; k--;
            while (j < k && nums[j] == nums[j - 1]) j++; // Skip duplicates
            while (j < k && nums[k] == nums[k + 1]) k--; // Skip duplicates
        } else if (sum < 0) j++;
        else k--;
    }
}
```

### ⏱ Time: `O(n²)`

### 📦 Space: `O(k)` for storing results, but **no extra space** used otherwise (`O(1)`)
![image](https://github.com/user-attachments/assets/ee6a0923-170c-4e64-becd-6083abc51c90)

---

## 📌 Summary Table

| Approach    | Time Complexity | Space Complexity        | Handles Duplicates? | Notes                      |
| ----------- | --------------- | ----------------------- | ------------------- | -------------------------- |
| Brute Force | O(n³)           | O(k)                    | ✔️ via `Set`        | Very slow for large inputs |
| Better      | O(n²)           | O(n) per iteration      | ✔️ via `Set`        | Faster with HashSet        |
| Optimal     | O(n²)           | O(1) (excluding result) | ✔️ efficiently      | Best and most efficient    |

---
