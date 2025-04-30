### 🔍 Problem Summary:
Given an unsorted array of integers `nums`, return the length of the **longest consecutive elements sequence**.

You must write an algorithm that runs in **O(n)** time.

---

### ✅ Key Idea:
To find the **longest consecutive sequence** in an unsorted array efficiently:
- Use a **HashSet** for quick O(1) lookups.
- Only **start counting** from numbers that are the **beginning** of a sequence.

---

### ✅ Brute Force (Sorting-based) Approach:
- Sort the array → O(n log n)
- Traverse and count streaks:
  - Skip duplicates.
  - Reset count if current number is not consecutive.

**Time Complexity:** O(n log n)  
**Space Complexity:** O(1) if sorting in place.

---

### ✅ Optimized HashSet Approach (O(n)):

#### ✨ Core Insight:
- Insert all elements into a `HashSet` for O(1) lookup.
- For each number, **only start a sequence if `(num - 1)` is NOT present**, i.e., this is the **start** of a sequence.
- Then, keep checking for `(num + 1), (num + 2), ...` and count length.

---

### ✅ Code Recap:

```java
public int longestConsecutive(int[] nums) {
    int maxLength = 0;
    Set<Integer> set = new HashSet<>();

    for (int num : nums) {
        set.add(num);
    }

    for (int num : set) {
        if (!set.contains(num - 1)) { // Only start from sequence beginning
            int currentNum = num;
            int count = 1;

            while (set.contains(currentNum + 1)) {
                currentNum++;
                count++;
            }

            maxLength = Math.max(maxLength, count);
        }
    }

    return maxLength;
}
```

---

### 📊 Dry Run:

Input: `[100, 4, 200, 1, 3, 2]`

- Set: `{100, 4, 200, 1, 3, 2}`
- Starting from `1`:
  - 2 → 3 → 4 → (not 5)
  - Count = 4

Output: `4`

---

### 🧠 Intuition:
- The `if (!set.contains(num - 1))` condition helps avoid repeated work.
- Without this, you'd recheck parts of the same sequence multiple times.

---

### ⏱️ Time and Space Complexity:

- **Time:** O(n)
  - Each number is added to the set once.
  - Each number is visited at most once for counting sequences.
- **Space:** O(n) for the HashSet.

---

### ✍️ Summary:

| Step | Operation                 | Complexity |
|------|---------------------------|------------|
| 1    | Add all elements to set   | O(n)       |
| 2    | Loop to find sequences    | O(n)       |
| 3    | Return max length found   | O(1)       |

---
