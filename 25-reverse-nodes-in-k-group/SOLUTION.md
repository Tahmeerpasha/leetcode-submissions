## 🔍 Problem Statement

Given a linked list, reverse the nodes of a list **k at a time** and return its modified list.
If the number of nodes is not a multiple of `k`, leave the last remaining nodes as is.

* You may **not alter the values** in the list’s nodes, only nodes themselves may be changed.
* Must use constant extra memory.

---

## 🔢 Example

### Input:

`head = [1,2,3,4,5], k = 2`

### Output:

`[2,1,4,3,5]`

### Input:

`head = [1,2,3,4,5], k = 3`

### Output:

`[3,2,1,4,5]`

---

## ✅ Optimal Approach (Iterative Group Reversal)

### 💡 Idea:

1. Traverse the list and find the kth node from the current node.
2. Reverse that group of k nodes.
3. Connect the reversed part with the previous part.
4. Continue for the next k-group.

---

### ✅ Code Walkthrough:

```java
public ListNode reverseKGroup(ListNode head, int k) {
    ListNode temp = head, prev = null;

    while (temp != null) {
        // Step 1: Find the kth node from current temp
        ListNode kthNode = findKthNode(temp, k);
        if (kthNode == null) {
            // Not enough nodes to reverse
            if (prev != null)
                prev.next = temp;
            break;
        }

        // Step 2: Isolate group and reverse
        ListNode nextNode = kthNode.next;
        kthNode.next = null;

        ListNode reversedHead = reverseList(temp);

        // Step 3: Connect previous group to reversed
        if (prev == null)
            head = reversedHead;
        else
            prev.next = reversedHead;

        // Step 4: Prepare for next group
        prev = temp;
        temp = nextNode;
    }

    return head;
}
```

---

### 🔧 Helper Functions

#### Find kth Node from a given node

```java
ListNode findKthNode(ListNode head, int k) {
    ListNode kthNode = head;
    k--;
    while (k > 0 && kthNode != null) {
        k--;
        kthNode = kthNode.next;
    }
    return kthNode;
}
```

#### Reverse a linked list recursively

```java
ListNode reverseList(ListNode head) {
    if (head == null || head.next == null) return head;
    ListNode newHead = reverseList(head.next);
    head.next.next = head;
    head.next = null;
    return newHead;
}
```

---

## 🧠 Complexity Analysis

| Metric    | Value                                                  |
| --------- | ------------------------------------------------------ |
| **Time**  | O(N)                                                   |
| **Space** | O(1) (auxiliary stack space for recursion, O(k) worst) |

---

## 🔄 Alternate Approaches

### 1. **Brute Force (ArrayList)**

* Store values in an array.
* Reverse every k group.
* Reconstruct list.
* ❌ Not allowed as node values cannot be changed.

### 2. **Iterative Reversal without Recursion**

* Same logic, but reverse list **iteratively** to avoid recursion stack.
* Saves memory in large lists.

```java
ListNode reverseList(ListNode head) {
    ListNode prev = null, curr = head;
    while (curr != null) {
        ListNode next = curr.next;
        curr.next = prev;
        prev = curr;
        curr = next;
    }
    return prev;
}
```

---

## ⚠️ Edge Cases

| Input          | Output  |
| -------------- | ------- |
| `[1], k = 1`   | `[1]`   |
| `[1,2], k = 3` | `[1,2]` |
| `[], k = 2`    | `[]`    |

---

## ✅ Summary Table

| Approach                 | Time | Space | Notes                        |
| ------------------------ | ---- | ----- | ---------------------------- |
| Recursive Group Reversal | O(N) | O(k)  | Your current approach        |
| Iterative Group Reversal | O(N) | O(1)  | Preferred for production use |
| ArrayList (Invalid)      | O(N) | O(N)  | Not allowed per constraints  |

---
