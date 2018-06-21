/** 45.Maximum Subarray Difference (lintcode)
 *    Given an array with integers. Find two non-overlapping subarrays A and B, which |SUM(A) - SUM(B)| is the largest.
 *    Return the largest difference.
 *
 *    Example: For [1, 2, -3, 1], return 6.
 */
 
 /* Idea: First find maximum and minimum sum of every subarray and then find the maximum difference
  *       - O(n) time and O(n) space.
  */
  public int maxDiffSubArrays(int[] nums) {
      // write your code here
      if(nums == null || nums.length == 0){
          return 0;
      }
      //left_max[i] = the maximum subarray sum in subarray [0,i]
      int[] left_max = new int[nums.length]; 
      //left_min[i] = the minimum subarray sum in subarray [0,i]
      int[] left_min = new int[nums.length];
      //right_max[i] = the maximum subarray sum in subarray [i,nums.length-1]
      int[] right_max = new int[nums.length];
      //right_min[i] = the minimum subarray sum in subarray [i,nums.length-1]
      int[] right_min = new int[nums.length];

      int max = nums[0];
      int max_sum = 0;
      // get left_max[]
      for(int i = 0; i < nums.length; i++){
          if(max_sum > 0){
              max_sum += nums[i];
          }
          else{
              max_sum = nums[i];
          }
          max = Math.max(max_sum, max);
          left_max[i] = max;
      }
      // get right_max[]
      max = nums[nums.length - 1];
      max_sum = 0;
      for(int i = nums.length - 1; i >= 0; i--){
          if(max_sum > 0){
              max_sum += nums[i];
          }
          else{
              max_sum = nums[i];
          }
          max = Math.max(max_sum, max);
          right_max[i] = max;
      }

      int min = nums[0];
      int min_sum = 0;
      //get left_min[]
      for(int i = 0; i < nums.length; i++){
          min_sum = Math.min(min_sum + nums[i], nums[i]);
          min = Math.min(min, min_sum);
          left_min[i] = min;
      }

      min = nums[nums.length - 1];
      min_sum = 0;
      //get right_min[]
      for(int i = nums.length - 1; i >= 0; i--){
          min_sum = Math.min(min_sum + nums[i], nums[i]);
          min = Math.min(min, min_sum);
          right_min[i] = min;
      }

      int max_diff = 0;
      for(int i = 0; i < nums.length - 1; i++){
          int diff1 = Math.abs(left_max[i] - right_min[i+1]);
          int diff2 = Math.abs(right_max[i+1] - left_min[i]);
          if(diff1 > diff2)
            max_diff = Math.max(diff1,max_diff);
          else
            max_diff = Math.max(diff2,max_diff);
      }
      return max_diff;
  }
