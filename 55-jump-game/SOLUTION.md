## 🔍 Problem Statement

You are given an integer array `nums` where each element represents the **maximum jump length** from that position.
You start at index `0` and need to determine if you can reach the **last index**.

**Example:**

```
Input: nums = [2,3,1,1,4]
Output: true  // jump 1 step to index 1, then 3 steps to last index
```

```
Input: nums = [3,2,1,0,4]
Output: false // cannot move past index 3 (stuck at zero)
```

---

## 🧠 1. Brute Force (DFS / Recursion)

### Idea:

Try **every possible jump** from each position and recursively check if you can reach the last index.

### Code:

```java
class Solution {
    public boolean canJump(int[] nums) {
        return canReach(0, nums);
    }

    private boolean canReach(int index, int[] nums) {
        if (index >= nums.length - 1) return true;
        int maxJump = nums[index];
        for (int i = 1; i <= maxJump; i++) {
            if (canReach(index + i, nums)) return true;
        }
        return false;
    }
}
```

### Complexity:

* **Time:** O(2^N) – exploring all paths
* **Space:** O(N) – recursion stack

### ⚠️ Not practical — exponential time.

---

## ⚙️ 2. Better (Dynamic Programming / Memoization)

### Idea:

Use a memo array to store whether a position can reach the end.
Avoid recomputation by caching results.

### Code:

```java
class Solution {
    public boolean canJump(int[] nums) {
        int n = nums.length;
        Boolean[] dp = new Boolean[n];
        return canReach(0, nums, dp);
    }

    private boolean canReach(int index, int[] nums, Boolean[] dp) {
        if (index >= nums.length - 1) return true;
        if (dp[index] != null) return dp[index];

        int maxJump = nums[index];
        for (int i = 1; i <= maxJump; i++) {
            if (canReach(index + i, nums, dp))
                return dp[index] = true;
        }
        return dp[index] = false;
    }
}
```

### Complexity:

* **Time:** O(N²) – in worst case, checking every possible jump
* **Space:** O(N) – recursion + memo

---

## 🚀 3. Optimal (Greedy)

### Idea:

Track the **farthest index reachable** so far.
If the current index exceeds that reach, you can’t proceed → return false.

### Code:

```java
class Solution {
    public boolean canJump(int[] nums) {
        int maxReach = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > maxReach) return false; // can't reach this index
            maxReach = Math.max(maxReach, i + nums[i]);
        }
        return true;
    }
}
```

### Complexity:

* **Time:** O(N)
* **Space:** O(1)

### ✅ Example walkthrough:

`nums = [2,3,1,1,4]`

| i | nums[i] | maxReach | Action        |
| - | ------- | -------- | ------------- |
| 0 | 2       | 2        | ok            |
| 1 | 3       | 4        | ok            |
| 2 | 1       | 4        | ok            |
| 3 | 1       | 4        | ok            |
| 4 | 4       | 8        | reached end ✅ |

---

## 💡 Interview Tips & Tricks

1. **Common Pattern**:
   “Can reach end?” → think **greedy reachability**.
   Similar problems: *Jump Game II*, *Gas Station*, *Can Place Flowers*.

2. **Pitfall**:
   Don’t just check `maxReach > nums.length`;
   you only need to ensure `i <= maxReach` throughout the loop.

3. **Greedy Proof Intuition**:
   If at any point you can reach `i`, then `maxReach` will capture the farthest reachable position.
   There’s no need to backtrack — forward reach is sufficient.

4. **Follow-up (Jump Game II)**:
   If asked *minimum jumps*, you’ll need a **BFS-like or layered greedy** approach.

---
