class Solution {
    public void reverseString(char[] s) {
        // Stack<Character> stack = new Stack<>();
        // for (char ch : s)
        //     stack.push(ch);
        // for (int i = 0; i < s.length; i++) {
        //     s[i] = stack.pop();
        // }
        int left = 0, right = s.length - 1;
        while (left <= right) {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
    }
}
