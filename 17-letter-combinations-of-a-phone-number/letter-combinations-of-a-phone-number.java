class Solution {
    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0)
            return new ArrayList<>();

        Map<Character, List<String>> numMap = new HashMap<>();
        numMap.put('2', Arrays.asList("a", "b", "c"));
        numMap.put('3', Arrays.asList("d", "e", "f"));
        numMap.put('4', Arrays.asList("g", "h", "i"));
        numMap.put('5', Arrays.asList("j", "k", "l"));
        numMap.put('6', Arrays.asList("m", "n", "o"));
        numMap.put('7', Arrays.asList("p", "q", "r", "s"));
        numMap.put('8', Arrays.asList("t", "u", "v"));
        numMap.put('9', Arrays.asList("w", "x", "y", "z"));

        List<String> result = new ArrayList<>();
        backtrack(digits, 0, "", numMap, result);
        return result;
    }

    void backtrack(String digits, int index, String path, Map<Character, List<String>> map, List<String> result) {
        if (index == digits.length()) {
            result.add(path);
            return;
        }

        char digit = digits.charAt(index);
        for (String letter : map.get(digit)) {
            backtrack(digits, index + 1, path + letter, map, result);
        }
    }
}
