# **543. Diameter of Binary Tree — NOTES**

## **What is Diameter?**

The **diameter** is the **longest path between any two nodes** in a binary tree.
Path length = number of edges, not nodes.

---

# **1. Brute Force Approach**

### **Idea**

For every node:

* Compute height of left subtree.
* Compute height of right subtree.
* Diameter at this node = leftHeight + rightHeight.
* Take the maximum across all nodes.

### **Code Skeleton**

```java
int height(TreeNode node) {
    if (node == null) return 0;
    return 1 + Math.max(height(node.left), height(node.right));
}

int diameter(TreeNode root) {
    if (root == null) return 0;
    int leftDia = diameter(root.left);
    int rightDia = diameter(root.right);

    int throughRoot = height(root.left) + height(root.right);

    return Math.max(throughRoot, Math.max(leftDia, rightDia));
}
```

### **Time Complexity**

* Height = O(n)
* For every node → height() called → O(n²) **TLE for large trees**

### **Space Complexity**

* O(h) recursion stack

❌ **Not acceptable for interviews. Only mention to show thought process.**

---

# **2. Better Approach**

Uses global `diameter[]`, but still recalculates heights inefficiently?
No — this already *is* the optimal bottom-up approach.
There’s no real “mid-level” here.

So skip this section.

---

# **3. Optimal Approach (Single DFS)**

### **Idea**

Use **postorder traversal** to compute:

* height of each node
* update diameter during the recursion

For each node:
`diameter = max(diameter, leftHeight + rightHeight)`

Return height to parent:
`1 + max(left, right)`

### **Your Code (Perfect Optimal Solution)**

```java
class Solution {
    public int diameterOfBinaryTree(TreeNode root) {
        int diameter[] = new int[1];
        findDiameter(root, diameter);
        return diameter[0];
    }

    int findDiameter(TreeNode root, int[] diameter){
        if(root == null) return 0;
        int left = findDiameter(root.left, diameter);
        int right = findDiameter(root.right, diameter);
        diameter[0] = Math.max(diameter[0], left + right);
        return 1 + Math.max(left, right);
    }
}
```

### **Time Complexity**

* Every node visited once → **O(n)**

### **Space Complexity**

* Recursion stack → **O(h)** (h = height of tree)
* Skewed tree worst case → O(n)

✔️ **This is the best possible solution.**

---

# **Interview Tips & Patterns**

### **1. This is a Height + Something problem**

Many tree problems follow:

> “Compute height. While computing, do extra work.”

Examples:

* Diameter
* Balanced tree check
* Maximum path sum
* Longest univalue path
* Maximum width using DFS height
* Lowest common ancestor (some variants)

Pattern:
**Postorder = do left → do right → compute answer**

---

### **2. Watch out: diameter = left + right (edges, not nodes)**

Interviewers may purposely confuse:

* Height (nodes)
* Diameter (edges)

Edge count is common.

---

### **3. Use int[] or global variable**

Java needs a mutatable container to update values inside recursion.

---

### **4. Avoid recomputing height**

If you write a function that calls height() inside diameter() → O(n²).
Interviewer warning sign.

---

### **5. Trick: Diameter does *not* necessarily pass through the root**

Your DFS handles this because diameter is updated at every node.

---

### **6. If interviewer pushes: return both height & diameter**

You can use a pair class:

```java
class Pair {
    int height;
    int diameter;
}
```

But your current method is simpler and widely accepted.

---

# **Short mental model for interviews**

Here’s how you explain your approach in 20 seconds:

> "I do a single DFS. At each node, I compute the height of the left and right subtrees.
> The diameter through that node is left height + right height.
> I keep a global maximum as I go.
> The height returned to parent is 1 + max(left, right).
> Time complexity is O(n), since I compute height and diameter in one pass."

This answer is clean and unbeatable.

---
