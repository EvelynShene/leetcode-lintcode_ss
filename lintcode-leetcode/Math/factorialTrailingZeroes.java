/** 172. Factorial Trailing Zeroes(leetcode) / 1347. Factorial Trailing Zeroes(lintcode)
 *    Given an integer n, return the number of trailing zeroes in n!.
 *    
 *    Example: 1) Input: 3 ; Output: 0
 *               Explanation: 3! = 6, no trailing zero.
 *             2) Input: 5 ; Output: 1
 *               Explanation: 5! = 120, one trailing zero.
 *
 *    Note: Your solution should be in logarithmic time complexity.
 */
 
 //Method: 找有多少个5存在，要注意如 25 = 5*5，有两个5
 class Solution {
    public int trailingZeroes(int n) {
        int res = 0;
        while(n > 0){
            n /= 5;
            res += n;
        }
        return res;
    }
}
