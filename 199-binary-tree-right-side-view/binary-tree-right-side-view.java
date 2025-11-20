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
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        rightView(root, result, 0);
        return result;
    }

    void rightView(TreeNode root, List<Integer> result, int level) {
        if (root == null)
            return;
        if (result.size() == level)
            result.add(root.val);
        if (root.right != null)
            rightView(root.right, result, level + 1);
        if (root.left != null)
            rightView(root.left, result, level + 1);
    }
}