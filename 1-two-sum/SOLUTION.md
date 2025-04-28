# 📚 1. Two Sum — Revision Notes

### ❓ Problem:
Given an array `nums` and an integer `target`,  
**return indices** of two numbers such that they add up to `target`.  
(Assume exactly one solution exists, and you cannot use the same element twice.)

---

# ✍️ Approaches

---

## 1. Brute Force  
🛠 **Idea**: Try every pair of elements.

```java
for (int i = 0; i < n; i++) {
    for (int j = i+1; j < n; j++) {
        if (nums[i] + nums[j] == target) {
            return new int[] {i, j};
        }
    }
}
```

- ✅ Check all pairs
- ❌ **Time**: O(n²)
- ❌ **Space**: O(1)
- 🚫 Not efficient for large arrays

---

## 2. HashMap Optimized Approach  
🛠 **Idea**: Store previously seen numbers in a map while iterating.  
Check if `target - current number` is already seen.

```java
HashMap<Integer, Integer> remainingSum = new HashMap<>();
for (int i = 0; i < n; i++) {
    int diff = target - nums[i];
    if (remainingSum.containsKey(diff)) {
        return new int[] {i, remainingSum.get(diff)};
    } else {
        remainingSum.put(nums[i], i);
    }
}
```

### 💡 Explanation:
- At every index `i`,  
  calculate `diff = target - nums[i]`
- If `diff` already exists in map → we found the pair
- Else, store `nums[i]` with its index in the map.

### 🔥 Why it works?
- Map ensures **constant time lookup** → O(1) for containsKey and put.

### 📈 Complexity:
- **Time**: O(n)
- **Space**: O(n)

---

# 📊 Dry Run Example

Given:  
`nums = [2, 7, 11, 15]`, `target = 9`

| i | nums[i] | diff | Map | Action |
|:-:|:------:|:----:|:---:|:------:|
| 0 | 2 | 7 | {} | Put (2, 0) |
| 1 | 7 | 2 | {2:0} | Found 2 in map → return [1, 0] |

✅ Output → `[1, 0]`

---

# 📌 Final Key Points
- HashMap approach is best for **O(n) time**.
- Always check `(target - nums[i])` first **before inserting** `nums[i]`.
- Brute force only if constraints are very small.

---
![image](https://github.com/user-attachments/assets/2287cc4a-1fd2-427f-9960-8c4ddfb10247)
