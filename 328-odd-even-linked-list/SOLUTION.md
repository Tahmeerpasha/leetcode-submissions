## 🔍 Problem Summary

**Given:** A singly linked list
**Task:** Group all **odd-indexed** nodes together followed by **even-indexed** nodes (indexing starts at 1).

**Example:**

```
Input: 1 -> 2 -> 3 -> 4 -> 5  
Output: 1 -> 3 -> 5 -> 2 -> 4
```

---

## ✅ Brute Force Approach

### 🔧 Idea

* Create **two new linked lists**: one for odd indices and one for even.
* Traverse the input list and **copy values** to corresponding new lists.
* Connect the odd list's end to the head of the even list.

### 🔁 Code Sketch

```java
ListNode oddList = null, evenList = null;
ListNode currOdd = null, currEven = null;
int count = 0;
ListNode temp = head;

while (temp != null) {
    if (count % 2 == 0) {
        // Add to odd list
    } else {
        // Add to even list
    }
    count++;
    temp = temp.next;
}
```

### ⏱ Complexity

| Metric | Value                     |
| ------ | ------------------------- |
| Time   | O(n)                      |
| Space  | O(n)  (new nodes created) |

---

## 🚀 Optimal Approach (In-Place Reordering)

### 🔧 Idea

* Use **two pointers**:

  * `odd` to link odd-indexed nodes
  * `even` to link even-indexed nodes
* Store the head of even list (`evenHead`) to connect later.
* Traverse and **relink nodes in-place**.

### ✅ Code

```java
public ListNode oddEvenList(ListNode head) {
    if (head == null || head.next == null)
        return head;

    ListNode odd = head, even = head.next, evenHead = even;

    while (even != null && even.next != null) {
        odd.next = even.next;
        even.next = even.next.next;

        odd = odd.next;
        even = even.next;
    }

    odd.next = evenHead;
    return head;
}
```

---

## 🧪 Dry Run

### Input:

`1 -> 2 -> 3 -> 4 -> 5`

* Initial: `odd = 1`, `even = 2`, `evenHead = 2`
* After 1st loop:

  * `odd = 3`, `even = 4`
* After 2nd loop:

  * `odd = 5`, `even = null`
* Final step: `odd.next = evenHead`

### Output:

`1 -> 3 -> 5 -> 2 -> 4`

---

## ⏱ Time & Space Complexity

| Metric | Value             |
| ------ | ----------------- |
| Time   | O(n)              |
| Space  | O(1) ✅ (in-place) |

---

## 📌 Summary

| Approach    | Time | Space | Notes                     |
| ----------- | ---- | ----- | ------------------------- |
| Brute Force | O(n) | O(n)  | Creates new nodes         |
| Optimal     | O(n) | O(1)  | ✅ Best, modifies in-place |

---
