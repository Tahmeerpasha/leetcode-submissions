class Solution {
    public int singleNonDuplicate(int[] a) {
        int n = a.length, low = 1, high = n - 2;
        // Edge cases
        if(n==1)return a[0];
        if(a[n-1] != a[n-2])return a[n-1];
        if(a[0] != a[1])return a[0];
        while (low <= high) {
            int mid = (low + high) / 2;
            // Unique element
            if(a[mid] != a[mid+1] && a[mid] != a[mid-1])return a[mid];
            // (even, odd) -> Element is in the right half so eliminate left half
            if (mid%2 == 1 && a[mid] == a[mid-1] || mid%2 == 0 && a[mid] == a[mid+1]) {
                low = mid+1;
            } else {
            // (odd, even) -> Element is in the left half so eliminate the right half
                high = mid-1;
            }
        }
        return -1; 
    }
}