class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        // int[][] rotatedMatrix = new int[n][n];
        // Brute force - > time -> O(n^2) & space -> O(n^2)
        // for(int row=0; row<n; row++){
        //     for(int col=0; col<n; col++){
        //         rotatedMatrix[col][n-row-1] = matrix[row][col];
        //     }
        // }

        // for(int row=0; row<n; row++){
        //     for(int col=0; col<n; col++){
        //         matrix[row][col] = rotatedMatrix[row][col];
        //     }
        // }

        // Optimal -> Transpose the matrix and reverse each row.
        // To Transpose -> Swap all the non diagonal elements of the matrix.
        // Time => O(n^2) & Space => O(1)
        for(int row=0; row<n; row++){
            for(int col=row; col<n; col++){
                if(row != col){
                    swap(matrix, row, col);
                }
            }
        }

        for(int row=0; row<n; row++){
            reverse(matrix[row]);
        }

    }

    void reverse(int[] arr){
        int i=0, j=arr.length-1;
        while(i<j){
            swap(arr, i++, j--);
        }
    }

    void swap(int[][] matrix, int row, int col){
        int temp = matrix[row][col];
        matrix[row][col] = matrix[col][row];
        matrix[col][row] = temp;
    }

    void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}