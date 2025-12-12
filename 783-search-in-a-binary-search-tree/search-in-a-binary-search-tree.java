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
    public TreeNode searchBST(TreeNode root, int val) {
        // TC - O(Log N) && SC - O(N) (recursion stack space)
        // if (root == null)
        //     return null;
        // if (root.val == val)
        //     return root;
        // if (val < root.val)
        //     return searchBST(root.left, val);
        // else
        //     return searchBST(root.right, val);

        // TC - O(Log N) && SC - O(1)
        while (root != null && root.val != val)
            root = val < root.val ? root.left : root.right;
        return root;
    }
}