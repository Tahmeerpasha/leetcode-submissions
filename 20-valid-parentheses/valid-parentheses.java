class Solution {
    public boolean isValid(String s) {
        // Time -> O(N) && Space -> O(N)
        // Odd length are not acceptable
        if (s.length() % 2 != 0)
            return false;
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(' || s.charAt(i) == '{' || s.charAt(i) == '[')
                stack.add(s.charAt(i));
            else if (stack.isEmpty())
                return false;
            else {
                Character ch = stack.pop();
                if (s.charAt(i) == ')' && ch == '(' || s.charAt(i) == '}' && ch == '{'
                        || s.charAt(i) == ']' && ch == '[')
                    continue;
                else
                    return false;
            }
        }
        return stack.isEmpty();
    }
}