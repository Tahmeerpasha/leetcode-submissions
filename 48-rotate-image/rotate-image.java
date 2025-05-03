class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        int[][] rotatedMatrix = new int[n][n];

        for(int row=0; row<n; row++){
            for(int col=0; col<n; col++){
                rotatedMatrix[col][n-row-1] = matrix[row][col];
            }
        }

        for(int row=0; row<n; row++){
            for(int col=0; col<n; col++){
                matrix[row][col] = rotatedMatrix[row][col];
            }
        }
    }
}