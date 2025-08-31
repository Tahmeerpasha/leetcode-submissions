# **Trapping Rain Water (Leetcode 42)**

### **Problem**

Given an array `height[]` representing elevation maps, compute how much water can be trapped after raining.

---

## **Brute Force Approach (Prefix + Suffix Arrays)**

**Idea**:
At every index, trapped water depends on the **min of the tallest bar on the left and right** minus the height at that index.
So → precompute prefix maximums and suffix maximums.

**Steps**:

1. Build `prefixMax[i] = max(height[0..i])`
2. Build `suffixMax[i] = max(height[i..n-1])`
3. For each `i`, trapped water = `min(prefixMax[i], suffixMax[i]) - height[i]` (only if positive).

**Code:**
```java
    public int trap(int[] height) {
        int[] prefixMax = prefixMax(height);
        int[] suffixMax = suffixMax(height);
        int total = 0;
        for (int i = 0; i < height.length; i++) {
            if (height[i] < prefixMax[i] && height[i] < suffixMax[i]) {
                total += Math.min(prefixMax[i], suffixMax[i]) - height[i];
            }
        }
        return total;
    }

    int[] prefixMax(int[] arr) {
        int prefix[] = new int[arr.length];
        prefix[0] = arr[0];
        for (int i = 1; i < prefix.length; i++) {
            prefix[i] = Math.max(prefix[i - 1], arr[i]);
        }
        return prefix;
    }

    int[] suffixMax(int[] arr) {
        int suffix[] = new int[arr.length];
        suffix[arr.length - 1] = arr[arr.length - 1];
        for (int i = suffix.length - 2; i >= 0; i--) {
            suffix[i] = Math.max(suffix[i + 1], arr[i]);
        }
        return suffix;
    }
```
**Code Complexity**:

* **Time**: `O(3n)` → (prefix array + suffix array + loop to calculate)
* **Space**: `O(2n)` → prefix & suffix arrays

✅ Easy to understand and implement.
❌ Uses extra space, not efficient.

---

## **Better Approach (1 Prefix Variable + 1 Suffix Array)**

**Idea**:
We don’t really need a full prefix array. We can maintain a running `prefixMax` while iterating, and just keep the suffix array.

**Steps**:

1. Compute suffix array (`suffixMax`).
2. Keep a running `prefixMax` as you iterate.
3. At each index, trapped water = `min(prefixMax, suffixMax[i]) - height[i]`.

**Code:**
```java
    public int trap(int[] height) {
        int[] suffixMax = suffixMax(height);
        int total = 0;
        int prefixMax = 0;
        for (int i = 0; i < height.length; i++) {
            prefixMax = Math.max(prefixMax, height[i]);
            if (height[i] < prefixMax && height[i] < suffixMax[i]) {
                total += Math.min(prefixMax, suffixMax[i]) - height[i];
            }
        }
        return total;
  }
```
**Code Complexity**:

* **Time**: `O(2n)` → (suffix array + loop)
* **Space**: `O(n)` → suffix array

✅ Saves space compared to brute.
❌ Still not fully optimal.

---

## **Optimal Approach (Two-Pointer with LeftMax & RightMax)**

**Idea**:
We don’t need any extra arrays. Instead, use **two pointers** (`left` and `right`) with **leftMax & rightMax**.

* Water at index `i` is bounded by the smaller side (leftMax or rightMax).
* Always move the pointer on the smaller side inward.

**Steps**:

1. Initialize `left=0`, `right=n-1`, `leftMax=0`, `rightMax=0`.
2. While `left < right`:

   * If `height[left] <= height[right]`:

     * If `height[left] < leftMax` → add water.
     * Else update `leftMax`.
     * Move `left++`.
   * Else:

     * If `height[right] < rightMax` → add water.
     * Else update `rightMax`.
     * Move `right--`.

**Code:**
```java
    public int trap(int[] height) {
        int leftMax = 0, rightMax = 0, total = 0;
        int left = 0, right = height.length - 1;
        while (left < right) {
            if (height[left] <= height[right]) {
                if (leftMax > height[left])
                    total += leftMax - height[left];
                else
                    leftMax = height[left];
                left++;
            } else {
                if (rightMax > height[right])
                    total += rightMax - height[right];
                else
                    rightMax = height[right];
                right--;
            }
        }
        return total;
  }
```
**Code Complexity**:

* **Time**: `O(n)`
* **Space**: `O(1)`

✅ Most optimal solution.
✅ Single pass.
✅ No extra space.

---

## **Tips & Tricks**

* **Visualization helps**: Draw histogram, highlight trapped areas.
* **Formula to remember**:
  `water[i] = min(leftMax[i], rightMax[i]) - height[i]`
* **Edge cases**:

  * Very short arrays (`n < 3` → no water).
  * Flat elevation (all equal heights → no water).
* Interview trick: If stuck, explain **brute force first** → then optimize step by step to show clarity.

---

👉 Best way to present in interviews:

* Start with brute (prefix + suffix arrays).
* Improve by removing one prefix array.
* Finally jump to **two-pointer optimal** (clean & efficient).

---
