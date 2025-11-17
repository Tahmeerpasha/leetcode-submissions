# **110. Balanced Binary Tree — Notes**

## **Problem**

Check if a binary tree is height-balanced:
For every node → |height(left) − height(right)| ≤ 1.

---

# **1. Brute Force Approach**

### **Idea**

At every node:

* Compute height of left subtree.
* Compute height of right subtree.
* Check the difference.
* Recursively check for left + right.

### **Issue**

Height itself is O(n) and you compute it for every node → leads to repeated work.

### **Time Complexity**

* Worst case: **O(n²)** (skewed tree)
* Space: **O(h)** (recursion stack)

### **Code Sketch**

```java
public boolean isBalanced(TreeNode root) {
    if (root == null) return true;

    int left = height(root.left);
    int right = height(root.right);

    if (Math.abs(left - right) > 1) return false;

    return isBalanced(root.left) && isBalanced(root.right);
}

public int height(TreeNode root) {
    if (root == null) return 0;
    return 1 + Math.max(height(root.left), height(root.right));
}
```

---

# **2. Better / Optimal (Postorder + Early Termination)**

### **Idea**

Do height and balance check in **one traversal**.

Recursive function returns:

* Height if subtree is balanced
* `-1` if subtree is unbalanced → propagate failure upward immediately

### **Why it’s optimal**

* You compute height once per node.
* You stop processing as soon as imbalance is found.

### **Time Complexity**

* **O(n)** — single DFS
* Space: **O(h)** recursion stack

### **Your solution is the optimal one.**

### **Final Optimal Code**

```java
public boolean isBalanced(TreeNode root) {
    return maxDepth(root) != -1;
}

public int maxDepth(TreeNode root) {
    if (root == null) return 0;

    int left = maxDepth(root.left);
    int right = maxDepth(root.right);

    if (left == -1 || right == -1) return -1;
    if (Math.abs(left - right) > 1) return -1;

    return 1 + Math.max(left, right);
}
```

---

# **3. Tips & Tricks for Interviews**

### **Key Patterns**

* **Use -1 as a failure flag** to short-circuit recursion.
* **Bottom-up DFS** is more efficient than top-down for repeated computations.
* Balanced tree problems almost always pair with:

  * **Height calculation**
  * **Diameter of binary tree**
  * **Binary tree max depth**
  * **Minimum depth**
  * **Check symmetric tree**

### **Common Pitfalls**

* Recalculating height multiple times → O(n²).
* Forgetting early termination.
* Confusing “binary search tree balanced” vs “binary tree balanced”.

### **How To Explain in an Interview (30-second pitch)**

> “I use a bottom-up DFS that returns the height of a subtree if it’s balanced and -1 otherwise. If any subtree returns -1, I propagate it upward and stop. This gives O(n) time because each node is processed once.”

This is short, sharp, and shows you understand recursion efficiency.

---
