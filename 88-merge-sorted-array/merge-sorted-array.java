class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // Brute -> Time = O(m+n) && Space -> O(m+n)
        // int[] result = new int[m+n];
        // int first=0, second=0, third=0;
        // while(first < m && second < n){
        //     if(nums1[first] < nums2[second]){
        //         result[third++] = nums1[first];
        //         first++;
        //     }else if(nums2[second] < nums1[first]){
        //         result[third++] = nums2[second];
        //         second++;
        //     }else{
        //         result[third++] = nums1[first++];
        //         result[third++] = nums2[second++];
        //     }
        // }

        // while(first < m){
        //     result[third++] = nums1[first++];
        // }

        // while(second < n){
        //     result[third++] = nums2[second++];
        // }

        // for(int i=0; i<m+n; i++){
        //     nums1[i] = result[i];
        // }

        // Optimal -> Time = O(nlogn) && Space = O(1)
        for(int i=m; i<m+n; i++){
            nums1[i] = nums2[i-m];
        }
        Arrays.sort(nums1);
    }
}