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
    public boolean checkTree(TreeNode root) {
        // Base case: Leaf node ? property holds
        if (root == null || (root.left == null && root.right == null))
            return true;

        int left = (root.left == null) ? 0 : root.left.val;
        int right = (root.right == null) ? 0 : root.right.val;

        // Check current node
        if (root.val != left + right)
            return false;

        // Check subtrees
        return checkTree(root.left) && checkTree(root.right);
    }
}