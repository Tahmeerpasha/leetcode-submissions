# 📝 Subarray Problem Cheat Sheet

### **Step 1: Identify problem type**

* Does the problem ask for **sum/length/max/min/count** of subarrays? ✅

---

### **Step 2: Check array properties**

| Property                                                    | Recommended Approach            |
| ----------------------------------------------------------- | ------------------------------- |
| All elements **non-negative**                               | Sliding Window / Two Pointers   |
| Elements **can be negative**                                | Prefix Sum + HashMap            |
| Asking for **max/min sum**                                  | Kadane’s Algorithm (DP)         |
| Monotonic property needed (next greater, min/max in window) | Stack / Deque / Monotonic Queue |
| Binary array (0/1)                                          | Sliding Window (AtMost trick)   |

---

### **Step 3: Identify pattern**

1. **Count subarrays with sum ≤ K / exactly K**

   * Sliding Window: `atMost(K) - atMost(K-1)`
   * Prefix sum + HashMap (general)
2. **Max/Min length subarray**

   * Sliding Window
   * Binary search on length (if constraints allow)
3. **Max/Min sum**

   * Kadane’s
4. **Max/Min in each window of size k**

   * Monotonic Queue / Deque

---

### **Step 4: Implementation Tips**

* Sliding window: maintain `[l..r]` window and expand/shrink based on condition.
* Prefix sum: store count of sums seen so far in hashmap.
* Kadane: maintain running sum and max/min at each step.
* Binary arrays: `atMost(goal) - atMost(goal-1)` trick works beautifully.

---

### **Step 5: Edge Cases**

* Empty array, single element
* Goal = 0 or sum = 0
* All zeros, all ones, alternating patterns
* Large inputs → optimize to O(n)

---

### **Visual Flowchart (Decision Making)**

```
           ┌─────────────┐
           │ Subarray?   │
           └─────┬───────┘
                 │
         ┌───────┴────────┐
         │                 │
  Non-negative?         Can be negative?
     │                       │
Sliding Window /       Prefix Sum + HashMap
Two Pointers                 │
     │                       │
  Exactly K?            Max/Min Sum?
     │                       │
  atMost(K) trick        Kadane Algorithm
```

---
