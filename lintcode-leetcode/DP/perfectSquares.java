/** 279. Perfect Squares(leetcode) / 513. Perfect Squares(lintcode)
 *      Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) 
 *  which sum to n.
 *
 *      Example: 1) Given n = 12, return 3 because 12 = 4 + 4 + 4
 *               2) Given n = 13, return 2 because 13 = 4 + 9
 */
 
 //Method: 
 public int numSquares(int n) {
    int[] dp = new int[n + 1];
    int i = 1;
    for(; i * i <= n; i++){
        dp[i * i] = 1;
    }
    if(dp[n] == 1){
        return dp[n];
    }
    for(int x = 2; x <= n; x++){
        if(dp[x] == 0){
            int min = Integer.MAX_VALUE;
            for(int j = 1; j * j <= x; j++){
                if(min > dp[x - j * j] + 1){
                    min = dp[x - j * j] + 1;
                }
            }
            dp[x] = min;
        }
    }
    return dp[n];
}
