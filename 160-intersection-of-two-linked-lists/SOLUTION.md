## 🔍 Problem Statement

You are given the heads of two singly linked lists `headA` and `headB`.
Return the node at which the two lists intersect, or `null` if they do not intersect.

🔁 *Two lists intersect if they share a common node (by reference, not value).*

---

## ✅ Approaches

---

### 1. **Brute Force Approach**

#### 💡 Idea:

* Traverse `headA`, store each node in a `HashMap`.
* Traverse `headB`, check if any node exists in the map.

#### ⏱ Time: `O(m + n)`

#### 🧠 Space: `O(m)` for storing nodes from list A.

#### ✅ Code:

```java
Map<ListNode, Integer> map = new HashMap<>();
while (headA != null) {
    map.put(headA, 1);
    headA = headA.next;
}
while (headB != null) {
    if (map.containsKey(headB))
        return headB;
    headB = headB.next;
}
return null;
```

---

### 2. **Better Approach – Length Difference**

#### 💡 Idea:

* Calculate lengths of both lists.
* Move the pointer of the longer list ahead by `|lenA - lenB|` nodes.
* Now traverse both together and compare.

#### ⏱ Time: `O(m + n)`

#### 🧠 Space: `O(1)`

#### ✅ Code Steps:

```java
// Step 1: Calculate lengths
int lenA = getLength(headA);
int lenB = getLength(headB);

// Step 2: Align heads
while (lenA > lenB) {
    headA = headA.next;
    lenA--;
}
while (lenB > lenA) {
    headB = headB.next;
    lenB--;
}

// Step 3: Compare step by step
while (headA != null && headB != null) {
    if (headA == headB)
        return headA;
    headA = headA.next;
    headB = headB.next;
}
return null;
```

---

### ✅ 3. Optimal Approach – Two Pointer Trick

#### 💡 Idea:

* Use two pointers (`temp1` on A and `temp2` on B).
* Move both one step at a time.
* When one pointer reaches the end, redirect it to the other list’s head.
* Eventually, both pointers will either meet at the intersection or reach `null`.

#### ⏱ Time: `O(m + n)`

#### 🧠 Space: `O(1)`

💡 **No need to calculate lengths!**

#### ✅ Code:

```java
ListNode temp1 = headA;
ListNode temp2 = headB;

while (temp1 != temp2) {
    temp1 = temp1 == null ? headB : temp1.next;
    temp2 = temp2 == null ? headA : temp2.next;
}
return temp1;
```

---

## 🧠 Why Does the Optimal Approach Work?

Both pointers traverse `m + n` nodes:

* If the lists intersect, the pointers will sync at the intersection node.
* If not, both reach `null` at the same time and loop ends.

---

## 🧮 Time & Space Summary

| Approach              | Time     | Space |
| --------------------- | -------- | ----- |
| Brute Force           | O(m + n) | O(m)  |
| Better (Length Align) | O(m + n) | O(1)  |
| ✅ Optimal (2 Ptrs)    | O(m + n) | O(1)  |

---

## 📌 Tips

* **Important for interviews**: 2-pointer technique is elegant and space-efficient.
* Works only if the intersection is by reference, not by value.

---
