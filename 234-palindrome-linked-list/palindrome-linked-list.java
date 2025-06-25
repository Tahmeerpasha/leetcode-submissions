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
        // Brute => Time -> O(2N) && Space -> O(N)
        // Stack<Integer> st = new Stack<>();
        // ListNode temp = head;
        // while (temp != null) {
        //     st.push(temp.data);
        //     temp = temp.next;
        // }
        // temp = head;
        // while (temp != null) {
        //     if (temp.data != st.peek()) {
        //         return false;
        //     }
        //     st.pop();
        //     temp = temp.next;
        // }
        // return true;

        if (head == null || head.next == null)
            return true;
        ListNode fast = head, slow = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode reversedHalf = reverseList(slow.next);
        ListNode firstHalf = head;
        while (reversedHalf != null) {
            if (firstHalf.val != reversedHalf.val) {
                reverseList(reversedHalf);
                return false;
            }
            firstHalf = firstHalf.next;
            reversedHalf = reversedHalf.next;
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
}