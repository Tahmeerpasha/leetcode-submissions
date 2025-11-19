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
    public int maxPathSum(TreeNode root) {
        int[] sum = new int[] { Integer.MIN_VALUE };
        maxPath(root, sum);
        return sum[0];
    }

    int maxPath(TreeNode root, int[] sum) {
        if (root == null)
            return 0;
        int left = maxPath(root.left, sum);
        int right = maxPath(root.right, sum);
        if (left < 0)
            left = 0;
        if (right < 0)
            right = 0;
        sum[0] = Math.max(sum[0], left + right + root.val);
        return root.val + Math.max(left, right);
    }
}