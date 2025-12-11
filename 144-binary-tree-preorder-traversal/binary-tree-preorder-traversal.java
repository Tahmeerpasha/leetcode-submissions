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
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        // Iterative - TC => O(n) && SC => O(n)
        // Stack<TreeNode> stack = new Stack<>();
        // if (root == null)
        //     return result;
        // stack.push(root);
        // while (!stack.isEmpty()) {
        //     root = stack.pop();
        //     result.add(root.val);
        //     if (root.right != null)
        //         stack.push(root.right);
        //     if (root.left != null)
        //         stack.push(root.left);
        // }
        // Recursive - TC => O(n) && SC => O(n)
        // preOrder(root, result);

        // Morris Travesal for preorder
        // TC - O(N) && SC - O(1)
        TreeNode curr = root;
        while (curr != null) {
            if (curr.left == null) {
                result.add(curr.val);
                curr = curr.right;
            } else {
                TreeNode prev = curr.left;
                while (prev.right != null && prev.right != curr)
                    prev = prev.right;

                if (prev.right == null) {
                    prev.right = curr;
                    result.add(curr.val);
                    curr = curr.left;
                } else {
                    prev.right = null;
                    curr = curr.right;
                }
            }
        }
        return result;
    }

    void preOrder(TreeNode root, List<Integer> result) {
        if (root == null)
            return;
        result.add(root.val);
        preOrder(root.left, result);
        preOrder(root.right, result);
    }
}