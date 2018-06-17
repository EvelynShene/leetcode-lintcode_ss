/** 148. Sort Colors(lintcode)/ 75. Sort Colors(leetcode)
 *   Given an array with n objects colored red, white or blue, 
 *    sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white and blue.
 *   Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
 *   Note: You are not suppose to use the library's sort function for this problem.
 */
 
 /* Method 1: A two-pass algorithm using counting sort.
  *   First, iterate the array counting number of 0's, 1's, and 2's. 
  *   Then overwrite array with total number of 0's, then 1's and followed by 2's.
  */
  
  public void sortColors(int[] nums) {
      // write your code here
      int red = 0;
      int white = 0;
      int blue = 0;

      for(int i = 0; i < nums.length; i++){
          if(nums[i] == 0){
              red++;
          }
          else if(nums[i] == 1){
              white++;
          }
          else{
              blue++;
          }
      }
      for(int i = 0; i < nums.length; i++){
          if(i < red){
              nums[i] = 0;
          }
          else if(i < red + white){
              nums[i] = 1;
          }
          else{
              nums[i] = 2;
          }
      }
  }
  
  /* Method 2: A one-pass algorithm using only constant space
   *  Idea: 用two pointers来实现。设一个指针i 表示i之前都是0，另一个指针j表示j之后都是2。一开始i指向开头元素，j指向末尾元素。
   *        用index遍历数组，当遇到红色0，就交换index与i位置元素，把0放到最左边去；
   *        遇到蓝色2就交换，就交换index与j位置元素，把2都放到最右边去，这样1就会被保留在最中间。
   *  Note: 当把蓝色2交换完毕之后，需要j--， 停留index在原地一次，因为还需要继续检查index位置被2交换回来的新数字。
   *        那当遇到红色0，交换完毕不需要停留index, 因为交换回来的只可能是1，对于1不需要做任何处理，直接过就可以。
   */
  public void sortColors(int[] nums) {
      int i = 0;
      int j = nums.length-1;
      int index = 0;
      while(index <= j){
          if(nums[index] == 2){
              nums[index] = nums[j];
              nums[j] = 2;
              j--;
          }
          else if(nums[index] == 0){
              nums[index] = nums[i];
              nums[i] = 0;
              index++;
              i++;
          }
          else{
              index++;
          }
      }
  }
