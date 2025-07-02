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
    public ListNode detectCycle(ListNode head) {
        // Brute => Time -> O(n) && Space -> O(n)
        // ListNode temp = head;
        // HashMap<ListNode, int> nodeMap = new HashMap<>();

        // while (temp != null) {
        //     if (nodeMap.containsKey(temp)) {
        //         return temp;
        //     }

        //     nodeMap.put(temp, 1);

        //     temp = temp.next;
        // }
        // return null;

        // Optimal => Time -> O(n) && Space -> O(1)
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                slow = head;
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }
        return null;
    }
}