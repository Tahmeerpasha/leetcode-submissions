# **98. Validate Binary Search Tree — Notes (Brute → Better → Optimal)**

## **🔍 Problem Summary**

You need to check whether a given binary tree is a valid BST.
A BST must satisfy:

* Left subtree < root < right subtree
* Both subtrees must also be valid BSTs

---

# **1️⃣ Brute Force Approach**

### **Idea**

For each node:

* Find the max value in its left subtree.
* Find the min value in its right subtree.
* Validate the BST property for the node.
* Recursively repeat for all nodes.

### **Why it's brute?**

Because for every node, you're scanning subtrees → repeated work → O(N²).

### **Time Complexity**

* Worst-case: **O(N²)**
* Skewed tree → max/min search becomes expensive
* Space: **O(N)** (recursion)

### **Code (Brute Force Idea — not recommended in interviews)**

```java
boolean isValidBST(TreeNode root) {
    if (root == null) return true;
    if (maxValue(root.left) >= root.val) return false;
    if (minValue(root.right) <= root.val) return false;

    return isValidBST(root.left) && isValidBST(root.right);
}

int maxValue(TreeNode node) {
    if (node == null) return Integer.MIN_VALUE;
    return Math.max(node.val, Math.max(maxValue(node.left), maxValue(node.right)));
}

int minValue(TreeNode node) {
    if (node == null) return Integer.MAX_VALUE;
    return Math.min(node.val, Math.min(minValue(node.left), minValue(node.right)));
}
```

---

# **2️⃣ Better Approach — Inorder Traversal**

### **Idea**

* Inorder traversal of a BST gives a **strictly increasing** sorted array.
* So just traverse and check if current value > previous value.

### **Time Complexity**

* **O(N)**
* Space: **O(N)** (recursive stack + array OR pointer)

### **Code (Better Version)**

```java
class Solution {
    Long prev = null;

    public boolean isValidBST(TreeNode root) {
        return inorder(root);
    }

    boolean inorder(TreeNode node) {
        if (node == null) return true;

        if (!inorder(node.left)) return false;

        if (prev != null && node.val <= prev) return false;
        prev = (long) node.val;

        return inorder(node.right);
    }
}
```

### **When is this useful?**

* When interviewer explicitly mentions "Use inorder traversal."

---

# **3️⃣ Optimal Approach — Range (Min–Max) Method**

### **Idea**

Use valid range constraints:

* For left child → max allowed = root.val
* For right child → min allowed = root.val
* If any node violates the range → invalid BST

### **This is the cleanest and most accepted solution.**

### **Time Complexity**

* **O(N)** — each node visited once
* **O(H)** recursion stack (worst O(N))

---

# **Optimal Code (Your Code) — Include This in Notes**

```java
class Solution {
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    boolean isValidBST(TreeNode root, long min, long max) {
        if (root == null)
            return true;
        if (root.val <= min || root.val >= max)
            return false;
        return isValidBST(root.left, min, root.val) &&
               isValidBST(root.right, root.val, max);
    }
}
```

---

# **💡 Tips & Tricks for Interviews**

### **1. Why use `long` for min & max?**

Because root values can be ±2³¹, so using `Integer.MIN_VALUE` and `Integer.MAX_VALUE` can cause edge-case failures.

### **2. Common Pitfall**

People check only:

* left.val < root.val
* right.val > root.val
  But this fails when a deeper node violates the BST rule.

### **3. When interviewer asks for most optimal?**

Always go for **range-based recursion** (your solution).

### **4. When interviewer pushes for "No extra space"?**

Use **Morris traversal inorder**, but **not required unless asked**.

### **5. Strict vs non-strict**

BST must be **strictly** increasing → no duplicates allowed.

### **6. Flow to explain in interviews**

* Start: "Brute is O(N²). But we can do O(N) using inorder."
* Finish: "Best is range-based DFS. Ensures global constraints per node."

---
