/** 397. Integer Replacement(leetcode) / 1259. Integer Replacement(lintcode)
 *      Given a positive integer n and you can do operations as follow:
 *        1) If n is even, replace n with n/2.
 *        2) If n is odd, you can replace n with either n + 1 or n - 1.
 *      What is the minimum number of replacements needed for n to become 1?
 *
 *      Example: 1) Input: 8 ; Output: 3
 *               Explanation: 8 -> 4 -> 2 -> 1
 *               2) Input: 7 ; Output: 4
 *               Explanation: 7 -> 8 -> 4 -> 2 -> 1
 *                            or
 *                            7 -> 6 -> 3 -> 2 -> 1
 */
 
 //Method 1: 递归，注意溢出
 public int integerReplacement(int n) {
    if(n == 0){
        return 1;
    }
    if(n == 1){
        return 0;
    }
    if(n % 2 == 0){
        return 1 + integerReplacement(n / 2);
    }
    else{
        long t = n;
        int res1 = 2 + integerReplacement((int)(t / 2));
        int res2 = 2 + integerReplacement((int)((t + 1) / 2));
        return Math.min(res1, res2);
    }
 }
 
 /* Method 2: Bit Operation
  *   1) n是偶数时，直接右移一位（即除以2）；
  *   2) n是奇数时，因为要用最少的操作，所以，尾数分01, 和11的奇数要分别考虑：
  *     a) 01的情况，需要直接-1，
  *     b) 11的情况用+1，变成100然后再做会快很多(让尾数0尽可能多); 但是3特殊，3-1 比 3+1 步骤少。
  *  【 位运算：左移 << ；右移 >> ； 无符号右移 >>> 】
  */  
 public int integerReplacement(int n) {
    if(n == 0){
        return 1;
    }
    if(n == 1){
        return 0;
    }
    int res = 0;
    while(n != 1){
        if((n & 1) == 0){
            n = n >>> 1;
        }
        else{
            if(n == 3 || (n & 3) == 1){
                n--;
            }
            else{
                n++;
            }
        }
        res++;
    }
    return res;
 }
