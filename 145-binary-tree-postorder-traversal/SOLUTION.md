# **145. Binary Tree Postorder Traversal — Notes & Tricks**

## ✅ **Traversal Definition**

**Postorder:**
**Left → Right → Root**

This is the most annoying traversal to do iteratively because you must print AFTER visiting both children.

---

# ✅ **Recursive Approach (Easiest)**

```java
void postOrder(TreeNode root, List<Integer> result) {
    if (root == null) return;
    postOrder(root.left, result);
    postOrder(root.right, result);
    result.add(root.val); // print last
}
```

---

# ✅ **Iterative Approach — 2 Stack Method (Simple & Standard)**

### Code:

```java
Stack<TreeNode> st1 = new Stack<>();
Stack<TreeNode> st2 = new Stack<>();

st1.push(root);

while (!st1.isEmpty()) {
    TreeNode node = st1.pop();
    st2.push(node);

    if (node.left != null) st1.push(node.left);
    if (node.right != null) st1.push(node.right);
}

while (!st2.isEmpty()) {
    result.add(st2.pop().val);
}
```

### How it works

* `st1` does a modified preorder: Root → Right → Left
* `st2` reverses it → Left → Right → Root (postorder)

### Why this is easiest

* Order is guaranteed.
* Zero edge-case issues.

---

# ✅ **Iterative Approach — 1 Stack Method (Interview Favorite)**

### **Goal:** Simulate recursion without using `st2`.

### Code (clean version):

```java
public List<Integer> postorderTraversal(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    Stack<TreeNode> st = new Stack<>();
    TreeNode curr = root;
    TreeNode lastVisited = null;

    while (curr != null || !st.isEmpty()) {

        // Go to leftmost node
        while (curr != null) {
            st.push(curr);
            curr = curr.left;
        }

        TreeNode top = st.peek();

        // If right child exists & not processed yet → go right
        if (top.right != null && lastVisited != top.right) {
            curr = top.right;
        } else {
            result.add(top.val);
            lastVisited = top;
            st.pop();
        }
    }

    return result;
}
```

---

# ⭐ How 1-Stack Logic Works

We simulate the recursion:

1. **Go left until null**
2. Look at stack top:

   * If it has an unvisited right child → go right
   * Else → both children done, so **print node** and pop

`lastVisited` ensures you don’t loop back into right child twice.

---

# ⭐ Interview-Level Notes

### **Should you learn 1-stack postorder?**

If you’re targeting product companies (which you are), **YES**.
It’s asked because it's the hardest of the 3 DFS traversals.

But if you’re in a hurry:

* Preorder iterative → must
* Inorder iterative → must
* Postorder 2-stack → acceptable but less optimal
* Postorder 1-stack → bonus points

---

# 🔥 Common Pitfalls

### ❌ Mistake 1: Pushing right before left in 2-stack incorrectly

Correct:

* Push `left`
* Push `right`
  Because `st2` reverses order.

---

### ❌ Mistake 2: Infinite loop in 1-stack due to missing `lastVisited` check

Always track last processed node.

---

### ❌ Mistake 3: Forgetting that postorder must process both children BEFORE root.

---

# 🎯 Quick Traversal Memory Trick

| Traversal | Pattern         | Stack Push Order                                   |
| --------- | --------------- | -------------------------------------------------- |
| Preorder  | Root–Left–Right | Push Right → Left                                  |
| Inorder   | Left–Root–Right | Push left until null                               |
| Postorder | Left–Right–Root | 2-stack: Left then Right; 1-stack: use lastVisited |

---

# 🔗 Related interview-heavy problems

* Binary Tree Preorder Traversal
* Binary Tree Inorder Traversal
* Postorder using 1 stack
* Flatten Binary Tree to Linked List
* Construct Tree from Preorder/Inorder
* Construct Tree from Postorder/Inorder

---
