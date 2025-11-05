# Brute-force (exponential)

**Idea**
Try every possible ordering/permutation of tasks (or backtrack placing tasks into time slots) and compute the minimum length schedule that obeys the cooldown `n`. This is mostly academic — only useful to reason about correctness.

**How it works (backtracking sketch)**

* Choose a task for the next time unit from the remaining tasks such that placing it doesn't violate the cooldown (track last-execution times or remaining counts + cooldown state).
* Recurse until all tasks placed and track minimum time (including idle slots).
* Because identical tasks are indistinguishable, the implementation is messy (avoid duplicate permutations), but complexity remains factorial/exponential.

**Complexity**

* Time: `O(k!)` in worst case (or exponential, where `k = number of tasks`) — impractical beyond very small sizes.
* Space: `O(k)` recursion stack + bookkeeping.

**When to use**

* Only for tiny inputs or to prove correctness by example. Not for production.

---

# Better (simulation using a max-heap / priority queue) — Greedy simulation

**Idea**
Always execute the currently most frequent available task, then put it on cooldown for `n` slots. Use a max-heap of remaining frequencies and simulate rounds of length `n+1` (execute up to `n+1` distinct tasks per round). Your posted code is this approach.

**How it works (summary)**

* Count frequencies of tasks (26 letters).
* Put frequencies > 0 into a max-heap.
* Repeatedly run cycles of length `n+1`:

  * In a cycle, poll up to `n+1` highest-frequency tasks, decrement them, store them temporarily.
  * After the cycle, push any leftover frequency (>0) back into heap.
  * If heap becomes empty after processing the temporary list, add `temp.size()` to `time` (we don't need to wait full `n+1`); otherwise add `n+1` (we used a full cycle including idle).
* Continue until heap empty.

**Code (your version — slightly annotated)**

```java
class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] map = new int[26];
        for (char task : tasks)
            map[task - 'A']++;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < 26; i++)
            if (map[i] > 0) pq.add(map[i]);

        int time = 0;
        while (!pq.isEmpty()) {
            List<Integer> temp = new ArrayList<>();
            // try to execute up to n+1 different tasks
            for (int i = 1; i <= n + 1; i++) {
                if (!pq.isEmpty()) {
                    int freq = pq.poll();
                    freq--;
                    temp.add(freq);
                }
            }
            // push back remaining frequencies
            for (int num : temp)
                if (num > 0) pq.add(num);

            // if pq empty, we used only temp.size() time units this final round,
            // otherwise we had to occupy the full n+1 (including idles)
            if (pq.isEmpty())
                time += temp.size();
            else
                time += n + 1;
        }
        return time;
    }
}
```

**Complexity**

* Time: `O(T * log 26)` → `O(T)` effectively, where `T = tasks.length`. The heap operations are bounded by at most 26 different keys (alphabet size), so it's basically linear.
* Space: `O(26)` → `O(1)` (frequency array + heap of ≤26 elements).

**Pros / Cons**

* Pros: Straightforward to implement, intuitive simulation of cooldowns, works well in practice.
* Cons: Slightly heavier than the constant-time formula; simulation does more work but still fast.

---

# Optimal (mathematical / bucket formula)

**Idea (most compact & fastest)**
Let `f_max` be the maximum frequency of any task. Let `count_max` be how many tasks have frequency `f_max`. The minimum schedule length is either `tasks.length` (if no idle needed) or forced by spacing the most frequent tasks with cooldowns:

Minimum length = `max( tasks.length, (f_max - 1) * (n + 1) + count_max )`

**Why it works (intuition)**

* Place the `f_max` occurrences of the most frequent task(s)`into`f_max`buckets. Between those occurrences there are`f_max - 1` gaps.
* Each gap must have at least `n` slots to satisfy cooldown, so fill them with other tasks or idle slots. A compact formula counts slots required when you pack tasks optimally.
* If there are multiple tasks tying for `f_max`, they occupy the last positions in each bucket, so add `count_max` to account for them in the last bucket row.
* Finally, if you have enough other tasks to fill required idle slots, total time reduces to `tasks.length`. So take `max` with `tasks.length`.

**Code (Java)**

```java
class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] freq = new int[26];
        for (char c : tasks) freq[c - 'A']++;
        int f_max = 0;
        for (int f : freq) if (f > f_max) f_max = f;
        int count_max = 0;
        for (int f : freq) if (f == f_max) count_max++;

        int partCount = f_max - 1;                 // full gaps between max freq tasks
        int partLength = n + 1;                    // size of each part when including one slot for a max-task
        int emptySlots = partCount * partLength;   // slots covered by (f_max-1) parts of length (n+1)
        int result = emptySlots + count_max;       // fill last row with count_max tasks
        return Math.max(tasks.length, result);
    }
}
```

**Complexity**

* Time: `O(T + 26) = O(T)` where `T = tasks.length`.
* Space: `O(26) = O(1)`.

**Example**
tasks = `A A A B B B`, `n = 2`

* `f_max = 3`, `count_max = 2` (A and B)
* `result = (3-1)*(2+1) + 2 = 2*3 + 2 = 8`
* `tasks.length = 6` → `answer = max(6, 8) = 8`
  A valid schedule: `A B idle A B idle A B` (8 time units).

---

# Tips, tricks & pitfalls

* The **formula** is the fastest and simplest: `max(T, (f_max - 1)*(n + 1) + count_max)`. Memorize that — it’s a common interview trick.
* If alphabet is limited (here 26), PQ-based simulation is effectively linear and simple to implement — good fallback if you forget the formula.
* Edge cases:

  * `n = 0` → result is `tasks.length` (no cooldown).
  * All tasks unique → `tasks.length`.
  * Many tasks with same max frequency → `count_max` matters. If `count_max > 1`, the required time increases by `count_max` instead of `1`.
* When deriving the formula, draw the bucket layout — that visual makes the derivation obvious.
* If tasks can be arbitrary characters (not just A–Z), adjust frequency array / map accordingly; the algorithmic ideas remain identical.
* Implementation detail: in simulation approach, using `n+1` window cycles is elegant and ensures you account for idles implicitly.
* Checking correctness: test with small examples including ties for `f_max`, `n=0`, and `tasks.length` much larger than required idle slots.

---
