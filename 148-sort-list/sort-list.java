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
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        // Brute => Time -> O(n) && Space -> O(n)
        // ArrayList<Integer> list = new ArrayList<>();
        // ListNode temp = head;
        // while (temp != null) {
        //     list.add(temp.val);
        //     temp = temp.next;
        // }
        // Collections.sort(list);
        // temp = head;
        // int i = 0;
        // while (temp != null) {
        //     temp.val = list.get(i++);
        //     temp = temp.next;
        // }
        // return head;

        // Optimal => Time -> O(n logn) && Space -> O(1)
        ListNode middleNode = findMiddleOfLL(head);
        ListNode leftHalf = head;
        ListNode rightHalf = middleNode.next;
        middleNode.next = null;
        ListNode left = sortList(leftHalf);
        ListNode right = sortList(rightHalf);
        return merge2LL(left, right);
    }

    ListNode merge2LL(ListNode left, ListNode right) {
        ListNode mergedHead = new ListNode(-1);
        ListNode temp = mergedHead;
        while (left != null && right != null) {
            if (left.val <= right.val) {
                temp.next = left;
                temp = left;
                left = left.next;
            } else {
                temp.next = right;
                temp = right;
                right = right.next;
            }
        }
        if (left != null)
            temp.next = left;
        else
            temp.next = right;
        return mergedHead.next;
    }

    ListNode findMiddleOfLL(ListNode head) {
        if (head == null)
            return null;
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
}