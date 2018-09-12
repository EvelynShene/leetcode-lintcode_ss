/** 36. Valid Sudoku(leetcode) / 389. Valid Sudoku(lintcode)
 *     Determine if a 9x9 Sudoku board is valid. Only the filled cells need to be validated according to the 
 *  following rules:
 *      1) Each row must contain the digits 1-9 without repetition.
 *      2) Each column must contain the digits 1-9 without repetition.
 *      3) Each of the 9 3x3 sub-boxes of the grid must contain the digits 1-9 without repetition.
 *    The Sudoku board could be partially filled, where empty cells are filled with the character '.'.
 *      
 *    Example: Input:
 *                   [
 *                     ["8","3",".",".","7",".",".",".","."],
 *                     ["6",".",".","1","9","5",".",".","."],
 *                     [".","9","8",".",".",".",".","6","."],
 *                     ["8",".",".",".","6",".",".",".","3"],
 *                     ["4",".",".","8",".","3",".",".","1"],
 *                     ["7",".",".",".","2",".",".",".","6"],
 *                     [".","6",".",".",".",".","2","8","."],
 *                     [".",".",".","4","1","9",".",".","5"],
 *                     [".",".",".",".","8",".",".","7","9"]
 *                   ]
 *            Output: false
 *                Explanation: Same as Example 1, except with the 5 in the top left corner being 
 *                       modified to 8. Since there are two 8's in the top left 3x3 sub-box, it is invalid.
 *
 *    Note:
 *       1) A Sudoku board (partially filled) could be valid but is not necessarily solvable.
 *       2) Only the filled cells need to be validated according to the mentioned rules.
 *       3) The given board contain only digits 1-9 and the character '.'.
 *       4) The given board size is always 9x9.
 */
 
 //Method:
public boolean isValidSudoku(char[][] board) {
    int[][] valid = new int[18][10];

    for(int i = 0; i < board.length; i++){
        for(int j = 0; j < board[0].length; j++){
            if(board[i][j] != '.'){
                int num = board[i][j] - '0';
                if(valid[i][num] != 0 || valid[j + 9][num] != 0){
                    return false;
                }
                valid[i][num] = 1;
                valid[j + 9][num] = 1;
            }
        }
    }

    for(int i = 0; i < 9; i += 3){
        for(int j = 0; j < 9; j += 3){
            Set<Integer> cube = new HashSet<Integer>();
            for(int x = 0; x < 9; x++){
                int row = i + x / 3;
                int col = j + x % 3;
                if(board[row][col] != '.'){
                    int num = board[row][col] - '0';
                    if(cube.contains(num)){
                        return false;
                    }
                    cube.add(num);
                }
            }
        }
    }
    return true;
}
