# **225. Implement Stack using Queues**

### **Problem**

Implement a **stack (LIFO)** using only **queues (FIFO)**.

---

## **Brute Force Approach**

* Use **two queues**.
* On `push(x)` → add element to `q2`, then move all elements from `q1` → `q2`. Swap names of `q1` and `q2`.
* Ensures **last pushed element is always at front** of the main queue.

**Complexity:**

* Push → O(N)
* Pop/Top → O(1)
* Space → O(N)

---

## **Better Approach (Using one queue)** ✅ *(used in code)*

* Maintain **only one queue**.
* On `push(x)`:

  * Add element to queue.
  * Rotate previous elements to back → ensures new element comes to **front**.
* `pop()` → remove from queue.
* `top()` → peek front.
* `empty()` → check size.

**Code:**

```java
class MyStack {
    Queue<Integer> queue;

    public MyStack() {
        queue = new LinkedList<>();
    }

    public void push(int x) {
        queue.add(x);
        for (int i = 1; i < queue.size(); i++) {
            queue.add(queue.remove());
        }
    }

    public int pop() {
        return queue.remove();
    }

    public int top() {
        return queue.peek();
    }

    public boolean empty() {
        return queue.isEmpty();
    }
}
```

**Complexity:**

* Push → O(N) (rotation)
* Pop/Top → O(1)
* Space → O(N)

---

## **Optimal Variant (Lazy Push, Expensive Pop)**

* Simply push at back → O(1).
* On `pop()` or `top()` → rotate elements until last comes front.
* Complexity trade-off:

  * Push → O(1)
  * Pop/Top → O(N)

---

### **Key Takeaway**

* Both **single queue** and **double queue** solutions work.
* Trade-off between making `push` costly vs `pop/top` costly.
* **Interview Tip**: Mention both, implement the single-queue version.

---
