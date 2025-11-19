# ✅ **124. Binary Tree Maximum Path Sum — Full Notes**

## **Problem Summary**

You need the **maximum path sum** in a binary tree where a *path* can start and end at **any** node, but it must be continuous through parent-child links.
Path can go:

* left → root → right
* or any single branch
* or even just a single node

---

# 🚫 **Brute Force (Very Impractical, For Understanding Only)**

### **Idea**

Try every possible path.
For each node:

* consider it as root of a path
* explore all possible path combinations through DFS

### **Complexity**

* **Time:** O(N²) worst case
* **Space:** O(H) recursion stack

### Why it's bad?

* For each node, exploring all downward paths leads to a quadratic blow-up.

---

# 🔍 **Better Approach (Still Not Optimal)**

### **Idea**

Compute max downward path from every node using memoization.

But still:

* you don't handle "root being the top of max path" cleanly
* and you still revisit a lot of subtrees indirectly

### **Complexity**

* **O(N)** time
* but more work than required
* not as clean as optimal solution

Honestly—skip this for interviews.

---

# 🥇 **Optimal Solution (Standard DFS + Postorder)**

### **Core Idea**

At each node, compute:

### **1. Max Downward Path Sum**

Value returned from recursion:

```
max(root.val,
    root.val + left downward sum,
    root.val + right downward sum)
```

But we actually restrict:

* If left or right gives negative sum, **ignore it** (take 0).

So:

```
downward = root.val + max(0, max(left, right))
```

### **2. Max Path Sum Through This Node**

This is potentially the **global max**:

```
left + right + root.val
```

This might include:

* left → root → right
* or just root
* or root + left
* or root + right

We update global max accordingly.

### **Algorithm**

1. Do postorder DFS
2. Get left & right downward sums
3. Update global max using:

   ```
   left + right + root.val
   ```
4. Return downward contribution:

   ```
   root.val + max(left, right)
   ```

### **Time Complexity:** **O(N)**

Each node visited once.

### **Space Complexity:**

* **O(H)** recursion stack
* **H = height of tree (logN for balanced, N for skewed)**

---

# 🧠 Tips & Tricks for Interviews

### **1️⃣ This is a "Tree DP" Problem**

It’s a classic:

* recursion returns a value
* but also updates a global answer

Interviewers look for this pattern.

### **2️⃣ Always consider Negative Values**

* Path can start and end anywhere.
* A single node can be the maximum path.

Hence initialize:

```
globalMax = Integer.MIN_VALUE
```

### **3️⃣ Classic Use of:**

```
Math.max(0, child contribution)
```

This is the key insight:

* Negative paths will *reduce* sum → ignore completely.

### **4️⃣ Distinguish This:**

The return value = **max downward path**
The global sum = **max path through node**

They are NOT the same.

### **5️⃣ Think Postorder Traversal**

Children first → root later.

---

# 📌 Quick Template to Remember

```
maxDown(node):
    if null -> 0
    left = max(0, maxDown(node.left))
    right = max(0, maxDown(node.right))
    global = max(global, left + right + node.val)
    return node.val + max(left, right)
```

---
