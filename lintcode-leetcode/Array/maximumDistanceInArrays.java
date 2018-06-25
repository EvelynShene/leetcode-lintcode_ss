/** 698. Maximum Distance in Arrays (lintcode) / 624. Maximum Distance in Arrays(leetcode-locked)
 *    Given m arrays, and each array is sorted in ascending order. 
 *    Now you can pick up two integers from two different arrays (each array picks one) and calculate the distance. 
 *    We define the difference between two integers a and b to be their absolute difference |a-b|. 
 *    Your task is to find the maximum difference.
 *
 *    Note: Each given array will have at least 1 number. There will be at least two non-empty arrays.
 *          The total number of the integers in all the m arrays will be in the range of [2, 10000].
 *          The integers in the m arrays will be in the range of [-10000, 10000].
 *
 *    Example: Given arrays = [[1,2,3], [4,5], [1,2,3]] ; Return 4
 */
 
 //My Method: 
 public int maxDiff(int[][] arrs) {
      if(arrs == null || arrs.length == 0){
          return 0;
      }
      int[] min = {arrs[0][0],arrs[0][0]};
      int[] min_index = new int[2];
      int[] max = {arrs[0][arrs[0].length-1],arrs[0][arrs[0].length-1]};
      int[] max_index = new int[2];
      for(int i = 0; i < arrs.length; i++){
          int size = arrs[i].length;
          if(min[0] >= arrs[i][0]){
              min[1] = min[0];
              min[0] = arrs[i][0];
              min_index[1] = min_index[0];
              min_index[0] = i;
          }
          if(max[0] <= arrs[i][size-1]){
              max[1] = max[0];
              max[0] = arrs[i][size-1];
              max_index[1] = max_index[0];
              max_index[0] = i;
          }
      }

      if(max_index[0] != min_index[0]){
          return Math.abs(max[0]-min[0]);
      }
      else{
          return Math.max(Math.abs(max[0]-min[1]),Math.abs(max[1]-min[0]));
      }   
  }
  
  //Method 2: (From leetcode)
    public int maxDistance(int[][] list) {
        int res = 0, min_val = list[0][0], max_val = list[0][list[0].length - 1];
        for (int i = 1; i < list.length; i++) {
            res = Math.max(res, Math.max(Math.abs(list[i][list[i].length - 1] - min_val), Math.abs(max_val - list[i][0])));
            min_val = Math.min(min_val, list[i][0]);
            max_val = Math.max(max_val, list[i][list[i].length - 1]);
        }
        return res;
    }
