class Solution {
    public boolean isValid(String s) {
        if (s.length() <= 1)
            return false;
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(' || s.charAt(i) == '{' || s.charAt(i) == '[')
                stack.add(s.charAt(i));
            if (s.charAt(i) == ')') {
                if (stack.size() != 0 && stack.peek() == '(')
                    stack.pop();
                else
                    return false;
            }
            if (s.charAt(i) == '}') {
                if (stack.size() != 0 && stack.peek() == '{')
                    stack.pop();
                else
                    return false;
            }
            if (s.charAt(i) == ']') {
                if (stack.size() != 0 && stack.peek() == '[')
                    stack.pop();
                else
                    return false;
            }
        }
        return stack.size() == 0;
    }
}