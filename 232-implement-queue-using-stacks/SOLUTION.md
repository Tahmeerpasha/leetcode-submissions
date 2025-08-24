# **232. Implement Queue using Stacks**

### **Problem**

Implement a **queue (FIFO)** using only **stacks (LIFO)**.

---

## **Brute Force (Approach 1: Push O(N), Pop O(1))**

* Use **two stacks**.
* On `push(x)`:

  * Move all elements from `stack1` → `stack2`.
  * Push `x` into `stack1`.
  * Move everything back from `stack2` → `stack1`.
* Ensures **front of queue is always on top of stack1**.
* `pop()` → just pop from `stack1`.
* `peek()` → just peek stack1.

**Complexity:**

* Push → O(N)
* Pop/Peek → O(1)
* Space → O(N)

---

## **Better/Optimal (Approach 2: Amortized Push O(1), Pop O(1))** ✅ *(used in code)*

* Maintain **two stacks**:

  * `stack1`: for push operations.
  * `stack2`: for pop/peek operations.
* On `push(x)` → simply push into `stack1`.
* On `pop()` / `peek()`:

  * If `stack2` is empty → move all elements from `stack1` → `stack2`.
  * Then pop/peek from `stack2`.
* Ensures FIFO order with lazy transfer.

**Code (Approach 2):**

```java
class MyQueue {
    private Stack<Integer> stack1, stack2;

    public MyQueue() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public void push(int x) {
        stack1.push(x);
    }

    public int pop() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty())
                stack2.push(stack1.pop());
        }
        return stack2.pop();
    }

    public int peek() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty())
                stack2.push(stack1.pop());
        }
        return stack2.peek();
    }

    public boolean empty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }
}
```

**Complexity:**

* Push → O(1)
* Pop/Peek → Amortized O(1) (each element moved at most once)
* Space → O(N)

---

## **Key Difference Between Approaches**

* **Approach 1:** Costly `push`, cheap `pop`.
* **Approach 2:** Costly `pop` sometimes, cheap `push` (preferred).

---
