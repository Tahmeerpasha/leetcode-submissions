# ✅ **Count Complete Tree Nodes — Interview Notes**

![Image](https://deen3evddmddt.cloudfront.net/uploads/content-images/what-is-complete-binary-tree.webp?utm_source=chatgpt.com)

![Image](https://cdn.programiz.com/sites/tutorial2program/files/complete-binary-tree_0.png?utm_source=chatgpt.com)

![Image](https://media.geeksforgeeks.org/wp-content/uploads/Tree_level.png?utm_source=chatgpt.com)

![Image](https://favtutor.com/resources/images/uploads/Level_Order_Traversal_in_Python.png?utm_source=chatgpt.com)

---

# **1️⃣ Problem Summary**

You are given the root of a **complete binary tree** (all levels filled except possibly the last, and the last is filled left to right).
Return the **total number of nodes**.

---

# **2️⃣ Brute Force**

### **Approach**

Just **traverse all nodes** (DFS or BFS) and count.

### **Code**

```java
int countNodes(TreeNode root) {
    if (root == null) return 0;
    return 1 + countNodes(root.left) + countNodes(root.right);
}
```

### **Time Complexity**

* **O(n)** → visits every node

### **Space Complexity**

* **O(h)** recursion stack (worst O(n), best O(log n))

### When interviewer expects this:

* If they say "How will you start?" → give brute.
* You *must* follow with: “But since the tree is *complete*, we can do better.”

---

# **3️⃣ Better (Binary Tree Property Used Lightly)**

Use **level order** to count nodes.

### **Approach**

BFS until queue empty → count++.

### **Time Complexity**

* **O(n)**

### **Space Complexity**

* **O(n)** queue

### Why not optimal:

Still ignores the special property of *complete* tree.

---

# **4️⃣ ⚡ Optimal Solution (Most Important)**

### **Key Insight**

For a **complete tree**:

* If **left subtree height == right subtree height**, then the tree is **perfect**, i.e.,
  [
  {number of nodes} = 2^{h} - 1
  ]

![Image](https://media.geeksforgeeks.org/wp-content/uploads/20220630154756/img2.jpg?utm_source=chatgpt.com)

![Image](https://miro.medium.com/0%2ArITJuVzwJRMA0Zjz.png?utm_source=chatgpt.com)

![Image](https://dz2cdn1.dzone.com/storage/temp/13820697-1596720679704.png?utm_source=chatgpt.com)

![Image](https://prepbytes-misc-images.s3.ap-south-1.amazonaws.com/assets/1655814973682-Image-01%20%281%29.png?utm_source=chatgpt.com)

### **Steps**

1. Compute **left height** by going left-left-left…
2. Compute **right height** by going right-right-right…
3. If equal → it's a perfect tree → compute nodes directly
4. Else → recursively count child subtrees


# **Optimal Code**

```java
class Solution {
    public int countNodes(TreeNode root) {
        if (root == null) return 0;

        int lh = leftHeight(root);
        int rh = rightHeight(root);

        if (lh == rh) {
            return (1 << (lh + 1)) - 1;
        }

        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    int leftHeight(TreeNode node) {
        int h = 0;
        while (node.left != null) {
            h++;
            node = node.left;
        }
        return h;
    }

    int rightHeight(TreeNode node) {
        int h = 0;
        while (node.right != null) {
            h++;
            node = node.right;
        }
        return h;
    }
}
```

---

# ⚙️ Time & Space Complexity (Optimal)

### **Time**

* Each level calculates left height and right height → O(log n)
* Maximum recursive depth → O(log n)

👉 **Total: O(log² n)** (accepted & optimal)

### **Space**

* Recursion depth: **O(log n)**

---

# 🔥 Interview Tips (These matter more than code)

### 1. Mention this upfront:

> “Since the tree is complete, I can leverage height comparisons to avoid traversing all nodes.”

Shows awareness of properties.

---

### 2. When heights match:

Say clearly:

> “If left height == right height, the subtree is perfect. Nodes = 2^(h) - 1.”

Interviewer wants this recognition.

---

### 3. Avoid common mistake:

Using `lh` instead of `lh + 1` for the power.

---

### 4. Common follow-up questions

Be ready for:

* Why height computed via only left edges?
* Why comparing leftmost vs rightmost paths?
* Worst-case input example?
  → Very skewed last level where recursion continues.

---

# 🧠 Final Summary Table

| Approach                        | Time          | Space        | Notes                                   |
| ------------------------------- | ------------- | ------------ | --------------------------------------- |
| Brute DFS                       | O(n)          | O(h)         | Simple but ignores “complete” property  |
| BFS                             | O(n)          | O(n)         | Worse space, no advantage               |
| **Optimal (height comparison)** | **O(log² n)** | **O(log n)** | Best solution; use perfect tree formula |

---
