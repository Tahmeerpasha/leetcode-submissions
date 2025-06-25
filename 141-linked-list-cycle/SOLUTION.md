## 🔍 Problem Summary

You are given the head of a linked list.
**Determine if the linked list has a cycle** (i.e., a node that links back to a previous node in the list).

---

## 🧠 Intuition

If a cycle exists, a **fast-moving pointer** will eventually meet a **slow-moving pointer** inside the cycle.

---

## ✅ Approach 1: Brute Force using HashMap

### 🔧 Code

```java
public boolean hasCycle(ListNode head) {
    Set<ListNode> visited = new HashSet<>();
    while (head != null) {
        if (visited.contains(head))
            return true;
        visited.add(head);
        head = head.next;
    }
    return false;
}
```

### ⏱ Complexity

| Type  | Value |
| ----- | ----- |
| Time  | O(n)  |
| Space | O(n)  |

---

## ⚠️ Rejected Approach: Modifying Node Values

You attempted this:

```java
while (temp != null && temp.val != MIN) {
    temp.val = MIN;
    temp = temp.next;
}
```

### ❌ Why not use this?

* **Modifies input**, which is not allowed in most interview questions.
* **Not reliable** if values like `Integer.MIN_VALUE` could be in input.
* Fails if list nodes have arbitrary values or duplicate `MIN`.

---

## ✅ Approach 2: Floyd’s Cycle Detection Algorithm (Optimal)

Also called **Tortoise and Hare** approach.

### 🔧 Code

```java
public boolean hasCycle(ListNode head) {
    ListNode slow = head, fast = head;
    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
        if (slow == fast)
            return true;
    }
    return false;
}
```

### ⏱ Complexity

| Type  | Value  |
| ----- | ------ |
| Time  | O(n)   |
| Space | O(1) ✅ |

---

## 🔄 Dry Run

### Input:

List = `3 → 2 → 0 → -4 ↘`
                 ↖

* Initially: `slow = 3`, `fast = 3`
* Iteration 1: `slow = 2`, `fast = 0`
* Iteration 2: `slow = 0`, `fast = 2`
* Iteration 3: `slow = -4`, `fast = -4` → **Match** → `true`

---

## 🧪 Comparison Table

| Approach          | Time | Space | Notes                            |
| ----------------- | ---- | ----- | -------------------------------- |
| HashMap (Brute)   | O(n) | O(n)  | Simple but uses extra space      |
| Floyd’s Algorithm | O(n) | O(1)  | ✅ Best approach – no space used  |
| Modifying Values  | O(n) | O(1)  | ❌ Not allowed in real interviews |

---

## ✨ Final Tips

* Use Floyd’s algorithm in **all interviews**.
* Don't modify input nodes unless the problem **explicitly** allows it.
* You can extend this to find the **starting point** of the cycle (in Leetcode 142).
