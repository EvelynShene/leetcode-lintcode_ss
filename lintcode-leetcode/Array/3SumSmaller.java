/** 918. 3Sum Smaller (lintcode) / 259. 3Sum Smaller (leetcode - locked)
 *      Given an array of n integers nums and a target, find the number of index triplets i, j, k with 0 <= i < j < k < n 
 *   that satisfy the condition nums[i] + nums[j] + nums[k] < target.
 *
 *   Example: Given nums = [-2,0,1,3], target = 2, return 2.
 *   Explanation: Because there are two triplets which sums are less than 2:
 *                 [-2, 0, 1]
 *                 [-2, 0, 3]
 */
            
//Method: Two pointer
  public int threeSumSmaller(int[] nums, int target) {
      // Write your code here
      if(nums == null || nums.length <= 2){
          return 0;
      }
      int count = 0;
      Arrays.sort(nums);
      for(int i = 0; i < nums.length; i++){
          int new_target = target - nums[i];
          int l = i + 1; 
          int r = nums.length - 1;
          while(l < r){
              if(nums[l] + nums[r] < new_target){
                  count += r - l;
                  l++;
              }
              else{
                  r--;
              }
          }
      }
      return count;
  }
