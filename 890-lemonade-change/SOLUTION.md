### 🔍 Problem Recap

You are selling lemonade for $5.
Customers come in order and pay with either $5, $10, or $20 bills.
You must return the correct change (if possible) to each customer.
Return `true` if you can give every customer the correct change; otherwise, return `false`.

---

## 🧠 Brute Force Approach

**Idea:**
Simulate every possible way to give change using recursion or backtracking.
For each customer:

* If they pay $10 → try giving back one $5 if available.
* If they pay $20 → try all possible valid combinations to make $15 change (either one $10 + one $5 or three $5s).

If any sequence leads to a valid configuration for all customers → return true.

**Code sketch:**

```java
boolean canChange(int i, int five, int ten, int[] bills) {
    if (i == bills.length) return true;

    int bill = bills[i];
    if (bill == 5) return canChange(i + 1, five + 1, ten, bills);

    if (bill == 10) {
        if (five > 0 && canChange(i + 1, five - 1, ten + 1, bills)) return true;
        return false;
    }

    if (bill == 20) {
        if (ten > 0 && five > 0 && canChange(i + 1, five - 1, ten - 1, bills)) return true;
        if (five >= 3 && canChange(i + 1, five - 3, ten, bills)) return true;
        return false;
    }

    return false;
}
```

**Complexity:**

* Time → `O(2^n)` (each 20$ customer can branch into 2 paths)
* Space → `O(n)` (recursion stack)

**Verdict:**
❌ Too slow for even moderate input size.

---

## ⚙️ Better Approach

**Idea:**
Instead of exploring all combinations, just **track counts** of each denomination.
Simulate the transaction greedily:

* Keep counters for $5 and $10 bills.
* Always give change using the **largest denominations first** (since larger bills are more valuable for future transactions).

**Algorithm:**

1. For each bill in `bills[]`:

   * If `5`: increment five count.
   * If `10`: decrement one `five`, increment one `ten`.
   * If `20`:

     * Prefer using `10 + 5` first (more optimal).
     * If not possible, use `5 + 5 + 5`.

2. If at any point you can’t make change → return false.

**Code:**

```java
class Solution {
    public boolean lemonadeChange(int[] bills) {
        int five = 0, ten = 0;

        for (int bill : bills) {
            if (bill == 5) five++;
            else if (bill == 10) {
                if (five == 0) return false;
                five--; ten++;
            } else {
                if (ten > 0 && five > 0) {
                    ten--; five--;
                } else if (five >= 3) {
                    five -= 3;
                } else return false;
            }
        }
        return true;
    }
}
```

**Complexity:**

* Time → `O(n)`
* Space → `O(1)`

**Verdict:**
✅ Optimal solution for all practical constraints.

---

## 🧩 Optimal (Same as Better)

There’s **no better** approach than the greedy one — it’s both optimal and minimal.
Reasoning:

* Local greedy decisions (use larger bills first) ensure global optimality because smaller bills are more flexible for future change.

---

## 💡 Tips & Tricks

1. **Always give change using higher denominations first.**
   Example: For a $20 bill, use $10 + $5 (if possible) instead of three $5s.

2. **Track only $5 and $10 bills.**
   You never need to store count of $20 because you never give $20 as change.

3. **Early exit helps.**
   If at any point you can’t make change, return false immediately — saves time.

4. **Edge case:**
   If the first customer doesn’t pay with a $5, instantly return false.

---

### 🧾 Summary Table

| Approach         | Description                                 | Time   | Space | Feasibility |
| ---------------- | ------------------------------------------- | ------ | ----- | ----------- |
| Brute Force      | Backtrack every possible way to give change | O(2^n) | O(n)  | ❌ Too slow  |
| Better / Optimal | Greedy count tracking using $5, $10         | O(n)   | O(1)  | ✅ Perfect   |

---
