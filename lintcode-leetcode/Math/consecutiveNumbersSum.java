/** 829. Consecutive Numbers Sum(leetcode)
 *      Given a positive integer N, how many ways can we write it as a sum of consecutive positive integers?
 *
 *      Example: 1) Input: 5 ; Output: 2
 *              Explanation: 5 = 5 = 2 + 3
 *               2) Input: 9 ; Output: 3
 *              Explanation: 9 = 9 = 4 + 5 = 2 + 3 + 4
 *               3) Input: 15 ; Output: 4
 *              Explanation: 15 = 15 = 8 + 7 = 4 + 5 + 6 = 1 + 2 + 3 + 4 + 5
 *
 *      Note: 1 <= N <= 10 ^ 9.
 */
 
 /* My Method: O(sqrt(N)) time complexity
  *     Idea: 寻找N能被factor个连续正整数相加得到，分两种情况讨论：
  *           1) factor是偶数，那么N/factor的余数必须是factor的一半；
  *              eg：N = 10, factor = 4, 10 / 4 = 2 ... 2 => 可以先组成 2 2 3 3，
  *              然后后一半的数分给后面的数相应的值，组成连续 => 1 2 3 4; 
  *              注意，第一个加数需要分给最后一个加数(mod - 1)，所有的加数都必须是正整数，所以必须 N/factor - (mod - 1) > 0.
  *           2) factor是奇数，那么N必须正处factor：
  *              eg：N = 15, factor = 5, 15 / 5 = 3 => 可以先组成 3 3 3 3 3，
  *              然后后一半的数分给后面的数相应的值，组成连续 => 1 2 3 4 5; 
  *              注意，第一个加数需要分给最后一个加数(factor/2)，所有的加数都必须是正整数，所以必须N/factor - factor/2 > 0.            
  *               
  */
 class Solution {
    public int consecutiveNumbersSum(int N) {
        int count = 1;
        int factor = 2;
        while(factor <= (int) Math.sqrt(2 * N)){
            if(factor % 2 == 0){// factor is even
                int mod = N % factor;
                if( (mod == factor / 2) && (N / factor + 1 - mod > 0)){
                    count++;
                }
            }
            else{// factor is odd
                if(N % factor == 0  && N / factor - factor / 2 > 0){
                    count++;
                }
            }
            factor++;
        }
        return count;
    }
}
