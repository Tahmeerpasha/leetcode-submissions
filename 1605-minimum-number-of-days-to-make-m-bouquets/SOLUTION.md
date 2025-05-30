## ✅ Problem Summary:

> You are given an array `bloomDay`, where `bloomDay[i]` is the day the `i-th` flower blooms. You want to make `m` bouquets, each containing `k` **adjacent** flowers.
> Return the **minimum number of days** required to make `m` bouquets. If it’s not possible, return `-1`.

---

## 🔎 Constraints:

* Must form **m bouquets**, each with **k adjacent flowers**.
* Only flowers bloomed **on or before that day** can be picked.
* You cannot skip flowers when grouping for bouquets.

---

## 💡 Approaches:

### 1. 🐢 Brute Force

* Try every day from `min(bloomDay)` to `max(bloomDay)` and check if it's possible to form `m` bouquets.
* For each day, count how many bouquets can be formed.
* **Time Complexity**: `O((max - min) * n)`
* **Space**: `O(1)`

```java
for (int day = minDay; day <= maxDay; day++) {
    if (isPossible(bloomDay, day, m, k))
        return day;
}
return -1;
```

---

### 2. ⚡ Optimal — Binary Search on Answer ✅

* Minimum possible day = `min(bloomDay)`
* Maximum possible day = `max(bloomDay)`
* Use binary search to find the smallest day for which it's possible to make `m` bouquets.
* **Time Complexity**: `O(n * log(max - min))`
* **Space**: `O(1)`

### Code:

```java
public int minDays(int[] bloomDay, int m, int k) {
    int n = bloomDay.length;
    if ((long) n < (long) m * k) return -1;

    int low = min(bloomDay), high = max(bloomDay);

    while (low <= high) {
        int mid = (low + high) / 2;
        if (isPossible(bloomDay, mid, m, k))
            high = mid - 1;
        else
            low = mid + 1;
    }
    return low;
}
```

#### Helper functions:

```java
boolean isPossible(int[] bloomDays, int day, int m, int k) {
    int count = 0, bouquets = 0;
    for (int val : bloomDays) {
        if (val <= day) count++;
        else {
            bouquets += count / k;
            count = 0;
        }
    }
    bouquets += count / k;
    return bouquets >= m;
}

int min(int[] a) {
    int min = Integer.MAX_VALUE;
    for (int i : a) min = Math.min(min, i);
    return min;
}

int max(int[] a) {
    int max = Integer.MIN_VALUE;
    for (int i : a) max = Math.max(max, i);
    return max;
}
```

---

## ✅ Summary Table:

| Approach      | Time Complexity         | Space  | Notes                                  |
| ------------- | ----------------------- | ------ | -------------------------------------- |
| Brute Force   | `O((max - min) * n)`    | `O(1)` | Try every day one by one               |
| Binary Search | `O(n * log(max - min))` | `O(1)` | Efficient search on min day to satisfy |

---
