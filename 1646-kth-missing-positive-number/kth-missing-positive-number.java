class Solution {
    public int findKthPositive(int[] arr, int k) {
        // Brute => Time - O(n) && Space - O(1)
        // for(int num:arr){
        //     if(num <= k)k++;
        //     else break;
        // }
        // return k;

        // Optimal => Time - O(logN) && Space - O(1)
        int low = 0, high = arr.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            int missing = arr[mid] - (mid + 1);
            if (missing < k)
                low = mid + 1;
            else
                high = mid - 1;
        }
        return low + k;
    }
}