### 🔍 Problem Summary:

You are given two `n x n` matrices, `mat` and `target`. You need to determine whether `target` can be obtained by rotating `mat` 0°, 90°, 180°, or 270° **clockwise**.

---

### ✅ Key Idea:

Compare the original matrix `mat` with `target` under all 4 possible rotation scenarios:

* 0° (no rotation)
* 90° clockwise
* 180° clockwise
* 270° clockwise
  If `mat` matches `target` after any of these rotations, return `true`.

---

### 🔁 Rotations Logic:

| Rotation | Formula (target\[i]\[j]) to match `mat[i][j]` |
| -------- | --------------------------------------------- |
| 0°       | `target[i][j]`                                |
| 90°      | `target[n - j - 1][i]`                        |
| 180°     | `target[n - i - 1][n - j - 1]`                |
| 270°     | `target[j][n - i - 1]`                        |

---

### 💡 Implementation Summary:

* Initialize counters for 0°, 90°, 180°, and 270° rotations.
* Iterate over every cell in the `mat` and compare it with the appropriate rotated position in `target`.
* Count the number of matching elements for each rotation.
* If **any** of the counters equals `n * n`, it means the matrices are a match after that rotation.

---

### 🕒 Time and Space Complexity:

* **Time Complexity:** O(n²) → Single traversal over the matrix for checking all four rotations.
* **Space Complexity:** O(1) → Only counters are used.

---

### ✅ Example Dry Run:

Let:

```java
mat = [[1, 2],
       [3, 4]]

target = [[3, 1],
          [4, 2]]
```

* This is a **90° rotation** of `mat`. The formula `target[n-j-1][i]` checks for this.
* The 90° counter will match all elements → return `true`.

---
