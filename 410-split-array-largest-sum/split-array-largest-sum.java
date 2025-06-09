class Solution {
    public int splitArray(int[] nums, int k) {
        if (nums.length < k)
            return -1;
        int low = max(nums);
        int high = sum(nums);

        // for (int i = low; i <= high; i++) {
        //     if (numOfSubArrays(nums, i) == k)
        //         return i;
        // }

        while (low <= high) {
            int mid = (low + high) / 2;
            if (numOfSubArrays(nums, mid) > k)
                low = mid + 1;
            else
                high = mid - 1;
        }
        return low;
    }

    int max(int[] nums) {
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            max = Math.max(max, num);
        }
        return max;
    }

    int sum(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        return sum;
    }

    int numOfSubArrays(int[] arr, int size) {
        int numOfSubArrays = 1, sum = 0;
        for (int num : arr) {
            sum += num;
            if (sum > size) {
                numOfSubArrays++;
                sum = num;
            }
        }
        return numOfSubArrays;
    }
}