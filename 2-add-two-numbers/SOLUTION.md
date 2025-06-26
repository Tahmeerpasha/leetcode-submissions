## 🔍 Problem Statement

You're given two non-empty linked lists representing two non-negative integers.
The digits are stored in **reverse order**, and each of their nodes contains a single digit.
Add the two numbers and return the sum as a **linked list**, also in reverse order.

---

## ✅ Constraints

* No need to handle negative numbers.
* Inputs are guaranteed to not contain leading zeros.
* Lengths of the two linked lists can be different.

---

## ✨ Intuition

Just like we add numbers digit-by-digit from right to left (units → tens → hundreds),
we do the same here since the least significant digit is at the head of the list.

---

## ✅ Optimal Approach (Used in Your Code)

### 💡 Idea:

* Use a **dummy node** to build the result list.
* Iterate through both lists and add corresponding digits and carry.
* At the end, if there's any leftover `carry`, add it as a new node.

### ⏱ Time Complexity: `O(max(m, n))`

* Where `m` and `n` are the lengths of the two input lists.

### 🧠 Space Complexity: `O(1)` auxiliary (output list doesn’t count).

### ✅ Code:

```java
public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    ListNode dummyNode = new ListNode(-1);
    ListNode curr = dummyNode;
    int carry = 0;

    while (l1 != null || l2 != null) {
        int sum = carry;
        if (l1 != null) {
            sum += l1.val;
            l1 = l1.next;
        }
        if (l2 != null) {
            sum += l2.val;
            l2 = l2.next;
        }

        carry = sum / 10;
        curr.next = new ListNode(sum % 10);
        curr = curr.next;
    }

    if (carry > 0) {
        curr.next = new ListNode(carry);
    }

    return dummyNode.next;
}
```

---

## 🧱 Other Approaches

### 1. **Brute Force (Convert to Integer)** ❌ *Not feasible for large inputs*

* Convert both lists to integers.
* Add and convert the result back to a linked list.
* Fails for large numbers due to overflow.

### ⏱ Time: O(m + n)

### 🧠 Space: O(1)

⚠️ Not recommended due to integer size constraints.

---

## 🧠 Dry Run Example

```
l1 = 2 → 4 → 3   (342)
l2 = 5 → 6 → 4   (465)
-------------------------
Result: 7 → 0 → 8   (807)
```

* 2 + 5 = 7
* 4 + 6 = 10 → Write 0, carry 1
* 3 + 4 + 1 = 8 → Done!

---

## ✅ Edge Cases

* One list is longer than the other
* Final carry > 0 (e.g., 9 → 9 + 1 = 0 → 0 → 1)
* One list is `null`
* Both lists are `null` (though constraints guarantee non-empty)

---

## Summary Table

| Scenario               | Explanation                          |
| ---------------------- | ------------------------------------ |
| Different lengths      | Handled by checking both temp1/temp2 |
| Carry at the end       | Handled by final carry check         |
| Null input (edge case) | Should be guarded before method call |

---
