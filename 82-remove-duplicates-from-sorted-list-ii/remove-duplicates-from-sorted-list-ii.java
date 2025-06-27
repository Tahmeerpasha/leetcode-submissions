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
    public ListNode deleteDuplicates(ListNode head) {
        ListNode prevNode = null, currNode = head;
        while (currNode != null) {
            if (currNode.next != null && currNode.val == currNode.next.val) {
                int val = currNode.val;
                while (currNode != null && currNode.val == val)
                    currNode = currNode.next;
                if (prevNode != null)
                    prevNode.next = currNode;
                else
                    head = currNode;
            } else {
                prevNode = currNode;
                currNode = currNode.next;
            }
        }
        return head;
    }
}