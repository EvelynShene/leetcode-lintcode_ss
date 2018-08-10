/** 367. Valid Perfect Square(leetcode) / 777. Valid Perfect Square(lintcode)
 *      Given a positive integer num, write a function which returns True if num is a perfect square else False.
 *    
 *      Note: Do not use any built-in library function such as sqrt.
 *
 *      Example: 1) Input: 16 ; Returns: True
 *               2) Input: 14 ; Returns: False
 */
 
 //Method: 用long 是为了防止惩罚溢出！！（用int会溢出出错） - O(logn) time complexity
 public boolean isPerfectSquare(int num) {
    long left = 1; 
    long right = num;
    while(left <= right){
        long mid = (left + right) / 2;
        if(mid * mid == num){
            return true;
        }
        else if(mid * mid > num){
            right = mid - 1;
        }
        else{
            left = mid + 1;
        }
    }
    return false;
 }
