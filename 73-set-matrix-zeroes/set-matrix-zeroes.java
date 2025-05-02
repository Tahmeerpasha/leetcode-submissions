class Solution {
    public void setZeroes(int[][] matrix) {
        List<Position> positions = new ArrayList<>();

        for(int i=0; i<matrix.length; i++){
            for(int j=0; j<matrix[0].length; j++){
                if(matrix[i][j] == 0)
                    positions.add(new Position(i, j));
            }
        }

        for(int i=0; i<positions.size(); i++){
            Position position = positions.get(i);
            int row = position.row;
            int col = position.col;
            for(int j=0; j<matrix[0].length; j++){
                matrix[row][j] = 0;
            }
            for(int j=0; j<matrix.length; j++){
                matrix[j][col] = 0;
            }
        }
    }
}

class Position {
    int row, col;

    Position(int row, int col) {
        this.row = row;
        this.col = col;
    }
}