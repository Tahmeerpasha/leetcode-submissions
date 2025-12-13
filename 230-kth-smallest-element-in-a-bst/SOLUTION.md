# **230. Kth Smallest Element in a BST — Notes**

## **Key Idea**

BST has the property: **inorder traversal yields sorted order**. So the **k-th smallest** is just the **k-th element in inorder order**.

---

# **1️⃣ Brute Force Approach**

### **Approach**

* Perform a full inorder traversal.
* Store all the nodes in an array/list.
* Return `list[k-1]`.

### **Complexity**

* **Time:** `O(N)` — traversing the entire tree.
* **Space:** `O(N)` — storing the inorder array.

### **When to use**

* Quick and easy when constraints are small or space isn't a concern.

---

# **2️⃣ Better Approach (Stop Early During Inorder)**

> You do inorder but **stop when you reach the k-th element**.
> Saves time in skewed trees and avoids storing nodes.

### **Approach**

* Inorder traversal.
* Keep reducing `k`.
* Stop traversal when `k == 0`.

### **Your Code (Optimal Recursive Inorder with Early Stop)**

Keep this exactly as-is for interview-ready explanation:

```java
class Solution {
    int k, ans;

    public int kthSmallest(TreeNode root, int k) {
        this.k = k;
        inorder(root);
        return ans;
    }

    private void inorder(TreeNode root) {
        if (root == null)
            return;

        inorder(root.left);

        // Process current node
        k--;
        if (k == 0) {
            ans = root.val;
            return; // stop further traversal
        }

        inorder(root.right);
    }
}
```

### **Complexity**

* **Time:** Worst-case `O(N)` (skewed tree). Best case early stop.
* **Space:** `O(H)` recursion stack ⇒ `H = height of tree`.

---

# **3️⃣ Optimal for Interview Discussion: Iterative Inorder Using Stack**

### **Approach**

* Mimics recursion using a stack.
* Push all left nodes.
* Pop one-by-one until k-th.

### **Why interviewers like it**

* Shows you understand traversal mechanics.
* Avoids recursion depth issues.

### **Complexity**

* **Time:** `O(H + k)`
* **Space:** `O(H)`
  Where `H` = height of BST.

(If asked to write, you easily code it in 2 minutes.)

---

# **4️⃣ Most Optimal (Follow-up): When Tree is Mutable & Many Queries**

If interviewer asks:

**"What if I ask kthSmallest repeatedly?"**

Then introduce **augmented BST nodes** storing **subtree size**.

### Operations become:

* Insert: `O(logN)`
* Delete: `O(logN)`
* Kth smallest: `O(logN)`

This shows strong system-design intuition.

---

# **Tips & Tricks for Interviews**

### ✔ Always mention that **inorder of BST = sorted**

They expect it.

### ✔ Mention early exit optimization

Shows you care about pruning unnecessary work.

### ✔ If interviewer hints at multiple queries or updates

Talk about **Order Statistic Tree** (subtree-size augmentation).

### ✔ Be clear about recursion limits

For skewed trees, recursion depth → `O(N)`.

### ✔ Have both recursive and iterative versions in your head

Seniors often prefer iterative stack version.

---

# **Quick Summary Table**

| Approach                                        | Time    | Space      | Notes                    |
| ----------------------------------------------- | ------- | ---------- | ------------------------ |
| Brute (store inorder)                           | O(N)    | O(N)       | Simple but memory-heavy  |
| Better (your recursive inorder with early stop) | O(N)    | O(H)       | Clean + efficient enough |
| Optimal for many queries                        | O(logN) | O(1) extra | Needs augmented tree     |

---
