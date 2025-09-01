# 📘 Problem: Sum of Subarray Minimums

You are given an array `arr`. For each subarray of `arr`, find the minimum element, then sum all those minimums. Return the answer modulo `1e9+7`.

---

## 1. 🐌 Brute Force Approach

**Idea:**

* Generate **all subarrays**.
* For each subarray, compute the **minimum element**.
* Add it to the answer.

### Code:

```java
class Solution {
    public int sumSubarrayMins(int[] arr) {
        long sum = 0;
        int MOD = 1000000007;

        for (int i = 0; i < arr.length; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = i; j < arr.length; j++) {
                min = Math.min(min, arr[j]);  // find minimum in current subarray
                sum = (sum + min) % MOD;
            }
        }
        return (int) sum;
    }
}
```

### Complexity:

* **Time:** O(n²) → Because we are checking all subarrays.
* **Space:** O(1).

✅ Easy to understand, but **too slow for large n (n ≤ 30,000)**.

---

## 2. ⚡ Better Approach (Prefix Minimums Reuse)

**Idea:**
Instead of recalculating minimum for each subarray, we can **reuse results** while extending the subarray.

* Keep track of **min till now** while extending from `i → j`.
* Already shown in brute, this reduces duplicate work but still O(n²).

👉 No major asymptotic improvement here, so usually skipped.

---

## 3. 🚀 Optimal Approach (Monotonic Stack + Contribution Method)

**Key Idea:**
Every element `arr[i]` contributes as the **minimum** in some subarrays.

* Count **how many subarrays** have `arr[i]` as their **minimum**.
* Contribution of \`arr\[i] = arr\[i] × (#subarrays where it’s minimum)\*\*.

How to count?

* Find **Previous Smaller Element (PSE)** → Nearest smaller element on left.
* Find **Next Smaller Element (NSE)** → Nearest smaller element on right.

For `arr[i]`:

* **Left choices = i - pse\[i]**
* **Right choices = nse\[i] - i**
* Total subarrays where `arr[i]` is min = `left × right`.

### Code:

```java
import java.util.*;

class Solution {
    public int sumSubarrayMins(int[] arr) {
        long sum = 0;
        int MOD = 1000000007;

        int[] nse = findNSE(arr);
        int[] pse = findPSE(arr);

        for (int i = 0; i < arr.length; i++) {
            int left = i - pse[i];
            int right = nse[i] - i;
            sum = (sum + (1L * arr[i] * left * right) % MOD) % MOD;
        }
        return (int) sum;
    }

    // Next Smaller Element (Right side)
    int[] findNSE(int[] arr) {
        Stack<Integer> st = new Stack<>();
        int[] nse = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            while (!st.isEmpty() && arr[st.peek()] >= arr[i])
                st.pop();
            nse[i] = st.isEmpty() ? arr.length : st.peek();
            st.push(i);
        }
        return nse;
    }

    // Previous Smaller Element (Left side)
    int[] findPSE(int[] arr) {
        Stack<Integer> st = new Stack<>();
        int[] pse = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            while (!st.isEmpty() && arr[st.peek()] > arr[i])
                st.pop();
            pse[i] = st.isEmpty() ? -1 : st.peek();
            st.push(i);
        }
        return pse;
    }
}
```

---

### Complexity:

* **Time:** O(n) → Each element pushed & popped at most once in stacks.
* **Space:** O(n) for PSE, NSE arrays + stack.

✅ Works efficiently for large inputs.

---

## 🔑 Tips to Remember

1. **Contribution Method:** Instead of recalculating min for each subarray, count **how many subarrays each element is min of**.
2. **Stack Use:**

   * PSE → handles left boundary.
   * NSE → handles right boundary.
   * Use `>` for PSE and `>=` for NSE to avoid double counting.
3. **Modulo:** Always take `% 1e9+7` at each step to avoid overflow.
4. **Pattern:** Same technique applies for problems like **sum of subarray maximums**, largest rectangle in histogram, etc.

---
