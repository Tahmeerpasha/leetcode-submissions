class Solution {
    public int minDeletion(String s, int k) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        if (map.size() <= k)
            return 0;
        return map.values().stream()
                .sorted()
                .limit(map.size() - k)
                .mapToInt(Integer::intValue)
                .sum();
    }
}