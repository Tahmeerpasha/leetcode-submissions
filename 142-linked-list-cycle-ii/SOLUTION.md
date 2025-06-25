## 🔍 Problem Summary

> Given a linked list, return the node where the **cycle begins**. If there is no cycle, return `null`.

You must **not modify** the linked list.

---

## 🧠 Intuition

If two pointers meet inside the cycle (Floyd’s Cycle Detection), you can reset one pointer to the head and move both one step at a time to find the **start of the cycle**.

---

## ✅ Approach 1: Brute Force Using HashMap

### 🔧 Code

```java
public ListNode detectCycle(ListNode head) {
    Set<ListNode> visited = new HashSet<>();
    while (head != null) {
        if (visited.contains(head))
            return head;
        visited.add(head);
        head = head.next;
    }
    return null;
}
```

### ⏱ Complexity

| Type  | Value |
| ----- | ----- |
| Time  | O(n)  |
| Space | O(n)  |

---

## ✅ Approach 2: Floyd’s Tortoise and Hare (Optimal)

### 🔧 Code

```java
public ListNode detectCycle(ListNode head) {
    ListNode slow = head, fast = head;

    // Step 1: Detect cycle
    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;

        if (slow == fast) {
            // Step 2: Find start of cycle
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
```

---

## 🤔 Why does this work?

Let:

* `L` = distance from head to cycle start
* `C` = cycle length
* `x` = distance moved by slow before meeting fast

When they meet, fast has traveled twice as far as slow:

```
2L + 2x = L + x + nC  (n = number of full cycle laps by fast)
=> L + x = nC
=> L = nC - x
```

Now moving both from `head` and `meeting point`, they meet at the cycle start after `L` steps.

---

## 🔄 Dry Run

Cycle: `3 → 2 → 0 → -4 ↘`
                  ↖

* Phase 1: detect cycle

  * slow = 0, fast = 0 → fast = -4 → match
* Phase 2: find cycle start

  * slow = 3, fast = -4
  * move both → meet at node 2 → return node 2

---

## ⏱ Complexity

| Type  | Value  |
| ----- | ------ |
| Time  | O(n)   |
| Space | O(1) ✅ |

---

## 🧪 Comparison Table

| Approach        | Time | Space | Notes                            |
| --------------- | ---- | ----- | -------------------------------- |
| HashMap (Brute) | O(n) | O(n)  | Simple but uses extra memory     |
| Floyd’s (Opt.)  | O(n) | O(1)  | ✅ Best approach – No extra space |

---

## ✅ Final Notes

* Always use Floyd’s algorithm in interviews.
* Only return the **node where the cycle starts**, not just a boolean.
* **Don't use brute** unless explicitly allowed or needed for verification.
