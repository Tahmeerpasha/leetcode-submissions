class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> list = new ArrayList<>();
        for(int i=1; i<=numRows; i++){
        List<Integer> answer = new ArrayList<Integer>();
            for(int j=1; j<=i; j++){
                answer.add(nCr(i-1,j-1));
            }
            list.add(answer);
        }
        return list;
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