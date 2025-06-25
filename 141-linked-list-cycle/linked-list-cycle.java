/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        int min = Integer.MIN_VALUE;
        ListNode temp = head;
        if (head == null || head.next == null)
            return false;
        while (temp != null && temp.val != min) {
            temp.val = min;
            temp = temp.next;
        }
        if (temp == null || temp.next == null)
            return false;
        else
            return true;
    }
}