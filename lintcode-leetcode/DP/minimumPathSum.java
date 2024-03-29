/** 64. Minimum Path Sum(leetcode) / 110. Minimum Path Sum(lintcode)
 *      Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which 
 *  minimizes the sum of all numbers along its path.
 *
 *      Note: You can only move either down or right at any point in time.
 *
 *      Example: Input:
 *                     [
 *                       [1,3,1],
 *                       [1,5,1],
 *                       [4,2,1]
 *                     ]
 *               Output: 7
 *                    Explanation: Because the path 1→3→1→1→1 minimizes the sum.
 */
 
//Method:
public int minPathSum(int[][] grid) {
    if(grid == null || grid.length == 0){
        return 0;
    }
    int m = grid.length;
    int n = grid[0].length;
    int [][] dp = new int[m][n];

    for(int i = 0; i < m; i++){
        for(int j = 0; j < n; j++){
            if(i == 0 && j == 0){
                dp[0][0] = grid[0][0];
            }
            else if(i == 0){
                dp[i][j] = grid[i][j] + dp[i][j - 1];
            }
            else if(j == 0){
                dp[i][j] = grid[i][j] + dp[i - 1][j];
            }
            else{
                dp[i][j] = grid[i][j] + Math.min(dp[i][j - 1], dp[i - 1][j]);
            }
        }
    }
    return dp[m - 1][n - 1];
}
