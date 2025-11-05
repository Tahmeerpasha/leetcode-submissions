# Problem intuition (1-line)

We need to partition the multiset of card values into groups of size `groupSize` where each group is a sequence of consecutive integers. If `hand.length % groupSize != 0` it’s impossible.

---

# 1) Brute force — backtracking (very slow)

Idea
Try to build groups one by one by picking `groupSize` unused cards that form a consecutive sequence; backtrack whenever you get stuck.

Why it’s brute: we explore permutations/combinations of assignments for cards — exponential blowup.

Pseudo / sketch (Java-style):

```java
// very high-level sketch; impractical for larger n
boolean backtrack(int[] hand, boolean[] used, int groupSize, int formedGroups) {
    if (formedGroups == hand.length / groupSize) return true;
    // find first unused index i -> start value
    // try to form group starting at hand[i] by locating groupSize distinct unused elements with consecutive values
    // for each possibility mark used, recurse, unmark (backtrack)
    return false;
}
```

Time complexity: **exponential** in `n` (roughly factorial/exponential depending on duplicates).
Space: O(n) recursion + used array.

When to use: Only for tiny inputs or for conceptual understanding. Not for real constraints.

---

# 2) Better — sort + greedy using visited array

Idea
Sort the array. Iterate left-to-right. For each value not yet used, greedily try to form a group `val, val+1, ..., val+groupSize-1` by scanning ahead in the sorted array and using the first matching unused occurrences.

Sketch (Java):

```java
Arrays.sort(hand);
boolean[] used = new boolean[hand.length];

for (int i = 0; i < hand.length; i++) {
    if (used[i]) continue;
    int start = hand[i];
    // need to find groupSize elements start..start+groupSize-1
    for (int num = start, cnt = 0; cnt < groupSize; ++num) {
        // scan j from left to find an unused hand[j] == num
        boolean found = false;
        for (int j = i; j < hand.length; j++) {
            if (!used[j] && hand[j] == num) { used[j] = true; found = true; break; }
        }
        if (!found) return false;
        cnt++;
    }
}
return true;
```

Complexity: Sorting O(n log n). The inner scans can make this **O(n * groupSize)** or worst-case **O(n^2)** if groupSize ~ n or many scans are repeated.
Space: O(n) for `used`.

When it’s decent: works for moderate `n` and typical `groupSize`, simpler to reason about than backtracking. But not the best asymptotically.

---

# 3) Optimal — frequency map with ordered keys (TreeMap or ordered map)

Idea (standard clean solution)
Count frequencies of each value. Always pick the smallest available value `x` (the leftmost start). Use its count `c` to remove `c` occurrences from each consecutive value `x, x+1, ..., x+groupSize-1`. Repeat until all counts are zero.

Why it works: The smallest remaining value must start some group(s); you must pair each of its occurrences with the next `groupSize-1` consecutive values. Doing fewer than its count would leave some of that smallest value unpaired later, which is impossible because there are no smaller values to pair it with.

Java implementation (clean, efficient):

```java
class Solution {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        if (hand.length % groupSize != 0) return false;

        TreeMap<Integer, Integer> freq = new TreeMap<>();
        for (int x : hand) freq.put(x, freq.getOrDefault(x, 0) + 1);

        while (!freq.isEmpty()) {
            int start = freq.firstKey();
            int count = freq.get(start);

            // try to take 'count' groups starting at start
            for (int v = start; v < start + groupSize; v++) {
                Integer f = freq.get(v);
                if (f == null || f < count) return false;
                if (f == count) freq.remove(v);
                else freq.put(v, f - count);
            }
        }
        return true;
    }
}
```

Complexity:

* Let `n` = total cards, `m` = number of distinct values (m ≤ n).
* Building the TreeMap: O(n log m).
* Each distinct key is processed once; for each processed start we touch `groupSize` successive keys. In total we do at most `m * groupSize` key accesses — but since each key is removed once, total number of map updates is O(n) across the run.
* Each map access/update is O(log m). So overall complexity is **O(n log m)** → worst-case **O(n log n)**.
  Space: O(m) for the map (≤ O(n)).

This is the accepted, robust solution for typical contest/LeetCode constraints.

---

# Notes on the provided PriorityQueue solution

Your posted code used a `PriorityQueue` of all numbers and a frequency map. It works functionally if implemented carefully: you poll smallest numbers and skip if its freq is already zero. But there are inefficiencies:

* The PQ stores all `n` elements (including duplicates), so you may poll many duplicates and waste operations. Each poll is O(log n), so worst-case cost is higher than TreeMap solution.
* TreeMap stores distinct keys only (m entries), so it’s typically faster and cleaner.
* Your code needs to be careful about `getOrDefault` and decrementing counts (you used it correctly), but PQ approach is less elegant.

If you prefer PQ, a better version would first sort and iterate — no PQ at all — or use the TreeMap frequency approach.

---

# Edge cases & small checks

* If `hand.length % groupSize != 0` → immediately `false`.
* If `groupSize == 1` → always `true`.
* Large gaps: if any required consecutive value is missing or its remaining frequency is less than required, return `false`.
* Negative numbers? The algorithm works for any integers (TreeMap handles negatives).
* Watch for integer overflow if you try to use arithmetic on very large values — not typical here.

---

# Quick correctness sketch (optimal)

Always removing `count` occurrences starting from smallest key is greedy but correct because the smallest value cannot be part of any group that starts with a number smaller than it. So any valid partition must consume all its occurrences paired with next `groupSize-1` values — our greedy does exactly that.

---

# Tips & tricks (contest / interview)

* Check divisibility early (`n % k`).
* Use frequency map + ordered keys for interval/group problems where “consecutive” matters (TreeMap in Java, OrderedDict+heap in Python/other).
* Avoid PQ with duplicates — it increases work. Prefer TreeMap or sorting + pointer/visited approach.
* If many distinct values are small or bounded, consider counting sort style arrays to get linear time.
* Always consider `m` = number of distinct values: many map-based operations depend on `log m`, not `log n`.
* Write a quick dry-run on small cases (duplicates, missing middle values, large gaps).

---

# Short example walkthrough

hand = [1,2,3,3,4,4,5,6], groupSize = 4
freq: {1:1,2:1,3:2,4:2,5:1,6:1}
start=1,count=1 → remove 1 from 1..4 → freq becomes {3:1,4:1,5:1,6:1}
start=3,count=1 → remove 1 from 3..6 → freq empty → return true.

---
