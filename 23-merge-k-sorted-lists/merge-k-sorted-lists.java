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
    public ListNode mergeKLists(ListNode[] lists) {
        /**
        Brute approach would be to simple add all the lists data in an array and sort the array. And finally just create a new linked list with those values;
         */
        if (lists.length == 0)
            return null;
        if (lists.length == 1)
            return lists[0];
        // Better => Time -> O(n^3) && Space -> O(1)
        // ListNode head = lists[0];
        // for (int i = 1; i < lists.length; i++) {
        //     head = mergeTwoLists(head, lists[i]);
        // }
        // return head;

        // Optimal => Time -> O(n^2) && Space -> O(k)
        // Create a min heap using PriorityQueue
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> a.val - b.val);

        // Add the head nodes of all linked lists to the min heap
        for (ListNode node : lists) {
            if (node != null) {
                minHeap.offer(node);
            }
        }

        // Create a dummy node to simplify the code
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        // Continuously pop the smallest node from the heap and add it to the result
        while (!minHeap.isEmpty()) {
            ListNode smallest = minHeap.poll();
            tail.next = smallest;
            tail = tail.next;

            // Move the smallest node's pointer forward in its original linked list
            if (smallest.next != null) {
                minHeap.offer(smallest.next);
            }
        }

        return dummy.next;
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
}