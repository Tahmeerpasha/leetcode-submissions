# 📘 **Flatten Binary Tree to Linked List — Interview Notes**

## 🎯 **Problem Goal**

Modify the binary tree **in-place** such that it becomes a **right-skewed linked list** following **preorder traversal**.

Example preorder: `root → left → right`
Flattened: all nodes on the `.right` chain, `.left = null` everywhere.

---

# 1️⃣ **Brute Force Approach (Using Extra Space)**

### **Idea**

* Do a **preorder traversal**.
* Store nodes in a list/array.
* Re-link nodes into a chain.

### **Code Sketch**

```java
List<TreeNode> list = new ArrayList<>();
preorder(root, list);

for (int i = 1; i < list.size(); i++) {
    TreeNode prev = list.get(i - 1);
    TreeNode curr = list.get(i);
    prev.left = null;
    prev.right = curr;
}
```

### **Complexity**

* **Time:** O(N)
* **Space:** O(N) — storing traversal

### **Why it's not preferred**

Uses extra space; violates the "in-place" spirit.

---

# 2️⃣ **Better Approach — Reverse Preorder Recursion (Optimal Time, Extra Stack Space)**

### **Intuition**

* Process nodes in **reverse preorder**: `right → left → root`.
* Maintain a global `prev` pointer that always points to the next flattened node.
* Rewire pointers as you unwind recursion.

### **Your Code (Recursive Version)**

```java
TreeNode prev = null;

public void flatten(TreeNode root) {
    if (root == null)
        return;

    flatten(root.right);
    flatten(root.left);

    root.right = prev;
    root.left = null;
    prev = root;
}
```

### **Complexity**

* **Time:** O(N)
* **Space:** O(H) → recursion stack, worst case O(N)

### **Why interviewers like this**

* Elegant
* Pure pointer manipulation
* Easy to explain

---

# 3️⃣ **Better Approach — Stack-Based (Iterative Preorder Simulation)**

### **Intuition**

* Simulate preorder traversal using a stack.
* Push right child first, then left child.
* Always connect `curr.right = next node`.

### **Your Code (Stack Version)**

```java
Stack<TreeNode> st = new Stack<>();
st.push(root);

while (!st.isEmpty()) {
    TreeNode curr = st.pop();

    if (curr.right != null)
        st.push(curr.right);
    if (curr.left != null)
        st.push(curr.left);

    if (!st.isEmpty())
        curr.right = st.peek();

    curr.left = null;
}
```

### **Complexity**

* **Time:** O(N)
* **Space:** O(N) — stack

### **Usage**

Good when interviewer wants **iteration**, not recursion.

---

# 4️⃣ **Optimal Approach — Morris Traversal (O(1) Extra Space)**

This is the **best in terms of space**. Pure pointer rewiring, no stack, no recursion.

### **Morris Intuition**

* For each node with a left subtree:

  1. Find the rightmost node of left subtree (`prev`).
  2. Attach `prev.right` to `curr.right`.
  3. Move entire left subtree to right side.
  4. Nullify `curr.left`.

### **Your Code (Morris Version)**

```java
TreeNode curr = root;
while (curr != null) {
    if (curr.left != null) {
        TreeNode prev = curr.left;
        while (prev.right != null)
            prev = prev.right;

        prev.right = curr.right;
        curr.right = curr.left;
        curr.left = null;
    }
    curr = curr.right;
}
```

### **Complexity**

* **Time:** O(N)
* **Space:** O(1) — this is the main benefit

### **Interviewer Insight**

* They rarely expect Morris.
* But if you mention it as an alternative, you look solid.

---

# 🧠 **Tips & Tricks for Interviews**

### ✅ 1. Always say this first:

> “Flatten means reorder in preorder fashion while modifying the tree in-place.”

This shows clarity.

### ✅ 2. Know the recursion version well

This is the most common solution asked.

### ✅ 3. Understand pointer rewiring

The key:
`root.right = prev`
`root.left = null`

### ✅ 4. Don’t start with Morris unless asked

Morris is impressive but not necessary for correctness.

### ✅ 5. If the interviewer asks for O(1) space → mention Morris

Even if you don’t code it perfectly, mentioning it scores points.

### ❌ Common Mistakes

* Forgetting to nullify `.left`
* Reversing preorder order
* Not updating `prev`
* Infinite loops when using Morris incorrectly

---

# 🔥 **Final Recommendation for You, Tahmeer**

If pressed on time, **master the recursive solution**.
If asked for alternatives, mention:

* stack-based iterative
* Morris (O(1) space)

That's more than enough for FAANG-level interviews.

---
