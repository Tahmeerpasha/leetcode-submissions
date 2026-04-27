class Solution {
    public int firstUniqChar(String s) {
        int[] freq = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            freq[ch - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (freq[ch - 'a'] == 1)
                return i;
        }
        return -1;
    }
}
/**
loveleetcode

l -> 2
o -> 2
v -> 1
e -> 4
t -> 1
c -> 1
d -> 1
 */