# **1008. Construct Binary Search Tree from Preorder Traversal — Notes**

---

# **🚫 Brute Force Approach**

### **Idea**

* Insert each element of the preorder array into a BST one by one using normal BST insert logic.

### **How it works**

* First element becomes the root.
* For each next value, walk down the BST and insert into the correct place.

### **Code Sketch**

```java
TreeNode insert(TreeNode root, int val) {
    if (root == null) return new TreeNode(val);
    if (val < root.val) root.left = insert(root.left, val);
    else root.right = insert(root.right, val);
    return root;
}

public TreeNode bstFromPreorder(int[] preorder) {
    TreeNode root = null;
    for (int val : preorder)
        root = insert(root, val);
    return root;
}
```

### **Complexity**

* **Worst case:** O(N²) — skewed tree (ascending preorder)
* **Best/average:** O(N log N)
* **Space:** O(H) recursion stack

### **Why it's bad**

* Preorder often produces *worst-case skewed data*, making this approach unreliable in interviews.

---

# **🟡 Better Approach**

## **Using Preorder + Sorted Preorder (Inorder) → Build Tree (Preorder + Inorder)**

### **Idea**

* For a BST, **sorted preorder = inorder traversal**.
* Now you have:

  * Preorder (given)
  * Inorder (obtained by sorting preorder)
* Use the classic *construct tree from preorder + inorder* technique.

### **Steps**

1. Copy preorder → sort → get inorder.
2. Maintain a global preorder index.
3. Build using recursion:

   * Pick current preorder element as root.
   * Split inorder array around root's value.
   * Recurse left and right.

### **Complexity**

* Sorting: O(N log N)
* Constructing tree:

  * If using map for inorder index lookup → O(N)
* **Overall:** O(N log N)
* **Space:** O(N)

### **Java Code (Better Solution)**

```java
class Solution {
    int preIdx = 0;

    public TreeNode bstFromPreorder(int[] preorder) {
        int[] inorder = preorder.clone();
        Arrays.sort(inorder);

        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++)
            inMap.put(inorder[i], i);

        return build(preorder, inorder, 0, inorder.length - 1, inMap);
    }

    TreeNode build(int[] preorder, int[] inorder, int inStart, int inEnd, Map<Integer, Integer> inMap) {
        if (inStart > inEnd) return null;

        int val = preorder[preIdx++];
        TreeNode root = new TreeNode(val);

        int inIndex = inMap.get(val);

        root.left = build(preorder, inorder, inStart, inIndex - 1, inMap);
        root.right = build(preorder, inorder, inIndex + 1, inEnd, inMap);

        return root;
    }
}
```

### **When to mention this in interviews**

* When interviewer wants **multi-method reasoning** or asks for alternatives.
* Good fallback when optimal solution doesn't come immediately.

---

# **🟢 Optimal Approach — O(N), No Sorting, No Map, No Extra Arrays**

### **Key Insight**

Preorder = Root → Left → Right
We process in preorder but maintain **upper bounds** so that:

* Left subtree values must be `< root.val`
* Right subtree values must be `< maxBound`

This avoids BST insert cost entirely.

### **Why this works**

* Preorder guarantees root always appears before its children.
* Bound checking ensures you never place a node in the wrong subtree.

### **Your Code (Optimal Solution)**

📌 Keep this as your final answer in interviews.

```java
class Solution {
    public TreeNode bstFromPreorder(int[] preorder) {
        return bstFromPreorder(preorder, new int[] { 0 }, Integer.MAX_VALUE);
    }

    TreeNode bstFromPreorder(int[] preorder, int[] idx, int maxBound) {
        if (idx[0] == preorder.length || preorder[idx[0]] > maxBound)
            return null;

        TreeNode node = new TreeNode(preorder[idx[0]++]);

        node.left = bstFromPreorder(preorder, idx, node.val);
        node.right = bstFromPreorder(preorder, idx, maxBound);

        return node;
    }
}
```

### **Complexity**

* **Time:** O(N) — each node visited once
* **Space:** O(H) recursion stack
  Worst case H = N (skewed), best = logN

### **Why this is the best**

* No sorting
* No extra arrays
* No hashmap
* Pure preorder-based construction
* Runs in strict O(N)

---

# **Interview Tips & Tricks**

### ✔ Always lead with the O(N) bound-based solution

Shows deep understanding of BST properties & recursion limits.

### ✔ Mention “preorder gives natural root-first access”

Interviewers want to see conceptual clarity, not just code.

### ✔ Explain the maxBound logic cleanly:

* Left subtree: allowed range = `( -∞ , root.val )`
* Right subtree: allowed range = `( root.val , maxBound )`

### ✔ Avoid brute-force unless specifically asked

But mention it quickly as a fallback category.

### ✔ If stuck, shift to the “better” preorder+inorder method

It’s a valid interview move and keeps the discussion going.

---
