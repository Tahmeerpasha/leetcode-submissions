### ✅ Problem: **154. Find Minimum in Rotated Sorted Array II**

Given a rotated sorted array `a` (may contain duplicates), return the **minimum** element.

---

### ✅ Approach: **Binary Search with Duplicate Handling**

---

### 🔍 Intuition:

In a rotated sorted array:

* Either the **left half is sorted**, or the **right half is sorted**.
* But **duplicates** (e.g., `a[low] == a[mid] == a[high]`) make it **unclear which part is sorted**, so we need to handle this ambiguity.

---

### 🔁 Step-by-step Logic:

```java
while (low <= high) {
    int mid = (low + high) / 2;

    // 🔄 Handle duplicates (ambiguous sorted half)
    if (a[low] == a[mid] && a[mid] == a[high]) {
        min = Math.min(min, a[low]);
        low++;
        high--;
        continue;
    }

    // ✅ Left half is sorted
    if (a[low] <= a[mid]) {
        min = Math.min(min, a[low]);
        low = mid + 1;
    }
    // ✅ Right half is sorted
    else {
        min = Math.min(min, a[mid]);
        high = mid - 1;
    }
}
```

---

### 📌 Key Conditions:

| Condition                     | Action                                         |
| ----------------------------- | ---------------------------------------------- |
| `a[low] == a[mid] == a[high]` | Can't decide — do `low++`, `high--`            |
| `a[low] <= a[mid]`            | Left half sorted — use `a[low]`, shift `low`   |
| `a[mid] < a[low]`             | Right half sorted — use `a[mid]`, shift `high` |

---

### 📦 Dry Run Example:

```text
Input: [2, 2, 2, 0, 1]

1st Iter: low=0, mid=2, high=4 → a[mid]=2 == a[low]==a[high] → do low++, high--
2nd Iter: low=1, high=3 → mid=2 → a[mid]=2 > a[low]=2 → left sorted → min=2, low=mid+1
3rd Iter: low=3, high=3 → mid=3 → a[mid]=0 → min=0
```

---

### 🧠 Final Notes:

| Property         | Value                                       |
| ---------------- | ------------------------------------------- |
| Technique        | Modified Binary Search                      |
| Time Complexity  | `O(log n)` average, `O(n)` worst (all dups) |
| Space Complexity | `O(1)`                                      |
| Edge Cases       | `[2,2,2,0,1]`, `[10,1,10,10]`, `[1,3,5]`    |

---

### ✅ Summary:

* Use binary search to find the minimum.
* When values repeat (duplicate elements), it's hard to decide the sorted half → shrink bounds safely.
* Always track the `min` during the search.

---
