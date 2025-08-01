class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        // Brute
        // return solve(new HashSet<>(wordDict), s, 0);
        // Optimal with DP
        return solve(new HashSet<>(wordDict), s, 0, new HashMap<>());
    }

    boolean solve(Set<String> dict, String s, int index) {
        if (index == s.length())
            return true;

        StringBuilder temp = new StringBuilder();
        for (int i = index; i < s.length(); i++) {
            temp.append(s.charAt(i));
            if (dict.contains(temp.toString())) {
                if (solve(dict, s, i + 1))
                    return true;
            }
        }
        return false;
    }

    boolean solve(Set<String> dict, String s, int index, Map<Integer, Boolean> memo) {
        if (index == s.length()) return true;
        if (memo.containsKey(index)) return memo.get(index);

        StringBuilder temp = new StringBuilder();
        for (int i = index; i < s.length(); i++) {
            temp.append(s.charAt(i));
            if (dict.contains(temp.toString())) {
                if (solve(dict, s, i + 1, memo)) {
                    memo.put(index, true);
                    return true;
                }
            }
        }

        memo.put(index, false);
        return false;
    }
}
