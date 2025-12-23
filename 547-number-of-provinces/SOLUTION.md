## Problem Recap (1-liner)

You’re given an **adjacency matrix** of cities. A **province** = a connected component in an **undirected graph**.

---

# 🧠 Core Insight

This is **connected components in a graph**.

* Cities = nodes
* `isConnected[i][j] == 1` = edge
* Count how many times you **start DFS/BFS from an unvisited node**

---

# 1️⃣ Brute Force (DFS directly on matrix)

### Idea

* Use DFS
* For a node `i`, scan its entire row in the matrix to find neighbors

### Steps

1. Maintain `visited[]`
2. For each city:

   * If not visited → start DFS → increment province count
3. DFS scans all `n` neighbors from matrix

### Code Sketch

```java
for (int i = 0; i < n; i++) {
    if (vis[i] == 0) {
        provinces++;
        dfsMatrix(i);
    }
}
```

### Complexity

* **Time:** `O(N^2)`
* **Space:** `O(N)` (visited + recursion stack)

### When to use

* If interviewer says **“don’t convert, use given matrix”**

---

# 2️⃣ Better (Adjacency List + DFS) ✅ *Your Code*

### Why better?

* Cleaner logic
* Reusable DFS template
* Scales well conceptually

---

## ✅ Your Code (KEEP THIS)

```java
class Solution {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;

        // 1. Create adjacency list
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < n; i++)
            adjList.add(new ArrayList<>());

        // 2. Fill adjacency list
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (isConnected[i][j] == 1 && i != j) {
                    adjList.get(i).add(j);
                }
            }
        }

        // 3. Visited array
        int[] vis = new int[n];
        int provinces = 0;

        // 4. Count DFS calls
        for (int i = 0; i < n; i++) {
            if (vis[i] == 0) {
                provinces++; // DFS called → new province
                dfs(i, adjList, vis);
            }
        }

        return provinces;
    }

    public void dfs(int node, ArrayList<ArrayList<Integer>> adjLst, int[] vis) {
        vis[node] = 1;
        for (Integer nd : adjLst.get(node)) {
            if (vis[nd] == 0)
                dfs(nd, adjLst, vis);
        }
    }
}
```

---

### Complexity

* **Building adj list:** `O(N^2)`
* **DFS traversal:** `O(N + E)`
* **Overall Time:** `O(N^2)`
* **Space:** `O(N + E)`

---

### 🔑 Interview Gold Line

> “The number of provinces is equal to the number of times DFS is initiated from an unvisited node.”

Say this confidently.

---

# 3️⃣ Optimal (Union–Find / DSU)

### Idea

* Each city starts as its own parent
* Union cities if `isConnected[i][j] == 1`
* Count unique parents

### Complexity

* **Time:** `O(N^2 α(N))` ≈ `O(N^2)`
* **Space:** `O(N)`

### When interviewer expects this

* If they say **“can we do it without DFS?”**
* If question morphs into **dynamic connectivity**

---

# 🔥 Comparison Table

| Approach       | Time    | Space    | Notes            |
| -------------- | ------- | -------- | ---------------- |
| DFS on Matrix  | `O(N²)` | `O(N)`   | Simplest         |
| Adj List + DFS | `O(N²)` | `O(N+E)` | Clean & reusable |
| Union-Find     | `O(N²)` | `O(N)`   | Best abstraction |

---

# ⚠️ Common Interview Traps

* ❌ Counting edges instead of components
* ❌ Forgetting `i != j`
* ❌ Thinking DFS calls = nodes (it’s **components**)
* ❌ Overcomplicating with BFS + extra data

---

# 🧠 Pattern Tag (IMPORTANT)

**Graph → Connected Components → DFS / BFS / DSU**

This pattern repeats in:

* Provinces
* Number of islands
* Friend circles
* Network connectivity

---
