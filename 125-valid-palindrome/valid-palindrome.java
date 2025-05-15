class Solution {
    // Recursion approach
    // public boolean isPalindrome(String s) {
    //    return findPalindrome(s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase().toCharArray(),0);
    // }

    // boolean findPalindrome(char[] s, int i){
    //     if(i>=s.length/2)return true;
    //     int n = s.length-i-1;
    //     if(s[i] != s[n])return false;
    //   
    
    public boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;

        while (left < right) {
            // Skip non-alphanumeric from left
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }

            // Skip non-alphanumeric from right
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }

            // Compare lowercase characters
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }

}