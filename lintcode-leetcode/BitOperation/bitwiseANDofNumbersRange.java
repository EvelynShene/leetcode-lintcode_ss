/** 201. Bitwise AND of Numbers Range(leetcode) / 1325. Bitwise AND of Numbers Range(lintcode)
 *      Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, 
 *  inclusive.
 *
 *      Example: 1) Input: [5,7] ; Output: 4
 *               2) Input: [0,1] ; Output: 0
 */
 
 /* Method: 
  *   最后的数是该数字范围内所有的数的左边共同的部分 <=> m 和 n 左边相同的部分
  *    Example: [26, 30]，它们的二进制如下：
  *        11010　　11011　　11100　　11101　　11110 => 相同部分 11000
  */
 public int rangeBitwiseAnd(int m, int n) {
    if(m == 0){
        return 0;
    }
    int c = 0;
    while(m != n){
        m = m >> 1;
        n = n >> 1;
        c++;
    }
    return (m << c);
 }
 
 //Other Method: https://www.cnblogs.com/grandyang/p/4431646.html
