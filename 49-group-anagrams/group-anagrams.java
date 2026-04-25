class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chrArr = str.toCharArray();
            Arrays.sort(chrArr);
            String newStr = new String(chrArr);
            if (map.get(newStr) != null) {
                map.get(newStr).add(str);
            } else {
                List<String> list = new ArrayList<>();
                list.add(str);
                map.put(newStr, list);

            }
        }
        return new ArrayList<>(map.values());
    }
}

/**
strs = ["eat","tea","tan","ate","nat","bat"]
map = 
aet -> [eat, tea, ate]
ant -> [tan,nat]
abt -> [bat]
 */
