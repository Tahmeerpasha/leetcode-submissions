## **Problem Recap**

We are given an integer array `nums` where every element appears **exactly three times**, except for one element that appears exactly **once**. We must find the single element.

---

## **1️⃣ Brute Force – Using HashMap**

**Idea:**

* Store the frequency of each number in a `HashMap`.
* Iterate over the map to find the number whose frequency is `1`.

**Pseudocode:**

```java
Map<Integer, Integer> map = new HashMap<>();
for (int num : nums)
    map.put(num, map.getOrDefault(num, 0) + 1);

for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
    if (entry.getValue() == 1)
        return entry.getKey();
}
```

**Complexity:**

* **Time:** `O(N)` → One pass to count + one pass to find unique element.
* **Space:** `O(N)` → Map stores all unique elements.

**When to use:**
✅ Simple and intuitive.
❌ Not optimal for space.

---

## **2️⃣ Better – Bit Counting**

**Idea:**

* For each **bit position (0–31)**, count how many numbers have that bit set.
* If the count of set bits at a position is **not divisible by 3**, that bit belongs to the unique number.

**Reasoning:**

* Since every number except one appears **three times**, bit counts should be multiples of 3 for those numbers. The leftover `1` comes from the unique number.

**Code snippet from your comment:**

```java
int ans = 0;
for (int bitIndex = 0; bitIndex <= 31; bitIndex++) {
    int count = 0;
    for (int num : nums) {
        if ((num & (1 << bitIndex)) != 0)
            count++;
    }
    if (count % 3 == 1)
        ans |= (1 << bitIndex);
}
return ans;
```

**Complexity:**

* **Time:** `O(32 * N)` → Effectively `O(N)` since 32 is constant.
* **Space:** `O(1)`.

**When to use:**
✅ Good balance of simplicity and space efficiency.
❌ Slightly more complex than brute force but still understandable in interviews.

---

## **3️⃣ Optimal 1 – Sorting**

**Idea:**

* Sort the array.
* Since elements appear in triplets, the unique number will be the first element that does not match its neighbor at every 3rd step.

**Code snippet from your comment:**

```java
Arrays.sort(nums);
for (int i = 1; i < nums.length; i += 3) {
    if (nums[i - 1] != nums[i])
        return nums[i - 1];
}
return nums[nums.length - 1];
```

**Complexity:**

* **Time:** `O(N log N)` (due to sorting) + `O(N/3)` for scanning.
* **Space:** `O(1)` if sorting in-place, `O(N)` if using extra memory.

**When to use:**
✅ Easier to implement if bit manipulation isn’t your strength.
❌ Not the fastest for large `N` because of sorting.

---

## **4️⃣ Optimal 2 – Bitmasking (State Tracking)**

**Idea:**

* Use **two variables** (`ones`, `twos`) to track bits seen **once** and **twice**.
* Iterate through numbers and update `ones` and `twos` so that bits are “rotated” between states:

  * First occurrence → goes into `ones`
  * Second occurrence → moves from `ones` to `twos`
  * Third occurrence → removed from both (`ones` and `twos`)

**State transitions:**

```
ones = (num ^ ones) & ~twos;
twos = (num ^ twos) & ~ones;
```

**Explanation:**

* `~twos` ensures bits that are in `twos` are removed from `ones`.
* Similarly, `~ones` ensures bits in `ones` are removed from `twos`.
* After processing all numbers, `ones` will contain the unique number.

**Code snippet:**

```java
int ones = 0, twos = 0;
for (int num : nums) {
    ones = (num ^ ones) & ~twos;
    twos = (num ^ twos) & ~ones;
}
return ones;
```

**Complexity:**

* **Time:** `O(N)` → Single pass.
* **Space:** `O(1)`.

**When to use:**
✅ Fastest and most memory efficient.
❌ Not intuitive—requires practice to recall and explain.

---

## **📊 Summary Table**

| Approach                | Time Complexity | Space Complexity | Pros                     | Cons                           |
| ----------------------- | --------------- | ---------------- | ------------------------ | ------------------------------ |
| **Brute (Map)**         | O(N)            | O(N)             | Easy to implement        | High space usage               |
| **Better (Bit Count)**  | O(32·N) ≈ O(N)  | O(1)             | Space efficient, logical | Needs bit logic                |
| **Optimal 1 (Sort)**    | O(N log N)      | O(1)             | Easy to code             | Slower due to sorting          |
| **Optimal 2 (Bitmask)** | O(N)            | O(1)             | Fastest, no extra space  | Hard to think of in interviews |

---
