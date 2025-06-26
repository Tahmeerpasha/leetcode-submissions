## 🔍 Problem Summary

**Given:** Two **sorted** singly linked lists
**Goal:** Merge them into a single sorted linked list and return its head.

---

## 🧠 Brute Force (Not Implemented Separately)

> Convert both lists to arrays, merge them, sort, and then create a new list.

* ❌ Loses the benefit of linked list structure
* ❌ Extra space required
* Not recommended when you’re given already **sorted lists**

---

## ✅ Optimal Approach – Iterative Merging (Two Pointers)

### 🔧 Intuition

Use **two pointers** (`list1` and `list2`) and a **dummy node** to build the result in one pass:

* Start with a dummy node to easily return the head later.
* Compare `list1.val` and `list2.val`
* Append the smaller node to the result
* Move forward in the respective list
* After loop, if any list has remaining nodes, attach them

---

### ✅ Code

```java
class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummyNode = new ListNode(-1);
        ListNode temp = dummyNode;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                temp.next = list1;
                list1 = list1.next;
            } else {
                temp.next = list2;
                list2 = list2.next;
            }
            temp = temp.next;
        }

        // Attach the remaining nodes
        temp.next = (list1 != null) ? list1 : list2;

        return dummyNode.next;
    }
}
```

---

## 🔄 Recursive Approach (Also Optimal)

```java
public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    if (l1 == null) return l2;
    if (l2 == null) return l1;

    if (l1.val < l2.val) {
        l1.next = mergeTwoLists(l1.next, l2);
        return l1;
    } else {
        l2.next = mergeTwoLists(l1, l2.next);
        return l2;
    }
}
```

---

## ⏱ Time & Space Complexity

| Metric | Iterative | Recursive                         |
| ------ | --------- | --------------------------------- |
| Time   | O(n + m)  | O(n + m)                          |
| Space  | O(1)      | O(n + m) stack (due to recursion) |

---

## 🧪 Example Dry Run

**Input:**
`list1 = 1 -> 2 -> 4`
`list2 = 1 -> 3 -> 4`

**Result:**
`1 -> 1 -> 2 -> 3 -> 4 -> 4`

---

## 📝 Summary

| Approach  | Time       | Space    | Notes                               |
| --------- | ---------- | -------- | ----------------------------------- |
| Brute     | O(n log n) | O(n)     | Not efficient for LL                |
| Iterative | O(n + m)   | O(1)     | Best for in-place merging ✅         |
| Recursive | O(n + m)   | O(n + m) | Cleaner, but risk of stack overflow |

---
