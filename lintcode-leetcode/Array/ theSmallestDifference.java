/** 387. The Smallest Difference(lintcode)
 *    Given two array of integers(the first array is array A, the second array is array B), 
 *    now we are going to find a element in array A which is A[i], and another element in array B which is B[j], 
 *    so that the difference between A[i] and B[j] (|A[i] - B[j]|) is as small as possible, return their smallest difference.
 */
 
  public int smallestDifference(int[] A, int[] B) {
      // write your code here
      if (A == null || A.length == 0 || B == null || B.length == 0) {
          return 0;
      }

      Arrays.sort(A);
      Arrays.sort(B);

      int i = 0, j = 0;
      int diff = Integer.MAX_VALUE;
      while(i < A.length && j < B.length){
          diff = Math.min(diff,Math.abs(A[i]-B[j]));
          if(diff == 0){
              return 0;
          }
          if(A[i] < B[j]){
              i++;
          }
          else{
              j++;
          }
      }
      return diff;
  }
