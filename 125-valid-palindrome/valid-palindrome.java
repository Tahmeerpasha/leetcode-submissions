class Solution {
    public boolean isPalindrome(String s) {
       return findPalindrome(s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase().toCharArray(),0);
    }

    boolean findPalindrome(char[] s, int i){
        if(i>=s.length/2)return true;
        int n = s.length-i-1;
        if(s[i] != s[n])return false;
        else return findPalindrome(s, i+1);
    }
}