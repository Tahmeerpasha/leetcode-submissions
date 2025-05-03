class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        int row=matrix.length, column=matrix[0].length;
        int top=0, bottom=row-1, left=0, right=column-1;
        List<Integer> answer = new ArrayList<>(row*column);
        while(top<=bottom && left <= right){
            for(int i=left; i<=right; i++){
                answer.add(matrix[top][i]);
            }
            top++;
            for(int i=top; i<=bottom; i++){
                answer.add(matrix[i][right]);
            }
            right--;
            if(top<=bottom){
                for(int i=right; i>=left; i--){
                    answer.add(matrix[bottom][i]);
                }
                bottom--;
            }
            if(left<=right){
                for(int i=bottom; i>=top; i--){
                    answer.add(matrix[i][left]);
                }
                left++;
            }
        }
        return answer;
    }
}