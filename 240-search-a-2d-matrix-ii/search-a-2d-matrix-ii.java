class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length;
        int col = matrix[0].length;
        // Better -> Time = O(row * log(col))
        // int ansCol = -1;
        // for(int i=0; i<row; i++){
        //     ansCol = binarySearch(matrix[i], target);
        //     if(ansCol != -1)return true;
        // }

        // Optimal -> Time = O(row+col)
        int ansRow = 0, ansCol = col - 1;
        while (ansRow < row && ansCol >= 0) {
            if (matrix[ansRow][ansCol] == target)
                return true;
            else if (matrix[ansRow][ansCol] < target)
                ansRow++;
            else
                ansCol--;
        }
        return false;
    }

    public int binarySearch(int[] a, int x) {
        int low = 0, high = a.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (a[mid] == x)
                return mid;
            if (a[mid] < x) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }
}