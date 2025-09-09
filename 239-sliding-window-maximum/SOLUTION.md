## 🔹 Problem Restatement

Given an array `nums` and an integer `k`, return the **maximum value in every sliding window of size `k`** as the window moves from left to right.

---

## 🔸 Brute Force Approach

**Idea:**
For each window of size `k`, scan all elements inside the window and find the maximum.

**Steps:**

1. Loop `i` from `0 → n - k`.
2. For each window `nums[i...i+k-1]`, find max by iterating through all `k` elements.
3. Store the max in the result array.

**Code Sketch:**

```java
for (int i = 0; i <= n - k; i++) {
    int max = Integer.MIN_VALUE;
    for (int j = i; j < i + k; j++) {
        max = Math.max(max, nums[j]);
    }
    res[i] = max;
}
```

**Complexity:**

* **Time:** O(n \* k) → For each of `n-k+1` windows, scan `k` elements.
* **Space:** O(1) extra (excluding output array).

**✅ Good for:** Small inputs (n, k ≤ 1000).
**❌ Bad for:** Large inputs (n up to 10⁵).

---

## 🔸 Better Approach (Heap / Priority Queue)

**Idea:**
Use a **Max-Heap (PriorityQueue with comparator)** to store elements of the window.

* At each step, push the current element into the heap.
* Remove elements that are outside the current window.
* The heap’s root is always the maximum.

**Steps:**

1. Use a max heap storing `(value, index)`.
2. Push current element with index.
3. If the max element’s index is outside the window (`index <= i - k`), pop it.
4. The top of the heap is the current window’s maximum.

**Complexity:**

* **Time:** O(n log k) → Each push/pop is log k, done n times.
* **Space:** O(k) for the heap.

**✅ Good for:** Understanding / easier to implement.
**❌ Bad for:** Not optimal for n=10⁵ (log k overhead).

---

## 🔸 Optimal Approach (Monotonic Deque)

**Idea:**
Maintain a **deque of indices** such that:

* The deque stores indices of elements in **decreasing order of values** (`nums[dq[0]] >= nums[dq[1]] >= ...`).
* The **front** (`dq.peekFirst()`) is always the index of the maximum element in the current window.
* Before pushing a new element:

  * Remove indices from the back if their values are smaller (they’ll never be needed).
  * Remove the front if it goes out of the window (`<= i - k`).

**Steps:**

1. Iterate over `nums[i]`.
2. Pop back while `nums[dq.peekLast()] < nums[i]`.
3. Push `i` into deque.
4. Remove front if out of window.
5. Record `nums[dq.peekFirst()]` once `i >= k-1`.

**Complexity:**

* **Time:** O(n) → Each element added/removed at most once.
* **Space:** O(k) for deque.

**✅ Best Choice:** Works efficiently for `n` up to 10⁵.

---

## 🔸 Key Tips

* **Deque trick**: Always store **indices**, not values (to check if index is out of window).
* **Monotonic property** ensures O(n) because each element is pushed and popped at most once.
* **Heap approach** is simpler but slower. Use deque in interviews for optimality.
* **Brute force** is only for explanation / warm-up.

---

👉 So final ranking:

* **Brute:** O(n\*k) — naive.
* **Better (Heap):** O(n log k).
* **Optimal (Deque):** O(n).

---
