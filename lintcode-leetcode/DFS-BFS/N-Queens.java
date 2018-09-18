/** 51. N-Queens(leetcode) / 33. N-Queens(lintcode)
 *      The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack 
 *  each other.
 *      Given an integer n, return all distinct solutions to the n-queens puzzle.
 *      Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both 
 *  indicate a queen and an empty space respectively.
 *
 *      Example: Input: 4
 *               Output: [
 *                         [".Q..",  // Solution 1
 *                          "...Q",
 *                          "Q...",
 *                          "..Q."],
 *
 *                         ["..Q.",  // Solution 2
 *                          "Q...",
 *                          "...Q",
 *                          ".Q.."]
 *                        ]
 *              Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above.
 */
 
 //My Method: DFS
 class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        if(n <= 0){
            return res;
        }
        char[][] b = new char[n][n];
        for(int i = 0; i < n; i++){
            Arrays.fill(b[i], '.');
        }
        dfs(res, b, 0, n);
        return res;
    }
    
    public void dfs(List<List<String>> res, char[][] b, int row, int n){
        if(row == n){
            addtoList(res, b);
            return;
        }
        for(int col = 0; col < n; col++){
            if(validBoard(b, row, col)){
                b[row][col] = 'Q';
                dfs(res, b, row + 1, n);
                b[row][col] = '.';
            }
        }
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
    
    public void addtoList(List<List<String>> res, char[][] b){
        List<String> l = new ArrayList<>();
        for(int i = 0; i < b.length; i++){
            l.add(String.valueOf(b[i]));
        }
        res.add(l);
    }
}
