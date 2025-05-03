### ✅ **Problem Statement:**

Given a 2D matrix, return all elements in **spiral order** (clockwise traversal).

---

### ✅ **Approach:**

We maintain 4 boundaries and update them as we traverse in layers:

* `top`: starting row
* `bottom`: ending row
* `left`: starting column
* `right`: ending column

We traverse in 4 steps:

1. Left ➝ Right → move across the top row
2. Top ⬇ Bottom → move down the right column
3. Right ⬅ Left → move across the bottom row (only if `top <= bottom`)
4. Bottom ⬆ Top → move up the left column (only if `left <= right`)

---

### ✅ **Time and Space Complexity:**

* **Time**: O(m × n), where `m` = rows, `n` = columns.
* **Space**: O(1) (excluding output list)

---

### ✅ **Dry Run Example:**

**Input**:

```java
matrix = [
 [1, 2, 3],
 [4, 5, 6],
 [7, 8, 9]
]
```

**Step-by-step traversal:**

* Top row: 1, 2, 3
* Right col: 6, 9
* Bottom row: 8, 7
* Left col: 4
* Inner top row: 5

**Output**: `[1, 2, 3, 6, 9, 8, 7, 4, 5]`

---
