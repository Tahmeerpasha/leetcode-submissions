## 🔁 Leetcode 26: **Remove Duplicates from Sorted Array**

### 📌 Problem:
Given a **sorted array**, remove the duplicates **in-place** such that each element appears only once and return the new length.

---

## ✅ Optimized Approach (Two-Pointer)

### 🔧 Java Code:
```java
class Solution {
    public int removeDuplicates(int[] nums) {
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[j] != nums[i]) {
                j++;
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }
        return j + 1;
    }
}
```

---

### 🧠 Intuition:
- The array is sorted, so **duplicates will be adjacent**.
- Maintain `j` for the **last unique element**.
- Iterate with `i`, and when you find a new unique element, move it next to the last unique one.

---

### 📊 Time & Space:
- **Time:** O(n)
- **Space:** O(1)

---

## 🔍 Dry Run:

### 🔧 Input:
```java
nums = [1, 1, 2, 2, 3]
```

| i | j | nums[i] | nums[j] | Action                          | nums                   |
|---|---|---------|---------|----------------------------------|------------------------|
| 0 | 0 | 1       | 1       | Same → do nothing                | [1, 1, 2, 2, 3]        |
| 1 | 0 | 1       | 1       | Same → do nothing                | [1, 1, 2, 2, 3]        |
| 2 | 0 | 2       | 1       | New element → j++, swap(i,j)     | [1, 2, 1, 2, 3]        |
| 3 | 1 | 2       | 2       | Same → do nothing                | [1, 2, 1, 2, 3]        |
| 4 | 1 | 3       | 2       | New element → j++, swap(i,j)     | [1, 2, 3, 2, 1]        |

✅ Unique elements: `[1, 2, 3]`, return `j + 1 = 3`

---

## 🧱 Brute Force (for learning)

### 🛠️ Idea:
- Use extra data structure (like Set).
- Add elements into set, then copy back to array.

```java
class Solution {
    public int removeDuplicates(int[] nums) {
        Set<Integer> set = new LinkedHashSet<>();
        for (int num : nums) set.add(num);

        int index = 0;
        for (int num : set) nums[index++] = num;

        return set.size();
    }
}
```

- **Time:** O(n)
- **Space:** O(n) ← not optimal as per problem constraints.

---
