/** 397. Longest Continuous Increasing Subsequence(lintcode)
 *   Give an integer array，find the longest increasing continuous subsequence in this array.
 *   An increasing continuous subsequence:
 *     Can be from right to left or from left to right.
 *     Indices of the integers in the subsequence should be continuous.
 *   Note: O(n) time and O(1) extra space.
 */
 
 public int longestIncreasingContinuousSubsequence(int[] A) {
      // write your code here
      int inc = 1;
      int dec = 1;
      int max = 1;

      if(A == null || A.length == 0){
          return 0;
      }
      if(A.length == 1){
          return 1;
      }

      for(int i = 1; i < A.length; i++){
          if(A[i] > A[i-1]){
              inc++;
          }
          else{
              max = Math.max(max,inc);
              inc = 1;
          }
      }
      max = Math.max(max,inc);

      for(int i = 1; i < A.length; i++){
          if(A[i] < A[i-1]){
              dec++;
          }
          else{
              max = Math.max(max,dec);
              dec = 1;
          }
      }
      max = Math.max(max,dec);

      return max;
  }
  
  /** 674. Longest Continuous Increasing Subsequence(leetcode)
   *  An increasing continuous subsequence: from right to left
   *  Example: Input: [2,2,2,2,2]; Output: 1
   */
  public int findLengthOfLCIS(int[] nums) {
      int ans = 0, anchor = 0;
      for (int i = 0; i < nums.length; ++i) {
          if (i > 0 && nums[i-1] >= nums[i]) anchor = i;
          ans = Math.max(ans, i - anchor + 1);
      }
      return ans;
  }
