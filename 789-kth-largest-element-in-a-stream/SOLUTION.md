## 🔍 Problem Summary

You need to design a class that finds the **Kth largest element** in a stream of integers (numbers are added one by one).
After each insertion, return the Kth largest element so far.

---

## 🧠 Intuition

If you always had a **sorted list** of numbers, finding the Kth largest would be easy — just pick the element at position `len - k`.
But since new numbers keep coming in, sorting every time would be too costly.
We only care about the **top K largest** elements at any point.
So instead of storing everything, we can maintain just **K elements** — the K largest ones.

---

## 🪓 Brute Force Approach

**Idea:**
Keep all elements in a list. Every time you add a new value:

1. Add it to the list.
2. Sort the list.
3. Return the element at index `len - k`.

**Code Sketch:**

```java
class KthLargest {
    List<Integer> list;
    int k;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        list = new ArrayList<>();
        for (int n : nums) list.add(n);
        Collections.sort(list);
    }

    public int add(int val) {
        list.add(val);
        Collections.sort(list);
        return list.get(list.size() - k);
    }
}
```

**Complexity:**

* Each add → O(n log n) (because of sorting)
* Space → O(n)

❌ **Not scalable** when the stream is large or K is small.

---

## ⚙️ Better Approach — Using Max Heap (Conceptually Simple but Costly)

**Idea:**
Use a **max heap** to always remove the largest elements until only `n - k + 1` remain — the top `k`th element would then be the root.

**Steps:**

1. Push all elements into a **max heap** (negate values or use custom comparator).
2. For each new element:

   * Add it to the heap.
   * Pop elements until heap size = n - k + 1.

**Complexity:**

* Each insertion/removal → O(log n)
* But unnecessary since we maintain extra elements

❌ Still inefficient memory-wise.

---

## ✅ Optimal Approach — Using Min Heap (Efficient & Elegant)

**Core Idea:**
Maintain a **min heap** of size `k` containing the **K largest elements** seen so far.

* The smallest element in this heap (`pq.peek()`) is the **Kth largest** overall.

**Steps:**

1. Initialize a min heap.
2. Add elements one by one:

   * Push new element.
   * If heap size > k → remove smallest (`poll()`).
3. The top of the heap is always the Kth largest.

**Implementation (Your Code):**

```java
class KthLargest {
    PriorityQueue<Integer> pq;
    int size;

    public KthLargest(int k, int[] nums) {
        pq = new PriorityQueue<>(); // Min heap
        size = k;
        for (int num : nums) {
            pq.add(num);
            while (pq.size() > k)
                pq.poll();
        }
    }

    public int add(int val) {
        pq.add(val);
        while (pq.size() > size)
            pq.poll();
        return pq.peek();
    }
}
```

---

## ⏱️ Complexity

* Each `add()` → O(log k) (heap insertion/removal)
* Space → O(k)

✅ **Best possible for streaming data**
We only store K elements regardless of how many have arrived.

---

## 🧩 Dry Run Example

```
K = 3
Nums = [4, 5, 8, 2]

Heap after init: [4,5,8] → min=4 (3rd largest)

add(3): [4,5,8,3] → remove 3 → [4,5,8] → 4  
add(10): [4,5,8,10] → remove 4 → [5,8,10] → 5  
add(9): [5,8,10,9] → remove 5 → [8,9,10] → 8  
add(4): [8,9,10,4] → remove 4 → [8,9,10] → 8
```

---

## 💡 Tips & Tricks

* Use **min heap** for Kth largest, **max heap** for Kth smallest.
* Keep heap **size = K**, not all elements.
* PriorityQueue in Java is a **min heap** by default.
* If you need a max heap → `new PriorityQueue<>(Collections.reverseOrder())`.

---

## 🧾 Quick Summary Table

| Approach | Data Structure    | Time per add | Space | Comments |
| -------- | ----------------- | ------------ | ----- | -------- |
| Brute    | Array + Sort      | O(n log n)   | O(n)  | Too slow |
| Better   | Max Heap (full)   | O(log n)     | O(n)  | Overkill |
| Optimal  | Min Heap (size k) | O(log k)     | O(k)  | ✅ Best   |

---
