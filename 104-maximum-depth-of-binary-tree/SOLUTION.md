# **104. Maximum Depth of Binary Tree — Notes**

## **Problem**

Find the maximum depth (height) of a binary tree.
Depth = number of nodes from root to the deepest leaf.

---

# **1. Brute Force Approach**

### **Idea**

Traverse every node and compute depth manually by exploring all possible root-to-leaf paths.

### **Method**

* Do a DFS from the root.
* Maintain a global/aux depth counter.
* Update maxDepth whenever you hit a leaf.

### **Time Complexity**

* **O(n)** — You still visit all nodes.

### **Space Complexity**

* **O(h)** recursion stack (worst O(n) for skewed tree).

### **Why brute?**

* Uses external variable.
* Not clean or elegant.

---

# **2. Optimal (Standard Recursive DFS)**

This is the best solution. Simple, direct, clean.

### **Code**

```java
public int maxDepth(TreeNode root) {
    if (root == null) return 0;
    int left = maxDepth(root.left);
    int right = maxDepth(root.right);
    return 1 + Math.max(left, right);
}
```

### **Time Complexity**

* **O(n)** — each node visited once.

### **Space Complexity**

* **O(h)** — height of tree (O(n) worst case, O(log n) best case).

### **Why optimal?**

* No extra variables.
* Pure functional recursion.
* Interviewers expect this.

---

# **3. Iterative Approach (Using BFS)**

This is the better alternative when recursion depth might overflow.

### **Idea**

Use **level-order traversal** (queue).
Each level increases depth by 1.

### **Code**

```java
public int maxDepth(TreeNode root) {
    if (root == null) return 0;

    Queue<TreeNode> q = new LinkedList<>();
    q.offer(root);
    int depth = 0;

    while (!q.isEmpty()) {
        int size = q.size();
        while (size-- > 0) {
            TreeNode node = q.poll();
            if (node.left != null) q.offer(node.left);
            if (node.right != null) q.offer(node.right);
        }
        depth++;
    }
    return depth;
}
```

### **Time Complexity**

* **O(n)**

### **Space Complexity**

* **O(n)** for BFS queue (worst case, last level).

### **When to use iterative?**

* When interviewer hints recursion stack overflow.
* When tree is extremely skewed.

---

# **4. Iterative DFS (Stack)**

Rarely asked, but good to know.

### **Idea**

Push (node, depth) into stack.

### **Code**

```java
public int maxDepth(TreeNode root) {
    if (root == null) return 0;

    Stack<Pair<TreeNode, Integer>> stack = new Stack<>();
    stack.push(new Pair<>(root, 1));
    int max = 0;

    while (!stack.isEmpty()) {
        Pair<TreeNode, Integer> p = stack.pop();
        TreeNode node = p.getKey();
        int depth = p.getValue();
        max = Math.max(max, depth);

        if (node.left != null) stack.push(new Pair<>(node.left, depth + 1));
        if (node.right != null) stack.push(new Pair<>(node.right, depth + 1));
    }
    return max;
}
```

---

# **Tips & Tricks for Interviews (Important)**

### **1. Expected answer is recursion**

95% of interviewers expect the clean recursive DFS.

### **2. This is a classic tree pattern**

Same logic applies to:

* Min depth of binary tree
* Diameter of binary tree (just more info)
* Balanced binary tree (check heights)
* Same tree / Symmetric tree
  All use the same recursive pattern → solve left, solve right, combine.

### **3. Understand “height” vs “depth”**

* Depth = number of nodes from root → leaf
* Height = number of edges root → leaf
  Leetcode uses depth = nodes count.

### **4. Worst-case recursion stack**

For skewed tree, stack = O(n).
If asked: “How to avoid recursion stack overflow?” → BFS solution.

### **5. Don’t overcomplicate**

This is a warm-up problem. They’re checking if:

* You know recursion basics.
* You know how to combine subproblems.

---
