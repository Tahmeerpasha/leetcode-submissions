## ✅ Problem Summary:

> Given a **sorted array of positive integers** `arr` (with **distinct elements**) and an integer `k`, return the **kᵗʰ missing** positive number.

🔸 The array starts from some value ≥ 1, and some positive numbers are missing in between.

---

## 🧠 Insight:

* For index `i`, the number of missing elements till `arr[i]` is:

  ```
  missing = arr[i] - (i + 1)
  ```

  Since the ideal value at index `i` (0-based) is `i + 1`.

---

## 💡 Approaches:

### 1. 🐢 Brute Force

* Iterate through array, and if `arr[i] <= k`, increment `k`.
* At each step, we're compensating for a non-missing number.
* **Time**: `O(n)`
* **Space**: `O(1)`

```java
for (int num : arr) {
    if (num <= k) k++;
    else break;
}
return k;
```

---

### 2. ⚡ Optimal — Binary Search ✅

* Use binary search to find how many numbers are missing before each index.
* Binary search to find the first position `low` where:

  ```
  missing = arr[mid] - (mid + 1) >= k
  ```
* Final answer is `low + k`.

#### Time & Space:

* **Time**: `O(log n)`
* **Space**: `O(1)`

---

## ✅ Code:

```java
public int findKthPositive(int[] arr, int k) {
    int low = 0, high = arr.length - 1;

    while (low <= high) {
        int mid = (low + high) / 2;
        int missing = arr[mid] - (mid + 1);
        if (missing < k)
            low = mid + 1;
        else
            high = mid - 1;
    }
    return low + k;
}
```

---

## 📝 Summary Table:

| Approach      | Time Complexity | Space  | Notes                                   |
| ------------- | --------------- | ------ | --------------------------------------- |
| Brute Force   | `O(n)`          | `O(1)` | Adjust `k` by shifting for present nums |
| Binary Search | `O(log n)`      | `O(1)` | Smart math trick with `arr[i] - (i+1)`  |

---
