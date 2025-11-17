# **94. Binary Tree Inorder Traversal — Notes & Interview Tricks**

## ✅ **Traversal Definition**

**Inorder traversal:**
**Left → Root → Right**

For BSTs, this returns the values in **sorted order** — that’s the key reason this traversal is important.

---

# ✅ **Recursive Approach (Simple & Clean)**

```java
void inOrder(TreeNode root, List<Integer> result) {
    if (root == null) return;
    inOrder(root.left, result);
    result.add(root.val);
    inOrder(root.right, result);
}
```

### **Why it works**

* Natural DFS.
* Always visit left subtree fully → node → right subtree.

---

# ✅ **Iterative Approach (Important for Interviews)**

### Code:

```java
Stack<TreeNode> stack = new Stack<>();
TreeNode node = root;

while (true) {
    if (node != null) {
        stack.push(node); 
        node = node.left;
    } else {
        if (stack.isEmpty()) break;
        node = stack.pop();
        result.add(node.val);
        node = node.right;
    }
}
```

### **Mental Model**

Think of it like:

* **Push all lefts**
* Pop → process (root)
* Move to right
* Repeat

This simulates the recursive call stack.

---

# ⭐ Interview-Level Notes

## **1. MUST know iterative inorder**

This is the most common traversal interviewers test, especially around:

* BST
* Validate BST
* K-th smallest element in BST
* Convert BST to sorted list
* Range sum problems

---

## **2. Identify inorder traversal**

Given this tree:

```
    1
   / \
  2   3
```

Inorder output:

```
2 1 3
```

**If the left subtree always appears first, it’s inorder.**

---

## **3. Time & Space Complexity**

* **Time:** O(n)
* **Space:** O(h) stack height (worst O(n) for skewed tree)

---

# 🔥 Common Pitfalls

### **Pitfall 1: Missing the “push-left-until-null” pattern**

If you don’t push all left nodes, you break the traversal order.

---

### **Pitfall 2: Confusing while(true) pattern**

Some people try to write complex conditions.
Cleanest approach is:

**while (node != null || !stack.isEmpty())**

But your version is fine too.

---

### **Pitfall 3: Thinking this prints sorted order for every tree**

Only **BSTs** give sorted output.
Normal binary trees will NOT give sorted output.

---

# 🎯 Tricks to Remember Traversals

* **Inorder (L R R):**
  Think **"Left first → Middle → Right"**
  Or **“Binary SEARCH = Inorder gives Sorted order”**

* **Preorder:** “Print first”

* **Postorder:** “Root last”

---

# 🔗 Related Problems You Should Practice

1. **Kth Smallest Element in BST**
2. **Validate Binary Search Tree**
3. **Binary Tree Preorder Traversal**
4. **Binary Tree Postorder Traversal**
5. **Morris Inorder Traversal** (advanced but great)

---
