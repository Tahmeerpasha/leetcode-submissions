class Solution {
    public String reverseWords(String s) {
        String[] words = s.split(" ");
        int firstWordVowelsCnt = findVowels(words[0]);
        for (int i = 1; i < words.length; i++) {
            int vowelsCnt = findVowels(words[i]);
            if (vowelsCnt == firstWordVowelsCnt) {
                StringBuilder str = new StringBuilder(words[i]);
                words[i] = str.reverse().toString();
            }
        }
        return String.join(" ", words);
    }

    int findVowels(String s) {
        int cnt = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'a' ||
                    s.charAt(i) == 'e' ||
                    s.charAt(i) == 'i' ||
                    s.charAt(i) == 'o' ||
                    s.charAt(i) == 'u')
                cnt++;
        }
        return cnt;
    }
}