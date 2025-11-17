# **144. Binary Tree Preorder Traversal — Notes & Interview Tricks**

## ✅ **Traversal Definition**

**Preorder traversal:**
**Root → Left → Right**

This order matters — many interview questions rely on understanding the traversal sequence.

---

# ✅ **Recursive Approach (Straightforward)**

```java
void preOrder(TreeNode root, List<Integer> result) {
    if (root == null) return;
    result.add(root.val);
    preOrder(root.left, result);
    preOrder(root.right, result);
}
```

### **Why it works**

* Natural DFS pattern.
* Easy to write.
* Stack frames implicitly handle traversal.

### **When to use**

* When interviewer doesn’t restrict recursion.
* When code clarity matters.

---

# ✅ **Iterative Approach (Important for Interviews)**

### Code:

```java
Stack<TreeNode> stack = new Stack<>();
stack.push(root);

while(!stack.isEmpty()) {
    TreeNode node = stack.pop();
    result.add(node.val);
    if (node.right != null) stack.push(node.right);
    if (node.left != null) stack.push(node.left);
}
```

### **Why push right first?**

Stack = LIFO

To process **left before right**, you must push **right**, then **left**.

---

# ⭐ Interview-Level Notes

## **1. You MUST know iterative preorder**

This is the **most expected** iterative traversal. Postorder is optional (1-stack version is rarely required). Inorder and Preorder iterative are must-knows.

---

## **2. Recognizing preorder**

If the traversal begins with the root and prints nodes **immediately when visited**, it is preorder.

Example input:

```
    1
   / \
  2   3
```

Output:

```
1 2 3
```

---

## **3. Time & Space Complexity**

* **Time:** O(n) — visiting each node once.
* **Space:**

  * Worst: O(n) stack (skewed tree)
  * Best: O(h) (balanced tree heights)

---

# 🔥 **Common Pitfalls**

### **Pitfall 1: Missing the push order**

Many candidates push left first, breaking the order.
Correct:
**push right → push left**

---

### **Pitfall 2: Modifying the root reference**

Doing `root = stack.pop()` is fine here, but in other problems this might cause confusion. Better to use a temp variable, e.g.:

```java
TreeNode node = stack.pop();
```

---

### **Pitfall 3: Using LinkedList as a stack**

Avoid `LinkedList.push/pop` — slower and less clean.

---

# 🎯 Tricks to Remember Traversals

* **Preorder (Root–Left–Right)**:
  Think **"DLR → Direct Left Right"** or **"Print before everything"**

* **Inorder (Left–Root–Right)**:
  Think **"LDR → Left first"**

* **Postorder (Left–Right–Root)**:
  Think **"LRD → Last Root Done"**

---

# 🧩 Related Problems (Good to practice)

1. **Binary Tree Inorder Traversal**
2. **Binary Tree Postorder Traversal**
3. **Binary Tree Level Order Traversal (BFS)**
4. **Morris Traversal** (advanced — good edge case skill)

---

# ✔ Final Tip (Important for Interviews)

If asked, **always state the recursive version first** (simple and clean), then implement iterative when requested. Shows clarity + depth.

---
