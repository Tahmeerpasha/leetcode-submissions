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
    class Pair {
        TreeNode node;
        int num;

        Pair(TreeNode node, int num) {
            this.node = node;
            this.num = num;
        }
    }

    public int widthOfBinaryTree(TreeNode root) {
        if (root == null)
            return 0;
        int ans = 0;
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(root, 0));
        while (!q.isEmpty()) {
            int size = q.size();
            int min = q.peek().num;
            int first = 0, last = 0;
            for (int i = 0; i < size; i++) {
                int currInd = q.peek().num - min;
                TreeNode node = q.peek().node;
                q.poll();
                if (i == 0)
                    first = currInd;
                if (i == size - 1)
                    last = currInd;
                if (node.left != null)
                    q.add(new Pair(node.left, 2 * currInd + 1));
                if (node.right != null)
                    q.add(new Pair(node.right, 2 * currInd + 2));
                ans = Math.max(ans, last - first + 1);
            }
        }
        return ans;
    }
}