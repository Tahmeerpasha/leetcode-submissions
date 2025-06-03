class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length;
        int col = matrix[0].length;

        // Better -> O(row * log(col))
        // int checkRow=-1;
        // for(int i=0; i<row; i++){
        //     if(target <= matrix[i][column-1] && target >= matrix[i][0] )checkRow=i;
        // }
        // if(checkRow == -1)return false;
        // else return binarySearch(matrix[checkRow], target);

        // Optimal -> Time -> O(log(row*col))
        int low = 0, high = (row * col - 1);
        while (low <= high) {
            int mid = (low + high) / 2;
            int rowVal = mid / col;
            int colVal = mid % col;
            if (matrix[rowVal][colVal] == target)
                return true;
            else if (matrix[rowVal][colVal] > target)
                high = mid - 1;
            else
                low = mid + 1;
        }
        return false;
    }

    public boolean binarySearch(int[] a, int x) {
        int low = 0, high = a.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (a[mid] == x)
                return true;
            if (a[mid] < x) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return false;
    }
}