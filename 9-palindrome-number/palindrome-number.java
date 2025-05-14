class Solution {
    public boolean isPalindrome(int x) {
        int temp = x, result=0;
        if(x < 0)return false;
        while(temp != 0){
            int lastDigit = temp % 10;
            result = result * 10 + lastDigit;
            temp = temp / 10;
        }
        return result == x;
    }
}