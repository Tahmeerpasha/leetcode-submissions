class Solution {
    public int totalFruit(int[] fruits) {
        // Brute - TC => O(n^2) && Space => O(n)
        // int maxLen = 0;
        // for (int i = 0; i < fruits.length; i++) {
        //     Set<Integer> set = new HashSet<>();
        //     for (int j = i; j < fruits.length; j++) {
        //         set.add(fruits[j]);
        //         if (set.size() > 2)
        //             break;
        //         maxLen = Math.max(maxLen, j - i + 1);
        //     }
        // }
        // return maxLen;
        int l = 0, r = 0, maxLen = 0;
        Map<Integer, Integer> map = new HashMap<>();
        while (r < fruits.length) {
            map.put(fruits[r], map.getOrDefault(fruits[r], 0) + 1);
            if (map.size() > 2) {
                map.put(fruits[l], map.get(fruits[l]) - 1);
                if (map.get(fruits[l]) <= 0)
                    map.remove(fruits[l]);
                l++;
            }
            maxLen = Math.max(maxLen, r - l + 1);
            r++;
        }
        return maxLen;
    }
}