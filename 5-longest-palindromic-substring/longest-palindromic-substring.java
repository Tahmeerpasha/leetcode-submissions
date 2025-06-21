class Solution {
    public String longestPalindrome(String s) {
        if(s.length() <= 1)return s;
        int max = 0;
        String str = "";
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j <= s.length(); j++) {
                if (isPalindrome(s.substring(i, j))) {
                    if(j-i > max){
                        max = j-i;
                        str = s.substring(i,j);
                    }
                }
            }
        }
        return str;
    }

    boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left <= right) {
            if (s.charAt(left) != s.charAt(right))
                return false;
            left++;
            right--;
        }
        return true;
    }
}