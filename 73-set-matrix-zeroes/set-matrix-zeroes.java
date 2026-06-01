class Solution {
    public void setZeroes(int[][] matrix) {
        int row0 = 1;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                if (row == 0 && matrix[row][col] == 0) {
                    row0 = 0;
                } else if (matrix[row][col] == 0) {
                    matrix[row][0] = 0;
                    matrix[0][col] = 0;
                }
            }
        }
        for (int row = 1; row < matrix.length; row++) {
            if (matrix[row][0] == 0) {
                for (int col = 0; col < matrix[0].length; col++) {
                    matrix[row][col] = 0;
                }
            }
        }
        for (int col = 0; col < matrix[0].length; col++) {
            if (matrix[0][col] == 0) {
                for (int row = 0; row < matrix.length; row++)
                    matrix[row][col] = 0;
            }
        }
        if (row0 == 0) {
            for (int col = 0; col < matrix[0].length; col++)
                matrix[row0][col] = 0;
        }
    }
}