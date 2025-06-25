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