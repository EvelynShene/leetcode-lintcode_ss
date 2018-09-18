/** 52. N-Queens II(leetcode) / 34. N-Queens II(lintcode)
 *      The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack
 *  each other.
 *      Given an integer n, return the number of distinct solutions to the n-queens puzzle.
 *   
 *      Example: Input: 4
 *               Output: 2
 *               Explanation: There are two distinct solutions to the 4-queens puzzle as shown below.
 *                      [
 *                       [".Q..",  // Solution 1
 *                        "...Q",
 *                        "Q...",
 *                        "..Q."],
 *
 *                       ["..Q.",  // Solution 2
 *                        "Q...",
 *                        "...Q",
 *                        ".Q.."]
 *                      ]
 */
 
 //My Method: DFS
 class Solution {
    public int totalNQueens(int n) {
        if(n <= 0){
            return 0;
        }
        char[][] b = new char[n][n];
        for(int i = 0; i < n; i++){
            Arrays.fill(b[i], '.');
        }
        return dfs(b, 0);
    }
    
    public int dfs(char[][] b, int row){
        if(row == b.length){
            return 1;
        }
        int res = 0;
        for(int col = 0; col < b.length; col++){
            if(validBoard(b, row, col)){
                b[row][col] = 'Q';
                res += dfs(b, row + 1);
                b[row][col] = '.';
            }
        }
        return res;
    }
    
    public boolean validBoard(char[][] b, int row, int col){
        for(int i = 0; i < row; i++){
            if(b[i][col] == 'Q'){
                return false;
            }
        }
        for(int i = 1; i <= row; i++){
            if(row - i >= 0){
                if(col - i >= 0 && b[row - i][col - i] == 'Q'){
                    return false;
                }
                if(col + i < b.length && b[row - i][col + i] == 'Q'){
                    return false;
                }
            }
        }
        return true;
    }
}
