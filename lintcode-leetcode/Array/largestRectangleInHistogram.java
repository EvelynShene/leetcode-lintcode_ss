/** 122. Largest Rectangle in Histogram(lintcode) /84. Largest Rectangle in Histogram(leetcode)
 *     Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, 
 *  find the area of largest rectangle in the histogram.
 *
 *     Example: Given height = [2,1,5,6,2,3], return 10.
 */
 
 //Method 1: Brute Force - O(n^2)
  public int largestRectangleArea(int[] height) {
      // write your code here
      if(height == null || height.length == 0){
          return 0;
      }

      int max_area = 0;
      for(int i = 0; i < height.length; i++){
          int min = height[i];
          for(int j = i; j < height.length; j++){
              min = Math.min(min,height[j]);
              int area = min*(j-i+1);
              max_area = Math.max(area, max_area);
          }
      }
      return max_area;
  }
 
 /* Method 2: [Idea and code from leetcode discuss]
  *     Idea: 对于每一个bar i, 包含它的最大面积是height[i]*(r-l+1);
  *           其中l是bar i 左边最远的不小于height[i]的bar坐标，r是bar i 右边最远的不小于height[i]的bar坐标。
  *           所以只要找到每个bar i对于的这两个值，用上述公式求面积，再比较大小值，即可找到最大的方形面积。
  */
  public static int largestRectangleArea(int[] height) {
    if (height == null || height.length == 0) {
        return 0;
    }
    int[] lessFromLeft = new int[height.length]; // idx of the first bar the left that is lower than current
    int[] lessFromRight = new int[height.length]; // idx of the first bar the right that is lower than current
    lessFromRight[height.length - 1] = height.length;
    lessFromLeft[0] = -1;
    
    /* Trick: 找左右两侧最远的大于bar i本身的bar的index位置，如果直接遍历是需要O(n^2);这里用到一个小技巧。
     *     当遍历到height[p] >= height[i]时，因为p处的bar高比i处的bar高，所以bar p左边的最远一个大于bar p高度的bar也一定大于bar i。
     *     所以可以在循环中直接使用 p = lessFromRight[p] 来跳着找bar i左边的最远一个大于bar i高度的bar位置。
     *     平均时间复杂 = O(n) ；最坏时间复杂度 = O(n^2)
     */
    for (int i = 1; i < height.length; i++) {
        int p = i - 1;

        while (p >= 0 && height[p] >= height[i]) {
            p = lessFromLeft[p];
        }
        lessFromLeft[i] = p;
    }

    for (int i = height.length - 2; i >= 0; i--) {
        int p = i + 1;

        while (p < height.length && height[p] >= height[i]) {
            p = lessFromRight[p];
        }
        lessFromRight[i] = p;
    }

    int maxArea = 0;
    for (int i = 0; i < height.length; i++) {
        maxArea = Math.max(maxArea, height[i] * (lessFromRight[i] - lessFromLeft[i] - 1));
    }
    return maxArea;
}
  
/* Method 3: 用栈，考虑升序 [from leetcode discuss]
 *   Idea: 1.若已知height数组是升序的，比如1,2,3,4,5 => 那么就是(1*5)vs.(2*4)vs.(3*3)vs.(4*2)vs.(5*1)(即 max(height[i]*(length-i)))
 *         2.使用栈的构造这样的升序序列，按照以上方法求解。比如范例：2,1,5,6,2,3
 *          （1）2进栈。s={2}, result = 0
 *          （2）1比2小，不满足升序条件，因此将2弹出，并记录当前结果为2*1=2。 将2替换为1重新进栈。s={1,1}, result = 2
 *          （3）5比1大，满足升序条件，进栈。s={1,1,5},result = 2
 *          （4）6比5大，满足升序条件，进栈。s={1,1,5,6},result = 2
 *          （5）2比6小，不满足升序条件，因此将6弹出，并记录当前结果为6*1=6。s={1,1,5},result = 6
 *              2比5小，不满足升序条件，因此将5弹出，并记录当前结果为5*2=10（因为已经弹出的5,6是升序的）。s={1,1},result = 10
 *              2比1大，将弹出的5,6替换为2重新进栈。s={1,1,2,2,2},result = 10
 *          （6）3比2大，满足升序条件，进栈。s={1,1,2,2,2,3},result = 10
 *              栈构建完成，满足升序条件，因此按照升序处理办法得到上述的max(height[i]*(size-i))=max{3*1, 2*2, 2*3, 2*4, 1*5, 1*6}=8<10
 *         综上所述，result=10
 */
 public int largestRectangleArea(int[] height) {
    int len = height.length;
    Stack<Integer> s = new Stack<Integer>();
    int maxArea = 0;
    for(int i = 0; i <= len; i++){
        int h = (i == len ? 0 : height[i]);
        if(s.isEmpty() || h >= height[s.peek()]){
            s.push(i);
        }else{
            int tp = s.pop();
            maxArea = Math.max(maxArea, height[tp] * (s.isEmpty() ? i : i - 1 - s.peek()));
            i--;
        }
    }
    return maxArea;
}
