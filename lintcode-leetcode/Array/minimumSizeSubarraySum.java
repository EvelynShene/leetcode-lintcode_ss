/** 406. Minimum Size Subarray Sum(lintcode) / 209. Minimum Size Subarray Sum (leetcode)
 *    Given an array of n positive integers and a positive integer s, 
 *    find the minimal length of a subarray of which the sum ≥ s. If there isn't one, return -1 instead.
 *
 *    Example: Given the array [2,3,1,2,4,3] and s = 7, Return 2.
 *          the subarray [4,3] has the minimal length under the problem constraint.
 */
 
 /* Method 1: Use 2 pointers, sliding the window - O(n) Time and O(1) Space
  *   滑动窗口 [left, right] 初始大小为0，滑动的规则如下：
  *     - 若元素之和 < s，窗口右边沿向右延伸，直到 元素之和≥s 为止。
  *     - 若元素之和 ≥ s，更新最小长度。然后窗口左边沿右移一位（即移除窗口中第一个元素），重复第1步。
  *   Example: 
  *     [2,3,1,2,4,3]
  *     [2,3,1,2]       ----- minimum window size = 4
  *       [3,1,2,4]     ----- minimum window size = 4
  *         [1,2,4]     ----- minimum window size = 3
  *           [2,4,3]   ----- minimum window size = 3
  *             [4,3]   ----- minimum window size = 2
  */
  
  public int minimumSize(int[] nums, int s) {
      // write your code here
      if(nums == null || nums.length == 0){
          return -1;
      }

      int min_array = Integer.MAX_VALUE;
      int sum = 0;
      int l = 0, r = 0;
      while(l < nums.length && r <= nums.length){
          while(sum < s && r < nums.length){
              sum += nums[r];
              r++;
          }

          if(sum >= s){
              min_array = Math.min(min_array,r-l);
              sum -= nums[l];
          }
          l++;
      }
      if(min_array == Integer.MAX_VALUE){
          return -1;
      }
      return min_array;
  }
  
