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
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prevNode = dummy, currNode = head;

        while (currNode != null) {
            // Check if current node is a duplicate
            if (currNode.next != null && currNode.val == currNode.next.val) {
                int duplicateVal = currNode.val;
                // Skip all nodes with this value
                while (currNode != null && currNode.val == duplicateVal) {
                    currNode = currNode.next;
                }
                // Connect prevNode to next distinct node
                prevNode.next = currNode;
            } else {
                prevNode = currNode;
                currNode = currNode.next;
            }
        }
        return dummy.next;
    }
}