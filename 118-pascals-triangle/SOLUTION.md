### ✅ **Problem Statement**

Given an integer `numRows`, generate the first `numRows` of **Pascal's Triangle**.

### 🧠 **Understanding Pascal’s Triangle**

* Each row starts and ends with `1`.
* Every other element is the **sum of the two elements directly above it** from the previous row.
* For `row i`, element at `column j` is:

  $$
  C(i, j) = \frac{i!}{j!(i-j)!}
  $$

---

### ✅ **Approach 1: Using nCr Formula (Combinatorics)**

#### 🔁 **Time Complexity**:

* Outer loop = O(n)
* Inner loop (nCr calculation) = O(r)
* Total = O(n²)
* But if `nCr()` is implemented naively → O(n³)

#### 🧠 **Key Idea**:

Use the combinatorial formula:

$$
C(n, r) = C(n, r-1) \times \frac{n - r + 1}{r}
$$

#### ✅ **Code Logic:**

```java
List<Integer> generateRow(int row) {
    List<Integer> answer = new ArrayList<>();
    int val = 1;
    answer.add(val);
    for (int col = 1; col < row; col++) {
        val = val * (row - col) / col;
        answer.add(val);
    }
    return answer;
}
```

---

### ✅ **Approach 2: Using Previous Row (Iterative Building)**

#### 🔁 **Time Complexity**: O(n²)

#### 💾 **Space Complexity**: O(1) (excluding output)

#### 🧠 **Key Idea**:

Build row by row. Use the previous row to calculate the current one.

```java
List<List<Integer>> result = new ArrayList<>();
for (int i = 0; i < numRows; i++) {
    List<Integer> row = new ArrayList<>();
    for (int j = 0; j <= i; j++) {
        if (j == 0 || j == i) row.add(1);
        else row.add(result.get(i - 1).get(j - 1) + result.get(i - 1).get(j));
    }
    result.add(row);
}
```

---

### 🧪 **Example Input/Output**

**Input**: `numRows = 5`
**Output**:

```
[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]
```

---

### 🧠 **Key Concepts Covered**

* Combinatorics
* Efficient calculation of combinations
* Dynamic programming-style row construction
* Math optimization for integer overflow

---
![image](https://github.com/user-attachments/assets/a8ffcec8-e4d6-4557-9050-2c53dcc07b30)
