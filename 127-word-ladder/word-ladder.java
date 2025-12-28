class Pair {
    String word;
    int steps;

    Pair(String w, int s) {
        word = w;
        steps = s;
    }
}

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Queue<Pair> q = new LinkedList<>();
        Set<String> st = new HashSet<>();
        int len = wordList.size();
        q.add(new Pair(beginWord, 1));
        for (String str : wordList)
            st.add(str);
        st.remove(beginWord);

        while (!q.isEmpty()) {
            String word = q.peek().word;
            int steps = q.peek().steps;
            q.remove();
            if (word.equals(endWord))
                return steps;
            for (int i = 0; i < word.length(); i++) {
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    char replacedCharArray[] = word.toCharArray();
                    replacedCharArray[i] = ch;
                    String replacedWord = new String(replacedCharArray);
                    if (st.contains(replacedWord)) {
                        st.remove(replacedWord);
                        q.add(new Pair(replacedWord, steps + 1));
                    }
                }
            }
        }
        return 0;
    }
}