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

        // Step 1: Find the length and last node
        int len = 1;
        ListNode last = head;
        while (last.next != null) {
            last = last.next;
            len++;
        }

        // Step 2: Reduce k
        k = k % len;
        if (k == 0)
            return head;

        // Brute => Time -> O(k*n) && Space => O(1)
        // return rotateKTimes(head, k);

        // Optimal -> Time => O(n) && Space => O(1)
        // Step 3: Find new tail (len - k)th node
        int newLastCount = len - k;
        ListNode newLastNode = head;
        for (int i = 1; i < newLastCount; i++) {
            newLastNode = newLastNode.next;
        }

        // Step 4: Make the list circular and break it
        ListNode newHead = newLastNode.next;
        newLastNode.next = null;
        last.next = head;

        return newHead;
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