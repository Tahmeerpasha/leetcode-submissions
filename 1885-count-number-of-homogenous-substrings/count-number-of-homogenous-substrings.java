class Solution {
    public int countHomogenous(String s) {
        long count = 1, result = 0, mod = 1_000_000_007;

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                count++;
            } else {
                result = (result + (count * (count + 1) / 2)) % mod;
                count = 1;
            }
        }

        // Add last group
        result = (result + (count * (count + 1) / 2)) % mod;

        return (int) result;
    }
}
