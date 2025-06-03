class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length;
        int column = matrix[0].length;

        int checkRow=-1;
        for(int i=0; i<row; i++){
            if(target <= matrix[i][column-1] && target >= matrix[i][0] )checkRow=i;
        }
        if(checkRow == -1)return false;
        else return binarySearch(matrix[checkRow], target);
    }

    public boolean binarySearch(int[] a, int x) {
        int low = 0, high = a.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if(a[mid] == x)return true;
            if (a[mid]< x) {
                low = mid+1;
            } else {
                high = mid-1;
            }
        }
        return false;
    }
}