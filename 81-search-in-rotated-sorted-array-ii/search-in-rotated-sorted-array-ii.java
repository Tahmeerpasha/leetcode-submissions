class Solution {
    public boolean search(int[] a, int x) {
        int low = 0, high = a.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (a[mid] == x)
                return true;
            if(a[low] == a[mid] && a[high] == a[mid]){
                low++;
                high--;
                continue;
            }
            // Left sorted
            if (a[low] <= a[mid]) {
                if (a[low] <= x && x <= a[mid]) {
                    high = mid - 1;
                } else
                    low = mid + 1;
            } else {
                // Right sorted
                if (a[mid] <= x && x <= a[high]) {
                    low = mid + 1;
                } else
                    high = mid - 1;
            }
        }
        return false;
    }
}