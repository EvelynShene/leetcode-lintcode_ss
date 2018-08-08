/** 7. Reverse Integer(leetcode) / 413. Reverse Integer(lintcode)
 *      Given a 32-bit signed integer, reverse digits of an integer.
 *
 *      Example: 1) Given x = 123, return 321
 *               2) Given x = -123, return -321
 *               3) Given x = 120, return 21
 */
 
 //My Method:
 public int reverseInteger(int n) {
    boolean negative = false;
    if(n < 0){
        negative = true;
        n = -n;
    }
    long res = 0l;
    while(n > 0){
        res = res*10 + n % 10;
        n = n / 10;
    }
    if(negative){
        res = -res;
    }
    if(res > Integer.MAX_VALUE || res < Integer.MIN_VALUE){
        return 0;
    }
    return (int)res;
 }
