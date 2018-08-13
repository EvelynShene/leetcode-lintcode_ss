/** 263. Ugly Number(leetcode) / 517. Ugly Number(lintcode)
 *      Write a program to check whether a given number is an ugly number.
 *      Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.
 *
 *      Note: 1 is typically treated as an ugly number.
 *            Input is within the 32-bit signed integer range: [−2^31,  2^31 − 1].
 *
 *      Example: 1) Input: 6 ;Output: true
 *                 Explanation: 6 = 2 × 3
 *               2) Input: 14 ; Output: false 
 *                 Explanation: 14 is not ugly since it includes another prime factor 7.
 */

//Method:
public boolean isUgly(int num) {
    if(num <= 0)
        return false;

    while(num > 1){
        if(num % 2 != 0 && num % 3 != 0 && num % 5 != 0){
            return false;
        }
        if(num % 2 == 0){
            num /= 2;
        }
        if(num % 3 == 0){
            num /= 3;
        }
        if(num % 5 == 0){
            num /= 5;
        }
    }
    return true;
}
