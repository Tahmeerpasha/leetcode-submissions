class Solution {
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length())
            return false;

        // Map<Character, Character> mapST = new HashMap<>();
        // Map<Character, Character> mapTS = new HashMap<>();

        // for (int i = 0; i < s.length(); i++) {
        //     char chS = s.charAt(i);
        //     char chT = t.charAt(i);

        //     if (mapST.containsKey(chS)) {
        //         if (mapST.get(chS) != chT)
        //             return false;
        //     } else {
        //         if (mapTS.containsKey(chT))
        //             return false;

        //         mapST.put(chS, chT);
        //         mapTS.put(chT, chS);
        //     }
        // }

        int[] mapS = new int[256];
        int[] mapT = new int[256];

        for (int i = 0; i < s.length(); i++) {
            if (mapS[s.charAt(i)] != mapT[t.charAt(i)])
                return false;
            mapS[s.charAt(i)] = i + 1;
            mapT[t.charAt(i)] = i + 1;
        }
        return true;
    }

}