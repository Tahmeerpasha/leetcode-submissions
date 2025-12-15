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
    public boolean findTarget(TreeNode root, int target) {
        if (root == null)
            return false;

        // Left iterator → gives next smallest
        BSTIterator asc = new BSTIterator(root, false);

        // Right iterator → gives next largest
        BSTIterator desc = new BSTIterator(root, true);

        int leftVal = asc.next();
        int rightVal = desc.next();

        while (leftVal < rightVal) {
            int sum = leftVal + rightVal;

            if (sum == target) {
                return true;
            } else if (sum < target) {
                leftVal = asc.next(); // move upwards from left side
            } else {
                rightVal = desc.next(); // move downwards from right side
            }
        }

        return false;
    }
}

class BSTIterator {
    private Stack<TreeNode> stack;
    private boolean reverse; // false → smallest iterator, true → largest iterator

    public BSTIterator(TreeNode root, boolean reverse) {
        this.stack = new Stack<>();
        this.reverse = reverse;
        pushPath(root);
    }

    // Returns next smallest (reverse = false) or next largest (reverse = true)
    public int next() {
        TreeNode node = stack.pop();

        if (!reverse) {
            pushPath(node.right); // normal inorder → left, node, right
        } else {
            pushPath(node.left); // reverse inorder → right, node, left
        }

        return node.val;
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }

    // Pushes all left (or right) children depending on iterator type
    private void pushPath(TreeNode node) {
        while (node != null) {
            stack.push(node);
            node = reverse ? node.right : node.left;
        }
    }
}