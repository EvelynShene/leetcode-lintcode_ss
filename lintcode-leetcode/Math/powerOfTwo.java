/** 231. Power of Two(leetcode) / 1314. Power of Two(lintcode)
 *    Given an integer, write a function to determine if it is a power of two.
 *
 *    Example: 1) Input: 1 ; Output: true 
 *                Explanation: 20 = 1
 *             2) Input: 16 ; Output: true
 *                Explanation: 24 = 16
 *             3) Input: 218 ;Output: false       
 */

//Method:
public boolean isPowerOfTwo(int n) {
    if(n <= 0){
        return false;
    }
    while(n > 1){
        if(n % 2 != 0){
            return false;
        }
        n = n / 2;
    }
    return true;
}

/* Method 2: 
 *     用二进制数的性质，如果是2的幂次方，那么用二进制表示时，除了最高位为1，其他位均为0；
 *     技巧：如果 n = 1000..00, 那么 n - 1 = 0111..11; 那么 n & (n - 1) == 0。
 */
 public boolean isPowerOfTwo(int n) {
    if(n <= 0){
        return false;
    }
    return (n & (n - 1)) == 0;// &的优先级比“==”小，要用括号
 }
