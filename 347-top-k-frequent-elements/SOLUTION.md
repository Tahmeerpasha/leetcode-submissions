## 🔹 Problem:

**Find the `k` most frequent elements** in an array of integers.
**Example:**
Input: `nums = [1,1,1,2,2,3], k = 2`
Output: `[1,2]`

---

## 🔸 Approach 1: Brute Force

**Idea:**
Count the frequency of each element → Sort by frequency → Return top `k`.

**Steps:**

1. Use a `HashMap` to store frequencies.
2. Convert map entries to a list.
3. Sort the list by frequency in descending order.
4. Pick top `k` elements.

**Code Sketch:**

```java
public int[] topKFrequent(int[] nums, int k) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int num : nums)
        map.put(num, map.getOrDefault(num, 0) + 1);

    List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
    list.sort((a, b) -> b.getValue() - a.getValue());

    int[] result = new int[k];
    for (int i = 0; i < k; i++)
        result[i] = list.get(i).getKey();
    return result;
}
```

**Time Complexity:**

* Building map → `O(n)`
* Sorting → `O(n log n)`
  **Space Complexity:** `O(n)`

✅ **Works fine for small `n`, but sorting is costly.**

---

## 🔸 Approach 2: Better (Min Heap)

**Idea:**
Use a **Min Heap (PriorityQueue)** to keep only the top `k` frequent elements.
We push all map entries into the heap and pop the smallest when size > k.

**Steps:**

1. Build frequency map.
2. Create a **min heap** of size `k` (sorted by frequency).
3. Iterate over map entries, add to heap, and remove smallest if heap exceeds size `k`.
4. Extract heap elements → These are your top k.

**Code:**

```java
public int[] topKFrequent(int[] nums, int k) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int num : nums)
        map.put(num, map.getOrDefault(num, 0) + 1);

    PriorityQueue<Map.Entry<Integer, Integer>> pq =
        new PriorityQueue<>((a, b) -> a.getValue() - b.getValue());

    for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
        pq.offer(entry);
        if (pq.size() > k)
            pq.poll();
    }

    int[] result = new int[k];
    for (int i = k - 1; i >= 0; i--)
        result[i] = pq.poll().getKey();

    return result;
}
```

**Time Complexity:**

* Building map → `O(n)`
* Heap operations → `O(n log k)`
  **Space Complexity:** `O(n + k)`

✅ **Efficient when k ≪ n.**
⚠️ If `k` is large (close to `n`), heap offers no real benefit.

---

## 🔸 Approach 3: Optimal (Bucket Sort)

**Idea:**
Since frequency can’t exceed `n`, use an **array of buckets**, where
`bucket[i]` stores all numbers appearing `i` times.
Then, traverse from highest freq to lowest until you collect `k` elements.

**Steps:**

1. Count frequencies in a map.
2. Create a list of buckets of size `n + 1`.
3. For each `(num, freq)` → put `num` in `bucket[freq]`.
4. Traverse from end of bucket (high freq → low) and collect elements.

**Code:**

```java
public int[] topKFrequent(int[] nums, int k) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int num : nums)
        map.put(num, map.getOrDefault(num, 0) + 1);

    List<List<Integer>> bucket = new ArrayList<>(nums.length + 1);
    for (int i = 0; i <= nums.length; i++)
        bucket.add(new ArrayList<>());

    for (Map.Entry<Integer, Integer> entry : map.entrySet())
        bucket.get(entry.getValue()).add(entry.getKey());

    int[] result = new int[k];
    int j = 0;
    for (int i = nums.length; i >= 0 && j < k; i--) {
        for (int num : bucket.get(i)) {
            result[j++] = num;
            if (j == k) break;
        }
    }
    return result;
}
```

**Time Complexity:**

* Building map → `O(n)`
* Bucketing and traversal → `O(n)`
  **Space Complexity:** `O(n)`

✅ **True linear time solution (`O(n)`).**
⚡ Best choice for interviews when `n` is large.

---

## 💡 Tips & Tricks

* **When to use Heap:** If you need *top k* but elements can’t be easily bucketed or you’re dealing with continuous data streams.
* **When to use Bucket Sort:** When the range of frequencies is bounded by `n`.
* **Remember:** Frequency-based problems often reduce to *count + sort by value*.
* **Edge cases:**

  * All numbers appear once.
  * `k == nums.length`.
  * Large input with few unique elements.

---

## ⚙️ Quick Recap Table

| Approach | Technique             | Time       | Space    | Notes             |
| -------- | --------------------- | ---------- | -------- | ----------------- |
| 1        | HashMap + Sort        | O(n log n) | O(n)     | Simple, intuitive |
| 2        | HashMap + Min Heap    | O(n log k) | O(n + k) | Great for small k |
| 3        | HashMap + Bucket Sort | O(n)       | O(n)     | Best overall      |

---
