/** 1021. Number of Subarrays with Bounded Maximum(lintcode) / 795. Number of Subarrays with Bounded Maximum
 *      We are given an array A of positive integers, and two positive integers L and R (L <= R).
 *      Return the number of (contiguous, non-empty) subarrays such that the value of the maximum array element 
 *    in that subarray is at least L and at most R.
 *
 *      Note: L, R  and A[i] will be an integer in the range [0, 10^9].
 *            The length of A will be in the range of [1, 50000].
 *
 *      Example: Input: A = [2, 1, 4, 3] , L = 2 , R = 3 ; Output: 3
 *        Explanation: There are three subarrays that meet the requirements: [2], [2, 1], [3].
 */
 
 //Method 1:
  public int numSubarrayBoundedMax(int[] A, int L, int R) {
      // Write your code here
      if(A == null || A.length == 0){
          return 0;
      }
      int count = 0;
      for(int i = 0; i < A.length; i++){
          int max = A[i];
          for(int j = i; j < A.length; j++){
              max = Math.max(max, A[j]);
              if(max >= L && max <= R){
                  count++;
              }
              else if(max > R){
                  break;
              }
          }
      }
      return count;
  }
