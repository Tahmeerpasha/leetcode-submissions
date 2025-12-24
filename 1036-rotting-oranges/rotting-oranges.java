class Pair {
    int row;
    int col;
    int time;

    public Pair(int row, int col, int time) {
        this.row = row;
        this.col = col;
        this.time = time;
    }
}

class Solution {
    public int orangesRotting(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        Queue<Pair> q = new LinkedList<>();
        int[][] vis = new int[row][col];
        int cntFresh = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 2) {
                    vis[i][j] = 2;
                    q.add(new Pair(i, j, 0));
                } else
                    vis[i][j] = 0;

                if (grid[i][j] == 1)
                    cntFresh++;
            }
        }
        int[] drow = { -1, 0, 1, 0 };
        int[] dcol = { 0, 1, 0, -1 };
        int cnt = 0, tm = 0;
        while (!q.isEmpty()) {
            int r = q.peek().row;
            int c = q.peek().col;
            int t = q.peek().time;
            q.remove();
            tm = Math.max(tm, t);
            for (int i = 0; i < 4; i++) {
                int nrow = r + drow[i];
                int ncol = c + dcol[i];
                if (nrow >= 0 && nrow < row && ncol >= 0 && ncol < col
                        && vis[nrow][ncol] == 0 && grid[nrow][ncol] == 1) {
                    q.add(new Pair(nrow, ncol, t + 1));
                    vis[nrow][ncol] = 2;
                    cnt++;
                }
            }
        }
        if (cnt != cntFresh)
            return -1;
        return tm;
    }
}