/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    void markParents(TreeNode root, Map<TreeNode, TreeNode> parentsMap, TreeNode target) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            if (current.left != null) {
                parentsMap.put(current.left, current);
                queue.add(current.left);
            }
            if (current.right != null) {
                parentsMap.put(current.right, current);
                queue.add(current.right);
            }
        }
    }

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        Map<TreeNode, TreeNode> parentsMap = new HashMap<>();
        markParents(root, parentsMap, target);
        Map<TreeNode, Boolean> visited = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(target);
        visited.put(target, true);
        int currLevel = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            if (currLevel == k)
                break;
            currLevel++;
            for (int i = 0; i < size; i++) {
                TreeNode currNode = queue.poll();
                if (parentsMap.get(currNode) != null && visited.get(parentsMap.get(currNode)) == null) {
                    queue.add(parentsMap.get(currNode));
                    visited.put(parentsMap.get(currNode), true);
                }
                if (currNode.left != null && visited.get(currNode.left) == null) {
                    queue.add(currNode.left);
                    visited.put(currNode.left, true);
                }
                if (currNode.right != null && visited.get(currNode.right) == null) {
                    queue.add(currNode.right);
                    visited.put(currNode.right, true);
                }
            }
        }
        List<Integer> result = new ArrayList<>();
        while (!queue.isEmpty())
            result.add(queue.poll().val);

        return result;
    }
}