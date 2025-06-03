class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        // Brute => Time - O(m+n) && Space - O(m+n)
        // int[] mergedArray = new int[m+n];
        // int i=0,j=0,k=0;
        // while(j < m && k < n){
        //     if(nums1[j] < nums2[k])
        //         mergedArray[i++] = nums1[j++];
        //     else if(nums1[j] > nums2[k])
        //         mergedArray[i++] = nums2[k++];
        //     else{
        //         mergedArray[i++] = nums1[j++];
        //         mergedArray[i++] = nums2[k++];
        //     }
        // }
        // while(j<m)
        //     mergedArray[i++] = nums1[j++];

        // while(k<n)
        //     mergedArray[i++] = nums2[k++];

        // int totalLength = m + n;
        // if (totalLength % 2 == 1) {
        //     return mergedArray[totalLength / 2];
        // } else {
        //     int mid1 = totalLength / 2 - 1;
        //     int mid2 = totalLength / 2;
        //     return (mergedArray[mid1] + mergedArray[mid2]) / 2.0;
        // }

        // Better 
        int totalN = m + n;
        int ind2 = totalN / 2;
        int ind1 = ind2 - 1;
        int cnt = 0;
        int ele1 = -1, ele2 = -1;
        int i = 0, j = 0;
        while (i < m && j < n) {
            if (nums1[i] < nums2[j]) {
                if (cnt == ind1)
                    ele1 = nums1[i];
                if (cnt == ind2)
                    ele2 = nums1[i];
                cnt++;
                i++;
            } else {
                if (cnt == ind1)
                    ele1 = nums2[j];
                if (cnt == ind2)
                    ele2 = nums2[j];
                cnt++;
                j++;
            }
        }
        while (i < m) {
            if (cnt == ind1)
                ele1 = nums1[i];
            if (cnt == ind2)
                ele2 = nums1[i];
            cnt++;
            i++;
        }

        while (j < n) {
            if (cnt == ind1)
                ele1 = nums2[j];
            if (cnt == ind2)
                ele2 = nums2[j];
            cnt++;
            j++;
        }

        if (totalN % 2 == 1)
            return ele2;
        else
            return (ele1 + ele2) / 2.0;
    }
}