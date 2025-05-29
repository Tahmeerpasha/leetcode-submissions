## ✅ Problem Summary:

> Koko loves bananas. Given an array `piles` representing the number of bananas in each pile and an integer `h` (number of hours), return the **minimum integer eating speed `k`** such that Koko can eat all the bananas within `h` hours.

---

## 💡 Approaches:

### 1. 🐢 Brute Force (TLE)

* **Idea**: Try every possible speed `k` from 1 to max pile size.
* For each `k`, compute total hours needed to eat all bananas.
* Pick the minimum `k` for which total hours ≤ `h`.
* **Time Complexity**: `O(max(piles) * n)`
* **Space Complexity**: `O(1)`

```java
public int minEatingSpeed(int[] piles, int h) {
    int max = Arrays.stream(piles).max().getAsInt();
    for (int k = 1; k <= max; k++) {
        int totalH = 0;
        for (int pile : piles) {
            totalH += Math.ceil((double) pile / k);
        }
        if (totalH <= h)
            return k;
    }
    return max;
}
```

---

### 2. ⚡ Optimal — Binary Search ✅

* **Idea**: Use binary search on `k` (eating speed). Range = \[1, max(pile)]
* For each mid speed `k`, calculate total hours:

  * If hours ≤ `h` → try a smaller speed (search left)
  * Else → try a bigger speed (search right)
* **Time Complexity**: `O(n * log(max(pile)))`
* **Space Complexity**: `O(1)`

### Code:

```java
public int minEatingSpeed(int[] piles, int h) {
    int low = 1, high = max(piles);
    while (low <= high) {
        int mid = (low + high) / 2;
        int totalH = findTotal(piles, mid);
        if (totalH <= h)
            high = mid - 1;
        else
            low = mid + 1;
    }
    return low;
}
```

#### Helper functions:

```java
int findTotal(int[] nums, int hour) {
    int total = 0;
    for (int num : nums)
        total += Math.ceil((double) num / (double) hour);
    return total;
}

int max(int[] nums) {
    int max = Integer.MIN_VALUE;
    for (int num : nums)
        max = Math.max(max, num);
    return max;
}
```

---

## ✅ Summary Table:

| Approach         | Time                     | Space  | Notes                             |
| ---------------- | ------------------------ | ------ | --------------------------------- |
| Brute Force      | `O(n * max(piles))`      | `O(1)` | Try all speeds from 1 to max      |
| Optimal (Binary) | `O(n * log(max(piles)))` | `O(1)` | Binary search on eating speed `k` |

---
