/** 363. Trapping Rain Water(lintcode)/42. Trapping Rain Water (leetcode)
 *    Given n non-negative integers representing an elevation map where the width of each bar is 1, 
 *    compute how much water it is able to trap after raining.
 *
 *    Example: Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.    
 */
 
 /* Method 1: Brute force - Time = O(n^2) 
  * Idea: For each bar(elelment) in the array, 
  *       the maximum level of water it can trap after the rain 
  *             = the minimum of maximum height of bars on both the sides minus its own height.
  *       (每一个bar的最大储水量 = 左右两边的最大的bar高中的小的那个值 - 当前bar高)
  *       (若左右两边的最大的bar高中的小的那个值 < 当前bar高,当前bar无法储水)
  */
 public class Solution {
    /**
     * @param heights: a list of integers
     * @return: a integer
     */
    public int trapRainWater(int[] heights) {
        // write your code here
        int water = 0;
        int left_max = 0;
        int right_max = 0;
        for(int i = 1; i < heights.length; i++){
            left_max = maxHeight(heights,0,i-1);
            right_max = maxHeight(heights,i+1,heights.length-1);
            if(Math.min(left_max,right_max) > heights[i]){
                water += Math.min(left_max,right_max) - heights[i];
            }
        }
        return water;
    }
    
    public int maxHeight(int[] heights, int left, int right){
        int max = 0;
        for(int i = left; i <= right; i++){
            max = Math.max(max,heights[i]);
        }
        return max;
    }
}

//Method 2: 还是Method 1的思路，但是用一个数组存右侧bar中的最大值 - Time = O(n); Space = O(n)
  public int trap(int[] height) {
      // write your code here
      int water = 0;
      int size = height.length;
      int left_max = 0; 
      int[] right_max = new int[size];//right_max[i] = maximum bar in subarray [i,height.lenght-1]

      for(int i = size-1; i >= 0; i--){
          if(i == size-1){
              right_max[size-1] = height[size-1];
          }
          else{
              right_max[i] = Math.max(right_max[i+1],height[i]);
          }   
      }

      for(int i = 0; i < size; i++){
          if(i == 0){
              left_max = height[0]; // left_max indicates the maximum bar in subarray [0,i]
          }
          else{
              left_max = Math.max(left_max, height[i]);
          }
          water += Math.min(left_max,right_max[i]) - height[i];
      }
      return water;
  }

 //Method 3: Two pointers - O(n) time and O(1) memory (From leetcode)
  public int trapRainWater(int[] heights) {
      // write your code here
      // write your code here
      int left = 0, right = heights.length - 1; //two pointers
      int ans = 0;
      int left_max = 0, right_max = 0;
      while (left < right) {
          if (heights[left] < heights[right]) { //The smaller one limit the water that can be trapped
              if(heights[left] >= left_max){ //左指针指向的bar比它左边的最大bar值大，更新left_max
                  left_max = heights[left];
              }
              else{//比它左边的最大bar值小，能形成左高凹槽，计算这个凹槽可以存储的水量
                  ans += (left_max - heights[left]);
              }
              left++;
          }
          else {
              if(heights[right] >= right_max){ //右指针指向的bar比它右边的最大bar值大，更新right_max
                  right_max = heights[right];
              }
              else{//比它右边的最大bar值小，能形成右高凹槽，计算这个凹槽可以存储的水量
                  ans += (right_max - heights[right]);
              }
              right--;
          }
      }
      return ans;
  }
