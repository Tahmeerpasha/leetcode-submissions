## 🔍 Problem Summary

**Given:** A singly linked list
**Goal:** Sort the list in ascending order and return its head

---

## 🟠 Brute Force Approach

### 🔧 Idea

1. Traverse and store all node values in an array/list
2. Sort the array
3. Re-assign the values to the original linked list

### ✅ Code Snippet

```java
ArrayList<Integer> list = new ArrayList<>();
ListNode temp = head;
while (temp != null) {
    list.add(temp.val);
    temp = temp.next;
}
Collections.sort(list);
temp = head;
int i = 0;
while (temp != null) {
    temp.val = list.get(i++);
    temp = temp.next;
}
```

### ⏱ Complexity

| Metric | Value                      |
| ------ | -------------------------- |
| Time   | O(n log n) (sorting array) |
| Space  | O(n) (for the list) ❌      |

---

## ✅ Optimal Approach – Merge Sort on Linked List

### 🔧 Idea

Use **merge sort** which naturally fits linked lists:

1. **Divide** the list into two halves using the middle node (fast & slow pointer)
2. **Sort** each half recursively
3. **Merge** the two sorted halves

### ✅ Code Breakdown

```java
public ListNode sortList(ListNode head) {
    if (head == null || head.next == null)
        return head;

    ListNode mid = findMiddleOfLL(head);
    ListNode left = head;
    ListNode right = mid.next;
    mid.next = null;

    ListNode leftSorted = sortList(left);
    ListNode rightSorted = sortList(right);

    return mergeTwoLists(leftSorted, rightSorted);
}
```

### 🔧 Helper 1 – Merge Two Sorted Lists

```java
ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    ListNode dummy = new ListNode(-1);
    ListNode current = dummy;

    while (l1 != null && l2 != null) {
        if (l1.val <= l2.val) {
            current.next = l1;
            l1 = l1.next;
        } else {
            current.next = l2;
            l2 = l2.next;
        }
        current = current.next;
    }

    current.next = (l1 != null) ? l1 : l2;
    return dummy.next;
}
```

### 🔧 Helper 2 – Find Middle Node

```java
ListNode findMiddleOfLL(ListNode head) {
    ListNode slow = head, fast = head.next;
    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
    }
    return slow;
}
```

---

## ⏱ Time & Space Complexity

| Metric | Value                             |
| ------ | --------------------------------- |
| Time   | O(n log n) ✅                      |
| Space  | O(log n) (recursion stack only) ✅ |

---

## 🧪 Dry Run

### Input: `4 -> 2 -> 1 -> 3`

Split:

* Left: `4 -> 2` → Sorted to `2 -> 4`
* Right: `1 -> 3` → Sorted to `1 -> 3`
* Final merge → `1 -> 2 -> 3 -> 4`

---

## 📝 Summary

| Approach           | Time       | Space    | Notes                                  |
| ------------------ | ---------- | -------- | -------------------------------------- |
| Brute Force        | O(n log n) | O(n)     | Uses extra space                       |
| Optimal Merge Sort | O(n log n) | O(log n) | In-place, recursive divide and merge ✅ |

---
