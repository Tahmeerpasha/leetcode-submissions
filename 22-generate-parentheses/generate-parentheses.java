class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        generate(n, "", result, 0, 0);
        return result;
    }

    void generate(int n, String curr, List<String> result, int open, int close) {
        if (curr.length() == 2 * n) {
            result.add(curr);
            return;
        }
        if (open < n)
            generate(n, curr + "(", result, open + 1, close);
        if (close < open)
            generate(n, curr + ")", result, open, close + 1);
    }
}