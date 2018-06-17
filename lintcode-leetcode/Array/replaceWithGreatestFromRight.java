/** 735. Replace With Greatest From Right(lintcode)
 *    Given an array of integers, replace every element with the next greatest element (greatest element on the right side) 
 *    in the array. Since there is no element next to the last element, replace it with -1. 
 *    For example, if the array is [16, 17, 4, 3, 5, 2], then it should be modified to [17, 5, 5, 5, 2, -1].
 *    Note : You should do it in place.
 */

//Method 1: Time complexity is O(n^2)
  public void arrayReplaceWithGreatestFromRight(int[] nums) {
      // Write your code here.
      for(int i = 0; i < nums.length; i++){
          int max = maxnum(nums,i+1);
          nums[i] = max;
      }
  }

  public int maxnum(int[] nums, int start){
      if(start == nums.length){
          return -1;
      }
      int max = Integer.MIN_VALUE;
      for(int i = start; i < nums.length; i++){
          max = Math.max(nums[i],max);
      }
      return max;
  }
  
  /* Method 2: Time complexity is O(n) 
   * Idea: 从后往前遍历数组，保留当前得到的最大值，用于替换前面的数。数组只需遍历一次
   */
    public void arrayReplaceWithGreatestFromRight(int[] nums) {
        // Write your code here.
        int max = Integer.MIN_VALUE;
        int premax = max;
        for(int i = nums.length-1; i >= 0; i--){
            if(i == nums.length-1){
                max = nums[i];
                nums[i] = -1;
            }
            else{
                premax = max;
                max = Math.max(max,nums[i]);
                nums[i] = premax;     
            }
        }
    }
  
