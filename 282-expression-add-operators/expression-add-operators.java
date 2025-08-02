class Solution {
    public List<String> addOperators(String num, int target) {
        List<String> result = new ArrayList<>();
        backtrack(result, "", num, target, 0, 0, 0);
        return result;
    }

    private void backtrack(List<String> result, String path, String num, int target, int index, long eval, long prev) {
        if (index == num.length()) {
            if (eval == target) {
                result.add(path);
            }
            return;
        }

        for (int i = index; i < num.length(); i++) {
            // Avoid numbers with leading zeros
            if (i != index && num.charAt(index) == '0')
                break;

            String currStr = num.substring(index, i + 1);
            long currNum = Long.parseLong(currStr);

            if (index == 0) {
                // First number, no operator
                backtrack(result, currStr, num, target, i + 1, currNum, currNum);
            } else {
                // Try +
                backtrack(result, path + "+" + currStr, num, target, i + 1, eval + currNum, currNum);
                // Try -
                backtrack(result, path + "-" + currStr, num, target, i + 1, eval - currNum, -currNum);
                // Try *
                backtrack(result, path + "*" + currStr, num, target, i + 1, eval - prev + prev * currNum,
                        prev * currNum);
            }
        }
    }
}
