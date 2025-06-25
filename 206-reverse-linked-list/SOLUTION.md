## 🔍 Problem Summary

You are given the `head` of a singly linked list.
**Reverse** the list and return the **new head**.

---

## 🧠 Intuition

We want to **reverse the direction of the `next` pointers** in a linked list, ideally in **O(n)** time and **O(1)** space.

---

## ✅ Approach 1: Iterative Reversal (Optimal)

### 🔧 Code

```java
public ListNode reverseList(ListNode head) {
    ListNode prev = null;
    ListNode curr = head;

    while (curr != null) {
        ListNode nextNode = curr.next;
        curr.next = prev;
        prev = curr;
        curr = nextNode;
    }
    return prev;
}
```

### ⏱ Complexity

| Type  | Value |
| ----- | ----- |
| Time  | O(n)  |
| Space | O(1)  |

---

## 🔄 Dry Run

Input: `1 → 2 → 3 → 4 → 5 → null`

Steps:

* `prev = null`, `curr = 1`
* `next = 2`, `1.next = null`, `prev = 1`, `curr = 2`
* `next = 3`, `2.next = 1`, `prev = 2`, `curr = 3`
* ...

Final list: `5 → 4 → 3 → 2 → 1 → null`

---

## ✅ Approach 2: Recursive Reversal

### 🔧 Code

```java
public ListNode reverseList(ListNode head) {
    if (head == null || head.next == null)
        return head;
    ListNode newHead = reverseList(head.next);
    head.next.next = head;
    head.next = null;
    return newHead;
}
```

### ⏱ Complexity

| Type  | Value                         |
| ----- | ----------------------------- |
| Time  | O(n)                          |
| Space | O(n) – due to recursion stack |

---

## 🔁 Alternate (Brute-like) Idea

You could store all nodes in a list/stack and reverse their `.next` pointers by iterating over the list. But this is **not optimal**, and increases space complexity to **O(n)** unnecessarily.

---

## 🧪 Comparison Table

| Approach    | Time | Space | Notes                     |
| ----------- | ---- | ----- | ------------------------- |
| Iterative   | O(n) | O(1)  | ✅ Best option             |
| Recursive   | O(n) | O(n)  | Simple but uses stack     |
| Using Stack | O(n) | O(n)  | ❌ Extra space, not needed |

---

## ✅ Final Thoughts

* Use **iterative** approach in interviews or production.
* **Recursive** is elegant, especially for practicing recursion.
* Make sure to handle **null** or single node edge cases.
