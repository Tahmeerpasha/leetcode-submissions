class MyQueue {
    private Stack<Integer> stack1, stack2;

    public MyQueue() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }
    // Approach 1
    // public void push(int x) {
    //     while (stack1.size() != 0)
    //         stack2.push(stack1.pop());
    //     stack1.push(x);
    //     while (stack2.size() != 0)
    //         stack1.push(stack2.pop());
    // }

    // public int pop() {
    //     return stack1.pop();
    // }

    // public int peek() {
    //     return stack1.peek();
    // }

    // public boolean empty() {
    //     return stack1.size() == 0;
    // }

    // Approach 2
    public void push(int x) {
        stack1.push(x);
    }

    public int pop() {
        if (stack2.size() != 0)
            return stack2.pop();
        else {
            while (stack1.size() != 0)
                stack2.push(stack1.pop());
            return stack2.pop();
        }
    }

    public int peek() {
        if (stack2.size() != 0)
            return stack2.peek();
        else {
            while (stack1.size() != 0)
                stack2.push(stack1.pop());
            return stack2.peek();
        }
    }

    public boolean empty() {
        return stack1.size() == 0 && stack2.size() == 0;
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */