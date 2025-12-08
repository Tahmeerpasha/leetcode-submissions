# **106. Construct Binary Tree from Inorder & Postorder**

## **1️⃣ Problem Summary**

Given:

* **Inorder** → Left, Root, Right
* **Postorder** → Left, Right, Root

Reconstruct the binary tree.

---

# **2️⃣ Approaches**

---

## **A. Brute Force Approach**

### **Idea**

* Last element of postorder = **root**.
* Search for root index in inorder using **linear search** → O(n).
* Split inorder around root into left/right subtrees.
* Use subtree sizes to find corresponding postorder segments.

### **Why is it brute?**

Repeated linear searches → **O(n²)** worst case.

### **Complexity**

* **Time:** O(n²)
* **Space:** O(n) recursion + storing tree nodes

---

## **B. Better Approach**

### **Idea**

* Use a **HashMap** to find inorder index in **O(1)**.
* This drops complexity to **O(n)**.

But array slicing still makes it slightly suboptimal.

### **Complexity**

* **Time:** O(n)
* **Space:** O(n) (map + recursion)

---

## **C. Optimal Approach (Your Solution)**

You remove array slicing and work **only with indexes**, making the implementation memory-efficient and clean.

This is the standard optimal solution.

---

# **3️⃣ Your Code (Optimal Solution)**

```java
class Solution {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++)
            inMap.put(inorder[i], i);
        return buildTree(postorder, 0, postorder.length - 1, inorder, 0, inorder.length - 1, inMap);
    }

    public TreeNode buildTree(int[] postorder, int postStart, int postEnd, int[] inorder, int inStart, int inEnd,
            Map<Integer, Integer> inMap) {
        if (postStart > postEnd || inStart > inEnd)
            return null;

        TreeNode root = new TreeNode(postorder[postEnd]);
        int inRoot = inMap.get(root.val);
        int numsLeft = inRoot - inStart;

        root.left = buildTree(postorder, postStart, postStart + numsLeft - 1, inorder, inStart, inRoot - 1, inMap);
        root.right = buildTree(postorder, postStart + numsLeft, postEnd - 1, inorder, inRoot + 1, inEnd, inMap);
        return root;
    }
}
```

---

# **4️⃣ Deep Explanation — How Left & Right Subtrees Are Constructed Using Indexes**

### **Postorder structure**

`[ left subtree | right subtree | ROOT ]`

So:

* `postorder[postEnd]` = current root.

### **Inorder structure**

`[ left subtree | ROOT | right subtree ]`

Let:

* `inRoot = index of root in inorder`
* `numsLeft = inRoot - inStart`

This tells you **exactly how many nodes belong to the left subtree**.

---

## **Left Subtree Boundaries**

### **Inorder**

```
Left inorder  = [inStart ... inRoot - 1]
```

### **Postorder**

Left subtree occupies the first `numsLeft` nodes:

```
postStart_left = postStart
postEnd_left   = postStart + numsLeft - 1
```

---

## **Right Subtree Boundaries**

### **Inorder**

```
Right inorder = [inRoot + 1 ... inEnd]
```

### **Postorder**

Right subtree is between left subtree and the root:

```
postStart_right = postStart + numsLeft
postEnd_right   = postEnd - 1
```

---

### **Why this works**

* Postorder gives root last → easy to pick root.
* Inorder splits left and right subtree cleanly.
* Using subtree size lets us determine correct postorder slicing *without touching arrays*.
* Passing only index ranges avoids O(n) slicing cost.

This is the exact reasoning interviewers want to see.

---

# **5️⃣ Time & Space Complexity**

### **Time → O(n)**

* Each node processed once
* HashMap lookup is O(1)

### **Space → O(n)**

* HashMap storing inorder indices
* Recursion stack worst-case (skewed tree)

---

# **6️⃣ Interview Notes, Tips & Tricks**

🔥 **Key points to state clearly:**

* Postorder gives the root at the end.
* Inorder determines the size of left subtree.
* Using HashMap avoids repeated scanning.
* Using index boundaries avoids creating subarrays.
* Recursion naturally rebuilds the tree.

---

### **Common interview traps**

❌ Incorrect postorder boundaries
❌ Forgetting to subtract 1 in left subtree postEnd
❌ Using array slicing → kills performance
❌ Not handling empty subtree case properly

---

### **How to explain this in 20 seconds**

> “Root comes from postorder’s end. Using a hashmap on inorder, I split it into left and right subtrees. Left subtree size tells me how to divide postorder indexes. I recurse on index ranges without copying arrays, giving O(n) time and space.”

Perfect, crisp answer.

---
