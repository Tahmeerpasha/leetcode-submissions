## 🧩 Problem:

**Find the Kth Largest Element in an Array**

We’re given an unsorted array `nums` and an integer `k`.
We need to return the **kth largest** element in the array (not the kth distinct).

---

## 🥉 **1. Brute Force — Sorting Approach**

### 💡 **Idea:**

If we sort the array, the elements will be in order.
Then, the **kth largest** element is just the element at index `n - k` (0-based indexing).

### 🧠 **Code:**

```java
Arrays.sort(nums);
return nums[nums.length - k];
```

### ⏱️ **Complexity:**

* **Time:** O(n log n) — because of sorting.
* **Space:** O(1) — in-place sorting (if `Arrays.sort()` for primitives).

### ⚙️ **When to use:**

* Quick and simple when constraints are small.
* Not the most efficient for very large arrays.

---

## 🥈 **2. Better — Max Heap (Priority Queue with Reverse Order)**

### 💡 **Idea:**

* Use a **max heap** (largest element always at top).
* Remove (`poll`) the largest `k - 1` elements.
* The next element at the top is the **kth largest**.

### 🧠 **Code:**

```java
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
for (int num : nums) maxHeap.add(num);

for (int i = 0; i < k - 1; i++) maxHeap.poll();

return maxHeap.peek();
```

### ⏱️ **Complexity:**

* Build heap: O(n)
* Remove (k-1) elements: O(k log n)
* ✅ **Total:** O(n + k log n)
* **Space:** O(n)

### ⚙️ **When to use:**

* Easier to reason about conceptually.
* Useful if you already have a heap-based utility.

---

## 🥇 **3. Optimal 1 — Min Heap of Size k (Most Practical)**

### 💡 **Idea:**

Instead of keeping *all* elements in a heap,
we only maintain the **top k largest elements** using a **min heap**.

Steps:

1. Iterate through each number.
2. Push into heap.
3. If heap size exceeds `k`, remove the smallest (top).
4. At the end, the top of the heap is the **kth largest**.

### 🧠 **Code:**

```java
PriorityQueue<Integer> pq = new PriorityQueue<>();
for (int num : nums) {
    pq.add(num);
    if (pq.size() > k)
        pq.poll();
}
return pq.poll();
```

### ⏱️ **Complexity:**

* Each add/poll: O(log k)
* For all n elements: O(n log k)
* ✅ **Total:** **O(n log k)**
* **Space:** **O(k)**

### ⚙️ **Why it’s optimal:**

* Much faster than sorting when `k << n`.
* Commonly used pattern for “kth largest” or “top K” problems.

---

## 💎 **4. Optimal 2 — QuickSelect (Average O(n))**

### 💡 **Idea:**

This is a variation of **QuickSort’s partition** logic.
We select a pivot, partition the array so that:

* Elements ≤ pivot are on one side,
* Elements > pivot are on the other.

Then:

* If pivot’s index == n - k → we found the kth largest.
* Else we move left/right based on pivot’s position.

### 🧠 **Code (in your file):**

```java
int left = 0, right = nums.length - 1;
int target = nums.length - k; // kth largest index (0-based)

while (true) {
    int pivotIndex = partition(nums, left, right);
    if (pivotIndex == target)
        return nums[pivotIndex];
    else if (pivotIndex < target)
        left = pivotIndex + 1;
    else
        right = pivotIndex - 1;
}
```

### ⏱️ **Complexity:**

| Case                   | Time  | Space |
| ---------------------- | ----- | ----- |
| **Average**            | O(n)  | O(1)  |
| **Worst (bad pivots)** | O(n²) | O(1)  |

### ⚙️ **Why it’s powerful:**

* Doesn’t need extra space.
* Extremely fast on average.
* Real-world implementations (like C++ `nth_element`) use this.

---

## ⚡ **Quick Comparison**

| Approach    | Time Complexity            | Space    | Type        | Notes                        |
| ----------- | -------------------------- | -------- | ----------- | ---------------------------- |
| Brute       | O(n log n)                 | O(1)     | Sort        | Easiest but slower           |
| Better      | O(n + k log n)             | O(n)     | Max Heap    | Heavy on space               |
| ✅ Optimal 1 | **O(n log k)**             | **O(k)** | Min Heap    | Best practical choice        |
| ⚡ Optimal 2 | **O(n)** avg / O(n²) worst | **O(1)** | QuickSelect | Fastest in average, trickier |

---

## 💡 **Tricks & Interview Tips**

| Concept                                           | Tip                                                         |
| ------------------------------------------------- | ----------------------------------------------------------- |
| kth largest → **Min Heap** of size k              | kth smallest → **Max Heap** of size k                       |
| `PriorityQueue` in Java → **Min Heap by default** | Use `Collections.reverseOrder()` for Max Heap               |
| In QuickSelect, always compute `target = n - k`   | Because 0-based indexing                                    |
| Mention tradeoffs                                 | “QuickSelect is faster on average but unstable worst-case.” |
| Use `peek()` to view top without removing         | Use `poll()` to remove                                      |

---
