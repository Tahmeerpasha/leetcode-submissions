# **700. Search in a Binary Search Tree — Notes**

## **Problem Summary**

Given the root of a BST and a value `val`, return the subtree whose root has value = `val`.
If the value is not found, return `null`.

---

# **1. Brute Force Approach (Not recommended in interviews)**

### **Idea**

Ignore the BST property and traverse the entire tree like a normal binary tree search.

### **Algorithm**

* DFS or BFS through all nodes.
* If a node's value matches `val`, return it.
* Else check all others.

### **Code (DFS example)**

```java
public TreeNode searchBST(TreeNode root, int val) {
    if (root == null) return null;
    if (root.val == val) return root;

    TreeNode left = searchBST(root.left, val);
    if (left != null) return left;

    return searchBST(root.right, val);
}
```

### **Complexity**

* **TC:** O(N)
* **SC:** O(H) recursion stack → O(N) worst case

### **When to mention**

Only if interviewer explicitly says *“don’t use BST properties”* (rare).

---

# **2. Better Approach (Use BST property, Recursion)**

### **Idea**

Because it's a BST:

* If `val < root.val` → go left
* If `val > root.val` → go right

### **Code**

*(You already wrote this; include in notes as asked)*

```java
// TC - O(Log N) && SC - O(N) (recursion stack space)
if (root == null)
    return null;
if (root.val == val)
    return root;
if (val < root.val)
    return searchBST(root.left, val);
else
    return searchBST(root.right, val);
```

### **Complexity**

* **TC:** O(log N) average, O(N) worst case (skewed tree)
* **SC:** O(H) recursion → same ranges as above

### **Pros**

* Clean
* Easy to explain
* Matches recursive thinking of BST search

---

# **3. Optimal Approach (Iterative BST Search)**

### **Idea**

Same logic as recursion, but avoid recursion stack → constant space.

### **Code (Your optimal solution — keep unchanged)**

```java
// TC - O(Log N) && SC - O(1)
while (root != null && root.val != val)
    root = val < root.val ? root.left : root.right;
return root;
```

### **Complexity**

* **TC:** O(log N) average
* **SC:** O(1)

### **Pros**

* Most efficient version
* Leetcode-style optimal
* Good impression in interviews

---

# **Interview Tips**

### ✔ Always emphasize:

* You exploit BST property → drastically reduces search space.
* Iterative avoids recursion overhead → optimal space.

### ✔ Be ready to discuss worst-case:

> *“If the BST is skewed (like a linked list), search becomes O(N). Balanced BST gives O(log N).”*

### ✔ If interviewer pushes further:

* Mention using **AVL** or **Red-Black Trees** keeps height = O(log N).
* That ensures search stays O(log N) worst-case.

### ✔ Edge cases to mention:

* Empty tree
* Value not present
* Searching for root value
* Tree with only one node

### ✔ Common follow-up questions:

1. How would you search in a *not* BST?
2. How do you insert a node in a BST?
3. What if the tree is extremely unbalanced — how to fix? (→ self-balanced trees)

---
