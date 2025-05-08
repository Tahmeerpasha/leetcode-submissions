class Solution {
    public int maxSubArray(int[] nums) {
    // ArrayList<Integer> answers = new ArrayList<>();
    int n = nums.length;
    if(n <= 1)return nums[0];

    // // O(n^3) -> time and space -> O(n^3)
    // for(int i=0; i<n ; i++){
    //   for(int j=i; j<n; j++){
    //     int sum = 0;
    //     for(int k=i; k<=j; k++)
    //       sum += nums[k];
    //     answers.add(sum);
    //   }
    // }
    // answers.sort(null);
    // return !answers.isEmpty() ? answers.get(answers.size()-1) : 0;

    // // O(n^2) -> time and space -> O(n^2)
    // for(int i=0; i<n ; i++){
    // int sum = 0;
    //   for(int j=i; j<n; j++){
    //       sum += nums[j];
    //     answers.add(sum);
    //   }
    // }
    // answers.sort(null);
    // return !answers.isEmpty() ? answers.get(answers.size()-1) : 0;

    // O(n^2) -> time and space -> O(1)
    // int max = Integer.MIN_VALUE;
    // for(int i=0; i<n ; i++){
    // int sum = 0;
    //   for(int j=i; j<n; j++){
    //       sum += nums[j];
    //       max = Math.max(max,sum);
    //   }
    // }
    // return max;

    // O(n^2) -> time and space -> O(1)
    // int max = Integer.MIN_VALUE;
    // for(int i=0; i<n ; i++){
    // int sum = 0;
    //   for(int j=i; j<n; j++){
    //       sum += nums[j];
    //       max = Math.max(max,sum);
    //   }
    // }
    // return max;

    // O(n) -> time and space -> O(1)
    int maximum = Integer.MIN_VALUE;
    int sum = 0;
    for(int index=0; index<n; index++){
        if(sum<0) sum=0;
        sum += nums[index];
        maximum = Math.max(maximum, sum);
    }
    return maximum;
  }
}
