class Solution {
    public int splitArray(int[] nums, int k) {
        ArrayList<Integer> list = Arrays.stream(nums)
                .boxed()
                .collect(Collectors.toCollection(ArrayList::new));
        return findPages(list, nums.length, k);
    }

    public int findPages(ArrayList<Integer> arr, int n, int m) {
        if (n < m)
            return -1;
        int low = arr.stream()
                .mapToInt(Integer::intValue)
                .max()
                .orElse(Integer.MIN_VALUE);
        int high = arr.stream().mapToInt(Integer::intValue).sum();

        // for (int i = low; i <= high; i++) {
        //     if (canAllocateToAll(arr, i) == m)
        //         return i;
        // }

        while(low <= high){
            int mid = (low+high)/2;
            if(canAllocateToAll(arr, mid) > m)low = mid+1;
            else high = mid-1 ;
        }
        return low;
    }

    int canAllocateToAll(ArrayList<Integer> arr, int size) {
        int numOfStudents = 1, sum = 0;

        for (int num : arr) {
            sum += num;
            if (sum > size) {
                numOfStudents++;
                sum = num;
            }
        }

        return numOfStudents;
    }
}