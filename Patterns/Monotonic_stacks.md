# 🔑 Monotonic Stack Patterns

---

## 1️⃣ Next Greater Element (NGE)

**Problem:** For each element, find the next element greater than it on the right.

**Stack type:** **Monotonic decreasing stack** (store indices).

**Template:**

```java
int[] nge = new int[n];
Stack<Integer> st = new Stack<>();
for (int i = n - 1; i >= 0; i--) {
    while (!st.isEmpty() && arr[st.peek()] <= arr[i]) st.pop();
    nge[i] = st.isEmpty() ? n : st.peek();
    st.push(i);
}
```

---

## 2️⃣ Next Smaller Element (NSE)

**Problem:** For each element, find the next element smaller than it on the right.

**Stack type:** **Monotonic increasing stack**.

**Template:**

```java
int[] nse = new int[n];
Stack<Integer> st = new Stack<>();
for (int i = n - 1; i >= 0; i--) {
    while (!st.isEmpty() && arr[st.peek()] >= arr[i]) st.pop();
    nse[i] = st.isEmpty() ? n : st.peek();
    st.push(i);
}
```

---

## 3️⃣ Previous Greater Element (PGE)

**Problem:** For each element, find the previous greater element to the left.

**Stack type:** **Monotonic decreasing stack**.

**Template:**

```java
int[] pge = new int[n];
Stack<Integer> st = new Stack<>();
for (int i = 0; i < n; i++) {
    while (!st.isEmpty() && arr[st.peek()] < arr[i]) st.pop();
    pge[i] = st.isEmpty() ? -1 : st.peek();
    st.push(i);
}
```

---

## 4️⃣ Previous Smaller Element (PSE)

**Problem:** For each element, find the previous smaller element to the left.

**Stack type:** **Monotonic increasing stack**.

**Template:**

```java
int[] pse = new int[n];
Stack<Integer> st = new Stack<>();
for (int i = 0; i < n; i++) {
    while (!st.isEmpty() && arr[st.peek()] > arr[i]) st.pop();
    pse[i] = st.isEmpty() ? -1 : st.peek();
    st.push(i);
}
```

---

## 5️⃣ Sum of Subarray Minimums (Leetcode 907)

**Problem:** Contribution of each element as the **minimum** in subarrays.

**Formula:**

$$
arr[i] \times (i - PSE[i]) \times (NSE[i] - i)
$$

**Stack rules:**

* NSE: `>=`
* PSE: `>`

---

## 6️⃣ Sum of Subarray Maximums

**Problem:** Contribution of each element as the **maximum** in subarrays.

**Formula:**

$$
arr[i] \times (i - PGE[i]) \times (NGE[i] - i)
$$

**Stack rules:**

* NGE: `<=`
* PGE: `<`

---

## 7️⃣ Sum of Subarray Ranges (Leetcode 2104)

**Problem:**

$$
\sum (\text{max}) - \sum (\text{min})
$$

* Compute **sum of subarray maxs** using (6).
* Compute **sum of subarray mins** using (5).
* Subtract.

---

# ⚡ Tips & Traps

* **Strict vs Non-strict**:

  * Use `>=` for NSE and `<=` for NGE.
  * Use `>` for PSE and `<` for PGE.
  * This avoids double counting duplicates.
* Always store **indices** in stack (not values).
* Watch for **overflow** → use `long`.
* If array length can be large (up to 1e5), O(n) stack solution is essential.

---
