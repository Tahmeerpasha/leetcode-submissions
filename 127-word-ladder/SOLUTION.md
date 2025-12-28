# 127. Word Ladder

## Problem Restatement

You’re given:

* `beginWord`
* `endWord`
* `wordList`

You can change **only one character at a time**, and every intermediate word **must exist in the wordList**.

Return the **length of the shortest transformation sequence** from `beginWord` to `endWord`.
If impossible → return `0`.

---

## Graph Modeling (Key Insight)

* Each **word = node**
* An **edge exists** if two words differ by **exactly one character**
* Graph is **unweighted** → **BFS gives shortest path**

The graph is **implicit** (not built fully).

---

## 1️⃣ Brute Force Approach (Too Slow)

### Idea

* Build a graph by comparing **every pair of words**
* Add an edge if they differ by one character
* Run BFS from `beginWord`

### Time Complexity

* Checking adjacency: `O(N² × L)`
* BFS: `O(N + E)`
* **Total:** ❌ Too slow for large input

### Why it fails

* `N` can be 5k
* `N²` comparisons is unacceptable

👉 **Good to mention only to show evolution of thought**

---

## 2️⃣ Better Approach (Your Code – BFS + On-the-Fly Neighbors)

### Idea

Instead of pre-building the graph:

* For the current word, try changing **each character**
* Generate all possible next valid words
* Push valid ones into BFS queue

### Why it works

* You only explore **reachable nodes**
* No unnecessary comparisons

---

### ✅ Your Code (KEEP THIS)

```java
class Pair {
    String word;
    int steps;

    Pair(String w, int s) {
        word = w;
        steps = s;
    }
}

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Queue<Pair> q = new LinkedList<>();
        Set<String> st = new HashSet<>();

        q.add(new Pair(beginWord, 1));

        for (String str : wordList)
            st.add(str);

        st.remove(beginWord);

        while (!q.isEmpty()) {
            String word = q.peek().word;
            int steps = q.peek().steps;
            q.remove();

            if (word.equals(endWord))
                return steps;

            for (int i = 0; i < word.length(); i++) {
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    char[] replacedCharArray = word.toCharArray();
                    replacedCharArray[i] = ch;
                    String replacedWord = new String(replacedCharArray);

                    if (st.contains(replacedWord)) {
                        st.remove(replacedWord); // mark visited
                        q.add(new Pair(replacedWord, steps + 1));
                    }
                }
            }
        }
        return 0;
    }
}
```

---

### Complexity Analysis

* For each word:

  * `L` positions × `26` letters
* **Time:** `O(N × L × 26)` → effectively `O(N × L)`
* **Space:** `O(N)` (queue + set)

✔️ **This is already accepted and optimal enough for interviews**

---

## 3️⃣ Optimal Approach (Bidirectional BFS)

### Idea

* BFS from **beginWord**
* BFS from **endWord**
* Expand the **smaller frontier**
* Stop when they meet

### Why it’s faster

* Reduces depth from `d` → `d/2`
* Exponential pruning in practice

### Time Complexity

* Still `O(N × L)`
* But **much faster constant factor**

### When to mention

* FAANG / senior interviews
* When interviewer asks: *“Can we optimize further?”*

---

## Interview Tips & Tricks 💡

### 1. Always Say This Early

> “This is a shortest path problem on an unweighted graph → BFS.”

### 2. Explain Why `Set.remove()` = Visited

* Avoids cycles
* Prevents revisiting
* Ensures shortest path

### 3. Why Not DFS?

* DFS doesn’t guarantee shortest path
* BFS level-order traversal does

### 4. Important Edge Case

```java
if (!wordList.contains(endWord)) return 0;
```

Mention this verbally even if not coded.

### 5. Common Mistake to Avoid

❌ Marking visited **after popping from queue**
✔️ Mark visited **when pushing into queue**

---

## Pattern Classification (Very Important)

* **Graph Traversal**
* **BFS on Implicit Graph**
* **Shortest Path (Unweighted)**
* **String Transformation Pattern**

Once you see:

> “Minimum steps / transformations / moves”
> → **Think BFS immediately**

---

## One-Line Summary (What to say in interview)

> “We treat each word as a node, generate neighbors by changing one character, and use BFS to find the shortest transformation sequence.”

---
