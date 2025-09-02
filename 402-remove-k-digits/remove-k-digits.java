class Solution {
    public String removeKdigits(String num, int k) {
        Stack<Character> st = new Stack<>();

        for (char c : num.toCharArray()) {
            while (!st.isEmpty() && k > 0 && st.peek() > c) {
                st.pop();
                k--;
            }
            st.push(c);
        }

        // If still have removals left
        while (k > 0 && !st.isEmpty()) {
            st.pop();
            k--;
        }

        // Build the number
        StringBuilder sb = new StringBuilder();
        for (char c : st)
            sb.append(c);

        // Remove leading zeros
        while (sb.length() > 0 && sb.charAt(0) == '0') {
            sb.deleteCharAt(0);
        }

        return sb.length() == 0 ? "0" : sb.toString();
    }
}