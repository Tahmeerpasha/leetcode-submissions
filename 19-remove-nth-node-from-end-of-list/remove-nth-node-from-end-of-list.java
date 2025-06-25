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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null)
            return head;
        if (head.next == null && n == 1)
            return null;
        int count = 0;
        ListNode temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        temp = head;
        int pos = count - n;
        if (pos < 0)
            return head;
        if(pos == 0)return head.next;
        count = 0;
        while (temp != null) {
            count++;
            if (count == pos)
                break;
            temp = temp.next;
        }
        if (temp != null && temp.next != null)
            temp.next = temp.next.next;
        return head;
    }
}