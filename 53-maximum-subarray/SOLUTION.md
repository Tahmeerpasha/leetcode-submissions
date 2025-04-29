### ✅ Problem: Maximum Subarray (Kadane's Algorithm)

Given an integer array `nums`, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

---

### ✅ Brute Force Approaches:

#### 🔴 O(n³) Time | O(n³) Space

```java
int max = Integer.MIN_VALUE;
for (int i = 0; i < n; i++) {
    for (int j = i; j < n; j++) {
        int sum = 0;
        for (int k = i; k <= j; k++)
            sum += nums[k];
        max = Math.max(max, sum);
    }
}
return max;
```

- Checks every possible subarray.
- Too slow for large inputs.

---

#### 🔴 O(n²) Time | O(n²) Space (Using ArrayList to store sums)

```java
ArrayList<Integer> answers = new ArrayList<>();
for(int i=0; i<n ; i++){
    int sum = 0;
    for(int j=i; j<n; j++){
        sum += nums[j];
        answers.add(sum);
    }
}
answers.sort(null);
return answers.get(answers.size()-1);
```

- Avoids the inner-most loop.
- Still not optimal.

---

### ✅ Optimized O(n²) | O(1) Space

```java
int max = Integer.MIN_VALUE;
for (int i = 0; i < n; i++) {
    int sum = 0;
    for (int j = i; j < n; j++) {
        sum += nums[j];
        max = Math.max(max, sum);
    }
}
return max;
```

---

### ✅ Best Approach: Kadane’s Algorithm  
#### ⏱ Time: O(n) | 📦 Space: O(1)

```java
int max = Integer.MIN_VALUE;
int sum = 0;
for (int i = 0; i < nums.length; i++) {
    if (sum < 0) sum = 0;
    sum += nums[i];
    max = Math.max(max, sum);
}
return max;
```

- Continually extends the subarray unless the sum drops below 0.
- Reset sum when it goes negative — that’s the **key trick**!

---

### ✅ Kadane’s Algorithm Intuition:
- You either continue your current subarray (if `sum >= 0`)  
- Or start a new subarray from the current index (if `sum < 0`).

---

### 📊 Visual Representation:
![Uploading image.png…]()
