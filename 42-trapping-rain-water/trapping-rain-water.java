class Solution {
    public int trap(int[] height) {
        int[] prefixMax = prefixMax(height);
        int[] suffixMax = suffixMax(height);
        int total = 0;
        for (int i = 0; i < height.length; i++) {
            if (height[i] < prefixMax[i] && height[i] < suffixMax[i]) {
                total += Math.min(prefixMax[i], suffixMax[i]) - height[i];
            }
        }
        return total;
    }

    int[] prefixMax(int[] arr) {
        int prefix[] = new int[arr.length];
        prefix[0] = arr[0];
        for (int i = 1; i < prefix.length; i++) {
            prefix[i] = Math.max(prefix[i - 1], arr[i]);
        }
        return prefix;
    }

    int[] suffixMax(int[] arr) {
        int suffix[] = new int[arr.length];
        suffix[arr.length - 1] = arr[arr.length - 1];
        for (int i = suffix.length - 2; i >= 0; i--) {
            suffix[i] = Math.max(suffix[i + 1], arr[i]);
        }
        return suffix;
    }
}