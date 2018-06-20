/** 374. Spiral Matrix(lintcode) / 54. Spiral Matrix (leetcode)
 *    Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
 *
 *    Example:  Given the following matrix:
 *                 [
 *                  [ 1, 2, 3 ],
 *                  [ 4, 5, 6 ],
 *                  [ 7, 8, 9 ]
 *                 ]
 *        You should return [1,2,3,6,9,8,7,4,5].
 */

//Method 1: Same as problem Spiral Matrix II
  public List<Integer> spiralOrder(int[][] matrix) {
      // write your code here
      List<Integer> list = new ArrayList<Integer>();
      if(matrix == null || matrix.length == 0){
          return list;
      }
      int m = matrix.length;
      int n = matrix[0].length;
      int[] dx = {0,1,0,-1};
      int[] dy = {1,0,-1,0};
      int index = 0;
      int row = 0, col = 0;
      int x = 0, y = 0;

      for(int i = 1; i <= n*m; i++){
          list.add(matrix[row][col]);
          matrix[row][col] = 0;
          x = x + dx[index];
          y = y + dy[index];

          if(x >= m || x < 0 || y >= n || y < 0 || matrix[x][y] == 0){
              index = (index + 1) % 4;
              x = row + dx[index];
              y = col + dy[index];
          }
          row = x;
          col = y;
      }
      return list;
  }
  
  //Method 2: Don't change the matrix, create another matrix
  public List<Integer> spiralOrder(int[][] matrix) {
      List<Integer> list = new ArrayList<Integer>();
      if(matrix == null || matrix.length == 0){
          return list;
      }
      int m = matrix.length;
      int n = matrix[0].length;
      boolean[][] getNum = new boolean[m][n];
      int[] dx = {0,1,0,-1};
      int[] dy = {1,0,-1,0};
      int index = 0;
      int row = 0, col = 0;
      int x = 0, y = 0;

      for(int i = 1; i <= n*m; i++){
          list.add(matrix[row][col]);
          getNum[row][col] = true;
          x = x + dx[index];
          y = y + dy[index];

          if(x >= m || x < 0 || y >= n || y < 0 || getNum[x][y]){
              index = (index + 1) % 4;
              x = row + dx[index];
              y = col + dy[index];
          }
          row = x;
          col = y;
      }
      return list;
  }
