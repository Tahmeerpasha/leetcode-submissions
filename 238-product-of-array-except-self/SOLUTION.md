## 🔁 Leetcode 238: Product of Array Except Self

### 🧩 Problem Statement:
Given an array `nums` of `n` integers, return an array `answer` such that `answer[i]` is equal to the product of all the elements of `nums` **except** `nums[i]`.

- You must solve it **without division**.
- The solution must be in **O(n)** time.
- Try optimizing for **constant space (excluding the output array)**.

---

## 🔨 Brute Force Approach

### 🧠 Idea:
For each element at index `i`, multiply all the elements except `i`.

### 🛠️ Code (O(n²)):
```java
public int[] productExceptSelf(int[] nums) {
    int n = nums.length;
    int[] answer = new int[n];
    for (int i = 0; i < n; i++) {
        int product = 1;
        for (int j = 0; j < n; j++) {
            if (i != j) {
                product *= nums[j];
            }
        }
        answer[i] = product;
    }
    return answer;
}
```

- **Time:** O(n²)
- **Space:** O(1) extra (excluding result)

---

## ⚙️ Better Approach — Using Prefix and Suffix Arrays

### 🧠 Idea:
Store product of elements **before i** (prefix) and **after i** (suffix), then multiply them.

### 🔧 Code (O(n) Time & O(n) Space):
```java
public int[] productExceptSelf(int[] nums) {
    int n = nums.length;
    int[] answer = new int[n];
    int[] prefix = new int[n];
    int[] suffix = new int[n];

    prefix[0] = 1;
    for (int i = 1; i < n; i++) {
        prefix[i] = prefix[i - 1] * nums[i - 1];
    }

    suffix[n - 1] = 1;
    for (int i = n - 2; i >= 0; i--) {
        suffix[i] = suffix[i + 1] * nums[i + 1];
    }

    for (int i = 0; i < n; i++) {
        answer[i] = prefix[i] * suffix[i];
    }

    return answer;
}
```

- **Time:** O(n)
- **Space:** O(n)

---

## 💎 Optimized Approach — O(n) Time, O(1) Space

### 🧠 Idea:
Instead of using separate prefix and suffix arrays, calculate prefix on the go and store in `answer`. Then traverse in reverse for suffix multiplication.

### ✅ Code:
```java
public int[] productExceptSelf(int[] nums) {
    int n = nums.length;
    int[] answer = new int[n];

    answer[0] = 1;
    for (int i = 1; i < n; i++) {
        answer[i] = answer[i - 1] * nums[i - 1];
    }

    int suffix = 1;
    for (int i = n - 1; i >= 0; i--) {
        answer[i] *= suffix;
        suffix *= nums[i];
    }

    return answer;
}
```

- **Time:** O(n)
- **Space:** O(1) (excluding result array)

---

## 🔍 Dry Run

### Input:
```java
nums = [1, 2, 3, 4]
```

### Pass 1 (Left → Right): Build prefix product

| i | answer[i]               |
|---|-------------------------|
| 0 | 1                       |
| 1 | 1 * nums[0] = 1         |
| 2 | 1 * nums[1] = 2         |
| 3 | 2 * nums[2] = 6         |

`answer = [1, 1, 2, 6]`

### Pass 2 (Right → Left): Multiply with suffix

| i | suffix | answer[i] (after update) |
|---|--------|--------------------------|
| 3 | 1      | 6 * 1 = 6                |
| 2 | 4      | 2 * 4 = 8                |
| 1 | 12     | 1 * 12 = 12              |
| 0 | 24     | 1 * 24 = 24              |

`Final answer = [24, 12, 8, 6]`

---

## ✅ Final Output:
```java
[24, 12, 8, 6]
```
