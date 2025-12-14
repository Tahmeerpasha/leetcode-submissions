# ✅ **173. Binary Search Tree Iterator — Full Notes (Brute → Optimal)**

## 🎯 **Goal**

Implement an iterator over a BST that returns elements in sorted (inorder) order, with:

* `next()` → returns next smallest element
* `hasNext()` → tells if more elements exist

---

# 🥉 **1. Brute Force Approach**

### **Idea**

Flatten the entire BST into a sorted array using inorder traversal.

### **Steps**

1. Do an inorder traversal → store all nodes in a list.
2. Keep an index pointer.
3. `next()` → return list[idx++]
4. `hasNext()` → idx < list.size()

### **Code Sketch**

```java
class BSTIterator {
    List<Integer> arr = new ArrayList<>();
    int idx = 0;

    public BSTIterator(TreeNode root) {
        inorder(root);
    }

    void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.left);
        arr.add(root.val);
        inorder(root.right);
    }

    public int next() {
        return arr.get(idx++);
    }

    public boolean hasNext() {
        return idx < arr.size();
    }
}
```

### **Complexity**

* **Time**

  * Preprocessing traversal: **O(N)**
  * `next()`: **O(1)**
* **Space**: **O(N)** (stores all nodes)

### **Why it's weak**

* Loads entire tree → not memory efficient
* Interviews expect better than this

---

# 🥈 **2. Better Approach (Lazy Inorder Using Recursion)**

### **Idea**

On `next()`, repeatedly recurse inorder and generate elements on the fly.

But recursion is messy here and doesn't maintain state cleanly across calls.

### **Why it’s not preferred**

* Requires global pointers
* Hard to maintain iterator semantics
* Interviewers usually skip this solution

---

# 🥇 **3. Optimal Solution — Using Stack (Controlled Inorder Traversal)**

### **Key Insight**

Inorder traversal = left → node → right.

You only need to maintain a stack containing the path to the next smallest element.

This is EXACTLY what your code does — good.

---

## ✅ **Your Optimal Stack-Based Code (Keep This in Notes)**

```java
class BSTIterator {
    Stack<TreeNode> st;

    public BSTIterator(TreeNode root) {
        this.st = new Stack<>();
        pushAll(root);
    }

    public int next() {
        if (st.isEmpty())
            return -1;
        TreeNode node = st.pop();
        pushAll(node.right);
        return node.val;
    }

    public boolean hasNext() {
        return !st.isEmpty();
    }

    void pushAll(TreeNode root) {
        while (root != null) {
            st.push(root);
            root = root.left;
        }
    }
}
```

---

# 🔍 **Optimal Approach Explanation**

* `pushAll(root)`:

  * Push root and all **left nodes** onto stack
  * The smallest element is always on top

* `next()`:

  * Pop the top → this is the next smallest element
  * Then push the right child and all its left children (because they're next in inorder)

* `hasNext()` → stack nonempty → valid next element exists.

---

# ⏱️ **Complexity Analysis (Optimal)**

### **Time**

* `next()` amortized **O(1)**

  * Each node is pushed & popped exactly once.

### **Space**

* **O(H)** worst-case (H = height of tree)

  * Skewed tree → O(N)
  * Balanced tree → O(log N)

### **Why Interviewers Love This**

* Doesn’t store full traversal
* Works lazily → generates values as needed
* Clean amortized constant time

---

# 💡 **Interview Tips & Tricks**

### ✔ Always explain “Why O(1)?”

Because each node is pushed once and popped once → total 2N stack ops → amortized O(1) per call.

### ✔ Don’t flatten the tree

They want you to **avoid O(N) space**.

### ✔ Know Morris Traversal?

You *can* mention it:

* Inorder without stack, O(1) space
* But it **modifies the tree** → unacceptable for an iterator
  So interviewers want the stack approach.

### ✔ Expect follow-ups:

1. **Implement a reverse iterator**
   → Same logic but use right subtree first.
2. **Merge two BST iterators**
3. **Kth smallest in BST** can be solved using similar push-left logic.

### ✔ Edge cases

* Empty tree → stack empty → hasNext() false
* Right subtree exists → push left chain of right child

---
