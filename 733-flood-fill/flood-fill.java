class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int[][] ans = image;
        int iniClr = image[sr][sc];
        int[] drow = { -1, 0, 1, 0 };
        int[] dcol = { 0, 1, 0, -1 };
        dfs(ans, image, drow, dcol, iniClr, color, sr, sc);
        return ans;
    }

    public void dfs(int[][] ans, int[][] image, int[] drow, int[] dcol, int iniClr, int nClr, int sr, int sc) {
        ans[sr][sc] = nClr;
        int row = image.length;
        int col = image[0].length;
        for (int i = 0; i < 4; i++) {
            int nrow = sr + drow[i];
            int ncol = sc + dcol[i];
            if (nrow >= 0 && nrow < row && ncol >= 0 && ncol < col
                    && image[nrow][ncol] == iniClr && ans[nrow][ncol] != nClr)
                dfs(ans, image, drow, dcol, iniClr, nClr, nrow, ncol);
        }
    }
}