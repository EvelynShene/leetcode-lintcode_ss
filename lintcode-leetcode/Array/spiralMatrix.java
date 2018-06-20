/** 381. Spiral Matrix II(lintcode) (Same as problem 769-Spiral Array)/ 59. Spiral Matrix II(leetcode)
 *    Given an integer n, generate a square matrix filled with elements from 1 to n^2 in spiral order.
 *
 *    Example: Given n = 3, you should return the following matrix:
 *            [
 *               [ 1, 2, 3 ],
 *               [ 8, 9, 4 ],
 *               [ 7, 6, 5 ]
 *            ]
 */

  public int[][] generateMatrix(int n) {
      // write your code here
      int[][] res = new int[n][n];
      if(n == 1){
          res[0][0] = 1;
          return res;
      }
      int[] dx = {0,1,0,-1};
      int[] dy = {1,0,-1,0};
      int index = 0;
      int row = 0, col = 0;
      int x = 0, y = 0;
      for(int i = 1; i <= n*n; i++){
          res[row][col] = i;
          x = x + dx[index];
          y = y + dy[index];
          if(y >= n || y < 0 || x >= n || x < 0 || res[x][y] != 0){
              index = (index + 1) % 4;
              x = row + dx[index];
              y = col + dy[index];
          }
          row = x;
          col = y;
      }
      return res;
  }
