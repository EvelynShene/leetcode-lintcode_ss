/** 877. Split Array with Equal Sum (lintcode) / 548. Split Array with Equal Sum (leetcode - locked)
 *    Given an array with n integers, you need to find if there are triplets (i, j, k) which satisfies following conditions:
 *      1. 0 < i, i + 1 < j, j + 1 < k < n - 1
 *      2. Sum of subarrays (0, i - 1), (i + 1, j - 1), (j + 1, k - 1) and (k + 1, n - 1) should be equal.
 *      where we define that subarray (L, R) represents a slice of the original array starting from the element indexed L to the element indexed R.
 *   
 *    Note: 1. 1 <= n <= 2000.
 *          2. Elements in the given array will be in range [-1,000,000, 1,000,000].
 *
 *    Example: Given nums = [1,2,1,2,1,2,1], return True
 *        Explanation: i = 1, j = 3, k = 5. 
 *                     sum(0, i - 1) = sum(0, 0) = 1
 *                     sum(i + 1, j - 1) = sum(2, 2) = 1
 *                     sum(j + 1, k - 1) = sum(4, 4) = 1
 *                     sum(k + 1, n - 1) = sum(6, 6) = 1
 */
 
  public boolean splitArray(List<Integer> nums) {
      // write your code here
      if(nums == null || nums.size() < 7){
          return false;
      }
      // left[i] = sum(0,i-1)
      int[] left = new int[nums.size()];
      // right[i] = sum(i+1,nums.size()-1)
      int[] right = new int[nums.size()];
      left[0] = 0;
      right[nums.size()-1] = 0;
      for(int i = 1; i < nums.size(); i++){
          left[i] = left[i-1] + nums.get(i-1);
      }
      for(int i = nums.size() - 2; i >= 0; i--){
          right[i] = right[i+1] + nums.get(i+1);
      }
      for(int i = 1; i < nums.size(); i++){
          for(int k = i + 4; k < nums.size() - 1; k++){
              if(left[i] == right[k]){
                  for(int j = i + 2; j < k - 1; j++){
                      if((right[i] - right[j] - nums.get(j)) == (right[j] - right[k] - nums.get(k)) && (right[i] - right[j] - nums.get(j)) == left[i]){
                          return true;
                      }
                  }
              }
          }
      }
      return false;
  }
