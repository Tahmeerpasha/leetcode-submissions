class Solution {
    public List<String> validStrings(int n) {
        List<String> result = new ArrayList<>();
        generateValidStrings(n, "", result);
        return result;

    }

    void generateValidStrings(int n, String curr, List<String> result) {
        if (curr.length() == n) {
            result.add(curr);
            return;
        }

        generateValidStrings(n, curr + "1", result);

        if (curr.isEmpty() || curr.charAt(curr.length() - 1) != '0')
            generateValidStrings(n, curr + "0", result);
    }
}