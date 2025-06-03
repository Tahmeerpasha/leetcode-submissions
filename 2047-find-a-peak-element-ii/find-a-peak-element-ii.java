class Solution {
    public int[] findPeakGrid(int[][] mat) {
        int[] ans = new int[2];
        int row = mat.length;
        int col = mat[0].length;
        int low = 0, high = col - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int maxIndInRow = maxInd(mat, 0, row - 1, mid);
            int left = mid - 1 >= 0 ? mat[maxIndInRow][mid - 1] : -1;
            int right = mid + 1 < col ? mat[maxIndInRow][mid + 1] : -1;
            if (mat[maxIndInRow][mid] > left && mat[maxIndInRow][mid] > right)
                return new int[] { maxIndInRow, mid };
            else if (mat[maxIndInRow][mid] < left)
                high = mid - 1;
            else
                low = mid + 1;
        }
        return new int[] { -1, -1 };
    }

    int maxInd(int[][] mat, int start, int end, int currCol){
        int max=-1, maxInd=-1;
        for(int i=start; i<=end; i++){
            if(mat[i][currCol] > max){
                max = mat[i][currCol];
                maxInd = i;
            }
        }
        return maxInd;
    }
}