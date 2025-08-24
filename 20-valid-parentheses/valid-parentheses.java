class Solution {
    public boolean isValid(String s) {
        // Brute
        // if (s.length() <= 1)
        //     return false;
        // Stack<Character> stack = new Stack<>();
        // for (int i = 0; i < s.length(); i++) {
        //     if (s.charAt(i) == '(' || s.charAt(i) == '{' || s.charAt(i) == '[')
        //         stack.add(s.charAt(i));
        //     if (s.charAt(i) == ')') {
        //         if (stack.size() != 0 && stack.peek() == '(')
        //             stack.pop();
        //         else
        //             return false;
        //     }
        //     if (s.charAt(i) == '}') {
        //         if (stack.size() != 0 && stack.peek() == '{')
        //             stack.pop();
        //         else
        //             return false;
        //     }
        //     if (s.charAt(i) == ']') {
        //         if (stack.size() != 0 && stack.peek() == '[')
        //             stack.pop();
        //         else
        //             return false;
        //     }
        // }
        // return stack.size() == 0;

        // Optimised
        // Odd length can't be valid
        if (s.length() % 2 != 0)
            return false;

        Stack<Character> stack = new Stack<>();
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put('}', '{');
        map.put(']', '[');

        for (char c : s.toCharArray()) {
            if (map.containsValue(c)) {
                // opening bracket
                stack.push(c);
            } else if (map.containsKey(c)) {
                // closing bracket
                if (stack.isEmpty() || stack.pop() != map.get(c)) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }
}