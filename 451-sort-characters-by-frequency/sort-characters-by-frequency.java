class Solution {
    // public String frequencySort(String s) {
    //     Map<Character, Integer> map = new HashMap<>();
    //     for (int i = 0; i < s.length(); i++) {
    //         map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
    //     }

    //     Map<Character, Integer> sortedMap = map.entrySet()
    //             .stream()
    //             .sorted((e1, e2) -> Integer.compare(e2.getValue(), e1.getValue())) // descending
    //             .collect(Collectors.toMap(
    //                     Map.Entry::getKey,
    //                     Map.Entry::getValue,
    //                     (e1, e2) -> e1,
    //                     LinkedHashMap::new));

    //     StringBuilder sb = new StringBuilder();
    //     for (Map.Entry<Character, Integer> entry : sortedMap.entrySet()) {
    //         char ch = entry.getKey();
    //         int freq = entry.getValue();
    //         sb.append(String.valueOf(ch).repeat(freq));
    //     }

    //     return sb.toString();
    // }

    public String frequencySort(String s) {
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : s.toCharArray())
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);

        List<Character>[] buckets = new ArrayList[s.length() + 1];
        for (char c : freqMap.keySet()) {
            int freq = freqMap.get(c);
            if (buckets[freq] == null)
                buckets[freq] = new ArrayList<>();
            buckets[freq].add(c);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = buckets.length - 1; i >= 1; i--) {
            if (buckets[i] != null) {
                for (char c : buckets[i]) {
                    sb.append(String.valueOf(c).repeat(i));
                }
            }
        }

        return sb.toString();
    }

}