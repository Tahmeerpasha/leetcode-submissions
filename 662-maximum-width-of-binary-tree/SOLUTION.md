# ✅ **662. Maximum Width of Binary Tree — Full Notes**

---

# 🔶 **Problem Summary**

The width of a level =
**(index of rightmost non-null node) – (index of leftmost non-null node) + 1**

You must treat the tree like a **complete binary tree with indexing**, even if nodes are missing.

---

# 🟠 **Brute Force (Not practical, but good to know)**

### **Idea**

* For each level, store the “complete binary tree index” of every node including null gaps.
* Actually build levels with placeholders for nulls.
* Count how many positions between first and last non-null node.

### **Why it's bad**

* Memory explodes for deep trees.
* Time wasted storing null nodes.

### **Time Complexity**

* Worst case: **O(N)** but with huge overhead
* Space: **O(N)** *per level* → can be **O(2^h)**

### **Interview Tip**

Just mention:

> “Brute force would push nulls too, but that explodes space. So we avoid storing nulls explicitly.”

---

# 🟡 **Better Approach (DFS with indexing)**

### **Idea**

* Use DFS and assign indices just like a heap:

  * left → `2*i + 1`
  * right → `2*i + 2`
* Track the **leftmost index at each depth**.
* Width = `currentIndex - leftmostIndex[depth] + 1`.

### **Steps**

* Maintain a `HashMap<depth, leftmostIndex>`.
* DFS(root, depth=0, index=0).
* Update answer every time you visit a node.

### **Time Complexity**

* **O(N)** (visit all nodes)
* **O(H)** space (recursion stack + map)

### **Pros**

* Clean, no queue, avoids integer overflow by normalizing with leftmost.

### **Cons**

* Interviewers prefer BFS version (more intuitive).

---

# 🟢 **Optimal Approach: BFS + Index Normalization (Your code)**

This is the cleanest and most commonly accepted method.

### **Idea**

* Use BFS level-order traversal.
* Assign each node a “position index” like a heap.
* For each level:

  * Normalize indices by subtracting `minIndex` (avoid overflow).
  * Width = `lastIndex - firstIndex + 1`.

### **Why subtract `minIndex`?**

Without this, indices become huge for deep trees and overflow `int`.

### **Pseudo Steps**

1. Push `(root, index=0)` to queue.
2. For each level:

   * Record `minIndex = queue.peek().index`
   * Normalize: `currIndex = index - minIndex`
   * Track `first` and `last`.
   * Push children with their heap-style indices.
3. Track max width.

### **Time Complexity**

* **O(N)** (every node processed once)
* **O(N)** worst-case queue size (skewed tree)

### **Space Complexity**

* **O(N)**

### **Why this is the best?**

* Simple
* Avoids overflow using normalization
* BFS naturally processes levels

---

# 📝 **Interview Tips / Tricks**

### 1. **Explain the indexing trick confidently:**

Say this:

> “I assign indices as if the tree were complete. This lets me measure gaps between nodes without storing nulls.”

### 2. **Why subtract min index?**

> “To avoid integer overflow for deep trees.”

Interviewers love this point.

### 3. **Edge cases**

* Single node → width = 1
* Skewed tree → width = 1
* Tree with missing children → indexing handles it

### 4. **Common mistake**

* Using `long` for index is safer in languages like C++.
* Using `int` without normalization → *wrong for deep trees*.

---

# 🧩 **Your Solution is Optimal**

Your code:

* BFS ✔️
* Index normalization ✔️
* Perfect structure ✔️
* Time & space optimal ✔️

This will pass in interviews.

---
