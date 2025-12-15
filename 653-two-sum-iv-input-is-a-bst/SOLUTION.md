# ✅ **653. Two Sum IV – Input is a BST — Notes**

---

# **❌ 1. Brute Force Approach**

### **Idea**

* Traverse the tree and store all node values in an array/list.
* Apply **Two Sum** using a hash set or sorting + two pointers.

### **Steps**

1. Do an inorder traversal → store all values in a list (this is implicitly sorted because it's a BST).
2. Use two pointers on the sorted list to check for `arr[l] + arr[r] == target`.

### **Time Complexity**

* Inorder traversal: **O(N)**
* Two-pointer scan: **O(N)**
* **Total: O(N)**

### **Space Complexity**

* Stores all nodes → **O(N)**

### **Why It’s Weak**

* Waste of BST structure.
* Extra space.

---

# **🟡 2. Better Approach – Use a HashSet**

### **Idea**

Classic Two Sum:

* While traversing, check whether `target - node.val` already exists.

### **Steps**

1. Use DFS (or BFS).
2. Maintain a `HashSet<Integer>`.
3. For each node:

   * If `target - node.val` is in the set → return true.
   * Otherwise, add `node.val`.

### **Time Complexity**

* Visit each node once → **O(N)**

### **Space Complexity**

* Hashset storing values → **O(N)**

### **Pros**

* Simple.
* Easy to code in interviews.

### **Cons**

* Doesn’t take advantage of BST ordering for an optimal solution.

---

# **🟢 3. Optimal Approach – Two BST Iterators (Your Code)**

This is the **best** approach for interviews involving BST + Two Sum.

### **Core Idea**

Use **two iterators**:

* One gives **next smallest** (inorder).
* One gives **next largest** (reverse inorder).

This is equivalent to applying the **two-pointer technique directly on the BST**, without converting it to an array.

### **Why This Is Optimal**

* No need to store all values (stack only stores path at any point).
* Moves exactly like two pointers converging from both ends.

---

# **Optimal Code (Your Provided Code)**

🟩 Include this in your notes:

```java
class Solution {
    public boolean findTarget(TreeNode root, int target) {
        if (root == null)
            return false;

        // Left iterator → gives next smallest
        BSTIterator asc = new BSTIterator(root, false);

        // Right iterator → gives next largest
        BSTIterator desc = new BSTIterator(root, true);

        int leftVal = asc.next();
        int rightVal = desc.next();

        while (leftVal < rightVal) {
            int sum = leftVal + rightVal;

            if (sum == target) {
                return true;
            } else if (sum < target) {
                leftVal = asc.next(); // move upwards from left side
            } else {
                rightVal = desc.next(); // move downwards from right side
            }
        }

        return false;
    }
}

class BSTIterator {
    private Stack<TreeNode> stack;
    private boolean reverse; // false → smallest iterator, true → largest iterator

    public BSTIterator(TreeNode root, boolean reverse) {
        this.stack = new Stack<>();
        this.reverse = reverse;
        pushPath(root);
    }

    // Returns next smallest (reverse = false) or next largest (reverse = true)
    public int next() {
        TreeNode node = stack.pop();

        if (!reverse) {
            pushPath(node.right); // normal inorder → left, node, right
        } else {
            pushPath(node.left); // reverse inorder → right, node, left
        }

        return node.val;
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }

    // Pushes all left (or right) children depending on iterator type
    private void pushPath(TreeNode node) {
        while (node != null) {
            stack.push(node);
            node = reverse ? node.right : node.left;
        }
    }
}
```

---

# **⏱️ Time & Space Complexity (Optimal)**

### **Time Complexity**

* Each node is visited at most once by an iterator
  → **O(N)** worst case

### **Space Complexity**

* Stack maintains at most height of BST: **O(H)**
* For balanced BST: **O(log N)**
* For skewed: **O(N)**

### **Why It's Optimal**

* Doesn't store whole inorder list.
* Pure two-pointer logic on BST.

---

# ⭐ Interview Tips & Tricks

### **1. If interviewer says “Input is a BST”, they WANT you to use ordering**

Not using BST properties is a yellow flag.

### **2. Think of BST inorder = sorted array**

This helps you mentally switch into two-pointer mode.

### **3. When interviewer pushes for space optimization → Talk about your iterator solution**

This solution is almost always the expected optimal one.

### **4. Never forget that brute & hashset are still valid answers**

Interviewers often ask for:

* "Simplest approach?"
* "Can we do better?"
* "Can we optimize space?"

### **5. The hardest part is designing `BSTIterator` cleanly**

Keep it simple:

* Stack + directional flag (`reverse`)
* `pushPath` loads the next path

### **6. Real confidence booster**

This pattern appears again in:

* **Kth smallest/largest**
* **BST Iterator problem itself**
* **Merge two BSTs**
* **Recover BST**

Knowing this template gives you leverage in multiple BST questions.

---
