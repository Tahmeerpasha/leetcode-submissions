## 🔍 Problem Statement

Given the `head` of a **sorted linked list**, delete all nodes that have **duplicate numbers**, leaving only distinct numbers from the original list. Return the linked list **sorted as well**.

---

## 🔢 Example

**Input:** `1 -> 2 -> 3 -> 3 -> 4 -> 4 -> 5`
**Output:** `1 -> 2 -> 5`

**Input:** `1 -> 1 -> 1 -> 2 -> 3`
**Output:** `2 -> 3`

---

## ✅ Optimal Approach (Two-Pointer with Dummy Node)

### 💡 Idea:

* Use a **dummy node** pointing to head to easily manage changes at the head.
* Use two pointers:

  * `prevNode`: last confirmed distinct node.
  * `currNode`: the current node being checked.
* If duplicates are detected, **skip the entire duplicate group**.

---

### ✅ Code:

```java
public ListNode deleteDuplicates(ListNode head) {
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode prevNode = dummy, currNode = head;

    while (currNode != null) {
        // Check if current node is a duplicate
        if (currNode.next != null && currNode.val == currNode.next.val) {
            int duplicateVal = currNode.val;
            // Skip all nodes with this value
            while (currNode != null && currNode.val == duplicateVal) {
                currNode = currNode.next;
            }
            // Connect prevNode to next distinct node
            prevNode.next = currNode;
        } else {
            prevNode = currNode;
            currNode = currNode.next;
        }
    }
    return dummy.next;
}
```

---

### ✅ Time Complexity: `O(n)`

Each node is visited at most twice.

### 🧠 Space Complexity: `O(1)`

Only pointer variables used.

---

## ✅ Brute Force (Convert to list + frequency map)

### ❌ Not preferred

* Traverse and count frequencies.
* Rebuild list with only freq == 1 nodes.
* **Extra space** used.

---

## 🔁 Dry Run

### Input: `1 → 1 → 2 → 3 → 3`

* `dummy → 1 → 1 → 2 → 3 → 3`
* `currNode = 1` (duplicate group found)
* Skip 1s → now at 2
* `prevNode.next = 2`
* `currNode = 2`, no duplicate → move both
* `currNode = 3` (duplicate found) → skip to null
* `prevNode.next = null`

✅ Final list: `2`

---

## ✅ Edge Cases

| Input       | Output  |
| ----------- | ------- |
| `[]`        | `[]`    |
| `[1,1,1]`   | `[]`    |
| `[1,2,2]`   | `[1]`   |
| `[1,1,2,3]` | `[2,3]` |

---

## ✅ Summary Table

| Approach                      | Time | Space | Notes                     |
| ----------------------------- | ---- | ----- | ------------------------- |
| Optimal (Two-pointer + dummy) | O(n) | O(1)  | Handles all cases cleanly |

---
