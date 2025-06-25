## 🔍 Problem Summary

**Given:** A singly linked list and an integer `n`.
**Task:** Remove the **n-th node from the end** of the list and return the head.

### 🧠 Key Insight

Removing the nth node from the end is equivalent to removing the (length - n + 1)-th node from the start.

---

## 🟠 Brute Force Approach

### 🔧 Idea

1. Count the total number of nodes (`length`) – `O(n)`
2. Find the position to delete from start → `length - n`
3. Traverse again to that position and remove the node

### 🔁 Code Sketch

```java
int length = 0;
ListNode temp = head;
while (temp != null) {
    length++;
    temp = temp.next;
}

int pos = length - n;
if (pos == 0) return head.next;

temp = head;
for (int i = 1; i < pos; i++) {
    temp = temp.next;
}
temp.next = temp.next.next;
```

### ⏱ Complexity

| Metric | Value |
| ------ | ----- |
| Time   | O(2n) |
| Space  | O(1)  |

---

## 🟢 Optimal Approach (Two Pointer Technique)

### 🔧 Idea

1. Use two pointers: `fast` and `slow`
2. Move `fast` ahead by `n` steps
3. Then move both `fast` and `slow` one step at a time until `fast` reaches the end
4. `slow` will now point to the node before the one to delete

### ✅ Code

```java
public ListNode removeNthFromEnd(ListNode head, int n) {
    ListNode fast = head, slow = head;

    // Move fast n steps ahead
    for (int i = 1; i <= n; i++) {
        fast = fast.next;
    }

    // If fast reached end, delete the head
    if (fast == null)
        return head.next;

    // Move both pointers
    while (fast.next != null) {
        fast = fast.next;
        slow = slow.next;
    }

    // Remove the target node
    slow.next = slow.next.next;
    return head;
}
```

---

## 🧪 Dry Run Example

### Input:

`head = [1,2,3,4,5], n = 2`
Target: Remove `4`

### Steps:

* Fast moves 2 steps → points to 3
* Move fast and slow together:

  * fast → 4, slow → 1
  * fast → 5, slow → 2
  * fast reaches end, slow at 3
* `slow.next = slow.next.next` → 3 skips 4

### Output:

`[1,2,3,5]`

---

## ⏱ Time & Space Complexity

| Metric | Value  |
| ------ | ------ |
| Time   | O(n)   |
| Space  | O(1) ✅ |

---

## ✅ Summary

| Approach | Time  | Space | Notes                       |
| -------- | ----- | ----- | --------------------------- |
| Brute    | O(2n) | O(1)  | Two passes                  |
| Optimal  | O(n)  | O(1)  | ✅ Single pass, two pointers |

---
