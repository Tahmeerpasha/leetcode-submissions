## 📝 Problem: Sort an Array

Given an array of integers `nums`, sort the array in **ascending order** and return it.

---

## ✅ Constraints:

* `1 <= nums.length <= 5 * 10⁴`
* `-5 * 10⁴ <= nums[i] <= 5 * 10⁴`

---

## 📘 Approach: **Merge Sort**

### 🔍 Why Merge Sort?

* **Efficient**: Guaranteed `O(n log n)` time complexity.
* **Stable**: Preserves the order of equal elements.
* **Divide & Conquer**: Splits the array, sorts both halves, and merges them.

---

### 🔧 Steps:

1. **Divide**:

   * Recursively divide the array into two halves until size becomes 1.

2. **Conquer (Sort)**:

   * Sort the left and right halves individually.

3. **Combine (Merge)**:

   * Merge the two sorted halves into a single sorted array.

---

### ✅ Time & Space Complexity:

| Type  | Complexity                                         |
| ----- | -------------------------------------------------- |
| Time  | `O(n log n)`                                       |
| Space | `O(n)` (due to temporary array used in merge step) |

---

### ✅ Code Explanation:

```java
public int[] sortArray(int[] nums) {
    mergeSort(nums, 0, nums.length - 1);
    return nums;
}
```

#### 🔁 `mergeSort()` function:

* Recursively splits the array into halves and sorts each half.

```java
void mergeSort(int[] nums, int low, int high) {
    if (low == high) return;

    int mid = low + (high - low) / 2;

    mergeSort(nums, low, mid);
    mergeSort(nums, mid + 1, high);

    merge(nums, low, mid, high);
}
```

#### 🔁 `merge()` function:

* Merges two sorted subarrays: `[low…mid]` and `[mid+1…high]`.

```java
void merge(int[] nums, int low, int mid, int high) {
    int[] temp = new int[high - low + 1];
    int left = low, right = mid + 1, i = 0;

    while (left <= mid && right <= high) {
        if (nums[left] <= nums[right]) {
            temp[i++] = nums[left++];
        } else {
            temp[i++] = nums[right++];
        }
    }

    while (left <= mid) temp[i++] = nums[left++];
    while (right <= high) temp[i++] = nums[right++];

    for (int j = low; j <= high; j++) {
        nums[j] = temp[j - low];  // Copy sorted elements back
    }
}
```

---

### 🧠 Intuition Behind `j - low`:

* The temp array is 0-indexed, while `nums` is from `low` to `high`.
* So, `temp[j - low]` maps the correct element back to `nums[j]`.

---

### 📦 In-Place Sorting Alternatives:

* Merge Sort uses extra space.
* To sort **in-place**, consider **Heap Sort** or **Quick Sort**.

---

## 🔁 Example Dry Run:

### Input:

```
nums = [5, 2, 3, 1]
```

**Step-by-step split & merge:**

```
[5, 2, 3, 1] → [5, 2] + [3, 1]
→ [5] + [2] → merge → [2, 5]
→ [3] + [1] → merge → [1, 3]
→ merge [2, 5] & [1, 3] → [1, 2, 3, 5]
```

---

## 🏁 Final Notes:

* Merge sort is **preferred** when stability is required or when worst-case performance must be guaranteed.
* For competitive coding or interviews, always mention space trade-offs of merge sort.
