class Solution {
    public int trap(int[] height) {
        // Brute -> Time => O(3n) && Space => O(2n) 
        // int[] prefixMax = prefixMax(height);
        // int[] suffixMax = suffixMax(height);
        // int total = 0;
        // for (int i = 0; i < height.length; i++) {
        //     if (height[i] < prefixMax[i] && height[i] < suffixMax[i]) {
        //         total += Math.min(prefixMax[i], suffixMax[i]) - height[i];
        //     }
        // }
        // return total;

        // Better -> Time => O(2n) && Space => O(n) 
        int[] suffixMax = suffixMax(height);
        int total = 0;
        int prefixMax = 0;
        for (int i = 0; i < height.length; i++) {
            prefixMax = Math.max(prefixMax, height[i]);
            if (height[i] < prefixMax && height[i] < suffixMax[i]) {
                total += Math.min(prefixMax, suffixMax[i]) - height[i];
            }
        }
        return total;

        // Optimal -> Time => O(n) && Space => O(1)
        // int leftMax = 0, rightMax = 0, total = 0;
        // int left = 0, right = height.length - 1;
        // while (left < right) {
        //     if (height[left] <= height[right]) {
        //         if (leftMax > height[left])
        //             total += leftMax - height[left];
        //         else
        //             leftMax = height[left];
        //         left++;
        //     } else {
        //         if (rightMax > height[right])
        //             total += rightMax - height[right];
        //         else
        //             rightMax = height[right];
        //         right--;
        //     }
        // }
        // return total;
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