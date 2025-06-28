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
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null)
            return head;
        int len = 1;
        ListNode last = head;
        while (last.next != null) {
            last = last.next;
            len++;
        }
        k = k % len;
        if (k == 0)
            return head;

        // Better
        // return rotateKTimes(head, k);

        // Optimal
        int newLastCount = len - k;
        ListNode newLastNode = head;
        ListNode prevLastNode = null;
        while (newLastCount > 0) {
            prevLastNode = newLastNode;
            newLastNode = newLastNode.next;
            newLastCount--;
        }
        last.next = head;
        head = prevLastNode.next;
        prevLastNode.next = null;

        return head;
    }

    ListNode rotateKTimes(ListNode head, int k) {
        ListNode prev = null, temp = head;
        while (k > 0) {
            while (temp.next != null) {
                prev = temp;
                temp = temp.next;
            }
            prev.next = null;
            temp.next = head;
            head = temp;
            k--;
        }
        return head;
    }
}