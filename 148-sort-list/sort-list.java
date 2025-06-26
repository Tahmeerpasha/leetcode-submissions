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
        return mergeTwoLists(left, right);
    }

    ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummyNode = new ListNode(-1);
        ListNode temp = dummyNode;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                temp.next = list1;
                temp = list1;
                list1 = list1.next;
            } else {
                temp.next = list2;
                temp = list2;
                list2 = list2.next;
            }
        }
        if (list1 != null)
            temp.next = list1;
        else
            temp.next = list2;
        return dummyNode.next;
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