class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        generate(result, n, 0, 0, "");
        return result;
    }

    void generate(List<String> result, int n, int open, int close, String currStr) {
        if (currStr.length() == n * 2) {
            result.add(currStr);
            return;
        }

        if (open < n)
            generate(result, n, open + 1, close, currStr + "(");
        if (close < open)
            generate(result, n, open, close + 1, currStr + ")");

    }
}