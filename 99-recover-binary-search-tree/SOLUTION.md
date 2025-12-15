## 99. Recover Binary Search Tree

### Problem Summary

A BST has **exactly two nodes swapped by mistake**.
Restore the BST **without changing its structure**.

---

## Key Observation (Core Insight)

* **Inorder traversal of a BST gives a sorted array**
* If two nodes are swapped:

  * Either **two violations** (non-adjacent swap)
  * Or **one violation** (adjacent swap)

---

## Example

Inorder should be:
`1 2 3 4 5`

Swapped cases:

* Non-adjacent → `1 4 3 2 5`
* Adjacent → `1 3 2 4 5`

---

## Brute Force Approach

### Idea

1. Do inorder traversal → store nodes in a list
2. Sort values
3. Replace values back

### Steps

* Inorder → `List<TreeNode>`
* Extract values → sort
* Reassign values

### Complexity

* **Time:** `O(N log N)`
* **Space:** `O(N)`

### Verdict

❌ **Not acceptable in interviews**
Violates the “constant space” expectation.

---

## Better Approach (Your Code – Recursive Inorder)

### Idea

Track **violations in inorder traversal** using pointers.

### Violations

| Case              | first | middle | last |
| ----------------- | ----- | ------ | ---- |
| Adjacent swap     | ✓     | ✓      | ✗    |
| Non-adjacent swap | ✓     | ✓      | ✓    |

### Logic

* Maintain `prev`
* If `root.val < prev.val` → violation
* First violation → `first = prev`, `middle = root`
* Second violation → `last = root`

---

### ✅ Your Code (KEEP THIS)

```java
class Solution {
    TreeNode first, middle, last, prev;

    public void recoverTree(TreeNode root) {
        first = middle = last = null;
        prev = new TreeNode(Integer.MIN_VALUE);
        inorder(root);
        if (first != null && last != null) {
            int temp = first.val;
            first.val = last.val;
            last.val = temp;
        } else if (first != null && middle != null) {
            int temp = first.val;
            first.val = middle.val;
            middle.val = temp;
        }
    }

    void inorder(TreeNode root) {
        if (root == null)
            return;
        inorder(root.left);
        if (prev != null && root.val < prev.val) {
            if (first == null) {
                first = prev;
                middle = root;
            } else
                last = root;
        }
        prev = root;
        inorder(root.right);
    }
}
```

### Complexity

* **Time:** `O(N)`
* **Space:** `O(N)` (recursion stack)

### Verdict

✅ **Perfect for most interviews**

---

## Optimal Approach (Morris Traversal)

### Idea

* Use **threaded BST**
* No recursion
* No stack
* Detect violations during Morris inorder traversal

### Complexity

* **Time:** `O(N)`
* **Space:** `O(1)` ← **true optimal**

### Verdict

⚠️ Only needed if interviewer explicitly says **constant space**

---

## Interview Tips (IMPORTANT)

### 1️⃣ Always Say This Line

> “Inorder traversal of a BST is sorted.”

Say it confidently. It sets the tone.

---

### 2️⃣ Explain Violation Clearly

* First wrong pair → `first` & `middle`
* Second wrong → `last`

---

### 3️⃣ Adjacent vs Non-Adjacent Swap

* Adjacent → only one inversion
* Non-adjacent → two inversions

Interviewers often ask this follow-up.

---

### 4️⃣ Why `prev = Integer.MIN_VALUE`?

Avoids null checks for the first node in inorder.

---

### 5️⃣ If Asked Space Optimization

Say:

> “We can use Morris traversal to achieve O(1) space.”

You don’t always need to code it.

---

## One-Line Summary (Memorize)

> Detect inorder violations, identify swapped nodes, and swap back their values.

---
