/** 37. Sudoku Solver(leetcode) / 802. Sudoku Solver(lintcode)
 *    Write a program to solve a Sudoku puzzle by filling the empty cells.
 *    A sudoku solution must satisfy all of the following rules:
 *        1) Each of the digits 1-9 must occur exactly once in each row.
 *        2) Each of the digits 1-9 must occur exactly once in each column.
 *        3) Each of the the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
 *    Empty cells are indicated by the character '.'.
 *
 *    Note: 1) The given board contain only digits 1-9 and the character '.'.
 *          2) You may assume that the given Sudoku puzzle will have a single unique solution.
 *          3) The given board size is always 9x9.
 */
 
 //Method: DFS + Backtracking
 class Solution {
    public void solveSudoku(char[][] board) {
        if(board == null || board.length == 0 || board[0].length == 0){
            return;
        }
        canSolveSudoku(board);
    }
    
    public boolean canSolveSudoku(char[][] board){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j] == '.'){
                    for(char c = '1'; c <= '9'; c++){
                        if(isValidSudoku(board, i, j, c)){
                            board[i][j] = c;
                            if(canSolveSudoku(board)){
                                return true;
                            }
                            board[i][j] = '.';
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
    
    public boolean isValidSudoku(char[][] board, int row, int col, char c){
        for(int i = 0; i < 9; i++){
            if(board[row][i] == c || board[i][col] == c){
                return false;
            }
            if(board[row / 3 * 3 + i / 3][col / 3 * 3 + i % 3] == c){
                return false;
            }
        }
        return true;
    }
}
