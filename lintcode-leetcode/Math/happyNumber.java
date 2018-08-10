/** 202. Happy Number(leetcode) / 488. Happy Number(lintcode)
 *      Write an algorithm to determine if a number is "happy".
 *      A happy number is a number defined by the following process: Starting with any positive integer, 
 *  replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1
 *  (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this 
 *  process ends in 1 are happy numbers.
 *
 *      Example: Input: 19 ; Output: true
 *                Explanation: 
 *                    1^2 + 9^2 = 82
 *                    8^2 + 2^2 = 68
 *                    6^2 + 8^2 = 100
 *                    1^2 + 0^2 + 0^2 = 1
 */
 
 //My Method: 
 class Solution {
    public boolean isHappy(int n) {
        if(n == 0)
            return false;
        if(n == 1)
            return true;
        Set<Integer> sum = new HashSet<Integer>();
        sum.add(n);
        while(n != 1){
            n = squareSum(n);
            if(sum.contains(n)){
                return false;
            }
            else{
                sum.add(n);
            }
        }
        return true;
    }
    public int squareSum(int n){
        int res = 0;
        while(n > 0){
            res += (n % 10) * (n % 10);
            n /= 10;
        }
        return res;
    }
}
