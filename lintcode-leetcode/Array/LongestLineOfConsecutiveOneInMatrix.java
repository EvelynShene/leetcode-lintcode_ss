/** 875. Longest Line of Consecutive One in Matrix(lintcode)/ 562. Longest Line of Consecutive One in Matrix (leetcode)
 *    Given a 01 matrix m, find the longest line of consecutive one in the matrix. 
 *    The line could be horizontal, vertical, diagonal or anti-diagonal.
 *
 *    Note: The number of elements in the given matrix will not exceed 10,000.
 *
 *    Example: Given m = 
 *               [
 *                   [0,1,1,0],
 *                   [0,1,1,0],
 *                   [0,0,0,1]
 *               ]
 *               return 3
 */
 
 /* Method: Use Dynamic Programming
  * Idea: 用数组dp[i][j][k]表示遍历到元素M[i][j]时，对应k方向的已有最长连续1的个数。
  *       k = 0,1,2,3分别表示水平、垂直、对角、反对角四个方向
  */
  public int longestLine(int[][] M) {
      // Write your code here
      if(M == null || M.length == 0){
          return 0;
      }
      int m = M.length;
      int n = M[0].length;
      int longest = 0;
      int[][][] dp = new int[m][n][4];
      for(int i = 0; i < m; i++){
          for(int j = 0; j < n; j++){
              if(M[i][j] == 1){
                  for(int k = 0; k < 4; k++){
                      dp[i][j][k] = 1; //自身是1，所以对四个方向的连续1都有1的贡献
                  }
                  if(j > 0){//horizontal
                      dp[i][j][0] += dp[i][j-1][0];
                  }
                  if(i > 0){//vertical
                      dp[i][j][1] += dp[i-1][j][1];
                  }
                  if(i > 0 && j < n-1){//diagonal
                      dp[i][j][2] += dp[i-1][j+1][2];
                  }
                  if(i > 0 && j > 0){//anti-diagonal
                      dp[i][j][3] += dp[i-1][j-1][3];
                  }
                  longest = Math.max(longest,Math.max(dp[i][j][0],dp[i][j][1]));
                  longest = Math.max(dp[i][j][2],longest);
                  longest = Math.max(dp[i][j][3],longest);
              }
          }

      }
      return longest;
  }
