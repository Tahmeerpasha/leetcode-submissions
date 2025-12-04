# **🚀 Problem Summary**

You're given:

* A binary tree root
* A target node
* An integer **K**

Find **all nodes at distance K** from the target.
Distance = number of edges.

---

# **1️⃣ Brute Force Approach**

### **Idea**

For each node in the tree:

1. Find the path distance between that node and the target node.
2. If distance equals K → add to answer.

To compute distance between any two nodes, you find LCA + compute depths → expensive.

### **Steps**

* Traverse every node.
* For each node:

  * Compute distance(node, target).
  * If distance == k → include it.

### **Time Complexity**

* Distance computation per node = O(N)
* There are N nodes
  ➡️ **O(N²)**

### **Space Complexity**

* O(H) recursion stack
  ➡️ **O(N)** worst case

### **Why This Is Bad**

* Extremely slow for large trees
* Recomputes the same paths repeatedly
* Interviewers expect better

---

# **2️⃣ Better Approach (Convert to Undirected Graph)**

### **Idea**

Treat the tree like an **undirected graph**:

* Parent-child connections both ways
* Run BFS starting from target
* Stop at level K

### **Steps**

1. Convert tree → adjacency list graph (each node stores neighbors).
2. BFS from target for K levels.
3. Collect nodes at level K.

### **Time Complexity**

* Build graph: O(N)
* BFS: O(N)
  ➡️ **O(N)**

### **Space Complexity**

* Graph adjacency list: O(N)
* BFS queue + visited: O(N)
  ➡️ **O(N)**

### **Pros**

* Clean logic
* Easy to explain in interviews
* No need for parent pointers during tree traversal

### **Cons**

* Requires additional adjacency list structure
* Slightly more memory

---

# **3️⃣ Optimal Approach (Parent Mapping + BFS)**

**→ This is exactly what your code implements.**

### **Idea**

1. **Map each node to its parent** in one BFS.
2. Then BFS from the target, but this time you can move:

   * Left child
   * Right child
   * Parent
3. Stop BFS when current level == K
4. All nodes in queue = answer

### **Steps**

#### **A. Mark parents**

Level-order traversal:

* Store parent pointers in HashMap

#### **B. BFS from target**

* Maintain visited map
* Move in 3 directions
* Stop at level k

### **Time Complexity**

All operations are linear:

* Parent marking = O(N)
* BFS = O(N)
  ➡️ **O(N)**

### **Space Complexity**

* Parent map = O(N)
* Visited + queue = O(N)
  ➡️ **O(N)**

### **Why This Is Optimal**

* No double processing
* No redundant graph building
* Simple + intuitive
* Best balance of time + space

---

# **🔥 Interview Tips & Tricks**

### **1. Mention Real-World Analogy**

“This is like finding all people at distance K in a social network starting from one person.”

This shows conceptual understanding — interviewers love it.

---

### **2. Be ready for follow-up questions**

Common follow-ups:

* What if the target doesn't exist?
* What if K > tree height?
* What if K = 0? (Return target’s value)

---

### **3. Mention BFS-level-order clarity**

When K is involved with levels → **BFS is usually the right tool**.

Mention this explicitly; it's a strong DSA insight.

---

### **4. Say how you avoid revisiting nodes**

Use a `visited` map → prevents cycles (since we treat parent links as edges)

---

### **5. Be ready to code parent mapping quickly**

Whether DFS or BFS, both OK:

* Interviewer-friendly version = BFS (cleaner)

---

### **6. Know the edge cases**

* Empty tree → return empty list
* K=0 → return [target.val]
* Target is leaf → only go upward and sibling side
* Unbalanced trees

---

# **4️⃣ Final Summary Table**

| Approach                               | Time  | Space | Notes                       |
| -------------------------------------- | ----- | ----- | --------------------------- |
| **Brute (Compute dist for all nodes)** | O(N²) | O(N)  | Not recommended             |
| **Better (Graph approach)**            | O(N)  | O(N)  | Convert to undirected graph |
| **Optimal (Parent mapping + BFS)**     | O(N)  | O(N)  | Cleanest + standard         |

---
