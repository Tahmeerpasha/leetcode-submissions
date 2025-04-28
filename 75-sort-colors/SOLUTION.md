# 75. Sort Colors

### Problem:
Sort an array containing 0s, 1s, and 2s **in-place** without using extra space.  
(Think of it as sorting colors Red(0), White(1), and Blue(2)).

---

## 1. Brute Force Approach

### Idea:
- **Count** the number of 0s, 1s, and 2s.
- **Overwrite** the original array based on the counts.

### Code:
```java
class Solution {
    public void sortColors(int[] nums) {
        int count0 = 0, count1 = 0, count2 = 0;
        for(int num : nums){
            if(num == 0) count0++;
            else if(num == 1) count1++;
            else count2++;
        }

        int i = 0;
        while(count0-- > 0) nums[i++] = 0;
        while(count1-- > 0) nums[i++] = 1;
        while(count2-- > 0) nums[i++] = 2;
    }
}
```

### Time: O(n)  
### Space: O(1) *(extra variables, no extra array)*

---

## 2. Better Approach (Sort function)

- Use Java’s inbuilt `Arrays.sort(nums)`.

```java
import java.util.Arrays;

class Solution {
    public void sortColors(int[] nums) {
        Arrays.sort(nums);
    }
}
```
### Time: O(n log n)  
### Space: O(1)

---

## 3. Optimal Approach (Dutch National Flag Algorithm)

### Idea:
- Maintain three regions:
  - **[0, low-1] → 0s**
  - **[low, mid-1] → 1s**
  - **[high+1, end] → 2s**
- Traverse the array using **mid** pointer:
  - If 0, swap with `low`, increment both.
  - If 1, just move `mid`.
  - If 2, swap with `high`, decrement `high` only.

---
### Code:
```java
class Solution {
    public void sortColors(int[] nums) {
        int low=0, mid=0, high=nums.length-1;
        while(mid <= high){
            if(nums[mid] == 0) swap(nums, low++, mid++);
            else if (nums[mid] == 2) swap(nums, mid, high--);
            else mid++;
        }
    }

    void swap(int[] nums, int fromIndex, int toIndex){
        if(fromIndex>toIndex) return;
        int temp = nums[fromIndex];
        nums[fromIndex] = nums[toIndex];
        nums[toIndex] = temp;
    }
}
```

### Time: O(n)  
### Space: O(1)

---

# 🔥 Dry Run

Example:  
`nums = [2,0,2,1,1,0]`

| low | mid | high | nums               | action             |
|:---:|:---:|:----:|:------------------:|:------------------:|
|  0  |  0  |  5   | [2,0,2,1,1,0]       | swap(mid,high)     |
|  0  |  0  |  4   | [0,0,2,1,1,2]       | swap(low,mid)      |
|  1  |  1  |  4   | [0,0,2,1,1,2]       | swap(low,mid)      |
|  2  |  2  |  4   | [0,0,2,1,1,2]       | swap(mid,high)     |
|  2  |  2  |  3   | [0,0,1,1,2,2]       | mid++ (1 found)    |
|  2  |  3  |  3   | [0,0,1,1,2,2]       | mid++ (1 found)    |

Final array: `[0,0,1,1,2,2]`

---

# ✨ Visual (Summary)

```
Initial: [2,0,2,1,1,0]
low=0, mid=0, high=5

[2,0,2,1,1,0]  -> Swap(0,5) => [0,0,2,1,1,2]
[0,0,2,1,1,2]  -> Swap(0,0) => [0,0,2,1,1,2]
[0,0,2,1,1,2]  -> Swap(1,1) => [0,0,2,1,1,2]
[0,0,2,1,1,2]  -> Swap(2,4) => [0,0,1,1,2,2]
[0,0,1,1,2,2]  -> No Swap (mid++)
[0,0,1,1,2,2]  -> No Swap (mid++)

Sorted colors: [0,0,1,1,2,2]
```

---
