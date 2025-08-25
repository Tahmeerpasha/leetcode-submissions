// class Pair {
//     int x, y;

//     Pair(int x, int y) {
//         this.x = x;
//         this.y = y;
//     }
// }

// class MinStack {
//     Stack<Pair> st;

//     public MinStack() {
//         st = new Stack<>();
//     }

//     public void push(int x) {
//         int min;
//         if (st.isEmpty()) {
//             min = x;
//         } else {
//             min = Math.min(st.peek().y, x);
//         }
//         st.push(new Pair(x, min));
//     }

//     public void pop() {
//         st.pop();
//     }

//     public int top() {
//         return st.peek().x;
//     }

//     public int getMin() {
//         return st.peek().y;
//     }
// }

class MinStack {
    Stack<Long> st;
    Long min;

    public MinStack() {
        st = new Stack<>();
        min = Long.MAX_VALUE;
    }

    public void push(int value) {
        Long val = Long.valueOf(value);
        if (st.isEmpty()) {
            min = val;
            st.push(val);
        } else {
            if (val > min)
                st.push(val);
            else {
                st.push((2 * val - min));
                min = val;
            }
        }
    }

    public void pop() {
        if (st.isEmpty())
            return;
        Long x = st.pop();
        if (x < min)
            min = (2 * min - x);
    }

    public int top() {
        if (st.isEmpty())
            return -1;
        Long x = st.peek();
        if (min < x)
            return x.intValue();
        else
            return min.intValue();

    }

    public int getMin() {
        return min.intValue();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */