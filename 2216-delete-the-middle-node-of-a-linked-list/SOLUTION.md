## 🔍 Problem Summary

**Given:** A singly linked list
**Task:** Delete the **middle node** of the list

* If list has even length, remove the **second middle**
* Return the **head** of the updated list

---

## 🧠 Key Insight

Use **fast and slow pointers** to find the middle in one pass. Maintain a reference to the node before the middle to remove it.

---

## 🟠 Brute Force Approach

### 🔧 Idea

1. First pass to **count the number of nodes** `n`
2. Middle = `n / 2` (floor division)
3. Second pass: stop at `middle - 1` and remove the next node

### ⏱ Complexity

| Metric | Value |
| ------ | ----- |
| Time   | O(2n) |
| Space  | O(1)  |

---

## ✅ Optimal Approach – Fast & Slow Pointers

### 🔧 Idea

1. `slow` and `fast` start at head
2. `fast` moves 2 steps at a time; `slow` moves 1 step
3. When `fast` reaches the end, `slow` will be at the **previous node** of the middle
4. Remove `slow.next`

### ✅ Code

```java
public ListNode deleteMiddle(ListNode head) {
    if (head == null || head.next == null)
        return null;

    ListNode slow = head;
    ListNode fast = head.next.next;

    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
    }

    slow.next = slow.next.next;
    return head;
}
```

---

## 🧪 Dry Run

### Input:

`head = [1, 2, 3, 4, 5]`

* Fast → moves: 1→3→5
* Slow → moves: 1→2
* Remove `slow.next` → 3

### Output:

`[1, 2, 4, 5]`

---

## ⏱ Time & Space Complexity

| Metric | Value  |
| ------ | ------ |
| Time   | O(n)   |
| Space  | O(1) ✅ |

---

## 🔄 Edge Case Handling

* If `head == null || head.next == null`, return `null`
* If list size is 2: remove the second node (handled directly)

---

## 📝 Summary

| Approach | Time  | Space  | Notes                  |
| -------- | ----- | ------ | ---------------------- |
| Brute    | O(2n) | O(1)   | Count + remove         |
| Optimal  | O(n)  | O(1) ✅ | Fast and slow pointers |

---
