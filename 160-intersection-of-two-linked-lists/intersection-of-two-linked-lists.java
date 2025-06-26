/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode temp1 = headA;
        ListNode temp2 = headB;
        // Brute => Time -> O(m+n) && Space -> O(m+n)
        // Map<ListNode, Integer> map = new HashMap<>();
        // while (temp1 != null) {
        //     map.put(temp1, 1);
        //     temp1 = temp1.next;
        // }
        // while (temp2 != null) {
        //     if (map.get(temp2) != null)
        //         return temp2;
        //     temp2 = temp2.next;
        // }
        // return null;

        // Better => Time -> O(n+2m) && Space -> O(1)
        /**
        Time complexity: O(2max(length of list1,length of list2))+O(abs(length of list1-length of list2))+O(min(length of list1,length of list2))
        Reason: Finding the length of both lists takes max(length of list1, length of list2) because it is found simultaneously for both of them. Moving the head pointer ahead by a difference of them. The next one is for searching.
         */
        // // Step 1: Calculate lengths
        // int lenA = getLength(headA);
        // int lenB = getLength(headB);

        // // Step 2: Align heads
        // while (lenA > lenB) {
        //     headA = headA.next;
        //     lenA--;
        // }

        // while (lenB > lenA) {
        //     headB = headB.next;
        //     lenB--;
        // }

        // // Step 3: Move both pointers together
        // while (headA != null && headB != null) {
        //     if (headA == headB)
        //         return headA;
        //     headA = headA.next;
        //     headB = headB.next;
        // }

        // return null;

        // Optimal => Time -> O(2*max(length of list1,length of list2)) && Space -> O(1)
        // Reason: Uses the same concept of the difference of lengths of two lists.
        if (temp1 == null || temp2 == null)
            return null;
        while (temp1 != temp2) {
            // temp1 = temp1.next;
            // temp2 = temp2.next;
            // if(temp1 == temp2)return temp1;
            // if(temp1 == null)temp1 = headB;
            // if(temp2 == null)temp2 = headA;

            // OR
            temp1 = temp1 == null ? headB : temp1.next;
            temp2 = temp2 == null ? headA : temp2.next;
        }
        return temp1;
    }

    private int getLength(ListNode node) {
        int length = 0;
        while (node != null) {
            length++;
            node = node.next;
        }
        return length;
    }
}