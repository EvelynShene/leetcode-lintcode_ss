/** 383. Container With Most Water(lintcode)/11. Container With Most Water(leetcode)
 *    Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). 
 *    n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). 
 *    Find two lines, which together with x-axis forms a container, such that the container contains the most water.
 *    Note: You may not slant the container.(container是方形的，不考虑梯形)
 */
 
 //Method 1: Brute Force [Accepted in lintcode; Time Limit Exceeded in leetcode]
  public int maxArea(int[] heights) {
      // write your code here
      int maxArea = 0;

      for(int i = 0; i < heights.length; i++){
          for(int j = i+1; j < heights.length; j++){
              int area = Math.min(heights[i],heights[j])*(j-i);
              maxArea = Math.max(area, maxArea);
          }
      }

      return maxArea;
  }
  
  //Method 2: 
    public int maxArea(int[] height) {
        int maxarea = 0, l = 0, r = height.length - 1;
        while (l < r) {
            maxarea = Math.max(maxarea, Math.min(height[l], height[r]) * (r - l));
            if (height[l] < height[r])
                l++;
            else
                r--;
        }
        return maxarea;
    }
