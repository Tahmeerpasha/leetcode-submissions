class Solution {
    public boolean findRotation(int[][] mat, int[][] target) {
        int n=mat.length;
        int c90=0,c180=0,c270=0,c0=0;
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                if(mat[i][j]==target[n-j-1][i])
                    c90++;
                if(mat[i][j]==target[n-i-1][n-j-1])
                    c180++;
                if(mat[i][j]==target[j][n-i-1])
                    c270++;
                if(mat[i][j]==target[i][j])
                    c0++;

                // if(mat[i][j]==target[i][n-j-1])
                //     c90++;
                // if(mat[i][j]==target[n-i-1][n-j-1])
                //     c180++;
                // if(mat[i][j]==target[n-i-1][j])
                //     c270++;
                // if(mat[i][j]==target[i][j])
                //     c0++;
            }
        }
        
        return (c90==n*n||c270==n*n||c180==n*n||c0==n*n);
    }
}