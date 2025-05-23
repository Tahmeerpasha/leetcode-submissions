class Solution {
    public int findMin(int[] a) {
        int low = 0, high = a.length - 1, min = Integer.MAX_VALUE;
        while (low <= high) {
            int mid = (low + high) / 2;
            if(a[low] <= a[mid] && a[mid] <= a[high]){
                min = Math.min(min, a[low]);
                low++;
                high--;
                continue;
            }
            // If in the sorted part then low will always have the minimum value
            // if(a[low] <= a[high]){
            //     min = Math.min(min, a[low]);
            //     break;
            // }
            // Left sorted then take the min element and eliminate the left space
            if (a[low] <= a[mid]) {
                min = Math.min(min, a[low]);
                low = mid+1;
            } else {
                // Right sorted then take the min element and eliminate the right space
                min = Math.min(min, a[mid]);
                high = mid-1;
            }
        }
        return min;
    }
}