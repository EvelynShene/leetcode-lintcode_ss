/** 308. Range Sum Query 2D - Mutable(leetcode) / 817. Range Sum Query 2D - Mutable(lintcode)
 *      Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left 
 *  corner (row1, col1) and lower right corner (row2, col2).
 *
 *      Example: Given matrix = [
 *                                 [3, 0, 1, 4, 2],
 *                                 [5, 6, 3, 2, 1],
 *                                 [1, 2, 0, 1, 5],
 *                                 [4, 1, 0, 1, 7],
 *                                 [1, 0, 3, 0, 5]
 *                              ]
 *                 sumRegion(2, 1, 4, 3) -> 8
 *                 update(3, 2, 2)
 *                 sumRegion(2, 1, 4, 3) -> 10
 *
 *      Note: 1) The matrix is only modifiable by the update function.
 *            2) You may assume the number of calls to update and sumRegion function is distributed evenly.
 *            3) You may assume that row1 ≤ row2 and col1 ≤ col2.
 */
 
/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * obj.update(row,col,val);
 * int param_2 = obj.sumRegion(row1,col1,row2,col2);
 */
 
 //Method: Binary Index Tree
 class NumMatrix {
    int[][] numbers;
    int[][] tree;
    int m;
    int n;
    
    public NumMatrix(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return;
        }
        m = matrix.length;
        n = matrix[0].length;
        numbers = new int[m][n];
        tree = new int[m + 1][n + 1];
        
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                update(i, j, matrix[i][j]);
            }
        }
    }
    
    public int lowBit(int x){
        return (x & (-x));
    }
    
    public int getSum(int row, int col){
        int total = 0;
        for(int i = row; i > 0; i = i - lowBit(i)){
            for(int j = col; j > 0; j = j - lowBit(j)){
                total += tree[i][j];
            }
        }
        return total;
    }
    
    public void update(int row, int col, int val) {
        int diff = val - numbers[row][col];
        numbers[row][col] = val;
        
        for(int i = row + 1; i <= m; i += lowBit(i)){
            for(int j = col + 1; j <= n; j += lowBit(j)){
                tree[i][j] += diff;
            }
        }
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        return getSum(row2 + 1, col2 + 1) - getSum(row2 + 1, col1) - getSum(row1, col2 + 1) + getSum(row1, col1);
    }
}
