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
    public TreeNode bstFromPreorder(int[] preorder) {
        return bstFromPreorder(preorder, new int[] { 0 }, Integer.MAX_VALUE);
    }

    TreeNode bstFromPreorder(int[] preorder, int[] idx, int maxBound) {
        if (idx[0] == preorder.length || preorder[idx[0]] > maxBound)
            return null;
        TreeNode node = new TreeNode(preorder[idx[0]++]);
        node.left = bstFromPreorder(preorder, idx, node.val);
        node.right = bstFromPreorder(preorder, idx, maxBound);
        return node;
    }
}