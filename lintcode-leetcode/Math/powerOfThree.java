/** 326. Power of Three(leetcode) / 1294. Power of Three(lintcode)
 *    Given an integer, write a function to determine if it is a power of three.
 *
 *    Example: 1) Input: 0 ; Output: false
 *             2) Input: 27 ; Output: true
 *             3) Input: 45 ; Output: false
 *
 *    Challenge: Could you do it without using any loop / recursion?
 */
 
 //Method 1: Use loop  - O(log3(n)) time complexity
 public boolean isPowerOfThree(int n) {
    if(n <= 0){
        return false;
    }
    while(n > 1){
        if(n % 3 != 0){
            return false;
        }
        n = n / 3;
    }
    return true;
 }
 
 /* Method 2: (Without loop) - O(1) time and space complexity
  *   因为输入参数是int，正数范围是(0,2^31)，在此范围中允许的最大的3的次方数为3^19=1162261467，那么只要看这个数能否被n整除即可.
  */
  public boolean isPowerOfThree(int n) {
      if(n <= 0){
          return false;
      }
      return (1162261467 % n == 0);
  }
 
 //More methods and their comparison are in https://leetcode.com/problems/power-of-three/solution/
