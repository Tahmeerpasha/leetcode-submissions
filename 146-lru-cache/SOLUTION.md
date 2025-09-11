# **146. LRU Cache – Notes**

## **Problem Restatement**

Design a data structure that supports:

1. `get(key)` – return value if present else -1
2. `put(key, value)` – insert/update key-value pair. If capacity is exceeded, evict the **Least Recently Used (LRU)** key.

Both operations should ideally run in **O(1)**.

---

## **Brute Force Approach**

### Idea

* Store elements in an **array/list** with order representing recency (front = most recent, back = least recent).
* On `get`:

  * Scan linearly → if found, move element to front.
* On `put`:

  * If exists → update and move to front.
  * If not exists and capacity full → remove last element, then insert at front.

### Complexity

* `get`: O(n) (linear scan)
* `put`: O(n) (scan + shifting)
* Space: O(capacity)

⚠️ Too slow when `n` is large (fails constraint that operations must be O(1)).

---

## **Better Approach**

### Idea

* Use **HashMap for O(1) lookup**.
* Maintain **recency order in a list/array**.
* On `get`: O(1) lookup + O(n) move to front.
* On `put`: O(1) update but O(n) to rearrange.

### Complexity

* `get`: O(n) (because of moving to front)
* `put`: O(n)
* Space: O(capacity)

⚠️ Still not O(1) for both operations.

---

## **Optimal Approach (HashMap + Doubly Linked List)**

### Idea

* Combine **HashMap** + **Doubly Linked List**:

  * HashMap → direct access to nodes in O(1).
  * Doubly Linked List → maintain order of recency efficiently:

    * Head = most recently used.
    * Tail = least recently used.

* On `get(key)`:

  * Lookup in O(1).
  * If exists → remove node from current position, insert at head.
  * If not → return -1.

* On `put(key, value)`:

  * If exists → delete old node, reinsert at head.
  * If new key and capacity full → evict `tail.prev` (least recently used).
  * Insert new node at head.

### Code:
```java
class LRUCache {
    class Node {
        Node prev, next;
        int key, value;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    Node head, tail;
    Map<Integer, Node> map;
    int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            deleteNode(node);
            insertAfterHead(node);
            return node.value;
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        if (map.containsKey(key))
            deleteNode(map.get(key));
        if (capacity == map.size())
            deleteNode(tail.prev);
        insertAfterHead(new Node(key, value));
    }

    private void deleteNode(Node node) {
        map.remove(node.key);
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void insertAfterHead(Node node) {
        map.put(node.key, node);
        node.next = head.next;
        node.next.prev = node;
        head.next = node;
        node.prev = head;
    }
}
```
### Complexity

* `get`: O(1)
* `put`: O(1)
* Space: O(capacity)

✅ Meets problem requirements perfectly.

---

## **Key Tips for Interview**

* **HashMap alone** gives O(1) access but can’t maintain order.
* **Linked List alone** maintains order but O(n) access.
* The magic is **combining both**.
* Use **dummy head and tail nodes** to simplify edge-case handling (no need for null checks when inserting/removing at ends).
* Always **remove and reinsert** on access/put to update recency.
* Evict **tail.prev** (not tail dummy node).

---

👉 Memory hook: **LRU = HashMap + DLL** (for O(1) access + O(1) order maintenance).

---
