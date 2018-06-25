/** 1310. Product of Array Except Self(lintcode) / 238. Product of Array Except Self (leetcode)
 *     Given an array nums of n integers where n > 1,  return an array output such that 
 *   output[i] is equal to the product of all the elements of nums except nums[i].
 *
 *     Note:  Please solve it without division and in O(n).
 *
 *     Example: Input:  [1,2,3,4]; Output: [24,12,8,6]
 */
 
 //Method 1: Brute Force - O(n^2)[AC in lintcode but Time Limit Exceeded in leetcode]
  public int[] productExceptSelf(int[] nums) {
      // write your code here
      if(nums == null || nums.length == 0){
          return null;
      }
      int[] output = new int[nums.length];

      for(int i = 0; i < nums.length; i++){
          int product = 1;
          for(int j = 0; j < nums.length; j++){
              if(i == j){
                  continue;
              }
              product *= nums[j];
              if(product == 0){
                  break;
              }
          }
          output[i] = product;
      }
      return output;
  }
  
  /* Method 2: —— O(n) Time and O(1) Space
   *   Idea: output[i] = nums[i]左侧的元素的乘积 * nums[i]右侧的元素的乘积
   *        左右两侧的乘积分别用一个循环遍历即可完成。
   */ 
   public int[] productExceptSelf(int[] nums) {
      if(nums == null || nums.length == 0){
          return null;
      }
      int[] output = new int[nums.length];
      output[0] = 1;
      for(int i = 1; i < nums.length; i++){
          output[i] = output[i-1] * nums[i-1];
      }
      int right_product = 1;
      for(int i = nums.length - 2; i >= 0; i--){
          output[i] *= right_product * nums[i+1];
          right_product *= nums[i+1];
      }
      return output;
  }

