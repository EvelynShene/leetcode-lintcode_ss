/** 372. Super Pow(leetcode) / 1275. Super Pow(lintcode)
 *      Your task is to calculate a^b mod 1337 where a is a positive integer and b is an extremely large positive 
 *  integer given in the form of an array.
 *
 *      Example:1) a = 2 ; b = [3] ; Result: 8
 *              2) a = 2 ; b = [1,0] ; Result: 1024
 */
 
 /* Method:
  *   数学知识：1) a^b mod c = (a mod c)^b mod c;  2) x*y mod c = ((x mod c) * (y mod c)) mod c
  *      思路：1) 现将 a = a % c，减小a的大小，防范溢出；
  *           2) b是一个用数组表示的整数，正好按位取，每次取a^b[i] % 1337 后得到一个余数remainder;
  *             然后(remainder^10 * a^b[i+1]) % 1337; 此过程循环进行，知道b中所有的数字取完。
  *          【进一步处理：(remainder^10 * a^b[i+1]) % 1337 = ((remainder^10)%1337 * (a^b[i+1])%1337) % 1337】
  */
  class Solution {
    public int superPow(int a, int[] b) {
        int n = b.length;
        a = a % 1337;
        int remainder = 1;
        for(int i = 0; i < n; i++){
            int tmp = pow(remainder, 10) % 1337;
            tmp = tmp * (pow(a, b[i]) % 1337);
            remainder = tmp % 1337;
        }
        return remainder;
    }
    
    public int pow(int a, int b){
        if(b == 0)
            return 1;
        if(b == 1)
            return a;
        int res = 1;
        for(int i = 0; i < b; i++){
            res = (res * a) % 1337;
        }
        return res;
    }
}
