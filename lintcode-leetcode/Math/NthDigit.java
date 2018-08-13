/** 400. Nth Digit(leetcode) / 1256. Nth Digit(lintcode)
 *    Find the nth digit of the infinite integer sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...
 *
 *    Note: n is positive and will fit within the range of a 32-bit signed integer (n < 2^31).
 *
 *    Example: 1) Input: 3 ; Output: 3
 *             2) Input: 11 ; Output: 0
 *                Explanation: The 11th digit of the sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... is a 0, 
 *                    which is part of the number 10.
 *
 *    题意：自然数序列看成一个长字符串，问第N位上的数字是什么。如例2第11位是数字10的0那一位。
 */
 
 /* Method: 要找到第n位数字具体出现在哪个数字的哪一位，Brute Force会超时
  *    思路：
  *    1）找出给定的n落在几位数的范围内：
  *        一位有９个数字，二位数有90个数字，三位数有900个数，依次类推．因此可以每次增加一位数字，看n是否还在这个范围内。
  *        例如：n = 150，首先一位有９个数字，这样剩下n-9 = 141。然后２位的数字有 2*90 = 180 > 141，所以180位数字肯定是2位数中。
  *    2）找到具体落在哪个数字：
  *        例子中可以用 10+(141-1)/2 = 80求出。解释：相当于求从10开始第141位数字，因为是两位数，每个数占2位，求是哪个数字要除以
  *      位数长度。而从10开始，已经算了一个数字了，所以是141减1后再除以2。
  *    3）找出具体在哪一位上：
  *        具体落在哪一位上，可以用(141-1)%2＝0 求出为第０位，即８。
  */
 class Solution {
    public int findNthDigit(int n) {
        int base = 1;
        int exp = 1;
        int tmp = 0;
        while(tmp + (long)9 * base * exp < n){ //乘法可能会溢出，所以转换为long
            tmp += 9 * base * exp;
            exp *= 10;
            base++;
        }
        n -= tmp;
        int num = exp + (n - 1) / base;
        String s = String.valueOf(num);
        
        return s.charAt((n - 1) % base) - '0';
    }
}
