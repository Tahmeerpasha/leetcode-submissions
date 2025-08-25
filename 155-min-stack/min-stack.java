// Brute Force: 
// getMin() -> O(N)
// Space -> O(N)
// -----------------------------

// Better Approach: (Pair method)
// Push, Pop, Top, getMin -> O(1)
// Space -> O(2N) [storing val + min]
// -----------------------------

// Optimal Approach: (Encoding Trick)
// Push, Pop, Top, getMin -> O(1)
// Space -> O(N)
// -----------------------------

class MinStack {
    // Brute Force
    // Stack<Integer> bruteSt;

    // Better
    // static class Pair {
    //     int val, min;

    //     Pair(int v, int m) {
    //         val = v;
    //         min = m;
    //     }
    // }

    // Stack<Pair> betterSt;

    // Optimal
    Stack<Long> optimalSt;
    long min;

    public MinStack() {
        // bruteSt = new Stack<>();
        // betterSt = new Stack<>();
        optimalSt = new Stack<>();
        min = Long.MAX_VALUE;
    }

    // -----------------------------------------
    // PUSH
    // -----------------------------------------
    public void push(int val) {

        // Brute Force: Just push the value
        // Time: O(1), Space: O(N)
        // bruteSt.push(val);

        // Better: Push value + minSoFar
        // Time: O(1), Space: O(2N)
        // if (betterSt.isEmpty())
        //     betterSt.push(new Pair(val, val));
        // else
        //     betterSt.push(new Pair(val, Math.min(val, betterSt.peek().min)));

        // Optimal: Encoding trick
        // Time: O(1), Space: O(N)
        long x = val;
        if (optimalSt.isEmpty()) {
            optimalSt.push(x);
            min = x;
        } else {
            if (x >= min) {
                optimalSt.push(x);
            } else {
                optimalSt.push(2 * x - min); // encode
                min = x;
            }
        }
    }

    // -----------------------------------------
    // POP
    // -----------------------------------------
    public void pop() {

        // Brute Force
        // Time: O(1)
        // if (!bruteSt.isEmpty())
        //     bruteSt.pop();

        // Better
        // Time: O(1)
        // if (!betterSt.isEmpty())
        //     betterSt.pop();

        // Optimal
        // Time: O(1)
        if (!optimalSt.isEmpty()) {
            long top = optimalSt.pop();
            if (top < min) {
                min = 2 * min - top; // decode previous min
            }
        }
    }

    // -----------------------------------------
    // TOP
    // -----------------------------------------
    public int top() {

        // Brute Force
        // Time: O(1)
        // int bruteTop = bruteSt.isEmpty() ? -1 : bruteSt.peek();

        // Better
        // Time: O(1)
        // int betterTop = betterSt.isEmpty() ? -1 : betterSt.peek().val;

        // Optimal
        // Time: O(1)
        int optimalTop = -1;
        if (!optimalSt.isEmpty()) {
            long top = optimalSt.peek();
            if (top >= min)
                optimalTop = (int) top;
            else
                optimalTop = (int) min;
        }

        return optimalTop;
    }

    // -----------------------------------------
    // GET MIN
    // -----------------------------------------
    public int getMin() {

        // Brute Force
        // Time: O(N)
        // int bruteMin = Integer.MAX_VALUE;
        // for (int x : bruteSt)
        //     bruteMin = Math.min(bruteMin, x);
        // if (bruteSt.isEmpty())
        //     bruteMin = -1;

        // Better
        // Time: O(1)
        // int betterMin = betterSt.isEmpty() ? -1 : betterSt.peek().min;

        // Optimal
        // Time: O(1)
        int optimalMin = optimalSt.isEmpty() ? -1 : (int) min;

        return optimalMin;
    }
}

/**
 * Usage:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int top = obj.top();
 * int min = obj.getMin();
 */
