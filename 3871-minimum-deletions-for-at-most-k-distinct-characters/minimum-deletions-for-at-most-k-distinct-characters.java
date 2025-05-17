class Solution {
    public int minDeletion(String s, int k) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        if (map.size() <= k)
            return 0;
        int sum = 0;
        List<Integer> list = map.values().stream().sorted().toList();
        for (int i = 0; i < list.size() - k; i++) {
            sum += list.get(i);
        }
        return sum;
    }
}