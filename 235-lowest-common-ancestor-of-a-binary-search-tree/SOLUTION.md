# **📌 235. Lowest Common Ancestor of a Binary Search Tree — Notes (Brute → Optimal)**

## **Problem Summary**

Given a **BST** and two nodes `p` and `q`, find their **Lowest Common Ancestor (LCA)** — the lowest node that has both `p` and `q` in its subtree.

Because it's a **BST**, you get ordering:

* Left subtree < root
* Right subtree > root

That makes the problem much easier.

---

# **1️⃣ Brute Force (General Binary Tree LCA)**

> Works for any binary tree, but overkill for a BST.

### **Approach**

1. Find path from root → `p`.
2. Find path from root → `q`.
3. Compare both paths and find the last common node.

### **Time Complexity**

* Path finding: O(N)
* Comparison: O(H)
  → **O(N)** overall

### **Space Complexity**

* O(H) for storing paths
  → **O(N)** worst-case for skewed tree.

### **Why It's Weak**

* It completely ignores BST properties.
* Extra space + slower.

---

# **2️⃣ Better Approach (Iterative BST Property Approach)**

> Leverages BST direction but uses iteration instead of recursion.

### **Approach**

Use the BST rule:

* If both values < root → go left
* If both values > root → go right
* Else → root is LCA

### **Code**

```java
public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    while (root != null) {
        if (p.val < root.val && q.val < root.val) {
            root = root.left;
        } else if (p.val > root.val && q.val > root.val) {
            root = root.right;
        } else {
            return root;
        }
    }
    return null;
}
```

### **Time Complexity**

O(H) → Height of tree (logN for balanced BST, N for skewed)

### **Space Complexity**

O(1) → iterative

### **Why It’s Good**

* Simple
* No recursion stack
* Fast
* Uses BST properties fully

---

# **3️⃣ Optimal Approach (Recursive — Your Code)**

> Clean, elegant, and optimal for BST.

### **Your Code (keep as is):**

```java
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null)
            return root;
        if (p.val < root.val && q.val < root.val)
            return lowestCommonAncestor(root.left, p, q);
        else if (p.val > root.val && q.val > root.val)
            return lowestCommonAncestor(root.right, p, q);
        return root;
    }
}
```

### **Time Complexity**

O(H) → worst case O(N), best O(logN)

### **Space Complexity**

O(H) due to recursion stack

### **Why It's Optimal**

* Directly uses BST ordering
* Stops early when split point is found
* Very easy to write under pressure

---

# **🔥 Interview Tips & Tricks**

### **1. Core Idea to Highlight**

Tell the interviewer:

> “In a BST, the LCA is the *first node where one value goes left and the other goes right*.”

This is the exact insight they want to hear.

---

### **2. Quick Decision Rules**

* Both values < root → move left
* Both values > root → move right
* Else → LCA found

Interviewer wants crisp reasoning, not wandering.

---

### **3. Be ready to explain edge cases**

* If `p == root` or `q == root`, root is LCA.
  Your code naturally handles this.

---

### **4. Common trick question**

**“What if it's not a BST?”**
→ You must switch to general binary tree LCA (post-order + returning match info).
Good to mention you know the difference.

---

### **5. Iterative vs Recursive**

If interviewer asks:

> "Which one is better?"

Say:

> “Iterative gives O(1) space, so it's slightly more optimal. Recursive is more readable.”

This shows maturity.

---
