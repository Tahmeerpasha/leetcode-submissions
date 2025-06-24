## 🔍 Problem Summary

Given the head of a singly linked list, return **the middle node** of the linked list.
If there are two middle nodes, return **the second** one.

---

## 🧠 Intuition

This is a classic case of using **slow and fast pointers**:

* `slow` moves one step at a time.
* `fast` moves two steps at a time.
  When `fast` reaches the end, `slow` will be at the middle.

---

## ✅ Optimal Approach (Two-Pointer)

```java
public ListNode middleNode(ListNode head) {
    ListNode fast = head;
    ListNode slow = head;

    while (fast != null && fast.next != null) {
        fast = fast.next.next;
        slow = slow.next;
    }
    return slow;
}
```

### Why it works:

* On odd-length lists: `slow` lands on the middle.
* On even-length lists: `slow` lands on the second middle (as required by the problem).

---

## 🔄 Dry Run

Input: `1 → 2 → 3 → 4 → 5 → 6`

Steps:

* Iteration 1: `slow = 2`, `fast = 3`
* Iteration 2: `slow = 3`, `fast = 5`
* Iteration 3: `slow = 4`, `fast = null`
  ➡️ **Return 4**

---

## ⏱ Time and Space Complexity

| Type      | Complexity |
| --------- | ---------- |
| **Time**  | O(n)       |
| **Space** | O(1)       |

Only one pass is required, with constant space.

---

## 🧪 Alternate Approaches

### 1. **Brute Force (Count Length)**

* First pass: Count the length `n`.
* Second pass: Traverse to `n/2`-th node.

```java
int count = 0;
ListNode temp = head;
while (temp != null) {
    count++;
    temp = temp.next;
}
temp = head;
for (int i = 0; i < count / 2; i++) {
    temp = temp.next;
}
return temp;
```

**Time:** O(n)
**Space:** O(1)

---

## ✅ Summary

| Approach         | Time | Space | Comments                            |
| ---------------- | ---- | ----- | ----------------------------------- |
| Two-Pointer      | O(n) | O(1)  | ✅ Optimal and elegant               |
| Count + Traverse | O(n) | O(1)  | Easy to understand, needs two loops |

---
