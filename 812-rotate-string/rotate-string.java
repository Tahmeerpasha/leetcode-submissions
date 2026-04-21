class Solution {
    public boolean rotateString(String s, String goal) {
        if (s.length() != goal.length())
            return false;
        String appendStr = s + s;
        return appendStr.contains(goal);
    }
}