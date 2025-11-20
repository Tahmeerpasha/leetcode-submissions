# ✅ **199. Binary Tree Right Side View — Notes**

## **Goal**

Return the nodes visible when the tree is viewed from the right side → i.e., the **last node of every level**.

---

# 🥉 **Brute Force Approach (Level Order + Track Last Node)**

### **Idea**

* Do a standard BFS level order.
* For each level, take the **last node** in that level.

### **Steps**

1. Use a queue.
2. For each level:

   * Traverse all nodes at that level.
   * Record the **last node processed**.

### **Time Complexity**

* **O(N)** — visiting each node once.

### **Space Complexity**

* **O(W)** — max width of the tree in the queue.

### **Pros**

* Simple, intuitive.

### **Cons**

* Not the most elegant. BFS always carries the full width.

---

# 🥈 **Better Approach (DFS, Right-Priority Traversal)**

*This is the one you coded. Clean and optimal.*

### **Idea**

* Do a DFS.
* Always go **right → left**.
* Whenever you visit a new level for the first time, add the node.

  * This works because right subtree is visited first.

### **Steps**

1. Maintain:

   * `result` list
   * current `level`
2. If `level == result.size()`, store node value.
3. Recursively visit:

   * right child
   * left child

### **Time Complexity**

* **O(N)** — each node is visited once.

### **Space Complexity**

* **O(H)** — recursion stack (H = height of tree, worst-case = N).

### **Pros**

* Extremely elegant.
* Perfect for recursion-heavy interviews.
* Interviewers love this because it tests:

  * recursion
  * DFS variant
  * level-tracking logic

### **Cons**

* Requires understanding of level-first-access logic.

---

# 🥇 **Another Optimal Approach (Reverse BFS)**

*(Equivalent performance, but BFS flavor.)*

### **Idea**

* BFS but push **right child first**.
* First node you meet at each level is the rightmost.

### **Steps**

1. Queue + for loop per level.
2. First node of each level → add to result.

### **Time Complexity**

* **O(N)**

### **Space Complexity**

* **O(W)**

---

# 🎯 **Which One to Use in Interviews?**

### **Use the DFS Right-First Recursion**

Why?

* Short.
* Clean.
* Shows depth-first control.
* Interviewers often ask follow-ups:

  * “How do you get left view?”
    → change traversal order to left → right.
  * “What if I want maximum value per level?”
    → same pattern but maintain max.

Your solution is already the best:

```java
if (result.size() == level) result.add(root.val);
rightView(root.right);
rightView(root.left);
```

Perfect.

---

# 🔥 **Tips & Tricks (Interview Gold)**

### **1. Key Insight**

Right view = **first node reached at each level when traversing right-first**.

### **2. Trick for Remembering**

* **Right view = Right DFS first**
* **Left view = Left DFS first**

### **3. Common Follow-Up Questions**

Interviewers love these:

#### **a) Left Side View?**

* Change `right → left` to `left → right`.

#### **b) Why DFS works?**

Because the first node you hit at each level (while prioritizing right subtree) is the rightmost.

#### **c) What if tree is skewed?**

* Time = O(N)
* Space = O(N)
* Worst-case: recursion depth = N

#### **d) Can BFS be used?**

Yes, and explain the reverse-order trick (push right child first).

---

# 📌 Final Advice

Tahmeer, this is a standard “view problem.” Solve it once with the DFS pattern and reuse it:

* **Right view** → right-first DFS
* **Left view** → left-first DFS
* **Top view** → BFS + horizontal distance map
* **Bottom view** → BFS + override map
* **Vertical order** → BFS + col,row map
