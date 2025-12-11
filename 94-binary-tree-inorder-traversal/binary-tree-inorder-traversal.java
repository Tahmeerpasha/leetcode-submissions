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
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        // Iterative: TC => O(n) && SC => O(n)
        // Stack<TreeNode> stack = new Stack<>();
        // TreeNode node = root;
        // while (true) {
        //     if (node != null) {
        //         stack.push(node);
        //         node = node.left;
        //     } else {
        //         if (stack.isEmpty())
        //             break;
        //         node = stack.pop();
        //         result.add(node.val);
        //         node = node.right;
        //     }
        // }
        // Recursive: TC => O(n) && SC => O(n)
        // inOrder(root, result);

        // Morris traversal for inorder
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
                    curr = curr.left;
                } else {
                    prev.right = null;
                    result.add(curr.val);
                    curr = curr.right;
                }
            }
        }
        return result;
    }

    void inOrder(TreeNode root, List<Integer> result) {
        if (root == null)
            return;
        inOrder(root.left, result);
        result.add(root.val);
        inOrder(root.right, result);
    }
}