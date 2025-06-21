class Solution {
    public int romanToInt(String s) {
        // Left to Right logic 
        // int num = giveNumFromChar(s.charAt(0));
        // for (int i = 1; i < s.length(); i++) {
        //     if (giveNumFromChar(s.charAt(i - 1)) < giveNumFromChar(s.charAt(i)))
        //         num += giveNumFromChar(s.charAt(i)) - (2 * giveNumFromChar(s.charAt(i - 1)));
        //     else
        //         num += giveNumFromChar(s.charAt(i));

        // }
        // return num;

        // Right to left logic
        int n = s.length();
        int ans = giveNumFromChar(s.charAt(n - 1));
        for (int i = n - 2; i >= 0; i--) {
            int curr = giveNumFromChar(s.charAt(i));
            int next = giveNumFromChar(s.charAt(i + 1));
            if (curr < next)
                ans -= curr;
            else
                ans += curr;
        }
        return ans;
    }

    int giveNumFromChar(Character c) {
        switch (c) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0;
        }
    }
}