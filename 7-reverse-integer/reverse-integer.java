class Solution {
    public int reverse(int x) {
        // int temp=x, ans=0;
        // while(temp != 0){
        //     int last = temp % 10;
        //     int newAnswer = ans * 10 + last;
        //     // If the value is changed then it won't be same after undoing the above operation
        //     if((newAnswer-last)/10 != ans)return 0;
        //     ans = newAnswer;
        //     temp /= 10;
        // }
        // return ans;


        int reversed = 0;
        while (x != 0) {
            int digit = x % 10;
            x /= 10;

            // Check for overflow before multiplying
            if (reversed > Integer.MAX_VALUE / 10 ||
                (reversed == Integer.MAX_VALUE / 10 && digit > 7)) return 0;

            if (reversed < Integer.MIN_VALUE / 10 ||
                (reversed == Integer.MIN_VALUE / 10 && digit < -8)) return 0;

            reversed = reversed * 10 + digit;
        }
        return reversed;
    }
}