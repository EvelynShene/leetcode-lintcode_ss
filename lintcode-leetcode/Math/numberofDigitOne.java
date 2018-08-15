/** 233. Number of Digit One(leetcode) / 1312. Number of Digit One(lintcode)
 *     Given an integer n, count the total number of digit 1 appearing in all non-negative integers less than 
 *  or equal to n.
 *
 *     Example: Input: 13 ; Output: 6 
 *              Explanation: Digit 1 occurred in the following numbers: 1, 10, 11, 12, 13.
 */
 
 //Method 1: Brute Force - TLE
 public int countDigitOne(int n) {
    int res = 0;
    for(int i = 0; i <= n; i++){
        String s = String.valueOf(i);
        if(s.indexOf("1") == -1){
            continue;
        }

        res++;
        for(int j = s.indexOf("1") + 1; j < s.length(); j++){
            if(s.charAt(j) == '1'){
                res++;
            }
        }
    }
    return res;
 }
 
 //Method 2: Solve it mathematically [from https://blog.csdn.net/xudli/article/details/46798619 / https://www.cnblogs.com/grandyang/p/4629032.html]
 public int countDigitOne(int n) {
    int ones = 0;
    for (long m = 1; m <= n; m *= 10) {
        long a = n / m;
        long b = n % m;
        ones += (a + 8) / 10 * m;
        if(a % 10 == 1){ 
            ones += b + 1;
        }
    }
    return ones;
 }
 
