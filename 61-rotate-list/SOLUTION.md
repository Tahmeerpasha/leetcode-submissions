## 🔍 Problem Statement

Given the head of a linked list and an integer `k`, rotate the list to the right by `k` places.

---

## 🔢 Examples

### Input:

`head = [1,2,3,4,5], k = 2`

### Output:

`[4,5,1,2,3]`

### Input:

`head = [0,1,2], k = 4`

### Output:

`[2,0,1]`

---

## ✅ Approach Summary

### 💡 Optimal Idea:

* Make the list **circular**.
* Break it at the correct point to form the rotated list.

---

## 🔄 Optimal Solution Breakdown

### Step-by-step:

1. Count the length of the list (`len`).
2. Connect the last node to the head (make it circular).
3. Calculate effective rotations: `k = k % len`.
4. Traverse to `len - k` nodes to find the new tail.
5. Break the cycle at that point and set the new head.

---

## ✅ Optimal Code Walkthrough:

```java
public ListNode rotateRight(ListNode head, int k) {
    if (head == null || head.next == null)
        return head;

    // Step 1: Find the length and last node
    int len = 1;
    ListNode last = head;
    while (last.next != null) {
        last = last.next;
        len++;
    }

    // Step 2: Reduce k
    k = k % len;
    if (k == 0)
        return head;

    // Step 3: Find new tail (len - k)th node
    int newLastCount = len - k;
    ListNode newLastNode = head;
    for (int i = 1; i < newLastCount; i++) {
        newLastNode = newLastNode.next;
    }

    // Step 4: Make the list circular and break it
    ListNode newHead = newLastNode.next;
    newLastNode.next = null;
    last.next = head;

    return newHead;
}
```

---

## 🧠 Complexity Analysis

| Metric    | Value |
| --------- | ----- |
| **Time**  | O(N)  |
| **Space** | O(1)  |

---

## 🚫 Brute Force Method (Repeated Shifting)

```java
ListNode rotateKTimes(ListNode head, int k) {
    ListNode prev = null, temp = head;
    while (k > 0) {
        while (temp.next != null) {
            prev = temp;
            temp = temp.next;
        }
        prev.next = null;
        temp.next = head;
        head = temp;
        k--;
    }
    return head;
}
```

### ❌ Time: O(k \* N)

* Not optimal when `k` is large.

---

## ⚠️ Edge Cases

| Case                        | Result                  |
| --------------------------- | ----------------------- |
| Empty list (`head == null`) | `null`                  |
| Single node list            | Same node               |
| `k = 0` or `k % len == 0`   | No rotation (same list) |

---

## 🔄 Summary Table

| Approach                  | Time  | Space | Comment                |
| ------------------------- | ----- | ----- | ---------------------- |
| Brute (rotate every time) | O(kN) | O(1)  | Too slow for large `k` |
| Optimal (circular break)  | O(N)  | O(1)  | ✅ Best approach        |

---
