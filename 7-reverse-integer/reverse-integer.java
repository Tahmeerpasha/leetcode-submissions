class Solution {
    public int reverse(int x) {
        int temp=x, ans=0;
        while(temp != 0){
            int last = temp % 10;
            int newAnswer = ans * 10 + last;
            // If the value is changed then it won't be same after undoing the above operation
            if((newAnswer-last)/10 != ans)return 0;
            ans = newAnswer;
            temp /= 10;
        }
        return ans;
    }
}