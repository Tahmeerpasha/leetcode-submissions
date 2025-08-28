# 🔑 Problem: 496. Next Greater Element I

You are given two arrays `nums1` and `nums2`, where `nums1` is a subset of `nums2`. For each element in `nums1`, find the **next greater element** in `nums2`. If no such element exists, return `-1`.

---

## 🥉 Brute Force (Nested Loops)

**Idea**: For each element in `nums1`, find its position in `nums2`, then linearly search to the right until you find a greater element.

```java
public int[] nextGreaterElement(int[] nums1, int[] nums2) {
    int[] result = new int[nums1.length];
    for (int i = 0; i < nums1.length; i++) {
        int num = nums1[i];
        int idx = -1;
        for (int j = 0; j < nums2.length; j++) {
            if (nums2[j] == num) {
                idx = j; break;
            }
        }
        int greater = -1;
        for (int k = idx + 1; k < nums2.length; k++) {
            if (nums2[k] > num) {
                greater = nums2[k]; break;
            }
        }
        result[i] = greater;
    }
    return result;
}
```

### Complexity

* **Time**: O(n1 × n2)
* **Space**: O(1)

✅ Easy to come up with.
❌ Very slow for large arrays.

---

## 🥈 Better Solution (Rebuild stack for each num1 element)

**Idea**: For each element in `nums1`, simulate scanning `nums2` with a stack.
Your second solution was in this category.
```java
  public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int n1 = nums1.length, k = n1 - 1;
        int n2 = nums2.length;
        int[] result = new int[n1];
        for (int i = n1 - 1; i >= 0; i--) {
            Stack<Integer> st = new Stack<>();
            for (int j = n2 - 1; j >= 0 && nums1[i] != nums2[j]; j--) {
                st.push(nums2[j]);
            }
            if (st.isEmpty() && k >= 0) {
                result[k--] = -1;
                continue;
            }
            while (!st.isEmpty() && st.peek() < nums1[i])
                st.pop();
            if (st.isEmpty())
                result[k--] = -1;
            else
                result[k--] = st.peek();
        }
        return result;
    }
```
### Complexity

* **Time**: O(n1 × n2) (still quadratic)
* **Space**: O(n2)

✅ Cleaner than brute.
❌ Still inefficient since you rebuild the stack again & again.

---

## 🥇 Optimal Solution (Monotonic Stack + HashMap)

**Idea**:

1. Traverse `nums2` **once** with a monotonic decreasing stack.

   * If current number > stack’s top → Pop stack and record that this popped number’s next greater is current number.
   * Push current number into stack.
2. After processing, any numbers left in stack have no greater element → map to -1.
3. Build result for `nums1` using the map.

```java
public int[] nextGreaterElement(int[] nums1, int[] nums2) {
    Map<Integer, Integer> nextGreater = new HashMap<>();
    Stack<Integer> st = new Stack<>();

    for (int num : nums2) {
        while (!st.isEmpty() && st.peek() < num) {
            nextGreater.put(st.pop(), num);
        }
        st.push(num);
    }
    while (!st.isEmpty()) {
        nextGreater.put(st.pop(), -1);
    }

    int[] result = new int[nums1.length];
    for (int i = 0; i < nums1.length; i++) {
        result[i] = nextGreater.get(nums1[i]);
    }
    return result;
}
```

### Complexity

* **Time**: O(n1 + n2)
* **Space**: O(n2) (map + stack)

✅ Each element pushed & popped once → linear time.
✅ Handles all cases in one pass.

---

## 🧠 Tips to Remember

1. **Pattern spotting** → If problem asks about **“Next Greater/Smaller to Left/Right”**, it’s almost always solvable with a **monotonic stack**.

   * Next Greater → Decreasing stack
   * Next Smaller → Increasing stack
2. **Don’t re-scan** arrays again and again → preprocessing is key.
3. Use **HashMap** to connect preprocessed results (`nums2`) with queries (`nums1`).
4. Practice these standard monotonic stack problems:

   * Next Greater Element I / II
   * Daily Temperatures
   * Stock Span Problem
   * Largest Rectangle in Histogram

---
