/** 29. Divide Two Integers(leetcode) / 414. Divide Two Integers(lintcode)
 *      Given two integers dividend and divisor, divide two integers without using multiplication, 
 *  division and mod operator.
 *
 *      Return the quotient after dividing dividend by divisor. The integer division should truncate toward zero.
 *
 *      Example: 1) Input: dividend = 10, divisor = 3 ; Output: 3 
 *               2) Input: dividend = 7, divisor = -3 ; Output: -2
 *
 *      Note: 1) Both dividend and divisor will be 32-bit signed integers.
 *            2) The divisor will never be 0.
 *            3) Assume we are dealing with an environment which could only store integers within the 32-bit 
 *        signed integer range: [−231,  231 − 1]. For the purpose of this problem, assume that your function 
 *        returns 231 − 1 when the division result overflows.
 */
 
 /* Method: 
  *    计算机内所有的整数都可以表示为：x = a0*2^0 + a1*2^1 + a2*2^2 + ... + an*2^n；所以可以用这种方法计算商的值
  *    例如：计算28/3，进行三次操作：
  *        1）使3*2*2*2=3*8=24<28(注意3*2^4=48>28).记录下2*2*2=2^3=8.
  *        2）28减去24得到4，然后重复上一步骤，计算4/3,则3*2^0=3<4.记录下2^0=1.
  *        3）由于4-3=1小于除数3，停止计算，并将每轮得到的值相加，得到8+1=9，即为商（即28/3=9）。
  */
 public int divide(int dividend, int divisor) {
    boolean negative = false;
    long x = dividend;
    long y = divisor;
    if(x < 0){
        x = - x;
        negative = negative? false: true;
    }
    if(y < 0){
        y = - y;
        negative = negative? false: true;
    }
    if(x < y){
        return 0;
    }
    long res = 0;

    while(x >= y){
        long mul = 1;
        long sum = y;
        while(sum + sum <= x){
            sum += sum; // sum = sum * 2;
            mul += mul; // mul = mul * 2;
        }
        x = x - sum;
        res += mul;
        if(res > Integer.MAX_VALUE){
            break;
        }
    }

    if(negative)
        res = -res;
    if(res > Integer.MAX_VALUE || res < Integer.MIN_VALUE)
        return Integer.MAX_VALUE;
    return (int)res;
 }
 
 //Method 2: 本质同上述方法，只是用到左移运算，一个数左移就等于乘以2【code from jiuzhang】
 public int divide(int dividend, int divisor) {
    if (divisor == 0) {
         return dividend >= 0? Integer.MAX_VALUE : Integer.MIN_VALUE;
    }

    if (dividend == 0) {
        return 0;
    }

    if (dividend == Integer.MIN_VALUE && divisor == -1) {
        return Integer.MAX_VALUE;
    }

    boolean isNegative = (dividend < 0 && divisor > 0) || 
                         (dividend > 0 && divisor < 0);

    long a = Math.abs((long)dividend);
    long b = Math.abs((long)divisor);
    int result = 0;
    while(a >= b){
        int shift = 0;
        while(a >= (b << shift)){
            shift++;
        }
        a -= b << (shift - 1);
        result += 1 << (shift - 1);
    }
    return isNegative? -result: result;
 }
