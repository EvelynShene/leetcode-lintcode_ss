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
