class Solution {
    static boolean[][] visited;

    public boolean exist(char[][] board, String word) {
        int row = board.length;
        int col = board[0].length;
        visited = new boolean[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (word.charAt(0) == board[i][j] && search(board, word, 0, i, j))
                    return true;
            }
        }
        return false;
    }

    boolean search(char[][] board, String word, int index, int i, int j) {
        if (index == word.length())
            return true;

        if (i >= board.length || i < 0 ||
                j >= board[0].length || j < 0 ||
                board[i][j] != word.charAt(index) || visited[i][j])
            return false;

        visited[i][j] = true;
        if (search(board, word, index + 1, i - 1, j) ||
                search(board, word, index + 1, i + 1, j) ||
                search(board, word, index + 1, i, j - 1) ||
                search(board, word, index + 1, i, j + 1)) {
            return true;
        }
        visited[i][j] = false; // backtrack
        return false;
    }
}