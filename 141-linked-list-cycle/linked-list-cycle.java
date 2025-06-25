/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        // Brute => Time -> O(n) && Space -> O(n)
        // Node temp = head;
        // Map<Node, int> nodeMap = new HashMap<>();
        // while (temp != null) {
        //     if (nodeMap.containsKey(temp)) {
        //         return true;
        //     }
        //     nodeMap.put(temp, 1);
        //     temp = temp.next;
        // }
        // return false;

        // Better => Time -> O(n) && Space -> O(1)
        // int min = Integer.MIN_VALUE;
        // ListNode temp = head;
        // if (head == null || head.next == null)
        //     return false;
        // while (temp != null && temp.val != min) {
        //     temp.val = min;
        //     temp = temp.next;
        // }
        // if (temp == null || temp.next == null)
        //     return false;
        // else
        //     return true;

        // Optimal => Time -> O(n) && Space -> O(1)
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast)
                return true;
        }
        return false;
    }
}