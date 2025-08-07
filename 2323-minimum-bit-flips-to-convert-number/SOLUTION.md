## ✅ Problem: **2220. Minimum Bit Flips to Convert Number**

Given two integers `start` and `goal`, return the **minimum number of bit flips** required to convert `start` to `goal`. A bit flip changes a `0` to `1` or `1` to `0`.

---

## ✅ Intuition:

* Bit difference between two numbers can be found using **XOR (`^`)**:

  ```java
  int diff = start ^ goal;
  ```

  * Wherever the bits differ, XOR gives `1`
  * Wherever the bits are same, XOR gives `0`
* Then, count the number of `1`s in the result — that's the number of bit flips needed.

---

## ✅ Code:

```java
class Solution {
    public int minBitFlips(int start, int goal) {
        int count = 0;
        int ans = start ^ goal;
        while (ans != 0) {
            ans = (ans & (ans - 1)); // remove lowest set bit
            count++;
        }
        return count;
    }
}
```

---

## ✅ Time & Space Complexity:

* **Time Complexity:**

  * `O(k)`, where `k` = number of set bits in `start ^ goal`
  * In worst case, `k = log₂(start ^ goal)`, so:

    $$
    \boxed{O(\log(\text{start} \oplus \text{goal}))}
    $$

* **Space Complexity:**

  * {O(1)}

---

## 🔍 Example:

```
start = 10 (1010)
goal  =  7 (0111)

XOR    = 13 (1101)
Bit flips needed = 3
```

---

## 🧠 Summary:

* XOR reveals differing bits.
* `n & (n - 1)` removes lowest set bit efficiently.
* Count of set bits = number of flips.
