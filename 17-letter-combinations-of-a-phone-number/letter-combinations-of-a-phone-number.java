class Solution {
    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0)
            return new ArrayList<>();

        String[] numMap = new String[] {
                "", // 0
                "", // 1
                "abc", // 2
                "def", // 3
                "ghi", // 4
                "jkl", // 5
                "mno", // 6
                "pqrs", // 7
                "tuv", // 8
                "wxyz" // 9
        };

        List<String> result = new ArrayList<>();
        // Time -> O(4^n) && Space -> O(4^n + n)
        backtrack(digits, 0, new StringBuilder(), numMap, result);
        return result;
    }

    void backtrack(String digits, int index, StringBuilder path, String[] map, List<String> result) {
        if (index == digits.length()) {
            result.add(path.toString());
            return;
        }

        String letters = map[digits.charAt(index) - '0'];
        for (char c : letters.toCharArray()) {
            path.append(c); // pick
            backtrack(digits, index + 1, path, map, result);
            path.deleteCharAt(path.length() - 1); // backtrack (unpick)
        }
    }
}
