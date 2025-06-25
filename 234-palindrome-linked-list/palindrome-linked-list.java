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
    public boolean isPalindrome(ListNode head) {
        ListNode clone = cloneList(head);
        ListNode reversedHead = reverseList(clone);
        ListNode temp1 = head, temp2 = reversedHead;
        while (temp1 != null && temp2 != null) {
            if (temp1.val != temp2.val)
                return false;
            temp1 = temp1.next;
            temp2 = temp2.next;
        }
        return true;
    }

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode newHead = reverseList(head.next);
        ListNode front = head.next;
        front.next = head;
        head.next = null;
        return newHead;
    }

    private ListNode cloneList(ListNode head) {
        if (head == null)
            return null;
        ListNode newHead = new ListNode(head.val);
        ListNode current = newHead;
        head = head.next;
        while (head != null) {
            current.next = new ListNode(head.val);
            current = current.next;
            head = head.next;
        }
        return newHead;
    }
}