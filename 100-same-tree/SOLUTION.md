# ✅ **100. Same Tree — Full Notes**

## 🔥 **Problem Summary**

Given two binary trees `p` and `q`, determine if they are **exactly identical**:

* Same structure
* Same node values
* Same arrangement of children

---

# 🧩 **Brute Force Approach**

### **Idea**

There isn't a real "brute force" that’s meaningfully worse than optimal.
The naive idea is:

1. Traverse both trees separately (e.g., preorder).
2. Serialize their structure + values into strings.
3. Compare the two strings.

### **Example**

Serialize like:
`1,2,null,null,3,null,null`

### **Code Sketch**

```
serialize(p) == serialize(q)
```

### ❌ Problems

* String building is extra overhead.
* Edge case–heavy.
* Memory-heavy.

### **Complexity**

* Time: **O(n)**
* Space: **O(n)** for serialized strings.

**Interview note:** This approach is usually discouraged. Only mention it if the interviewer asks for alternatives.

---

# ⭐ **Better / Optimal Solution (Recursive)**

(Your solution is already optimal.)

### **Idea**

Two trees are the same if:

* Both nodes are null → same.
* One null, one non-null → not same.
* Values differ → not same.
* Otherwise:
  Check **left** subtrees AND **right** subtrees.

### **Code**

```java
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null || q == null)
            return p == q;
        return p.val == q.val 
            && isSameTree(p.left, q.left) 
            && isSameTree(p.right, q.right);
    }
}
```

### **Complexity**

* **Time:** O(n) — visit all nodes once
* **Space:** O(h) — recursion stack

  * Worst case skewed → O(n)
  * Balanced → O(log n)

### Why this is optimal?

* You must inspect every node in the worst case.
* There is no faster way than a single DFS.

---

# ⚡ **Iterative Version (Also Optimal)**

Sometimes interviewers want BFS/DFS using a queue or stack.

### **Approach**

* Push both roots into a queue.
* While queue not empty:

  * Pop pair (p, q)
  * If both null → continue
  * If one null or values mismatch → return false
  * Push respective children pairs:

    * (p.left, q.left)
    * (p.right, q.right)

### **Code**

```java
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(p);
        queue.offer(q);

        while (!queue.isEmpty()) {
            TreeNode a = queue.poll();
            TreeNode b = queue.poll();

            if (a == null && b == null) continue;
            if (a == null || b == null || a.val != b.val) return false;

            queue.offer(a.left);
            queue.offer(b.left);

            queue.offer(a.right);
            queue.offer(b.right);
        }
        return true;
    }
}
```

### **Complexity**

* Time: **O(n)**
* Space: **O(n)** (queue)

---

# 🧠 **Important Patterns Learned**

This question introduces key tree problem patterns:

### ✔ **1. Tree comparison template**

Used for:

* Same Tree
* Symmetric Tree
* Subtree of Another Tree
* Mirror Trees
* Identical structure validation

### ✔ **2. Recursive DFS with pairwise comparison**

Compare:

* Values
* Left subtrees
* Right subtrees

### ✔ **3. Short-circuit return**

As soon as mismatch occurs → return false immediately.

---

# 🎯 **Tips & Tricks for Interviews**

### 🔸 Always handle `null` cases first

If you mis-handle null, recursion fails.

### 🔸 Think in terms of pairs

You are never comparing a node alone—everything is compared in pairs.

### 🔸 Use the "base case must define equality clearly" rule:

```
if (p == null && q == null) → equal
if (p == null || q == null) → not equal
if (p.val != q.val) → not equal
```

### 🔸 If interviewer asks "Can you do without recursion?"

Yes → BFS/DFS using queue/stack.
Show confidence by mentioning it.

### 🔸 If interviewer pushes further:

Mention **preorder serialization trick**, but say it's not recommended.

---
