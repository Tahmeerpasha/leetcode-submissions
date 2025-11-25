# ✅ **Lowest Common Ancestor of a Binary Tree — Full Notes**

---

# **1️⃣ Problem Summary**

Given a binary tree (not necessarily BST) and two nodes `p` and `q`, return their **Lowest Common Ancestor (LCA)** — the *lowest* node in the tree that has both `p` and `q` as descendants.

---

# **2️⃣ Brute Force Approach**

### **Idea**

1. For each node, check if `p` and `q` lie in:

   * Left subtree
   * Right subtree
   * Opposite subtrees → that node is LCA
2. Use a helper to find if a node exists in a subtree.

### **Steps**

* For each node, run a DFS to check presence of p and q.
* Extremely inefficient but easy to think of first.

### **Time Complexity**

* For each node, you do a DFS → **O(N)**
* Repeated for all nodes → **O(N²)** worst case.

### **Space Complexity**

* Recursion stack: **O(N)**

### **When to use**

Never in actual interviews — but useful to explain "how I'd build intuition".

---

# **3️⃣ Better Solution – Parent Mapping + Visited Set**

### **Idea**

1. Traverse the tree and store each node’s parent in a hashmap.
2. Move upward from node `p` and mark all ancestors in a set.
3. Move upward from node `q` until you find a node already visited.

### **Steps**

* BFS or DFS to fill the `parent` map.
* Move up from p.
* Move up from q.

### **Time Complexity**

* Building parent map: **O(N)**
* Traversing ancestors: **O(H)**
  → Overall **O(N)**.

### **Space Complexity**

* Parent map + visited set → **O(N)**.

### **Pros**

* Easy, intuitive.
* Works well even for large trees.

### **Cons**

* Requires extra space.
* Not as elegant as the recursive solution.

---

# **4️⃣ Optimal Solution – Recursive DFS (What you already wrote)**

### **Core Intuition**

* If current root == p or q → return root.
* Explore left and right subtrees.
* If both left and right return non-null → this root is LCA.
* If only one subtree returns non-null → propagate it upward.
* If both null → return null.

### **Code**

You already nailed it:

```java
public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null || root == p || root == q)
        return root;

    TreeNode left = lowestCommonAncestor(root.left, p, q);
    TreeNode right = lowestCommonAncestor(root.right, p, q);

    if (left == null) return right;
    if (right == null) return left;
    return root;
}
```

### **Time Complexity**

* You visit each node once → **O(N)**

### **Space Complexity**

* Recursion stack: **O(H)** (H = height of tree)
* Worst case skewed tree → **O(N)**
* Balanced tree → **O(log N)**

### **Why interviewers love this**

* Clean logic
* Uses post-order traversal
* No extra memory
* Elegant recursion reasoning

---

# **5️⃣ Tips & Tricks for Interviews**

### ✔ Always clarify tree type:

* **Binary tree or BST?**
  BST allows a simpler solution; here it’s BT.

### ✔ Stay structured in explanation:

1. What does the function return?
2. What does returning null mean?
3. Why is recursion post-order?

### ✔ Key mental model:

> **A node is LCA when p and q lie in different subtrees OR when root is p/q itself.**

### ✔ Don’t forget edge cases:

* p == q
* p or q is the root
* Skewed tree
* One node missing (depends on problem constraints)

### ✔ When stuck, draw the classic diagram:

```
        3
       / \
      5   1
     / \ / \
    6  2 0  8
      / \
     7   4
```

They love candidates who reason visually.

---

# **6️⃣ How to explain in 60 seconds (interview pitch)**

> “I use a post-order DFS.
> If I hit p or q, I return the node upwards.
> Left and right recursive calls tell me where p and q were found.
> If both sides return non-null, current node is the LCA.
> Otherwise, return whichever side had a match.
> This gives O(N) time and O(H) space.”

This is the crispest explanation, and it shows strong understanding.

---
