## 🔍 Problem Statement

You're given the head of a linked list and an integer `val`.
Remove **all nodes** of the linked list that have `val` as their value, and return the **new head**.

---

## 🔢 Example

**Input:**
`head = [1,2,6,3,4,5,6]`, `val = 6`
**Output:**
`[1,2,3,4,5]`

---

## ✅ Optimal Iterative Approach

### 💡 Idea:

* Use two pointers: `prev` and `curr`.
* Traverse the list, and whenever `curr.val == val`, skip that node.

> Special care is needed if the **head itself** contains the target value.

### ✅ Time Complexity: `O(n)`

### 🧠 Space Complexity: `O(1)`

### ✅ Code:

```java
public ListNode removeElements(ListNode head, int val) {
    ListNode dummy = new ListNode(-1);
    dummy.next = head;
    ListNode curr = dummy;

    while (curr.next != null) {
        if (curr.next.val == val) {
            curr.next = curr.next.next;
        } else {
            curr = curr.next;
        }
    }

    return dummy.next;
}
```

> ✅ Using a dummy node handles edge cases where the `head` itself needs to be removed.

---

## 🔁 Recursive Approach (Used in Your Code)

### ✅ Time: `O(n)`

### 🧠 Space: `O(n)` (due to recursive call stack)

### ✅ Code:

```java
public ListNode removeElements(ListNode head, int val) {
    if (head == null)
        return null;
    head.next = removeElements(head.next, val);
    return head.val == val ? head.next : head;
}
```

### 🔄 How it works:

* Traverse till the end.
* Rebuild the list while skipping nodes with `val`.

---

## 🚫 Brute Force (Not needed for this problem)

* Convert to array or list, filter, and rebuild the list.
* Extra space usage makes it suboptimal.

---

## ✅ Edge Cases

| Case                        | Handled? |
| --------------------------- | -------- |
| Head node is to be deleted  | ✅ Yes    |
| All nodes match `val`       | ✅ Yes    |
| No node matches `val`       | ✅ Yes    |
| Empty list (`head == null`) | ✅ Yes    |

---

## 🧠 Dry Run

### Input:

`head = [6, 1, 2, 6, 3], val = 6`

### Iterative with Dummy:

```
dummy -> 6 -> 1 -> 2 -> 6 -> 3

Step 1: 6 → removed (dummy -> 1)
Step 2: 1 kept
Step 3: 2 kept
Step 4: 6 → removed
Final: [1,2,3]
```

---

## ✅ Summary Table

| Approach  | Time | Space | Notes                       |
| --------- | ---- | ----- | --------------------------- |
| Iterative | O(n) | O(1)  | Most efficient and clean    |
| Recursive | O(n) | O(n)  | Elegant but uses call stack |

---
