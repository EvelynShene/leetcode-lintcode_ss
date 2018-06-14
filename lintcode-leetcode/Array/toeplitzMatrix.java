/** 1042. Toeplitz Matrix(lintcode) / 766. Toeplitz Matrix(leetcode)
 *  A matrix is Toeplitz if every diagonal from top-left to bottom-right has the same element.
 *  Now given an M x N matrix, return True if and only if the matrix is Toeplitz.
 *
 *  Example: Input: matrix = [[1,2,3,4],[5,1,2,3],[9,5,1,2]]
 *  Output: True
 *  Explanation:
 *      1234
 *      5123
 *      9512
 */
 
 //Method 1: Use HashMap,group by Category
 //Hint: Two coordinates are on the same diagonal if and only if r1 - c1 == r2 - c2.
 public boolean isToeplitzMatrix(int[][] matrix) {
    Map<Integer, Integer> groups = new HashMap();
    for (int r = 0; r < matrix.length; ++r) {
        for (int c = 0; c < matrix[0].length; ++c) {
            if (!groups.containsKey(r-c))
                groups.put(r-c, matrix[r][c]);
            else if (groups.get(r-c) != matrix[r][c])
                return False;
        }
    }
    return True;
 }
 
 
 //Method 2: Compare With Top-Left Neighbor
  public boolean isToeplitzMatrix(int[][] matrix) {
      for (int r = 0; r < matrix.length; ++r)
          for (int c = 0; c < matrix[0].length; ++c)
              if (r > 0 && c > 0 && matrix[r-1][c-1] != matrix[r][c])
                  return false;
      return true;
  }


 //My Method:
 public boolean isToeplitzMatrix(int[][] matrix) {
    // Write your code here
    int m = matrix.length;
    int n = matrix[0].length;

    for(int i = 0; i < m; i++){
        int j = i + 1;
        int col = 1;
        while(j < m && col < n){
            if(matrix[i][0] != matrix[j][col]){
                return false;
            }
            else{
                j++;
                col++;
            }
        }
    }
    for(int j = 1; j < n; j++){
        int row = 1;
        int i = j+1;
        while(i < n && row < m){
            if(matrix[0][j] != matrix[row][i]){
                return false;
            }
            else{
                i++;
                row++;
            }
        }
    }
    return true;
 }
