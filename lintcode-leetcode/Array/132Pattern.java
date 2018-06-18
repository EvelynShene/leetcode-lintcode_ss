/**  636. 132 Pattern(lintcode) / 456. 132 Pattern(leetcode)
 *    Given a sequence of n integers a1, a2, ..., an, a 132 pattern is a subsequence ai, aj, ak such that i < j < k and ai < ak < aj.
 *    Design an algorithm that takes a list of n numbers as input and checks whether there is a 132 pattern in the list.
 *    Note: n will be less than 20,000.
 *
 *    Example: Given nums = [1, 2, 3, 4]
 *                return False // There is no 132 pattern in the sequence.
 *             Given nums = [3, 1, 4, 2]
 *                return True // There is a 132 pattern in the sequence: [1, 4, 2].
 */
 
 /* Method 1: O(n^2)
  *   先假设三个数中的最大数aj = nums[j]固定，现在需要找另外两个数ai,ak，使得ai < ak < nums[j].（aj是中间位置的那个数值）
  *   对于给定的aj = nums[j]，区间[ai,aj]越大，找到存在的中间数ak的可能性越高（因为区间值越多）。
  *   而ai越小，区间越大，所以ai定义为数组[0，j]之间的最小值，找到132模式的可能性最大。
  *   ai,aj都定下来后，从数组（j,length-1]中查看有没有大小在[ai,aj]区间内的ak存在，有则返回true。
  *   (因为固定aj的同时，也可以计算数组在[0，j]之间的最小值，所以只需要一层循环)  
  */
  public boolean find132pattern(int[] nums) {
      // write your code here
      if(nums.length < 3){
          return false;
      }
      int l = 0;
      int left_min = Integer.MAX_VALUE;  

      for(int j = 0; j < nums.length; j++){
          left_min = Math.min(left_min, nums[j]);
          for(int k = j+1; k < nums.length; k++){
              if(nums[k] < nums[j] && nums[k] > left_min){
                  return true;
              }
          }
      }
      return false;
  }
  
 /* Method 2: Time and space complexity are O(n)
  *   用到栈和其FILO的性质：
  *   维护一个min[length],min[i] = 数组[0,i]中的最小值，用来定义ai(同Method1)
  *   从后往前遍历数组并建立一个栈：（每一次遍历都是固定一个最大值aj=nums[j],而栈中压入的是可能满足要求的ak）
  *    1.如果当前栈顶元素的值大于数组[0,j]中的最小值，考察当前栈顶元素（即ak）：
  *     1)循环判断：栈顶元素 <= 数组[0,j]中的最小值，弹出栈顶元素 (ak要大于ai，所以小于ai的数都是不满足要求的，弹出栈)
  *     2)(栈顶元素 > 数组[0,j]中的最小值)栈顶元素 < 当前考察的数组元素nums[j]，返回true
  *     3)(栈顶元素 > 数组[0,j]中的最小值)栈顶元素 >= 当前考察的数组元素nums[j](即 ak > aj > ai)：把aj压入栈
  *                                                                                 （因为j--后，当前aj变为对于新j的可能的ak）
  *
  *   (Note: 实际运行时，method 1 的速度快于method 2)
  */
  public boolean find132pattern(int[] nums) {
      if (nums.length < 3)
          return false;
      Stack < Integer > stack = new Stack < > ();
      int[] min = new int[nums.length];
      min[0] = nums[0];
      for (int i = 1; i < nums.length; i++)
          min[i] = Math.min(min[i - 1], nums[i]);
      for (int j = nums.length - 1; j >= 0; j--) {
          if (nums[j] > min[j]) {
              while (!stack.isEmpty() && stack.peek() <= min[j])
                  stack.pop();
              if (!stack.isEmpty() && stack.peek() < nums[j])
                  return true;
              stack.push(nums[j]);
          }
      }
      return false;
  }
