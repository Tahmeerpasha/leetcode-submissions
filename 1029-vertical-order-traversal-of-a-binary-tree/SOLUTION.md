# **987. Vertical Order Traversal of a Binary Tree — Notes**

---

# ✅ **Problem Summary**

You must group tree nodes by their “vertical” alignment (column index).
Rules:

* Left child → column - 1
* Right child → column + 1
* If nodes share same **col** and **row**, sort by **value**
* Output columns from left → right

This is not the basic vertical order. The sorting by **row** then by **value** makes it trickier.

---

# ⚠️ **Approach Requirements**

To satisfy the constraints, each node must be tracked with:

* column
* row
* value

We need ordering by:

1. column ASC
2. row ASC
3. value ASC

---

# 🚫 **Brute Force Approach**

### **Idea**

* Do a DFS and store all nodes in a list of triplets `(col, row, value)`
* Sort the list based on required rules
* Group by column

### **Steps**

1. DFS(root, col, row)
2. Push `(col, row, value)` into an array
3. Sort by:

   * col
   * row
   * value
4. Group by col

### **Time Complexity**

* DFS: O(N)
* Sorting: **O(N log N)**
* Final grouping: O(N)

👉 **Total: O(N log N)**
👉 **Space: O(N)**

### **Why brute is fine?**

Sorting is already O(N log N). This is enough for passing constraints, but BFS-based structuring is cleaner.

---

# ⚡ **Better Approach (Using Sorting + BFS/DFS)**

Similar to brute, but we record nodes using BFS for correctness in BFS order (row increasing).
Still requires sorting afterward.

### **Idea**

* BFS traversal
* Push `(col, row, val)` everywhere
* Sort the list

Same complexity as brute. Cleaner logic.

👉 **Still O(N log N)**.

---

# 🏆 **Optimal Approach (TreeMap + PriorityQueue) — The one you implemented**

This is what interviewers expect.

### **Core Idea**

Use nested sorted structures:

```
TreeMap<col, TreeMap<row, MinHeap(values)>>
```

BFS ensures row assignment correctly.

### **Algorithm**

1. Use a queue storing `(node, col, row)`
2. Insert into nested TreeMaps
3. For each (col → row → PQ):

   * Dump values in sorted order

### **Why this works**

TreeMap automatically keeps cols & rows sorted.
PriorityQueue guarantees values sorted when row & col match.

---

# ⏳ **Complexity**

Let **N = number of nodes**.

### **Time Complexity**

* BFS traversal: O(N)
* Insert into TreeMap → O(log N)
* Polling priority queues → O(N log N) worst-case

👉 **Total: O(N log N)**

### **Space Complexity**

👉 **O(N)** (maps + queues + BFS storage)

---

# 💡 Tips & Tricks for Interviews

### **1️⃣ BFS is better than DFS here**

Because:

* BFS naturally increments rows level by level
* Avoids mistakes in row assignment order

### **2️⃣ Always track (col, row)**

If you don’t track row, the vertical output will be incorrect.

### **3️⃣ Sorting inside the same col & row is mandatory**

If two nodes land in the same position, they must be sorted by value.

### **4️⃣ TreeMap ensures lexicographic ordering**

Use:

* `TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>>`
* Java map ordering is crucial here.

### **5️⃣ PriorityQueue must be a min-heap**

Since value ordering needs ascending order.

### **6️⃣ Why BFS?**

Many candidates use DFS and get row collisions wrong when nodes at different depths get messed up.

### **7️⃣ Common Pitfalls**

* Forgetting to sort values for equal (col,row)
* Using normal HashMap instead of TreeMap
* Using DFS without proper row handling
* Right-shifting col instead of left-right increments

### **8️⃣ Quick way to explain to interviewer**

> “I’ll use BFS to track each node with a column and row index.
> Then store them inside a sorted map-of-maps with a min-heap to handle ties.
> Finally, traverse columns left to right, rows top to bottom, values sorted.”

Clean, simple, confident.

---
