class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length())
            return false;
        String str1 = SortString(s).trim();
        String str2 = SortString(t).trim();
        return str1.equals(str2);
    }

    public static String SortString(String str) {
        char c[] = str.toCharArray();
        Arrays.sort(c);
        return new String(c);
    }
}