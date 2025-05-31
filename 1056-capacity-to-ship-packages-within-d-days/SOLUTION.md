## ✅ Problem Summary:

> Given an array `weights` where `weights[i]` is the weight of the `i-th` package, and an integer `days`, return the **minimum capacity** of a ship required to deliver all the packages within `days`.
> You must ship in the order they appear. You can’t split packages.

---

## 🔎 Constraints:

* You must **preserve the order** of packages.
* Every day, load packages sequentially until reaching the daily capacity.
* Once capacity is exceeded, move to the next day.

---

## 💡 Approaches:

### 1. 🐢 Brute Force

* Try every capacity from `max(weights)` to `sum(weights)` and check if it’s possible to ship within `days`.
* **Why `max(weights)` as the lower bound?** → A ship cannot carry less than the heaviest package.
* **Time Complexity**: `O((high - low + 1) * n)`
* **Space**: `O(1)`

```java
for (int cap = max; cap <= sum; cap++) {
    if (canShipAllWithCapacity(weights, cap, days))
        return cap;
}
```

---

### 2. ⚡ Optimal — Binary Search on Answer ✅

* **Lower bound**: `max(weights)`
* **Upper bound**: `sum(weights)`
* Perform binary search to find the smallest valid ship capacity.
* **Time Complexity**: `O(n * log(sum - max))`
* **Space**: `O(1)`

### Code:

```java
public int shipWithinDays(int[] weights, int days) {
    int low = max(weights), high = sumOfAllWeights(weights);

    while (low <= high) {
        int mid = (low + high) / 2;
        if (canShipAllWithCapacity(weights, mid, days))
            high = mid - 1;
        else
            low = mid + 1;
    }
    return low;
}
```

#### Helper functions:

```java
int max(int[] weights) {
    int max = Integer.MIN_VALUE;
    for (int w : weights) max = Math.max(max, w);
    return max;
}

int sumOfAllWeights(int[] weights) {
    int sum = 0;
    for (int w : weights) sum += w;
    return sum;
}

boolean canShipAllWithCapacity(int[] weights, int capacity, int days) {
    int current = 0, totalDays = 1;
    for (int w : weights) {
        current += w;
        if (current > capacity) {
            totalDays++;
            current = w;
        }
    }
    return totalDays <= days;
}
```

---

## ✅ Summary Table:

| Approach      | Time Complexity         | Space  | Notes                               |
| ------------- | ----------------------- | ------ | ----------------------------------- |
| Brute Force   | `O(n * (sum - max))`    | `O(1)` | Try every capacity one by one       |
| Binary Search | `O(n * log(sum - max))` | `O(1)` | Efficient — find min valid capacity |

---
