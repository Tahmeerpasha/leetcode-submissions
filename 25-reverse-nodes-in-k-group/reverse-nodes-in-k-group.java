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
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode temp = head, prev = null;
        while (temp != null) {
            ListNode kthNode = findKthNode(temp, k);
            if (kthNode == null) {
                if (prev != null)
                    prev.next = temp;
                break;
            }

            ListNode nextNode = kthNode.next;
            kthNode.next = null;
            ListNode reversedHead = reverseList(temp);
            if (prev == null) {
                head = reversedHead;
            } else
                prev.next = reversedHead;
            prev = temp;
            temp = nextNode;

        }
        return head;
    }

    ListNode findKthNode(ListNode head, int k) {
        ListNode kthNode = head;
        k--;
        while (kthNode != null && k > 0) {
            k--;
            kthNode = kthNode.next;
        }
        return kthNode;
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
}