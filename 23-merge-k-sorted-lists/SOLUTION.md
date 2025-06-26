## 🔍 Problem Statement

You are given an array of `k` linked lists, each sorted in ascending order.
You need to **merge all the linked lists** into one sorted linked list.

---

## ✅ Approaches

---

### 1. **Brute Force Approach**

#### 💡 Idea:

* Collect all the values into a list.
* Sort them.
* Create a new linked list from sorted values.

#### ⏱ Time Complexity:

* Collecting values: O(N)
* Sorting: O(N log N)
* Building new list: O(N)
  ⏳ **Total:** `O(N log N)`
  🧠 **Space:** O(N) for storing all node values.

---

### 2. **Better Approach (Sequential Two-Way Merge)**

#### 💡 Idea:

* Start from the first list, merge it with the second using `mergeTwoLists()`.
* Then merge the result with the third, and so on.

#### ⏱ Time Complexity:

* Worst case O(k \* N), where each merge takes longer as the list grows.
* Still better than brute if lists are small and mostly sorted.

🧠 **Space:** O(1) extra (in-place)

---

### 3. **✅ Optimal Approach – Using Min Heap (PriorityQueue)**

#### 💡 Idea:

* Use a min heap (priority queue) to always pick the **smallest head node** among all lists.
* Every time you poll a node, push its `next` if not null.

#### 💻 Code Summary:

```java
PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> a.val - b.val);

// Insert all heads into heap
for (ListNode node : lists) {
    if (node != null) minHeap.offer(node);
}

ListNode dummy = new ListNode(-1), tail = dummy;

while (!minHeap.isEmpty()) {
    ListNode curr = minHeap.poll();
    tail.next = curr;
    tail = curr;
    if (curr.next != null) minHeap.offer(curr.next);
}

return dummy.next;
```

#### ⏱ Time Complexity:

* Heap insert/poll: O(log k)
* Each node (N nodes total) is processed once → O(N log k)

🧠 **Space:** O(k) for heap
⚡ **Best for large k!**

---

## 📊 Time & Space Complexity Comparison

| Approach            | Time       | Space |
| ------------------- | ---------- | ----- |
| Brute Force         | O(N log N) | O(N)  |
| Better (Sequential) | O(kN)      | O(1)  |
| ✅ Optimal (MinHeap) | O(N log k) | O(k)  |

Where:

* `k` = number of lists
* `N` = total number of nodes

---

## 📌 Related Concepts

* Priority Queue / Min Heap
* Linked List Merge (classic)
* Divide and Conquer (you can also use Merge Sort style merge for even better performance)

---

## ✅ Summary

| Technique        | When to Use                                |
| ---------------- | ------------------------------------------ |
| Brute            | For small input or interview warm-up       |
| Sequential Merge | When k is small, or no extra space allowed |
| 🔥 MinHeap       | **Most efficient and scalable** approach   |

---
