/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public boolean findTarget(TreeNode root, int k) {
        if (root == null)
            return false;
        BSTIterator next = new BSTIterator(root, false);
        BSTIterator before = new BSTIterator(root, true);
        // NEXT
        int l = next.next();
        // BEFORE
        int r = before.next();

        while (l < r) {
            if (l + r == k)
                return true;
            else if (l + r < k)
                l = next.next();
            else
                r = before.next();
        }
        return false;
    }
}

class BSTIterator {
    Stack<TreeNode> st;
    boolean isBefore;

    public BSTIterator(TreeNode root, boolean isBefore) {
        this.st = new Stack<>();
        this.isBefore = isBefore;
        pushAll(root);
    }

    public int next() {
        if (st.isEmpty())
            return -1;
        TreeNode node = st.pop();
        if (!isBefore) {
            pushAll(node.right);
        } else {
            pushAll(node.left);
        }
        return node.val;

    }

    public boolean hasNext() {
        return !st.isEmpty();
    }

    void pushAll(TreeNode root) {
        while (root != null) {
            st.push(root);
            if (!isBefore)
                root = root.left;
            else
                root = root.right;
        }
    }
}