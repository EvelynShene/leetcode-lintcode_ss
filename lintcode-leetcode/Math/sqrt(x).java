/** 69. Sqrt(x) (leetcode) / 141. Sqrt(x) (lintcode)
 *      Implement int sqrt(int x).
 *      Compute and return the square root of x, where x is guaranteed to be a non-negative integer.
 *      Since the return type is an integer, the decimal digits are truncated and only the integer part of the 
 *  result is returned.
 *
 *      Example: sqrt(3) = 1
 *               sqrt(4) = 2
 *               sqrt(5) = 2
 *               sqrt(10) = 3
 */
 
 /* Method 1: 二分法 - O(logn) time complexity
  *   对于一个非负整数n，它的平方根不会大于（n/2+1)。所以在[0, n/2+1]这个范围内可以进行二分搜索，求出n的平方根
  */
 public int mySqrt(int x) {
    if(x == 0)
        return 0;
    if(x == 1)
        return 1;

    int left = 1;
    int right = x;
    while(left <= right){
        int mid = (left + right) / 2;
        if(mid == x / mid)
            return mid;
        else if(mid > x / mid){
            right = mid - 1;
        }
        else{
            left = mid + 1;
        }
    }
    return left - 1;
 }
 
 /* Method 2: 牛顿迭代法 [code from leetcode]
  *     https://www.cnblogs.com/AnnieKim/archive/2013/04/18/3028607.html
  */
 public int mySqrt(int x) {
    if (x == 0) return 0;
	  long i = x;
	  while(i > x / i)  
		  i = (i + x / i) / 2;	    	
	  return (int)i;
 }
