/** 289. Game of Life(leetcode)
 *     According to the Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton 
 *  devised by the British mathematician John Horton Conway in 1970."
 *     Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell interacts 
 *  with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above
 *  Wikipedia article):
 *      1) Any live cell with fewer than two live neighbors dies, as if caused by under-population.
 *      2) Any live cell with two or three live neighbors lives on to the next generation.
 *      3) Any live cell with more than three live neighbors dies, as if by over-population..
 *      4) Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
 *     Write a function to compute the next state (after one update) of the board given its current state. 
 *  The next state is created by applying the above rules simultaneously to every cell in the current state,
 *  where births and deaths occur simultaneously.
 *
 *     Example: Input: 
 *                 [
 *                   [0,1,0],
 *                   [0,0,1],
 *                   [1,1,1],
 *                   [0,0,0]
 *                 ]
 *              Output: 
 *                 [
 *                   [0,0,0],
 *                   [1,0,1],
 *                   [0,1,1],
 *                   [0,1,0]
 *                 ]
 *
 *     Follow up:
 *       1) Could you solve it in-place? Remember that the board needs to be updated at the same time: 
 *          You cannot update some cells first and then use their updated values to update other cells.
 *       2) In this question, we represent the board using a 2D array. In principle, the board is infinite, 
 *          which would cause problems when the active area encroaches the border of the array. 
 *          How would you address these problems?
 */
 
 //Method 1: O(n^2) time and space complexity
 public void gameOfLife(int[][] board) {
    int m = board.length;
    int n = board[0].length;
    int[] detax = {-1,-1,-1,0,0,1,1,1};
    int[] detay = {-1,0,1,-1,1,-1,0,1};
    int[][] newboard = new int[m][n];
    for(int i = 0; i < m; i++){
        for(int j = 0; j < n; j++){
            int round = 0;
            for(int k = 0; k < 8; k++){
                if(i+detax[k] < m && i+detax[k] >=0 && j+detay[k] < n && j+detay[k] >= 0)
                    round += board[i+detax[k]][j+detay[k]];
            }
            if(board[i][j] == 0 && round == 3)
                newboard[i][j] = 1;
            else if(board[i][j] == 1 && round >= 2 && round <= 3){
                newboard[i][j] = 1;
            }
            else{
                newboard[i][j] = 0;
            }
        }
    }
    for(int i = 0; i < m; i++){
        System.arraycopy(newboard[i],0,board[i],0,n);
    }
 }

//Method 2: Solve it in-place
public void gameOfLife(int[][] board) {
     int m = board.length;
     int n = board[0].length;
     int[] detax = {-1,-1,-1,0,0,1,1,1};
     int[] detay = {-1,0,1,-1,1,-1,0,1};

     for(int i = 0; i < m; i++){
         for(int j = 0; j < n; j++){
             int round = 0;
             for(int k = 0; k < 8; k++){
                 if(i+detax[k] < m && i+detax[k] >=0 && j+detay[k] < n && j+detay[k] >= 0){
                     if(board[i+detax[k]][j+detay[k]] == 2){
                         continue;
                     }
                     else if(board[i+detax[k]][j+detay[k]] == -1){
                         round += 1;
                     }
                     else{
                         round += board[i+detax[k]][j+detay[k]];
                     }
                 }
             }
             if(board[i][j] == 0 && round == 3)//0 -> 1 : 2
                 board[i][j] = 2;
             else if(board[i][j] == 1 && round >= 2 && round <= 3){// 1 -> 1
                 board[i][j] = 1;
             }
             else{
                 if(board[i][j] == 1)//1 -> 0
                     board[i][j] = -1;
                 else // 0 -> 0
                     board[i][j] = 0;
             }
         }
     }
     for(int i = 0; i < m; i++){
         for(int j = 0; j < n; j++){
             if(board[i][j] == -1){
                 board[i][j] = 0;
             }
             else if(board[i][j] == 2){
                 board[i][j] = 1;
             }
         }
     }
 }
