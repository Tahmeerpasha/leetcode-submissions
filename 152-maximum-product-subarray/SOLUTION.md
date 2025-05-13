### ✅ Problem Statement:

Given an integer array `nums`, find the **contiguous subarray (containing at least one number)** which has the **largest product**, and return that product.

---

### 🔍 Example:

```
Input: nums = [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product = 6.
```

---

### 🧠 Brute Force Approach

#### 🔧 Logic:

* Try all possible subarrays.
* For each subarray, calculate the product and update the max.

#### ✅ Steps:

1. Use two nested loops to consider every subarray.
2. Keep multiplying elements from `i` to `j`.
3. Keep track of the maximum product.

#### ⏱️ Time Complexity:

* `O(n^2)`: All possible subarrays.

#### 💾 Space Complexity:

* `O(1)`: Constant space.

#### ✅ Code:

```java
int max = Integer.MIN_VALUE;
for(int i = 0; i < nums.length; i++) {
    int product = 1;
    for(int j = i; j < nums.length; j++) {
        product *= nums[j];
        max = Math.max(max, product);
    }
}
```

---

### ⚡ Optimal Approach (Prefix & Suffix Traversal)

#### 🔧 Key Insight:

* A **negative number** can turn a small product into a large one when multiplied with another negative.
* **Zeroes** reset the product. So we start fresh after a zero.

#### ✅ Strategy:

* Traverse from **left to right (prefix)** and **right to left (suffix)**.
* Track the product from both ends.
* Reset to 1 if product becomes 0.
* Keep updating the maximum product seen.

#### 🔁 Why Prefix & Suffix?

* To handle negative numbers correctly. A negative product might become positive when multiplied by another negative later.

#### ⏱️ Time Complexity:

* `O(n)`: Single pass from both ends.

#### 💾 Space Complexity:

* `O(1)`: No extra space used.

#### ✅ Code:

```java
int max = Integer.MIN_VALUE;
int prefix = 1, suffix = 1;

for(int i = 0; i < nums.length; i++) {
    if(prefix == 0) prefix = 1;
    if(suffix == 0) suffix = 1;

    prefix *= nums[i];                      // left to right
    suffix *= nums[nums.length - 1 - i];    // right to left

    max = Math.max(max, Math.max(prefix, suffix));
}
return max;
```
![image](https://github.com/user-attachments/assets/07e6ff27-fad2-4037-8f75-50f0ff3b975c)

---

### ✨ Edge Case Examples:

1. `nums = [-2, 0, -1]` → Output: `0`
   (Subarray must contain at least one number; zero resets the product.)
2. `nums = [-2,3,-4]` → Output: `24`
   (Product becomes positive by two negatives.)

---

### 🧠 Alternate Optimal Approach (Kadane's variant for product)

Track:

* `maxProd`: max product ending at current index
* `minProd`: min product ending at current index (important for handling negatives)

```java
int maxProd = nums[0], minProd = nums[0], result = nums[0];

for (int i = 1; i < nums.length; i++) {
    if (nums[i] < 0) {
        int temp = maxProd;
        maxProd = minProd;
        minProd = temp;
    }

    maxProd = Math.max(nums[i], maxProd * nums[i]);
    minProd = Math.min(nums[i], minProd * nums[i]);

    result = Math.max(result, maxProd);
}
return result;
```

#### ⏱️ Time: `O(n)`, Space: `O(1)`

---

### ✅ Final Takeaways:

* Handle negative numbers and zero carefully.
* Use **prefix-suffix method** or **modified Kadane's** for optimal solution.
* Avoid brute force in interviews unless asked for exploration.
