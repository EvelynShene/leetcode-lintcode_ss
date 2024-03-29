/** 304. Range Sum Query 2D - Immutable(leetcode) / 665. Range Sum Query 2D - Immutable(lintcode)
 *      Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left 
 *  corner (row1, col1) and lower right corner (row2, col2).
 *
 *      Note: 1) You may assume that the matrix does not change.
 *            2) There are many calls to sumRegion function.
 *            3) You may assume that row1 ≤ row2 and col1 ≤ col2.
 *
 *      Example: Given matrix = [
 *                                 [3, 0, 1, 4, 2],
 *                                 [5, 6, 3, 2, 1],
 *                                 [1, 2, 0, 1, 5],
 *                                 [4, 1, 0, 1, 7],
 *                                 [1, 0, 3, 0, 5]
 *                              ]
 *                 sumRegion(2, 1, 4, 3) -> 8
 *                 sumRegion(1, 1, 2, 2) -> 11
 *                 sumRegion(1, 2, 2, 4) -> 12
 */
 
 //My Method: Time complexity: O(m) time per sumRegion query, O(mn) time pre-computation; O(mn) space complexity
 class NumMatrix {
    int[][] sum;
    public NumMatrix(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
            sum = new int[0][0];
            return;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        sum = new int[m][n + 1];
        
        for(int i = 0; i < m; i++){
            for(int j = 1; j <= n; j++){
                sum[i][j] = sum[i][j - 1] + matrix[i][j - 1];
            }
        }
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        int total = 0;
        for(int i = row1; i <= row2; i++){
            total += sum[i][col2 + 1] - sum[i][col1];
        }
        return total;
    }
}

//Method 2: O(1) time per sumRegion query, O(mn) time pre-computation; O(mn) space complexity
class NumMatrix {
    int[][] sum;
    public NumMatrix(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        sum = new int[m + 1][n + 1];
        
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                sum[i + 1][j + 1] = sum[i + 1][j] + sum[i][j + 1] - sum[i][j] + matrix[i][j];
            }
        }
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        return sum[row2 + 1][col2 + 1] + sum[row1][col1] - sum[row1][col2 + 1] - sum[row2 + 1][col1];
    }
}
 
