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
    public ListNode deleteMiddle(ListNode head) {
        if (head == null || head.next == null)
            return null;
        if (head.next.next == null) {
            head.next = null;
            return head;
        }
        // Brute => Time -> O(2n) && Space -> O(1)
        // ListNode temp = head;
        // int n = 0;
        // while (temp != null) {
        //     n++;
        //     temp = temp.next;
        // }
        // int res = n / 2;
        // temp = head;
        // while (temp != null) {
        //     res--;
        //     if (res == 0) {
        //         temp.next = temp.next.next;
        //         break;
        //     }
        //     temp = temp.next;
        // }
        // return head;

        ListNode slow = head, fast = head;
        fast = fast.next.next;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return head;
    }
}