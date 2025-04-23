## 🗳️ LeetCode 169: Majority Element

### ✅ Problem Statement  
Given an array `nums`, return the **majority element** — the element that appears more than `n / 2` times.  
You may assume that the majority element **always exists** in the array.

---

## 💡 Approaches

---

### 1. 🔨 Brute Force

#### 🧠 Logic:
- Count the frequency of each element using nested loops.
- Return the element whose frequency is greater than `n / 2`.

#### 💻 Java Code:
```java
class Solution {
    public int majorityElement(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (nums[j] == nums[i]) {
                    count++;
                }
            }
            if (count > n / 2) return nums[i];
        }
        return -1;
    }
}
```

#### ⏱️ Complexity:
- **Time:** O(n²)  
- **Space:** O(1)

---

### 2. 🧠 Better Approach (Using HashMap)

#### 🧠 Logic:
- Count the frequency of each element using a `HashMap`.
- Return the element whose count is greater than `n / 2`.

#### 💻 Java Code:
```java
import java.util.*;

class Solution {
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int n = nums.length;

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > n / 2) {
                return entry.getKey();
            }
        }

        return -1;
    }
}
```

#### ⏱️ Complexity:
- **Time:** O(n)  
- **Space:** O(n)

---

### 3. ⚡ Optimized Approach — Moore’s Voting Algorithm

#### 🧠 Logic:
- Initialize `count = 0`, `candidate = 0`.
- Traverse the array:
  - If `count == 0`, assign current element as `candidate`.
  - If current element equals `candidate`, increment `count`.
  - Else, decrement `count`.
- At the end, the `candidate` will be the majority element.

#### 💻 Java Code:
```java
class Solution {
    public int majorityElement(int[] nums) {
        int count = 0;
        int candidate = 0;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }

            if (num == candidate) {
                count++;
            } else {
                count--;
            }
        }

        return candidate;
    }
}
```

#### ⏱️ Complexity:
- **Time:** O(n)  
- **Space:** O(1)

---

### 🔍 Dry Run: Input = `[2, 2, 1, 1, 1, 2, 2]`

| Step | num | candidate | count | Action |
|------|-----|-----------|-------|--------|
| 1    | 2   | 2         | 1     | Set candidate to 2 |
| 2    | 2   | 2         | 2     | Same as candidate → count++ |
| 3    | 1   | 2         | 1     | Different → count-- |
| 4    | 1   | 2         | 0     | Different → count-- |
| 5    | 1   | 1         | 1     | count = 0, new candidate = 1 |
| 6    | 2   | 1         | 0     | Different → count-- |
| 7    | 2   | 2         | 1     | count = 0, new candidate = 2 |

✅ Final candidate = **2**

---

### 🧪 Example

```java
Input: nums = [2,2,1,1,1,2,2]
Output: 2
```
