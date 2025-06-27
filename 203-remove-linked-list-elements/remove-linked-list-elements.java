/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode removeElements(ListNode head, int val) {
        // Optimal -> Time => O(n) && Space -> O(1)
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode curr = dummy;

        while (curr.next != null) {
            if (curr.next.val == val) {
                curr.next = curr.next.next;
            } else {
                curr = curr.next;
            }
        }

        return dummy.next;

        // Recursive approach => Time -> O(n) && Space -> O(n) -> auxilary space
        // if (head == null)
        //     return null;
        // head.next = removeElements(head.next, val);
        // return head.val == val ? head.next : head;
    }
}