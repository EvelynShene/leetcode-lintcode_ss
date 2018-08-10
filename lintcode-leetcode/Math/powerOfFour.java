/** 342. Power of Four(leetcode) / 1285. Power of Four(lintcode)
 *    Given an integer (signed 32 bits), write a function to check whether it is a power of 4.
 *    
 *    Example: 1) Input: 16 ; Output: true
 *             2) Input: 5 ; Output: false
 *
 *    Follow up: Could you solve it without loops/recursion?
 */
 
 //Method 1: Use loop
 public boolean isPowerOfFour(int num) {
    if(num <= 0){
        return false;
    }
    while(num > 1){
        if(num % 4 != 0){
            return false;
        }
        num = num / 4;
    }
    return true;
 }
 
 /* Method 2: Follow up
  *     4的幂次方的最高位都是1，所以可以同求2的幂次方一样用 n & (n - 1) == 0 判断；但是还需要进一步判断。4的幂次方的二进制数，最高位
  *  后面都有偶数个0，所以可以用到一个数(0x55555555) == 1010101010101010101010101010101，4的幂次方和这个数相与，得到的数还是
  *  其本身。
  */
  public boolean isPowerOfFour(int num) {
      return (num > 0) && ((num & (num - 1)) == 0) && ((num & (0x55555555)) == num);
  }
  
  /* Method 3:
   *    或者在确定其是2的次方数了之后，发现只要是4的次方数，减1之后可以被3整除.
   */
   public boolean isPowerOfFour(int num) {
      return (num > 0) && ((num & (num - 1)) == 0) && ((num - 1) % 3 == 0);
   }
