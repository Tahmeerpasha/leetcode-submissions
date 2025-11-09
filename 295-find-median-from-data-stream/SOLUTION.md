## 🧩 Problem

Design a data structure that supports:

* `addNum(int num)` → adds a number to the stream
* `findMedian()` → returns the median of all elements added so far

---

## 🪓 Brute Force Approach

### 🔹 Intuition

Store all elements in a list.
Each time a new element is added, **sort the list** before finding the median.

### 🔹 Steps

1. Maintain a `List<Integer>`
2. On every `addNum`, insert the number and sort the list.
3. `findMedian()`:

   * If even → average of middle two
   * If odd → middle element

### 🔹 Code

```java
class MedianFinder {
    List<Integer> list;
    public MedianFinder() {
        list = new ArrayList<>();
    }

    public void addNum(int num) {
        list.add(num);
        Collections.sort(list);
    }

    public double findMedian() {
        int n = list.size();
        int mid = n / 2;
        if (n % 2 == 0)
            return (list.get(mid) + list.get(mid - 1)) / 2.0;
        else
            return list.get(mid);
    }
}
```

### 🔹 Complexity

* `addNum`: O(n log n) due to sorting
* `findMedian`: O(1)
* **Overall:** O(n log n) per insertion

### 🔹 Limitation

* Re-sorting after every insertion is wasteful.
* Not efficient for real-time or streaming data.

---

## ⚙️ Better Approach

### 🔹 Intuition

Instead of sorting every time, insert the element in **sorted order** using **binary search**.

### 🔹 Steps

1. Use `ArrayList`
2. On insertion, find position via binary search (O(log n))
3. Insert in list (O(n) shift cost)

### 🔹 Complexity

* `addNum`: O(n) (since shifting after insertion costs linear time)
* `findMedian`: O(1)
* Still **not optimal** for large data, but better than sorting each time.

---

## 🚀 Optimal Approach (Using Two Heaps)

### 🔹 Intuition

Maintain two heaps:

* **Max heap (`smallHeap`)** → holds the smaller half
* **Min heap (`largeHeap`)** → holds the larger half

This allows median retrieval in **O(1)** and insertion in **O(log n)**.

---

### 🔹 Algorithm

1. **Add number:**

   * Add to `smallHeap` (max-heap)
   * Move the largest from `smallHeap` to `largeHeap` (to keep order property)
   * If `largeHeap` size > `smallHeap` → move top from `largeHeap` to `smallHeap` (balance)

2. **Find median:**

   * If sizes equal → average of tops
   * Else → top of `smallHeap` (since it’ll have one extra element)

---

### 🔹 Code

```java
class MedianFinder {
    PriorityQueue<Integer> smallHeap; // Max-heap
    PriorityQueue<Integer> largeHeap; // Min-heap

    public MedianFinder() {
        smallHeap = new PriorityQueue<>(Collections.reverseOrder());
        largeHeap = new PriorityQueue<>();
    }

    public void addNum(int num) {
        smallHeap.offer(num);
        largeHeap.offer(smallHeap.poll());

        if (largeHeap.size() > smallHeap.size()) {
            smallHeap.offer(largeHeap.poll());
        }
    }

    public double findMedian() {
        if (largeHeap.size() == smallHeap.size()) {
            return (smallHeap.peek() + largeHeap.peek()) / 2.0;
        }
        return smallHeap.peek();
    }
}
```

---

### 🔹 Complexity

| Operation      | Time     | Space |
| -------------- | -------- | ----- |
| `addNum()`     | O(log n) | O(n)  |
| `findMedian()` | O(1)     | O(n)  |

---

## 🧠 Tips & Tricks

* **Balancing rule:** `smallHeap.size()` can exceed `largeHeap.size()` by at most **1**.
* **Order invariant:** Every element in `smallHeap` ≤ every element in `largeHeap`.
* **Heaps are perfect when:**

  * You need running medians
  * You care about top-k or smallest/largest elements dynamically
* **Don’t store all data:** The heaps handle partition logic — you never need the full sorted array.
* **Check edge cases:** Empty data stream, duplicate numbers, and alternating sequences (e.g. 1,2,3,4…).

---

## 🧾 Interview Summary

| Approach  | Data Structure              | Time Complexity | Space | Notes                            |
| --------- | --------------------------- | --------------- | ----- | -------------------------------- |
| Brute     | List + sort                 | O(n log n)      | O(n)  | Simple but slow                  |
| Better    | List + binary search insert | O(n)            | O(n)  | Still inefficient                |
| Optimal ✅ | Two Heaps (Min + Max)       | O(log n)        | O(n)  | Best tradeoff for streaming data |

---
