# 📘 Problem: **88. Merge Sorted Array**

**Link**: [LeetCode 88 - Merge Sorted Array](https://leetcode.com/problems/merge-sorted-array/)
You're given two sorted integer arrays `nums1` and `nums2`, and two integers `m` and `n`, representing the number of elements in each respectively.
The task is to **merge `nums2` into `nums1`** as one sorted array.
The final sorted array should be stored inside `nums1`.

---

## ✅ Constraints

* `nums1.length == m + n`
* `nums2.length == n`
* All values are sorted in non-decreasing order
* Only the first `m` elements in `nums1` are valid; the rest are zeros acting as placeholders.

---

## 🧠 Observations

* Both arrays are sorted.
* `nums1` has enough space to hold the merged array.
* The task is **in-place**, so space usage should be minimized.

---

## 1️⃣ Brute Force Approach

### 💡 Idea:

* Merge both arrays into a **new array**, then copy the result back to `nums1`.

### ✅ Steps:

1. Use two pointers to traverse `nums1[0...m-1]` and `nums2[0...n-1]`
2. Merge them into a temporary array
3. Copy back the result into `nums1`

### 💻 Code Snippet:

```java
int[] result = new int[m + n];
int i = 0, j = 0, k = 0;

while (i < m && j < n) {
    if (nums1[i] <= nums2[j]) result[k++] = nums1[i++];
    else result[k++] = nums2[j++];
}

while (i < m) result[k++] = nums1[i++];
while (j < n) result[k++] = nums2[j++];

for (int l = 0; l < m + n; l++) nums1[l] = result[l];
```

### ⏱ Time: `O(m + n)`

### 📦 Space: `O(m + n)`

---

## 2️⃣ Suboptimal / Basic Optimal Approach

### 💡 Idea:

* Copy `nums2` into the unused part of `nums1`, then sort `nums1`.

### ✅ Steps:

1. Copy `nums2` into `nums1` starting at index `m`
2. Sort the entire `nums1` array

### 💻 Code:

```java
for (int i = 0; i < n; i++) {
    nums1[m + i] = nums2[i];
}
Arrays.sort(nums1);
```

### ⏱ Time: `O((m + n) log(m + n))`

### 📦 Space: `O(1)`

> ⚠️ Not the best for interviews. Although it's simple, it's not the most efficient in terms of logic.

---

## 3️⃣ 🥇 Optimal In-Place Approach (Two Pointers from End)

### 💡 Idea:

* Use **three pointers**:

  * `i = m - 1`: end of nums1's actual data
  * `j = n - 1`: end of nums2
  * `k = m + n - 1`: end of nums1 (final position)

* Start placing the largest elements at the end of `nums1` to avoid overwriting.

### ✅ Steps:

1. Compare `nums1[i]` and `nums2[j]`
2. Place the bigger at position `k`
3. Move pointers accordingly

### 💻 Code:

```java
int i = m - 1, j = n - 1, k = m + n - 1;
while (i >= 0 && j >= 0) {
    if (nums1[i] > nums2[j]) {
        nums1[k--] = nums1[i--];
    } else {
        nums1[k--] = nums2[j--];
    }
}

while (j >= 0) {
    nums1[k--] = nums2[j--];
}
```

### ⏱ Time: `O(m + n)`

### 📦 Space: `O(1)`

✅ **Best choice for interviews and performance.**

---

## 🧾 Summary Table

| Approach                      | Time Complexity     | Space Complexity | In-Place | Notes                                     |
| ----------------------------- | ------------------- | ---------------- | -------- | ----------------------------------------- |
| Brute Force (Extra Array)     | O(m + n)            | O(m + n)         | ❌        | Easy but not space efficient              |
| Sort After Append             | O((m + n) log(m+n)) | O(1)             | ✅        | Simple but not time optimal               |
| 🔥 Optimal Two Pointers (End) | O(m + n)            | O(1)             | ✅        | Best performance, preferred in interviews |

---

## 🔚 Final Notes:

* Always clarify in interviews whether you can use extra space.
* The **two-pointer from end** approach is classic and frequently asked.
* If the array is not sorted, you'd have to sort before merging, which changes the time complexity.
