class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length, col = matrix[0].length;
        int low = 0, high = row * col - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int rowVal = mid / col;
            int colVal = mid % col;
            if (matrix[rowVal][colVal] == target)
                return true;
            if (matrix[rowVal][colVal] > target)
                high = mid - 1;
            else
                low = mid + 1;
        }
        return false;
    }
}