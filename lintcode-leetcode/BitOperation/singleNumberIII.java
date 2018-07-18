/** 84. Single Number III(lintcode) / 260. Single Number III(leetcode)
 *      Given an array of numbers nums, in which exactly two elements appear only once and all the other elements 
 *   appear exactly twice. Find the two elements that appear only once.
 *
 *      Note: The order of the result is not important. So in the above example, [5, 3] is also correct.
 *            Your algorithm should run in linear runtime complexity. 
 *            Could you implement it using only constant space complexity?
 *
 *      Example: Input:  [1,2,1,3,2,5] ; Output: [3,5]
 */
 
 /* Idea: 原理 - 两个相同的数异或得到0 
  *    1) 先遍历一边数组，把所有数都异或，最终得到的结果是 xor = A ^ B. (A,B是所要求的只出现了一次的那两个数)
  *    2) 通过运算 xor = xor & (~(xor - 1)) 得到一个新的数xor,这个数中只有一位是1，其他的都是0.
  *       解释：xor&(~(xor - 1))是找到xor最右侧的1，例如假设xor是...1000, 减1后变成...0111(前面的位不变);
  *            取反后变成...1000(前面的位都相反)，再与上原来的xor，只剩下 00..01000 了。
  *            之所以要得到这个是1的位，是因为A,B在这一位上是不同了(异或的性质，只有A,B在这一位不同，得到的结果在这一位上才是1)
  *    3) 再用整个数组跟新得到的xor做 & 运算，可分成两组0和1:
  *       1)0组：对于有重复的数，它们与xor做 & 运算得到的结果一样，让它们异或即得0. 最后只留下一个数,假设是A。
  *       2)1组：对于有重复的数，它们与xor做 & 运算得到的结果一样，让它们异或即得0. 最后只留下一个数B。
  *       (A, B在这一位上不一样，所以它们的与xor异或得到结果肯定不一样。)
  */
  
  public int[] singleNumber(int[] nums) {
      int[] res = new int[2];
      int xor = 0;
      for(int i = 0; i < nums.length; i++){
          xor = xor ^ nums[i];
      }

      xor = xor & (~(xor - 1));
      for(int i = 0; i < nums.length; i++){
          if((xor & nums[i]) == 0){
              res[0] = res[0] ^ nums[i];
          }
          else{
              res[1] = res[1] ^ nums[i];
          }
      }
      return res;
  }
