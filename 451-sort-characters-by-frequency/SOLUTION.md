## 🔍 Problem Summary

**Given:** A string `s`
**Goal:** Return the characters in the string **sorted by decreasing frequency**.

Example:
**Input:** `"tree"`
**Output:** `"eert"` or `"eetr"` (both valid)

---

## 🧠 Key Idea

* Count frequency of each character.
* Sort characters by their frequency (descending).
* Rebuild the string.

---

## 🌀 Brute Force Approach (Sort using custom comparator)

### 🔧 Logic:

* Use a `HashMap` to count frequency of each character.
* Sort map entries based on values (frequency).
* Rebuild the string by repeating characters according to their frequency.

### ✅ Code:

```java
public String frequencySort(String s) {
    Map<Character, Integer> map = new HashMap<>();
    for (char c : s.toCharArray())
        map.put(c, map.getOrDefault(c, 0) + 1);

    List<Map.Entry<Character, Integer>> list = new ArrayList<>(map.entrySet());
    list.sort((a, b) -> b.getValue() - a.getValue());

    StringBuilder sb = new StringBuilder();
    for (Map.Entry<Character, Integer> entry : list)
        sb.append(String.valueOf(entry.getKey()).repeat(entry.getValue()));

    return sb.toString();
}
```

### 📊 Complexity:

* **Time:** O(n log k) — where `k` is number of unique characters (at most 62 for alphanumeric)
* **Space:** O(k + n)

---

## ⚡ Optimal Approach – Using **Bucket Sort**

### 🔧 Logic:

* Count frequencies.
* Create `buckets` where index represents frequency.
* Append characters from high to low frequency.

### ✅ Code:

```java
public String frequencySort(String s) {
    Map<Character, Integer> freqMap = new HashMap<>();
    for (char c : s.toCharArray())
        freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);

    List<Character>[] buckets = new ArrayList[s.length() + 1];
    for (char c : freqMap.keySet()) {
        int freq = freqMap.get(c);
        if (buckets[freq] == null) buckets[freq] = new ArrayList<>();
        buckets[freq].add(c);
    }

    StringBuilder sb = new StringBuilder();
    for (int i = buckets.length - 1; i >= 1; i--) {
        if (buckets[i] != null) {
            for (char c : buckets[i]) {
                sb.append(String.valueOf(c).repeat(i));
            }
        }
    }

    return sb.toString();
}
```

### 📊 Complexity:

* **Time:** O(n)
* **Space:** O(n) (for buckets + result)

---

## 🧪 Other (Less Efficient / Alternate)

### Using Java Streams (Your Code):

* Sort `Map.Entry` using Java 8 Streams.
* Collect into `LinkedHashMap` to maintain order.
* Use `.repeat()` for reconstruction.

✅ Clean and modern, but **not as fast** as bucket sort due to stream and sorting overhead.

---

## ✅ Summary

| Approach                | Time       | Space | Notes                                |
| ----------------------- | ---------- | ----- | ------------------------------------ |
| Brute Force (Sort Map)  | O(n log k) | O(n)  | Uses list sorting                    |
| ⚡ Optimal (Bucket Sort) | O(n)       | O(n)  | Most efficient for large input sizes |
| Java Streams            | O(n log k) | O(n)  | Clean but slower than bucket sort    |

---
