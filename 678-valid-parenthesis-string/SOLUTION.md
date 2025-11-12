## 🔹 Problem

Given a string `s` containing `'('`, `')'`, and `'*'`, determine if it can be a valid parenthesis string.

Rules:

* `'('` must have a corresponding `')'`.
* `'*'` can be `'('`, `')'`, or an empty string.

---

## 🧠 Brute Force (Recursion / Backtracking)

**Idea:**
Try all possibilities for each `'*'`:

* Replace it with `'('`
* Replace it with `')'`
* Remove it (empty)

Then check if any generated string is valid.

**Steps:**

1. Use recursion or DFS.
2. At each `'*'`, branch into 3 recursive calls.
3. Validate parentheses using a counter.
4. If any branch leads to valid parentheses, return `true`.

**Time Complexity:** `O(3^n)` — exponential (each `'*'` triples possibilities)
**Space Complexity:** `O(n)` (recursion depth)

✅ *Only for understanding logic; not feasible for large strings.*

---

## 🔹 Better Approach (Stack Simulation)

**Idea:**
Use **two stacks**:

* One for `'('` positions.
* One for `'*'` positions.

**Steps:**

1. Traverse string:

   * If `'('`, push index to `openStack`.
   * If `'*'`, push index to `starStack`.
   * If `')'`, try to pop `'('` first; if not available, pop `'*'`; else invalid.
2. After traversal, try to balance remaining `'('` with later `'*'` (since `'*'` after `'('` can act as `')'`).
3. If any `'('` remains unmatched → invalid.

**Time Complexity:** `O(n)`
**Space Complexity:** `O(n)`

✅ Easier to reason about than brute, still intuitive.

---

## 🔹 Optimal Approach (Greedy Range Tracking)

**Idea:**
Track the **range** of possible open parentheses counts.

Let:

* `min` = minimum number of open brackets possible
* `max` = maximum number of open brackets possible

**Algorithm:**

1. Traverse each character:

   * If `'('`: `min++`, `max++`
   * If `')'`: `min--`, `max--`
   * If `'*'`:

     * It can be `'('`, `')'`, or empty → `min--`, `max++`
2. If `max < 0` at any point → invalid (`too many ')'`)
3. Clamp `min` to `0` (can't have negative opens)
4. End: valid only if `min == 0`

**Time Complexity:** `O(n)`
**Space Complexity:** `O(1)`

✅ **Most efficient** and **clean** (used in your submitted code).

---

## 🪄 Tips & Tricks

* Think of `'*'` as **wildcard** balancing power:

  * `min` assumes `'*'` as `')'`
  * `max` assumes `'*'` as `'('`
* If `max` ever drops below 0 → impossible to recover.
* Setting `min = 0` ensures we don’t overcount unmatched `')'`.
* Greedy works because order matters; we’re tracking possible valid states, not all permutations.

---

## 🧩 Summary Table

| Approach    | Technique                 | Time   | Space | Notes                           |
| ----------- | ------------------------- | ------ | ----- | ------------------------------- |
| Brute Force | Backtracking (try all)    | O(3^n) | O(n)  | Explore all `'*'` possibilities |
| Better      | Two Stacks (`'('`, `'*'`) | O(n)   | O(n)  | Matches `'('` using later `'*'` |
| Optimal ✅   | Greedy Range Tracking     | O(n)   | O(1)  | Elegant & efficient             |

---
