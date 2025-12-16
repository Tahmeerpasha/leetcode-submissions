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
    // Recursive approach
    // public List<Integer> postorderTraversal(TreeNode root) {
    //     List<Integer> result = new ArrayList<>();
    //     // TC => O(n) && SC => O(n)
    //     postOrder(root, result);
    //     return result;
    // }

    void postOrder(TreeNode root, List<Integer> result) {
        if (root == null)
            return;
        postOrder(root.left, result);
        postOrder(root.right, result);
        result.add(root.val);
    }

    // Iterative approach
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> st = new Stack<>();
        TreeNode curr = root;
        TreeNode lastVisited = null;

        while (curr != null || !st.isEmpty()) {

            // Go to leftmost node
            while (curr != null) {
                st.push(curr);
                curr = curr.left;
            }

            TreeNode top = st.peek();

            // If right child exists & not processed yet → go right
            if (top.right != null && lastVisited != top.right) {
                curr = top.right;
            } else {
                result.add(top.val);
                lastVisited = top;
                st.pop();
            }
        }

        return result;
    }
}