class Solution {
    public int romanToInt(String s) {
        int num = giveNumFromChar(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            if(giveNumFromChar(s.charAt(i-1)) < giveNumFromChar(s.charAt(i)))
                num += giveNumFromChar(s.charAt(i)) - (2 * giveNumFromChar(s.charAt(i-1)));
            else
            num += giveNumFromChar(s.charAt(i));
            
        }
        return num;
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