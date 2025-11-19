# ✅ **Zigzag Level Order Traversal — Notes (Brute → Better → Optimal)**

## 🎯 **Problem Summary**

You need to return the **level order traversal**, but the direction alternates:

* Level 0 → left to right
* Level 1 → right to left
* Level 2 → left to right
  … and so on.

---

# 🥉 **Brute Force Idea**

**Approach:**

1. Do a **normal BFS level-order traversal** (left → right always).
2. For every odd level, manually **reverse** the level list before pushing into result.

### ✔️ Code Idea

```java
while(!queue.isEmpty()) {
    List<Integer> level = new ArrayList<>();
    // collect normally
    if(levelNumber % 2 == 1) Collections.reverse(level);
}
```

### ⏱️ Time Complexity

* BFS = **O(N)**
* Reversing each odd level worst case = **O(N)**
* Worst-case total: **O(N)** actually (each node visited once + limited reversals)

### 💾 Space Complexity

* **O(N)** for queue + result.

### 🔥 Downsides

* Unnecessary reversal operations → extra cost.
* Not the cleanest.

---

# 🥈 **Better Approach: BFS + Insert at Ends (Your Solution)**

This avoids reversing.

**Key idea:**
Use a **Deque<Integer>** to build the level:

* `leftToRight`? → `addLast()`
* else → `addFirst()`

So direction is handled *on the fly*.

### ✔️ Why it’s better?

* No post-processing or reversal.
* Time strictly linear.

### ⏱️ Time Complexity

* **O(N)** (every node processed once)

### 💾 Space Complexity

* **O(N)** (queue + deque + result)

This is the accepted **optimal** BFS approach.

---

# 🥇 **Optimal Approach (Alternate Style): Using Two Stacks**

Some interviewers like to see this pattern.

## **Two-stack zigzag**

* Stack 1 → left-to-right
* Stack 2 → right-to-left
  Swap stacks each level.

### ✔️ Logic

* When popping from stack1: push children **left then right**
* When popping from stack2: push children **right then left**
  This naturally gives zigzag without extra flags or deques.

### ⏱️ Time Complexity

* **O(N)**

### 💾 Space Complexity

* **O(N)**

### 👍 When to use?

* When interviewer says “avoid queue”
* When interviewer wants a **DFS-like** stack manipulation trick.

---

# 🧠 **Interview Tips & Tricks (Important for Trees)**

### ✔️ 1. Always think **Level Order = BFS using Queue**

Most zigzag/vertical/level problems boil down to BFS.

### ✔️ 2. Zigzag means **direction flip each level**

Two common patterns:

* Use flag + deque (your solution)
* Use two stacks

Both are valid. Deque is simpler, but two-stack impresses some interviewers.

### ✔️ 3. Don’t store direction inside the queue

Keep it separately (boolean flag). Cleaner & expected.

### ✔️ 4. Avoid reversing unless asked explicitly

Reversal triggers unnecessary operations. Deque is cleaner.

### ✔️ 5. Watch out for null checks

Don’t push null children into the queue.

---

# 🧩 **Patterns This Problem Belongs To**

This is part of the **"Level Order Variations"** pattern — common in product-based interviews:

1. **Normal Level Order**
2. **Reverse Level Order**
3. **Zigzag Level Order**
4. **Vertical Order**
5. **Right/Left View**
6. **Level Order Averages**

If you master the BFS skeleton, 20+ tree questions automatically become easy.

---
