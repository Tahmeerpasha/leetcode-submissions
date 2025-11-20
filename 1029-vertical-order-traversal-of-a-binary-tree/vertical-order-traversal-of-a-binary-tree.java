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
    class Tuple {
        int col;
        int row;
        TreeNode node;

        public Tuple(TreeNode node, int col, int row) {
            this.node = node;
            this.col = col;
            this.row = row;
        }
    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map = new TreeMap<>();
        Queue<Tuple> queue = new LinkedList<>();
        queue.add(new Tuple(root, 0, 0));
        while (!queue.isEmpty()) {
            Tuple currTuple = queue.poll();
            TreeNode currNode = currTuple.node;
            int col = currTuple.col;
            int row = currTuple.row;

            if (!map.containsKey(col))
                map.put(col, new TreeMap<>());

            if (!map.get(col).containsKey(row))
                map.get(col).put(row, new PriorityQueue<>());

            map.get(col).get(row).add(currNode.val);

            if (currNode.left != null)
                queue.add(new Tuple(currNode.left, col - 1, row + 1));
            if (currNode.right != null)
                queue.add(new Tuple(currNode.right, col + 1, row + 1));
        }
        List<List<Integer>> list = new ArrayList<>();
        for (TreeMap<Integer, PriorityQueue<Integer>> val : map.values()) {
            list.add(new ArrayList<>());
            for (PriorityQueue<Integer> nodes : val.values()) {
                while (!nodes.isEmpty()) {
                    list.get(list.size() - 1).add(nodes.poll());
                }
            }
        }
        return list;
    }
}