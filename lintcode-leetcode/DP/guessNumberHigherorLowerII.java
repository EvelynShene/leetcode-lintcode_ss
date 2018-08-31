/** 375. Guess Number Higher or Lower II(leetcode) / 666. Guess Number Higher or Lower II(lintcode)
 *      We are playing the Guess Game. The game is as follows:
 *      I pick a number from 1 to n. You have to guess which number I picked.
 *      Every time you guess wrong, I'll tell you whether the number I picked is higher or lower.
 *      However, when you guess a particular number x, and you guess wrong, you pay $x. You win the game when you 
 *   guess the number I picked.
 *
 *      Example: n = 10, I pick 8.
 *                First round:  You guess 5, I tell you that it's higher. You pay $5.
 *                Second round: You guess 7, I tell you that it's higher. You pay $7.
 *                Third round:  You guess 9, I tell you that it's lower. You pay $9.
 *               Game over. 8 is the number I picked.
 *
 *                You end up paying $5 + $7 + $9 = $21.
 *          Given a particular n ≥ 1, find out how much money you need to have to guarantee a win.
 *
 */
 
 //Method: [Idea from https://leetcode.com/problems/guess-number-higher-or-lower-ii/discuss/84787/Java-DP-solution]
 public int getMoneyAmount(int n) {
    int[][] dp = new int[n][n];
    for(int i = 0; i < n; i++){
        if(i + 1 < n){
            dp[i][i+1] = i + 1;
        }
        if(i + 2 < n){
            dp[i][i+2] = i + 2;
        }
    }
    //dp[i][j] = money needed to guess a number between [i,j]
    for (int jminusi = 1; jminusi < n; jminusi++) {
        for (int i = 0; i + jminusi < n; i++) {
            int j = i + jminusi;
            if(dp[i][j] == 0){
                dp[i][j] = Integer.MAX_VALUE;
                for(int k = i; k <= j; k++){
                    int money = (k + 1) + Math.max(k - 1 < i? 0: dp[i][k-1], k + 1 > j? 0: dp[k+1][j]);
                    dp[i][j] = Math.min(dp[i][j], money);
                }
            }
        }
    } 
    return dp[0][n-1];
 }
 
 //Method 2: DP + Recursive
 class Solution {
    public int getMoneyAmount(int n) {
        //dp[i][j] = money needed to guess a number between [i,j]
        int[][] dp = new int[n + 1][n + 1];
        getMoney(1, n, dp);
        
        return dp[1][n];
    }
    
    public void getMoney(int start, int end, int[][] dp){
        if(start >= end){
            return;
        }
        if(start + 1 == end){
            dp[start][end] = start;
            return;
        }
        if(dp[start][end] != 0){
            return;
        }
        dp[start][end] = Integer.MAX_VALUE;
        for(int i = start; i <= end; i++){
            getMoney(start, i - 1, dp);
            getMoney(i + 1, end, dp);
            int money = i + Math.max(i - 1 < start? 0: dp[start][i - 1], i + 1 > end? 0: dp[i + 1][end]);
            dp[start][end] = Math.min(dp[start][end], money);
        }
    }
}
