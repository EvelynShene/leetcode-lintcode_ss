
/** 769. Spiral Array(lintcode)
 *  Given an integer n, return a spiral array of n * n sizes.
 *  Example: Given n = 3, the spiral array is:
 *            [
 *              [1,2,3]
 *              [8,9,4]
 *              [7,6,5]
 *            ]
 */
 
 public int[][] spiralArray(int n) {
      // write your code here
      int[][] res = new int[n][n];
      int number = 1;
      int row = 0, col = 0;
      int index = 0;
      int nr,nc;
      
      //找规律：螺旋矩阵的赋值顺序是:(row不变，col递增1)->(row递增1，col不变)->(row不变，col递减1)->(row递减1，col不变),以此循环
      int[] detar = {0,1,0,-1};
      int[] detac = {1,0,-1,0};

      while(number <= n*n){
          res[row][col] = number;
          nr = row + detar[index];
          nc = col + detac[index];
          number++;

          if(nr < 0 || nr >= n || nc < 0 || nc >= n || res[nr][nc] != 0){
              index = (index + 1) % 4;
              nr = row + detar[index];
              nc = col + detac[index];
          }
          row = nr;
          col = nc;
      }

      return res;
  }
