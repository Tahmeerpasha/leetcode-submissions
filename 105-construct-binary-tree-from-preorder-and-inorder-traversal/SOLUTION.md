# **105. Construct Binary Tree from Preorder & Inorder Traversal**

## **1️⃣ Problem Summary**

Given:

* **Preorder** (Root → Left → Right)
* **Inorder** (Left → Root → Right)

Reconstruct the original binary tree.

---

# **2️⃣ Approaches**

---

## **A. Brute Force Approach**

### **Idea**

* Preorder gives you the **root**.
* Find that root in inorder by **linear search**.
* Everything left of root in inorder → **left subtree**
* Everything right → **right subtree**
* Recursively build subtrees.

### **Why it’s brute?**

You search linearly for every root inside inorder → **O(n)** search for each recursive call → worst case **O(n²)**.

### **Complexity**

* **Time:** O(n²)
* **Space:** O(n) for recursion + tree

### **Why it fails in interviews?**

* Too slow for skewed trees
* Shows you don't optimize

---

## **B. Better Approach**

### **Idea**

* Use a **HashMap** to store **inorder value → index**.
* This makes root lookup **O(1)**.

### **Complexity**

* **Time:** O(n)
* **Space:** O(n) map + O(n) recursion

### **This is what most candidates give.**

But still considered *optimal* for most constraints.

---

## **C. Optimal Approach (Your Code)**

Your solution is already optimal.

### **Key Insight**

You don’t create subarrays.
You simply pass **indexes** that specify:

* preorder range
* inorder range

This avoids extra space + repeated slicing.

---

# **3️⃣ Your Code (Optimal Solution)**

```java
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++)
            inMap.put(inorder[i], i);
        return buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, inMap);
    }

    public TreeNode buildTree(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd,
            Map<Integer, Integer> inMap) {
        if (preStart > preEnd || inStart > inEnd)
            return null;

        TreeNode root = new TreeNode(preorder[preStart]);
        int inRoot = inMap.get(root.val);
        int numsLeft = inRoot - inStart;

        root.left = buildTree(preorder, preStart + 1, preStart + numsLeft, inorder, inStart, inRoot - 1, inMap);
        root.right = buildTree(preorder, preStart + numsLeft + 1, preEnd, inorder, inRoot + 1, inEnd, inMap);
        return root;
    }
}
```

---

# **4️⃣ Deep Explanation — How Left & Right Subtrees Are Built Using Indexes**

This is the part interviewers care about.

### **Preorder Structure**

`[ root | left subtree | right subtree ]`

### **Inorder Structure**

`[ left subtree | root | right subtree ]`

---

### **Step-by-step logic**

Let:

* `preStart` = index of current root in preorder
* `rootVal = preorder[preStart]`
* `inRoot = index of rootVal in inorder`

### **Number of nodes in left subtree**

`numsLeft = inRoot - inStart`
because inorder looks like:

```
inorder: [inStart ... inRoot-1] [root] [inRoot+1 ... inEnd]
               left subtree            right subtree
```

So left subtree contains exactly `numsLeft` nodes.

---

### **Left Subtree Index Boundaries**

Preorder:

* First element after root = left subtree start
* Left subtree takes `numsLeft` items

So:

```
preStart_left = preStart + 1
preEnd_left = preStart + numsLeft
```

Inorder:

```
inStart_left = inStart
inEnd_left = inRoot - 1
```

---

### **Right Subtree Index Boundaries**

Preorder:

```
preStart_right = preStart + numsLeft + 1
preEnd_right = preEnd
```

Inorder:

```
inStart_right = inRoot + 1
inEnd_right = inEnd
```

---

### **Why this works?**

Because preorder gives you the **root order**, and inorder gives you the **shape** of the left/right subtrees.

By controlling indexes alone, you reconstruct the tree *without scanning* or *copying* arrays.

This is the optimal trick interviewers expect.

---

# **5️⃣ Time & Space Complexity**

### ✔ Time: **O(n)**

Each node is processed once
HashMap lookup: O(1)

### ✔ Space: **O(n)**

Due to:

* HashMap storing inorder positions
* Recursion stack (max O(n) for skewed tree)

---

# **6️⃣ Interview Tips & Tricks**

🔥 **Must mention these points — these get you brownie points:**

* Preorder gives root ordering.
* Inorder splits left and right subtree.
* Use HashMap to avoid O(n) lookups.
* Use index ranges instead of slicing arrays → memory efficient.
* Recursion naturally simulates tree construction order.

---

### **Common mistakes to avoid**

❌ Not handling base case when `preStart > preEnd`
❌ Recomputing inorder index with linear search
❌ Creating new arrays (wastes time & memory)
❌ Miscalculating preorder boundaries

---

### **How to explain this in an interview**

> “I use preorder to pick the root, use a hashmap on inorder to find subtree boundaries, and recursively build left/right subtrees by passing index ranges—no array copies, so the complexity stays linear.”

Clean, precise, gets you the pass.

---
