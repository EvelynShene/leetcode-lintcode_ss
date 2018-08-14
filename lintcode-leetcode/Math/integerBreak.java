/** 343. Integer Break(leetcode) / 1284. Integer Break(lintcode)
 *     Given a positive integer n, break it into the sum of at least two positive integers and maximize the product
 *  of those integers. Return the maximum product you can get.
 *
 *     Example: 1) Input: 2 ; Output: 1
 *              Explanation: 2 = 1 + 1, 1 × 1 = 1.
 *              2) Input: 10 ; Output: 36
 *              Explanation: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36.
 *    
 *     Note: You may assume that n is not less than 2 and not larger than 58.
 */
 
 //Method 1: Brute Force(递归) - TLE
 class Solution {
    public static int integerBreak(int n) {
        int max = 0;
        for(int i = 1; i < n; i++){
            int res = i * intBreak(n-i);
            max = Math.max(res,max);
        }
        return max;
    }
	public static int intBreak(int n){
		 if(n == 1){
	         return n;
	     }
		 int max = 0;
	     for(int i = 1; i <= n; i++){
	        	int res = i;
	        	if(n - i != 0){
	        		res *= intBreak(n-i);
	        	}
	            max = Math.max(res,max);
	     }
	     return max;
		 
	}
}

/* Method 2: 观察找规律
 *       观察发现，最大乘积应该出现在几个加数的值比较平均的时候，所以从分成多少个加数出发，让使得所有的加数平均化，求得最大乘积。
 *    用余数q来平均化加数，当要分成i个加数时，先用除法等分加数得到p，有余数q，将余数按1的分配到每一个加数p上，然后比较得到的乘积与
 *    之前算得的最大乘积，取大的值。
 */
public int integerBreak(int n) {
    int max = 0;
    for(int i = 2; i <= n; i++){
        int p = n / i;
        int q = n % i; 
        int product = 1;
        for(int j = 1; j <= i - q; j++){
            product *= p;
        }
        for(int j = 1; j <= q; j++){
            product *= (p+1);
        }
        max = Math.max(max, product);
    }
    return max;
}

/* Method 3: DP - O(n^2) time and O(n) space complexity
 *    令dp[n]为n对应的最大积; 边界:dp[1] = 1; dp[2]=1;
 *    递推方程：dp[n]=max(i*dp[n-i],i*(n-i))(其中i从1到n-1)。    
 */ 
public int integerBreak(int n) {
    int[] dp = new int[n+1];
    dp[1] = 1;
    dp[2] = 1;
    for(int i = 3; i < n + 1; i++){
        for(int j = 1; j < i; j++)
        dp[i] = Math.max(j * dp[i - j],Math.max(dp[i],j * (i - j)));
    }
    return dp[n];
}
