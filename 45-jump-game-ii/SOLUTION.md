## 🔍 Problem Statement

You are given an integer array `nums`, where each element represents your **maximum jump length** at that position.
Your goal is to **reach the last index in the minimum number of jumps**.
You can assume that you can always reach the end.

**Example:**

```
Input: nums = [2,3,1,1,4]
Output: 2
Explanation: Jump 1 step to index 1, then 3 steps to last index.
```

---

## 🧠 1. Brute Force (DFS / Recursion)

### Idea:

Try **all possible jump paths** recursively and pick the **minimum** number of jumps needed to reach the end.

### Code:

```java
class Solution {
    public int jump(int[] nums) {
        return minJumps(0, nums);
    }

    private int minJumps(int index, int[] nums) {
        if (index >= nums.length - 1) return 0;

        int minSteps = Integer.MAX_VALUE;
        for (int jump = 1; jump <= nums[index]; jump++) {
            int next = index + jump;
            int steps = 1 + minJumps(next, nums);
            minSteps = Math.min(minSteps, steps);
        }
        return minSteps;
    }
}
```

### Complexity:

* **Time:** O(N^N) → exponential (each index can branch into multiple next steps)
* **Space:** O(N) → recursion stack

### ⚠️ Not feasible for large inputs.

---

## ⚙️ 2. Better (Dynamic Programming - Memoization)

### Idea:

Store the **minimum jumps from each index** to avoid recomputation.

### Code:

```java
class Solution {
    public int jump(int[] nums) {
        int n = nums.length;
        Integer[] dp = new Integer[n];
        return minJumps(0, nums, dp);
    }

    private int minJumps(int index, int[] nums, Integer[] dp) {
        if (index >= nums.length - 1) return 0;
        if (dp[index] != null) return dp[index];

        int minSteps = Integer.MAX_VALUE;
        for (int jump = 1; jump <= nums[index]; jump++) {
            int next = index + jump;
            int steps = 1 + minJumps(next, nums, dp);
            minSteps = Math.min(minSteps, steps);
        }
        return dp[index] = minSteps;
    }
}
```

### Complexity:

* **Time:** O(N²) – at most, each index loops over next reachable elements
* **Space:** O(N) – recursion + memoization

---

## 🚀 3. Optimal (Greedy / BFS Level Approach)

### Idea:

Think of the array as **levels** in a BFS.
Each time you make a jump, you move to the **next layer of reachable indices**.

* Maintain a **window [l, r]** for the current layer.
* Find the **farthest** index reachable within that layer.
* Once `r` (current layer end) is processed, move to the next layer.
* Increment `jumps` each time you move to a new layer.

---

### Code (your version is correct ✅)

```java
class Solution {
    public int jump(int[] nums) {
        int l = 0, r = 0, jumps = 0;

        while (r < nums.length - 1) {
            int farthest = 0;
            for (int i = l; i <= r; i++) {
                farthest = Math.max(farthest, i + nums[i]);
            }
            l = r + 1;
            r = farthest;
            jumps++;
        }

        return jumps;
    }
}
```

### Complexity:

* **Time:** O(N)
* **Space:** O(1)

---

## 🧩 Dry Run Example

`nums = [2,3,1,1,4]`

| Jump               | l | r | farthest | Next Window | Explanation                  |
| ------------------ | - | - | -------- | ----------- | ---------------------------- |
| 1                  | 0 | 0 | 2        | [1,2]       | From 0, reach up to index 2  |
| 2                  | 1 | 2 | 4        | [3,4]       | From [1,2], reach last index |
| ✅ Result = 2 jumps |   |   |          |             |                              |

---

## 💡 Interview Tips & Tricks

1. **Greedy Intuition:**

   * Each "jump" is like choosing the **farthest boundary** you can reach in one go.
   * You don't need to simulate each path; just track how far your current jump can take you.

2. **Alternative implementation (simplified):**

   ```java
   class Solution {
       public int jump(int[] nums) {
           int jumps = 0, curEnd = 0, farthest = 0;
           for (int i = 0; i < nums.length - 1; i++) {
               farthest = Math.max(farthest, i + nums[i]);
               if (i == curEnd) {
                   jumps++;
                   curEnd = farthest;
               }
           }
           return jumps;
       }
   }
   ```

   This avoids managing `l` and `r` explicitly but does the same thing.

3. **Common traps:**

   * Don’t count unnecessary jumps after reaching the end.
   * Don’t confuse this with Jump Game I — here, you **must minimize jumps**, not just check reachability.

4. **Pattern Recognition:**

   * Similar problems:
     *Minimum number of ladders to reach the top*,
     *Gas Station traversal*,
     *Interval coverage (like minimum number of arrows to burst balloons)*.

5. **Edge cases:**

   * `[0]` → 0 jumps (already at last index)
   * `[1,0]` → 1 jump
   * `[1,1,1,1]` → `n-1` jumps

---
