/** 1083. Maximum Sum of 3 Non-Overlapping Subarrays(lintcode) / 689. Maximum Sum of 3 Non-Overlapping Subarrays (leetcode)
 *    In a given array nums of positive integers, find three non-overlapping subarrays with maximum sum.
 *    Each subarray will be of size k, and we want to maximize the sum of all 3*k entries.
 *    Return the result as a list of indices representing the starting position of each interval (0-indexed). 
 *    If there are multiple answers, return the lexicographically smallest one.
 *
 *    Note: 1)nums.length will be between 1 and 20000.
 *          2)nums[i] will be between 1 and 65535.
 *          3)k will be between 1 and floor(nums.length / 3).
 *
 *    Example: Input: [1,2,1,2,6,7,5,1], 2 ; Output: [0, 3, 5]
 *             Explanation: Subarrays [1, 2], [2, 6], [7, 5] correspond to the starting indices [0, 3, 5].
 *             We could have also taken [2, 1], but an answer of [1, 3, 5] would be lexicographically larger.
 */
 
 /* Method: 
  *   先求出所有长度为k的子数组和，用ksum[i]代表以第i个元素数开头的长度为k的子数组的和
  *   然后 1)用 left_max 记录第i个元素开头的k长子数组的左边的和最大的子数组的开头位置
  *       2) 用 right_max 记录第i个元素开头的k长子数组的右边的和最大的子数组的开头位置
  *   最后遍历所有的三个区域[0,i-k] [i,i+k-1][i+k,len)，求和的最大值对应的三个开始位置，记录在ins数组中并返回
  */  
  public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
      // Write your code here
      if(nums == null || nums.length < 3*k || k == 0){
          return null;
      }

      int[] ksum = new int[nums.length - k + 1];
      int sum = 0;
      int index = 0;
      for(int i = 0; i < nums.length; i++){
          if(i < k){
              sum += nums[i];
          }
          else{
              if(i == k){
              ksum[index] = sum;
                  index++;
            }
              sum = sum + nums[i] - nums[i - k];
              ksum[index] = sum;
              index++;
          }
      }

      int[] left_max = new int[ksum.length];
      int[] right_max = new int[ksum.length];
      int max = 0;
      for(int i = 0; i < ksum.length; i++){
          if(i - k < 0){
              left_max[i] = -1;
          }
          else{
              if(max < ksum[i - k]){
                  left_max[i] = i - k;
                  max = ksum[i - k];
              }
              else{
                  left_max[i] = left_max[i-1];
              }
          }
      }
      max = 0;
      for(int i = ksum.length - 1; i >= 0; i--){
          if(i + k >= ksum.length){
              right_max[i] = -1;
          }
          else{
              if(max < ksum[i + k]){
                  right_max[i] = i + k;
                  max = ksum[i + k];
              }
              else{
                  right_max[i] = right_max[i + 1];
              }
          }
      }
      max = 0;
      int[] ins = new int[3];
      for(int i = 0; i < ksum.length; i++){
          if(left_max[i] != -1 && right_max[i] != -1){
              sum = ksum[i] + ksum[left_max[i]] + ksum[right_max[i]];
              if(max < sum){
                  max = sum;
                  ins[0] = left_max[i];
                  ins[1] = i;
                  ins[2] = right_max[i];
              }
          }
      }
      return ins;
  }
