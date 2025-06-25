## 🔍 Problem Summary

> Given the `head` of a singly linked list, return `true` if it is a **palindrome**, otherwise return `false`.

---

## 🧠 Intuition

A palindrome reads the same forwards and backwards. We need to compare the **first half** with the **reversed second half** of the list.

---

## ✅ Brute Force Approach

### 🔧 Idea

* Store all values in a **stack** or **array/list**.
* Traverse the list again and compare each node with its corresponding value from the stack.

### 🧾 Code (using stack)

```java
public boolean isPalindrome(ListNode head) {
    Stack<Integer> st = new Stack<>();
    ListNode temp = head;
    
    while (temp != null) {
        st.push(temp.val);
        temp = temp.next;
    }
    
    temp = head;
    while (temp != null) {
        if (temp.val != st.pop())
            return false;
        temp = temp.next;
    }
    return true;
}
```

### ⏱ Complexity

| Metric | Value |
| ------ | ----- |
| Time   | O(2N) |
| Space  | O(N)  |

---

## 🚀 Optimal Approach (Reverse Second Half)

### 🔧 Idea

1. Use **fast and slow** pointers to find the middle of the list.
2. Reverse the **second half** of the list.
3. Compare the first half and the reversed second half.
4. (Optional) Restore the reversed part.

### ✅ Code

```java
public boolean isPalindrome(ListNode head) {
    if (head == null || head.next == null)
        return true;

    // Step 1: Find mid
    ListNode slow = head, fast = head;
    while (fast.next != null && fast.next.next != null) {
        slow = slow.next;
        fast = fast.next.next;
    }

    // Step 2: Reverse second half
    ListNode secondHalfHead = reverseList(slow.next);

    // Step 3: Compare
    ListNode firstHalfHead = head;
    ListNode temp = secondHalfHead;
    while (temp != null) {
        if (firstHalfHead.val != temp.val)
            return false;
        firstHalfHead = firstHalfHead.next;
        temp = temp.next;
    }

    // Step 4: Optional - Restore the list
    // slow.next = reverseList(secondHalfHead);

    return true;
}

private ListNode reverseList(ListNode head) {
    ListNode prev = null, curr = head;
    while (curr != null) {
        ListNode next = curr.next;
        curr.next = prev;
        prev = curr;
        curr = next;
    }
    return prev;
}
```

---

## 💡 Dry Run

List: `1 → 2 → 2 → 1`

* `slow` stops at first `2`.
* Reverse second half: `2 → 1` becomes `1 → 2`.
* Compare first half: `1 → 2` and second half: `1 → 2` → ✅ Match

---

## ⏱ Time & Space Complexity

| Metric | Value  |
| ------ | ------ |
| Time   | O(N)   |
| Space  | O(1) ✅ |

---

## 📊 Comparison Table

| Approach      | Time | Space | Notes                  |
| ------------- | ---- | ----- | ---------------------- |
| Stack (Brute) | O(N) | O(N)  | Uses extra memory      |
| Reverse Half  | O(N) | O(1)  | ✅ Best space-efficient |

---

## 📝 Notes

* For interviews, always aim for the **reverse-half** method.
* Optional restoration of the list is good for preserving input data, but not always necessary.
