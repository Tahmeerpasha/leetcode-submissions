# ✅ **101. Symmetric Tree — Notes**

## **Goal**

Check if a binary tree is a **mirror of itself** around its center.

---

# 🥉 **Brute Force Approach (Build Mirror + Compare)**

**Not recommended in interviews**, but good to know as a thought process.

### **Idea**

1. Create a **mirror** of the left subtree.
2. Compare it with the right subtree.

### **Steps**

* Recursively build mirror tree of left subtree.
* Compare structural + value equality with right subtree.

### **Time Complexity**

* Mirroring: **O(N)**
* Comparing: **O(N)**
* Overall: **O(N)**

### **Space Complexity**

* **O(N)** extra memory (for mirror tree)

### **Cons**

* Wasteful memory, unnecessary.
* Interviewers don’t expect or want this.

---

# 🥈 **Better Approach (Level Order Using Queue)**

An iterative approach using BFS.

### **Idea**

Use queue to compare nodes level-by-level by pushing mirrored pairs:

* `(left.left, right.right)`
* `(left.right, right.left)`

### **Steps**

1. Use a queue.
2. Push `root.left` and `root.right`.
3. While queue not empty:

   * Pop two nodes at a time.
   * If both null → continue.
   * If one null → not symmetric.
   * If values differ → not symmetric.
   * Push:

     * `left.left, right.right`
     * `left.right, right.left`

### **Time Complexity**

* **O(N)**

### **Space Complexity**

* **O(N)** queue

### **Pros**

* No recursion.
* Good if interviewer wants iterative version.

### **Cons**

* Slightly more verbose than recursion.

---

# 🥇 **Optimal Approach (DFS Mirror Check — Your Solution)**

This is the cleanest and preferred solution.

### **Idea**

A tree is symmetric if:

* left subtree is a mirror of right subtree.

Mirror check conditions:

* Both null → mirror
* One null → not mirror
* Values differ → not mirror
* Recursively:

  * left.left  ↔ right.right
  * left.right ↔ right.left

### **Code Logic**

Exactly what you wrote:

```java
boolean isSymmetricHelper(left, right):
    if one is null → return left == right
    if values differ → return false
    return mirror(left.left, right.right) 
           && mirror(left.right, right.left)
```

### **Time Complexity**

* **O(N)** — each node visited only once.

### **Space Complexity**

* **O(H)** recursion stack

  * Worst-case skewed tree → **O(N)**
  * Balanced tree → **O(log N)**

### **Pros**

* Short, readable.
* Matches definition of symmetry.
* Interview-friendly.

---

# 🎯 **How to Explain the Recursion in Interview**

Tell them:

> “To check symmetry, I compare the left subtree and right subtree in a mirror fashion: left’s left with right’s right, and left’s right with right’s left.”

This one line shows clear understanding.

---

# 🔥 **Common Follow-Up Questions & Answers**

### **1. Can you write an iterative version?**

Yes → BFS queue with pairwise popping.

### **2. Difference between Symmetric Tree and Same Tree?**

* **Same Tree**: structure + values identical.
* **Symmetric Tree**: mirror equivalence of left & right.

### **3. How to check if two trees are mirror of each other?**

Use the helper function directly:
`isMirror(tree1, tree2)` → same logic.

### **4. What if tree is huge and recursion depth becomes a problem?**

Use iterative BFS (queue) — avoids stack overflow.

---

# 🧠 **Tricks to Learn / Remember**

* **Symmetry = Mirror**
* Always compare:

  ```
  left.left ↔ right.right
  left.right ↔ right.left
  ```
* If interviewer asks “why this order?” →
  Because symmetry flips left subtree horizontally.

---
