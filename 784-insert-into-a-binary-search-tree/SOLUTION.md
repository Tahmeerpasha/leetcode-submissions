# **701. Insert into a Binary Search Tree — Notes**

## **Problem Summary**

Given a **BST** and a value, insert the value into the BST such that the tree remains a BST. Return the root.

A BST maintains:

* Left subtree < root
* Right subtree > root

---

# **1. Brute Force Approach (Not ideal, but good for reasoning)**

### **Idea**

* Perform an **inorder traversal**, which gives sorted order.
* Insert the value into the sorted list.
* Construct a completely new BST from the sorted array.

### **Why it sucks**

* Rebuilding a full BST is unnecessary when you only need to insert one node.

### **Time Complexity**

* Inorder → O(N)
* Build balanced BST → O(N)
* **Total: O(N)**

### **Space Complexity**

* O(N) for the traversal list + recursion.

### **Usefulness**

Purely conceptual. Never do this in real interviews unless asked.

---

# **2. Better Approach — Recursion (Clean & Expected)**

### **Idea**

Keep recursing:

* If `val > root.val` → go right
* If `val < root.val` → go left
* When you hit `null`, create a new node.

### **Recursive Code (common interview solution)**

```java
public TreeNode insertIntoBST(TreeNode root, int val) {
    if (root == null)
        return new TreeNode(val);

    if (val < root.val)
        root.left = insertIntoBST(root.left, val);
    else
        root.right = insertIntoBST(root.right, val);

    return root;
}
```

### **Complexity**

* **Time:** O(H) where H = height of tree

  * Best/Avg (balanced): O(log N)
  * Worst (skewed): O(N)
* **Space:** O(H) recursion stack

### **Why this is preferred**

Elegant, minimal, and shows clarity of thought.

---

# **3. Optimal Approach — Iterative (Your code; best for skewed trees)**

### **Idea**

Traverse the tree iteratively until you find a null spot, then attach the new node.

### **Your Code**

```java
class Solution {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        TreeNode node = new TreeNode(val);
        if (root == null)
            return node;

        TreeNode curr = root;
        while (true) {
            if (curr.val <= val) {
                if (curr.right != null)
                    curr = curr.right;
                else {
                    curr.right = node;
                    break;
                }
            } else {
                if (curr.left != null)
                    curr = curr.left;
                else {
                    curr.left = node;
                    break;
                }
            }
        }
        return root;
    }
}
```

### **Complexity**

* **Time:** O(H)
* **Space:** O(1) (iterative → no recursion)

### **Why this is optimal**

* Avoids recursion overhead → constant space.
* Best for environments with recursion limits.
* Many interviewers like iterative solutions for BST operations.

---

# **Tips & Tricks for Interviews**

### **1. State the BST invariant clearly**

Interviewers want to hear:

> “Since it's a BST, insertion simply follows the property:
> go left if smaller, go right if larger.”

This signals you know the fundamentals.

---

### **2. Watch out for duplicates**

Different companies treat duplicates differently:

* Insert on right (your approach)
* Insert on left
* Or reject duplicates

Always ask the interviewer:

> “Should I assume all values are unique?”

Your code handles `<=` by pushing duplicates to right — acceptable if unspecified.

---

### **3. Know time complexity tradeoffs**

* Balanced BSTs → O(log N)
* Worst-case skewed tree → O(N)

If they ask how to fix worst-case:
→ Mention **AVL**, **Red-Black Tree**, **Treaps**, **Splay Trees**.

That shows depth.

---

### **4. Avoid unnecessary rebuilding**

If you propose the brute-force rebuild method, immediately mention:

> “This is conceptually correct but very inefficient; we should instead insert directly.”

Shows maturity.

---

### **5. Iterative vs Recursive**

If asked which is better:

* **Iterative** → more optimal (O(1) space).
* **Recursive** → more readable.

Give that tradeoff confidently.

---

# **Final Summary Table**

| Approach                     | Time | Space | Notes                   |
| ---------------------------- | ---- | ----- | ----------------------- |
| Brute: Inorder + rebuild     | O(N) | O(N)  | Not practical           |
| Recursive insert             | O(H) | O(H)  | Standard clean solution |
| Iterative insert (your code) | O(H) | O(1)  | Best for interviews     |

---
