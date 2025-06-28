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
        ListNode temp = head, kthNode = head, prev = null;
        int cnt = k;
        while (kthNode != null) {
            while (kthNode != null && cnt != 1) {
                kthNode = kthNode.next;
                cnt--;
            }
            if (kthNode != null && cnt == 1) {
                ListNode nextNode = kthNode.next;
                kthNode.next = null;
                ListNode reversedNode = reverseList(temp);
                if (prev == null) {
                    head = reversedNode;
                } else {
                    prev.next = reversedNode;
                }
                prev = temp;
                kthNode = nextNode;
                temp = nextNode;
            }else{
                prev.next = temp;
            }
            cnt = k;
        }
        return head;
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