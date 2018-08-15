/** 357. Count Numbers with Unique Digits(leetcode) / 1279. Count Numbers with Unique Digits(lintcode)
 *    Given a non-negative integer n, count all numbers with unique digits, x, where 0 ≤ x < 10n.
 *    
 *    Example: Input: 2 ; Output: 91 
 *             Explanation: The answer should be the total numbers in the range of 0 ≤ x < 100, 
 *                          excluding 11,22,33,44,55,66,77,88,99
 */
 
 /* Method:
  *     n = 0, res = 1
  *     n = 1, res = C(10,1)
  *     n = 2, res = C(9,1) * C(9,1) (第一个数字不能是0，所以只能从1-9中任选；第二个数字可以选0了，所以还是有9个可选数字)
  *     n = 3, res = C(9,1) * C(9,1) * C(8,1)
  *     ......
  */
  
  public int countNumbersWithUniqueDigits(int n) {
    if(n == 0){
        return 1;
    }
    if(n == 1){
        return 10;
    }
    int res = 10;
    int product = 9;
    int base = 9;
    for(int i = 2; i <= n && base > 0; i++){
        product *= base;
        res += product;
        base--;
    }
    return res;
 }
