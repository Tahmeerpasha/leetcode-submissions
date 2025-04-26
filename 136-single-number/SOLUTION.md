# 136. Single Number

## Problem
> Given a non-empty array of integers `nums`, every element appears twice except for one. Find that single one.

---

## Brute Force Approach

**Idea:**  
For each element, count its occurrence by looping through the array.  
The element with count = 1 is our answer.

### Code
```java
class Solution {
    public int singleNumber(int[] nums) {
        for(int i=0; i<nums.length; i++){
            int count = 0;
            for(int j=0; j<nums.length; j++){
                if(nums[i] == nums[j]){
                    count++;
                }
            }
            if(count == 1) return nums[i];
        }
        return -1; // This case shouldn't occur as per problem description.
    }
}
```

### Time Complexity:  
- **O(n²)** → because for each element, we traverse the array again.

### Space Complexity:  
- **O(1)** → no extra space used.

---

## Better Approach

**Idea:**  
Use a HashMap to count occurrences.

### Code
```java
import java.util.HashMap;

class Solution {
    public int singleNumber(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums){
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (int key : map.keySet()){
            if (map.get(key) == 1)
                return key;
        }
        return -1;
    }
}
```

### Time Complexity:
- **O(n)** → traverse array twice

### Space Complexity:
- **O(n)** → extra space for HashMap

---

## Optimized Approach (using XOR)

**Idea:**  
- XOR of two same numbers is 0.
- XOR of any number with 0 is the number itself.
- So, XORing all numbers together cancels out the duplicates and leaves the single number.

### Code
```java
class Solution {
    public int singleNumber(int[] nums) {
        int answer = 0;
        for (int i = 0; i < nums.length; i++) {
            answer ^= nums[i];
        }
        return answer;
    }
}
```

### Time Complexity:
- **O(n)** → single traversal

### Space Complexity:
- **O(1)** → no extra space

---

## Dry Run (XOR Approach)

Given:
```
nums = [4,1,2,1,2]
```
Step-by-step:

| Step | num  | answer (answer ^= num) |
|:----:|:----:|:----------------------:|
| 0    | 4    | 0 ⊕ 4 = 4               |
| 1    | 1    | 4 ⊕ 1 = 5               |
| 2    | 2    | 5 ⊕ 2 = 7               |
| 3    | 1    | 7 ⊕ 1 = 6               |
| 4    | 2    | 6 ⊕ 2 = 4               |

Final `answer = 4`, which is the single number.

---
