# 🧠 215. Kth Largest Element in an Array — Complete Notes

---

## 🔹 1. **Brute Force (Sorting)**

### 💡 Idea:

Sort the array and directly pick the kth largest element.

### 🧠 Code:

```java
Arrays.sort(nums); // ascending order
return nums[nums.length - k];
```

—or—

```java
Arrays.sort(nums, Collections.reverseOrder()); // descending order (if using Integer[])
return nums[k - 1];
```

### ⏱️ Time Complexity:

* Sorting → **O(n log n)**
* Access → **O(1)**
  ✅ **Total = O(n log n)**

### 🧮 Space Complexity:

* **O(1)** (if in-place sorting)

### ⚙️ Trick:

* Easy one-liner; good for first try but not efficient for large inputs.

---

## 🔸 2. **Better Approach — Max Heap**

### 💡 Idea:

Use a **max heap** and remove the top `k - 1` largest elements.
The next top element is the **kth largest**.

### 🧠 Code:

```java
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
for (int num : nums) {
    maxHeap.add(num);
}
for (int i = 0; i < k - 1; i++) {
    maxHeap.poll();
}
return maxHeap.peek();
```

### ⏱️ Time Complexity:

* Build heap: O(n)
* Remove (k-1) elements: O(k log n)
  ✅ **Total = O(n + k log n)**

### 🧮 Space Complexity:

* **O(n)** (heap stores all elements)

---

## 🔹 3. **Optimal Approach — Min Heap of Size k (Your Code ✅)**

### 💡 Idea:

Keep a **min heap** of size `k`.
Whenever size exceeds `k`, remove the smallest (top).
Finally, top element is kth largest.

### 🧠 Code:

```java
PriorityQueue<Integer> pq = new PriorityQueue<>();
for (int num : nums) {
    pq.add(num);
    if (pq.size() > k)
        pq.poll();
}
return pq.peek();
```

### ⏱️ Time Complexity:

* Each add/poll → O(log k)
* For all n → **O(n log k)**

### 🧮 Space Complexity:

* **O(k)**

### ⚙️ Trick:

* Min heap = best balance of simplicity + efficiency.
* Works well when `k << n`.

---

## 💎 4. **QuickSelect (Truly Optimal Average O(n))**

### 💡 Idea:

Use a **partition-based approach** (like QuickSort):

* Choose a pivot.
* Partition the array so that:

  * All elements > pivot are on the left,
  * All elements < pivot are on the right.
* Based on pivot’s final position:

  * If pivot index = n - k → found kth largest.
  * Else move left/right accordingly.

### 🧠 Code:

```java
class Solution {
    public int findKthLargest(int[] nums, int k) {
        int left = 0, right = nums.length - 1;
        int target = nums.length - k; // kth largest index (0-based)
        
        while (true) {
            int pivotIndex = partition(nums, left, right);
            if (pivotIndex == target)
                return nums[pivotIndex];
            else if (pivotIndex < target)
                left = pivotIndex + 1;
            else
                right = pivotIndex - 1;
        }
    }

    private int partition(int[] nums, int left, int right) {
        int pivot = nums[right];
        int i = left;
        for (int j = left; j < right; j++) {
            if (nums[j] <= pivot) {
                swap(nums, i, j);
                i++;
            }
        }
        swap(nums, i, right);
        return i;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
```

### ⏱️ Time Complexity:

* Average: **O(n)**
* Worst case (bad pivots): **O(n²)**

### 🧮 Space Complexity:

* **O(1)** (in-place)

### ⚙️ Trick:

* Same concept as **QuickSort partition**.
* Excellent when space is tight and average performance matters.

---

## ⚡ Summary Table

| Approach       | Technique         | Time           | Space | Notes                            |
| -------------- | ----------------- | -------------- | ----- | -------------------------------- |
| Brute          | Sort array        | O(n log n)     | O(1)  | Simple                           |
| Better         | Max Heap          | O(n + k log n) | O(n)  | Easier logic                     |
| ✅ Optimal      | Min Heap (size k) | O(n log k)     | O(k)  | Most practical                   |
| ⚡ True Optimal | QuickSelect       | Avg O(n)       | O(1)  | Fastest avg, tricky to implement |

---

## 💡 Interview Tricks

| Concept                                              | Tip                                       |
| ---------------------------------------------------- | ----------------------------------------- |
| Kth largest → use **Min Heap** of size k             | Kth smallest → use **Max Heap** of size k |
| Use QuickSelect if interviewer says “no extra space” | It's in-place                             |
| Always explain time tradeoff before coding           | Shows clarity                             |
| Mention heapify O(n) build time if you use arrays    | Bonus points!                             |
