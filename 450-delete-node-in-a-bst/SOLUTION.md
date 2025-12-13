# 🔥 **450. Delete Node in a BST — Full Notes**

## ✅ **Problem Summary**

Given the root of a BST and a key, delete the node with that key while keeping the BST property intact.

---

# 🧠 **1) Brute Force Approach**

### **Idea**

Forget the BST structure.

1. Do an inorder traversal → store values.
2. Remove the value equal to the key from the list.
3. Rebuild a **balanced BST** from the updated list.

### **Complexity**

* **TC:**

  * Inorder traversal: O(N)
  * Rebuilding balanced BST: O(N)
  * Total: **O(N)**
* **SC:** O(N) (for array + recursion)

### **Why it's bad**

* Completely wastes the BST structure
* Expensive memory usage
* Not acceptable in interviews unless asked explicitly

---

# 🧠 **2) Better Approach (Recursive BST Delete — Standard)**

### **Idea**

Use BST rules:

1. If key < root → go left
2. If key > root → go right
3. If found → handle 3 cases:

   * Node with **0 children** → return null
   * Node with **1 child** → return that child
   * Node with **2 children** →

     * Replace with inorder successor (min in right subtree)
     * Delete successor in right subtree

### **Complexity**

* **TC:** O(H) = O(log N) for balanced, O(N) worst-case skewed
* **SC:** O(H) recursion

### **Why this is good**

* Uses BST property
* Easy to implement recursively
* Most common interviewer expectation

---

# 🧠 **3) Optimal Approach (Your Provided Code — Iterative Parent-Tracking + Left-Max Merge)**

This avoids recursion and uses a clever strategy for deleting a node with two children:

### **Key Idea**

Instead of using inorder successor, you:

* Find the **rightmost node in the left subtree** (max in left subtree)
* Attach the right subtree to this node
* Return the left subtree as the new root

This is perfectly valid and avoids modifying two subtrees separately.

---

# ⭐ Your Code (Keep this exactly as-is in your notes)

```java
class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null)
            return null;
        if (root.val == key)
            return helper(root);
        TreeNode rootRef = root;
        while (root != null) {
            if (root.val > key) {
                if (root.left != null && root.left.val == key) {
                    root.left = helper(root.left);
                    break;
                } else
                    root = root.left;
            } else {
                if (root.right != null && root.right.val == key) {
                    root.right = helper(root.right);
                    break;
                } else
                    root = root.right;
            }
        }
        return rootRef;
    }

    TreeNode helper(TreeNode root) {
        if (root.left == null)
            return root.right;
        if (root.right == null)
            return root.left;
        TreeNode rightChild = root.right;
        TreeNode lastRight = findLastRight(root.left);
        lastRight.right = rightChild;
        return root.left;
    }

    TreeNode findLastRight(TreeNode root) {
        if (root.right == null)
            return root;
        return findLastRight(root.right);
    }
}
```

---

# 📌 **Complexity of Your Optimal Iterative Approach**

### **Time Complexity**

* Traversal to find key: O(H)
* Finding last right: O(H)
* **Total:** O(H)
  H = tree height → O(log N) average, O(N) worst-case.

### **Space Complexity**

* O(1) extra space (iterative)
* O(H) worst-case for recursion in `findLastRight`

Still optimal.

---

# 🎯 **Interview Tips & Tricks**

### ✔ Understand the 3 node deletion cases deeply

Interviewers test reasoning, not code memorization.

### ✔ Mention both standard + your alternative approach

Shows depth of knowledge.

### ✔ For the 2-children case, explain both strategies:

* Using inorder successor
* Using left-max (what you implemented)

Both are correct.

### ✔ Discuss BST height implications

Balanced → log N
Skewed → N
Most candidates forget this.

### ✔ Mention iterative vs recursive tradeoffs

Iterative avoids stack overflows; recursion is simpler.

---
