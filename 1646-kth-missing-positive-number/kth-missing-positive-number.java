class Solution {
    public int findKthPositive(int[] arr, int k) {
        // Brute => Time - O(n) && Space - O(1)
        for(int num:arr){
            if(num <= k)k++;
            else break;
        }
        return k;
    }
}