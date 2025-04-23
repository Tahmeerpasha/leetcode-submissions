## 🔄 LeetCode 283: Move Zeroes

### ✅ Problem Statement
Given an integer array `nums`, move all `0`'s to the end of it while maintaining the relative order of the non-zero elements.

---

## 💡 Approaches

---

### 1. 🔨 Brute Force Approach

#### 🧠 Logic:
- Create a new array.
- Copy all non-zero elements first.
- Then fill the remaining positions with zeroes.

#### 💻 Java Code:
```java
class Solution {
    public void moveZeroes(int[] nums) {
        int[] result = new int[nums.length];
        int index = 0;
        
        for (int num : nums) {
            if (num != 0) {
                result[index++] = num;
            }
        }
        
        for (int i = 0; i < nums.length; i++) {
            nums[i] = result[i];
        }
    }
}
```

#### ⏱️ Complexity:
- **Time:** O(n)
- **Space:** O(n) (extra array used)

---

### 2. 🔧 Better Approach

#### 🧠 Logic:
- Use a two-pass approach.
- First pass: Move all non-zero elements to the front.
- Second pass: Fill the remaining with `0`s.

#### 💻 Java Code:
```java
class Solution {
    public void moveZeroes(int[] nums) {
        int index = 0;

        // Move all non-zero elements to the front
        for (int num : nums) {
            if (num != 0) {
                nums[index++] = num;
            }
        }

        // Fill the rest with zeroes
        while (index < nums.length) {
            nums[index++] = 0;
        }
    }
}
```

#### ⏱️ Complexity:
- **Time:** O(n)
- **Space:** O(1)

---

### 3. ⚡ Optimized Approach (In-place Swapping)

#### 🧠 Logic:
- Use two pointers `i` and `j`.
- `j` tracks the next non-zero placement.
- If `nums[i] != 0`, swap it with `nums[j]`, increment `j`.

#### 💻 Java Code:
```java
class Solution {
    public void moveZeroes(int[] nums) {
        int j = 0, temp;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                j++;
            }
        }
    }
}
```

#### ⏱️ Complexity:
- **Time:** O(n)
- **Space:** O(1)

---

### 🧪 Example Dry Run

```java
Input:  [0, 1, 0, 3, 12]
Output: [1, 3, 12, 0, 0]
```

- `i = 0` → skip  
- `i = 1` → swap(1, 0) → [1, 0, 0, 3, 12]  
- `i = 3` → swap(3, 0) → [1, 3, 0, 0, 12]  
- `i = 4` → swap(12, 0) → [1, 3, 12, 0, 0]
