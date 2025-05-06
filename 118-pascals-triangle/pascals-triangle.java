class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        // Time -> O(n*n*r) -> O(n^3) & Space -> O(1)
        // for(int i=1; i<=numRows; i++){
        // List<Integer> answer = new ArrayList<Integer>();
        //     for(int j=1; j<=i; j++){
        //         answer.add(nCr(i-1,j-1));
        //     }
        //     result.add(answer);
        // }
        
        // Time -> O(n^2) & Space -> O(1)
        // for (int i = 0; i < numRows; i++) {
        //     List<Integer> row = new ArrayList<>();
        //     for (int j = 0; j <= i; j++) {
        //         if (j == 0 || j == i) row.add(1);
        //         else row.add(result.get(i - 1).get(j - 1) + result.get(i - 1).get(j));
        //     }
        //     result.add(row);
        // }

        // Time -> O(n^2) & Space -> O(1)
        for(int row=1; row<=numRows; row++){
            result.add(generateRow(row));
        }

        return result;
    }

    List<Integer> generateRow(int row){
        List<Integer> answer = new ArrayList<Integer>();
        int ans = 1;
        answer.add(1);
        for(int col=1; col<row; col++){
            ans *= row-col;
            ans /= col;
            answer.add(ans);
        }
        return answer;
    }

    int nCr(int n, int r){
        int answer=1;
        for(int i=0; i<r; i++){
            answer*=n-i;
            answer/=i+1;
        }
        return answer;
    }
}