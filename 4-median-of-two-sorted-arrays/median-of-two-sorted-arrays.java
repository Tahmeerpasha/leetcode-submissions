class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int[] mergedArray = new int[m+n];
        int i=0,j=0,k=0;
        while(j < m && k < n){
            if(nums1[j] < nums2[k])
                mergedArray[i++] = nums1[j++];
            else if(nums1[j] > nums2[k])
                mergedArray[i++] = nums2[k++];
            else{
                mergedArray[i++] = nums1[j++];
                mergedArray[i++] = nums2[k++];
            }
        }
        while(j<m)
            mergedArray[i++] = nums1[j++];

        while(k<n)
            mergedArray[i++] = nums2[k++];

        int totalLength = m + n;
        if (totalLength % 2 == 1) {
            return mergedArray[totalLength / 2];
        } else {
            int mid1 = totalLength / 2 - 1;
            int mid2 = totalLength / 2;
            return (mergedArray[mid1] + mergedArray[mid2]) / 2.0;
        }
    }
}