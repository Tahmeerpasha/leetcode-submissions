# 🔑 Problem: 503. Next Greater Element II

We’re given a circular array `nums`. For each element, find the **next greater element** in its circular order (wrap around to beginning). If none exists, return `-1`.

---

## 🥉 Brute Force

**Idea**: For each element `nums[i]`, scan the next `n-1` elements (wrapping using modulo).

```java
class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        for (int i = 0; i < n; i++) {
            int greater = -1;

            // Scan up to n-1 steps ahead (circular check using %)
            for (int j = 1; j < n; j++) {
                int nextIdx = (i + j) % n;
                if (nums[nextIdx] > nums[i]) {
                    greater = nums[nextIdx];
                    break;
                }
            }

            result[i] = greater;
        }

        return result;
    }
}
```

### Complexity

* **Time**: O(n²) (n elements × up to n lookups each)
* **Space**: O(1) (ignoring result array)

✅ Easy to write.
❌ Not scalable for large arrays (TLE on LeetCode).

---

## 🥈 Better (Duplicate Array + NGE I Optimal)

**Idea**:

1. Duplicate the array (`nums2 = nums + nums`).
2. Compute Next Greater Element I (monotonic stack) for `nums2`.
3. Take only the first `n` results.

```java
class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] extended = new int[2 * n];
        for (int i = 0; i < 2 * n; i++) {
            extended[i] = nums[i % n];
        }

        int[] nge = new int[2 * n];
        Stack<Integer> st = new Stack<>();

        // Process extended array
        for (int i = 2 * n - 1; i >= 0; i--) {
            while (!st.isEmpty() && st.peek() <= extended[i]) {
                st.pop();
            }
            nge[i] = st.isEmpty() ? -1 : st.peek();
            st.push(extended[i]);
        }

        // First n elements are our answer
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = nge[i];
        }
        return result;
    }
}
```

### Complexity

* **Time**: O(2n) = O(n)
* **Space**: O(2n) (stack + extended array + nge array)

✅ Reuses the solution from 496.
❌ Uses extra space for duplicated array.

---

## 🥇 Optimal (Single Pass with Modulo)

**Idea**:
Instead of creating an extended array, just simulate it by iterating `2n` times and using `i % n` for circular indexing.
Only fill results for first `n` indices.

```java
class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        Stack<Integer> st = new Stack<>();

        // Initialize with -1 (default if no greater exists)
        Arrays.fill(result, -1);

        // Traverse 2n times (simulate circular array)
        for (int i = 2 * n - 1; i >= 0; i--) {
            int idx = i % n;

            // Maintain monotonic decreasing stack
            while (!st.isEmpty() && st.peek() <= nums[idx]) {
                st.pop();
            }

            // Only record answer for the first pass (i < n)
            if (i < n) {
                result[idx] = st.isEmpty() ? -1 : st.peek();
            }

            // Push current element into stack
            st.push(nums[idx]);
        }

        return result;
    }
}
```

### Complexity

* **Time**: O(2n) = O(n) (Each element pushed/popped at most once)
* **Space**: O(n) (stack)

✅ Most efficient → no duplicated array needed.
✅ Clean, elegant, interview-ready.

---

## 🧠 Tips to Remember

1. If the problem says **circular array + next greater/smaller** → think **simulate with 2n traversal**.
2. **Pattern**:

   * "Next Greater Element" → monotonic decreasing stack.
   * "Next Smaller Element" → monotonic increasing stack.
3. Initialize result array with `-1` to avoid extra handling.
4. If it feels like O(n²), try:

   * Extending array (`nums + nums`), or
   * Using `% n` trick to simulate circular traversal.
5. Related practice problems:

   * 496\. Next Greater Element I
   * 739. Daily Temperatures
   * 907. Sum of Subarray Minimums
   * 84. Largest Rectangle in Histogram

---
