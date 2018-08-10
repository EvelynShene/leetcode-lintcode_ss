/** 50. Pow(x,n) (leetcode)/ 428. Pow(x,n) (lintcode)
 *    Implement pow(x, n), which calculates x raised to the power n (x^n).
 *
 *    Note: 1) -100.0 < x < 100.0
 *          2) n is a 32-bit signed integer, within the range [−2^31, 2^31 − 1]
 *
 *    Example: Pow(2.1, 3) = 9.261
 *             Pow(0, 1) = 0
 *             Pow(1, 0) = 1
 *             Pow(2.0000, -2) = 0.25000
 */
 
 /* Method 1: 递归调用
  *   注意：对于 n < 0 的处理方式是用(1/x) * (myPow(1/x,-(n+1)));而不能用 1 / myPow(x,-n);
  *        因为 n = -2^31 时，取负数溢出，还是负值，所以先+1再取负数。
  */
 public double myPow(double x, int n) {
    if(n < 0){
        return (1/x) * (myPow(1/x,-(n+1)));
    }
    if(n == 0){
        return 1;
    }
    if(n == 1){
        return x;
    }
    if(n == 2){
        return x*x;
    }
    if(n % 2 == 0){
        double tmp = myPow(x,n/2);
        return myPow(tmp,2);
    }
    else{
        double tmp = myPow(x,n/2);
        return x*myPow(tmp,2);
    }
 }
 
 //Method 2: 简化上述代码
 public double myPow(double x, int n) {
    if(n == 0){
        return 1;
    }
    double tmp = myPow(x, n / 2);
    if(n % 2 == 0){
        return tmp * tmp;
    }
    else{
        if(n < 0){ // x^(-7) = x^(-3) * x^(-3) * (1 / x)
            return (1 / x) * tmp * tmp;
        }
        return x * tmp * tmp; // x^7 = x^3 * x^3 * x
    }
 }
 //Method 3: 变体 —— 由递归后对myPow翻倍变成对x翻倍
 public double myPow(double x, int n) {
    if(n == 0){
        return 1;
    }
    boolean min = false;
    if(n < 0){
        if(n == Integer.MIN_VALUE){ //处理 n = -2^31 的情况
            n = -(n+1);
            min = true;
        }
        else{
            n = -n;
        }
        x = 1 / x;  
    }
    double res = 0;
    if(n % 2 == 0){
        res = myPow(x*x, n / 2);
    }
    else{
        res = x*myPow(x*x, n / 2);
    }
    if(min){
        res = res * x;
    }
    return res;
 }
 
 //Method 4: 迭代 - 最慢
public double myPow(double x, int n) {
     if(n == 0){
         return 1;
     }
     boolean min = false;
     if(n < 0){
         if(n == Integer.MIN_VALUE){
             n = -(n+1);
             min = true;
         }
         else{
             n = -n;
         }
         x = 1 / x;  
     }
     double tmp = x;
     double res = 1;
     while(n > 0){
         if(n % 2 != 0){
             res *= x;
         }
         x *= x;
         n /= 2;
     }

     if(min){
         res = res * tmp;
     }
     return res;
 }
