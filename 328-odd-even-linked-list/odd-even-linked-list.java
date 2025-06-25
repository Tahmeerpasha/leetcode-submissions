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
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        // Brute => Time -> O(n) && Space -> O(n)
        // ListNode currOdd = null, currEven = null;
        // ListNode temp = head, oddList = null, evenList = null;
        // int count = 0;
        // while (temp != null) {
        //     if (count % 2 == 1) {
        //         if (evenList == null) {
        //             evenList = new ListNode(temp.val, null);
        //             currEven = evenList;
        //         } else {
        //             currEven.next = new ListNode(temp.val, null);
        //             currEven = currEven.next;
        //         }
        //     } else {
        //         if (oddList == null) {
        //             oddList = new ListNode(temp.val, null);
        //             currOdd = oddList;
        //         } else {
        //             currOdd.next = new ListNode(temp.val, null);
        //             currOdd = currOdd.next;
        //         }
        //     }
        //     temp = temp.next;
        //     count++;
        // }
        // currOdd.next = evenList;
        // return oddList;

        // Optimal => Time -> O(n) && Space -> O(1)
        ListNode odd = head, even = head.next, evenHead = even;
        while (even != null && even.next != null) {
            odd.next = odd.next.next;
            even.next = even.next.next;
            
            odd = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }
}