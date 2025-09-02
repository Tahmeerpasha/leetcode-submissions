# 🚀 Problem: 2104. Sum of Subarray Ranges

We need the **sum of ranges of all subarrays**.

* Range of subarray = `max - min`.
* So:

  $$
  \text{Answer} = \sum(\text{subarray max}) - \sum(\text{subarray min})
  $$

---

## 🟢 Brute Force Approach

### Idea

* Enumerate all subarrays.
* For each subarray:

  * Track `min` and `max`.
  * Add `(max - min)` to result.

### Code

```java
class Solution {
    public long subArrayRanges(int[] nums) {
        long sum = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int min = nums[i], max = nums[i];
            for (int j = i; j < n; j++) {
                min = Math.min(min, nums[j]);
                max = Math.max(max, nums[j]);
                sum += max - min;
            }
        }
        return sum;
    }
}
```

### Complexity

* Time: **O(n²)** → two loops.
* Space: **O(1)**.

✅ Works but not feasible for `n = 10^5`.

---

## 🟡 Better Approach (Still O(n²), optimized updates)

* While extending a subarray `(i → j)`, just update `min` and `max` instead of recomputing from scratch.
* Already applied in the brute code above.
* So complexity **remains O(n²)**.

No real improvement beyond brute.

---

## 🔵 Optimal Approach – Contribution + Monotonic Stack

### Key Idea

* Every element contributes as:

  * **Max:** how many subarrays it is the maximum of.
  * **Min:** how many subarrays it is the minimum of.

👉 Final Answer = **Σ contributions as max − Σ contributions as min**

---

### Contribution Formula

For element `arr[i]`:

* Let `p` = index of **Previous Greater (or Smaller)**
* Let `n` = index of **Next Greater (or Smaller)**
* Then number of subarrays where `arr[i]` is min/max =

  $$
  (i - p) \times (n - i)
  $$
* Contribution = `arr[i] * (left * right)`

---

### Inequality Rules (⚠ Important!)

* For **Minimums**:

  * NSE → `>=`
  * PSE → `>`

* For **Maximums**:

  * NGE → `<=`
  * PGE → `<`

This avoids **double-counting equal elements**.

---

### Optimal Code (Fixed Version)

```java
import java.util.*;

class Solution {
    public long subArrayRanges(int[] nums) {
        return sumSubarrayMaxs(nums) - sumSubarrayMins(nums);
    }

    // -------- MIN contributions --------
    public long sumSubarrayMins(int[] arr) {
        long sum = 0;
        int[] nse = findNSE(arr);
        int[] pse = findPSE(arr);
        for (int i = 0; i < arr.length; i++) {
            int left = i - pse[i];
            int right = nse[i] - i;
            sum += (long) arr[i] * left * right;
        }
        return sum;
    }

    int[] findNSE(int[] arr) {
        Stack<Integer> st = new Stack<>();
        int[] nse = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            while (!st.isEmpty() && arr[st.peek()] >= arr[i]) // >=
                st.pop();
            nse[i] = st.isEmpty() ? arr.length : st.peek();
            st.push(i);
        }
        return nse;
    }

    int[] findPSE(int[] arr) {
        Stack<Integer> st = new Stack<>();
        int[] pse = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            while (!st.isEmpty() && arr[st.peek()] > arr[i]) // >
                st.pop();
            pse[i] = st.isEmpty() ? -1 : st.peek();
            st.push(i);
        }
        return pse;
    }

    // -------- MAX contributions --------
    public long sumSubarrayMaxs(int[] arr) {
        long sum = 0;
        int[] nge = findNGE(arr);
        int[] pge = findPGE(arr);
        for (int i = 0; i < arr.length; i++) {
            int left = i - pge[i];
            int right = nge[i] - i;
            sum += (long) arr[i] * left * right;
        }
        return sum;
    }

    int[] findNGE(int[] arr) {
        Stack<Integer> st = new Stack<>();
        int[] nge = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            while (!st.isEmpty() && arr[st.peek()] <= arr[i]) // <=
                st.pop();
            nge[i] = st.isEmpty() ? arr.length : st.peek();
            st.push(i);
        }
        return nge;
    }

    int[] findPGE(int[] arr) {
        Stack<Integer> st = new Stack<>();
        int[] pge = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            while (!st.isEmpty() && arr[st.peek()] < arr[i]) // <
                st.pop();
            pge[i] = st.isEmpty() ? -1 : st.peek();
            st.push(i);
        }
        return pge;
    }
}
```

---

## ✅ Complexity of Optimal Solution

* **Time:** O(n) (each element pushed & popped once).
* **Space:** O(n) (stack + helper arrays).

---

## 📝 Tips for Interview

1. If problem asks for **range sums** → think in terms of **max contribution - min contribution**.
2. Use **monotonic stacks** for "next/previous greater/smaller" patterns.
3. Pay attention to **strict vs non-strict inequality** rules.
4. Use `long` to avoid overflow (ranges can get large).
5. Don’t add `% MOD` unless explicitly required (2104 doesn’t need it).

---
