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
    public ListNode reverseList(ListNode head) {
        // Iterative => Time -> O(N) && Space -> O(1)
        // ListNode curr = head, prev = null;
        // while(curr != null){
        //     ListNode nextNode = curr.next;
        //     curr.next = prev;
        //     prev = curr;
        //     curr = nextNode;
        // }
        // return prev;

        // Recursive => Time -> O(N) && Space -> O(1)
        if(head == null || head.next == null)return head;
        ListNode newHead = reverseList(head.next);
        ListNode front = head.next;
        front.next = head;
        head.next = null;
        return newHead;
    }
}