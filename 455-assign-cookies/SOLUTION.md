## 🧩 Problem Summary

You’re given:

* `g[i]`: the *greed factor* of each child (minimum cookie size they need).
* `s[j]`: the *size* of each cookie.

Each child can get at most one cookie.
Goal → **maximize** the number of content children (where `cookie_size >= greed_factor`).

---

## 🧠 Intuition

This is a **classic greedy matching problem**:
Give the smallest cookie that can satisfy the least greedy child first — that way, larger cookies can go to greedier children later.

---

## 🪜 1. Brute Force

### 🔹 Idea

Try to assign each cookie to every child and see if it satisfies.
Use a visited array to ensure each cookie is used once.

### 🔹 Code

```java
class Solution {
    public int findContentChildren(int[] g, int[] s) {
        boolean[] used = new boolean[s.length];
        int count = 0;

        for (int i = 0; i < g.length; i++) {
            for (int j = 0; j < s.length; j++) {
                if (!used[j] && s[j] >= g[i]) {
                    used[j] = true;
                    count++;
                    break;
                }
            }
        }
        return count;
    }
}
```

### 🔹 Complexity

* **Time:** O(m * n)
* **Space:** O(n)

---

## ⚙️ 2. Better Solution (Using Sorting + Nested Loops)

### 🔹 Idea

Sort both arrays to reduce unnecessary comparisons.
Then iterate greedily: for each child, find the smallest cookie that fits.

### 🔹 Code

```java
class Solution {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int count = 0;

        for (int i = 0, j = 0; i < g.length && j < s.length; ) {
            if (s[j] >= g[i]) {
                count++;
                i++;
                j++;
            } else {
                j++;
            }
        }
        return count;
    }
}
```

### 🔹 Complexity

* **Time:** O(m log m + n log n) — due to sorting
* **Space:** O(1)

---

## ⚡ 3. Optimal Solution (Two Pointers — Clean Greedy)

### 🔹 Idea

Same as above, but clearly framed as two pointers:

* Pointer `l` for child
* Pointer `r` for cookie

We assign cookies in increasing order.
If the cookie fits the child → move both pointers.
Else, move only cookie pointer.

### 🔹 Code

```java
class Solution {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int l = 0, r = 0;

        while (l < g.length && r < s.length) {
            if (g[l] <= s[r]) l++; // assign cookie
            r++; // always move cookie pointer
        }
        return l;
    }
}
```

### 🔹 Complexity

* **Time:** O(m log m + n log n)
* **Space:** O(1)

---

## 💡 Tips & Tricks

* **Greedy principle:** Match smallest with smallest to leave room for bigger ones later.
* **Edge case check:** If no cookie can satisfy any child, return 0.
* **Shortcut thinking:**
  When you see *maximize number of satisfied pairs* → likely **sorting + two pointers**.
* Sorting helps you “line up” both sides to make efficient matches.

---

## 🔚 Summary Table

| Approach | Method                    | Time                 | Space | Notes                    |
| -------- | ------------------------- | -------------------- | ----- | ------------------------ |
| Brute    | Try all cookies per child | O(m·n)               | O(n)  | Too slow for large input |
| Better   | Sorted + nested loop      | O(m log m + n log n) | O(1)  | Greedy after sorting     |
| Optimal  | Two-pointer greedy        | O(m log m + n log n) | O(1)  | Cleanest and concise     |

---
