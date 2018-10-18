/** 348. Design Tic-Tac-Toe(leetcode)
 *      Design a Tic-tac-toe game that is played between two players on a n x n grid.
 *      You may assume the following rules:
 *          1. A move is guaranteed to be valid and is placed on an empty block.
 *          2. Once a winning condition is reached, no more moves is allowed.
 *          3. A player who succeeds in placing n of their marks in a horizontal, vertical, or diagonal row wins 
 *      the game.
 *  
 *      Example: [see in https://leetcode.com/problems/design-tic-tac-toe/description/]
 *
 *      Follow up: Could you do better than O(n2) per move() operation?
 */
 
 //My Method: O(n) per move()
 class TicTacToe {

    /** Initialize your data structure here. */
    char[][] board;
    int winner = 0; // 1 -> player 1 wins, 2 -> player 2 wins, 3 -> a draw, 0 -> no one wins 
    int n;
    
    public TicTacToe(int n) {
        this.n = n;
        board = new char[n][n];
        for(int i = 0; i < n; i++){
            Arrays.fill(board[i], 'E');
        }
    }
    
    /** Player {player} makes a move at ({row}, {col}).
        @param row The row of the board.
        @param col The column of the board.
        @param player The player, can be either 1 or 2.
        @return The current winning condition, can be either:
                0: No one wins.
                1: Player 1 wins.
                2: Player 2 wins. */
    public int move(int row, int col, int player) {
        if(winner != 0){
            return winner;
        }
        char piece = 'X';
        if(player == 2){
            piece = 'O';
        }
        
        int count = 0;
        board[row][col] = piece;
        for(int i = 0; i < n; i++){
            if(board[row][i] != piece){
                break;
            }
            count++;
        }
        if(count == n){
            winner = player;
            return winner;
        }
        count = 0;
        for(int i = 0; i < n; i++){
            if(board[i][col] != piece){
                break;
            }
            count++;
        }
        if(count == n){
            winner = player;
            return winner;
        }
        
        if(row == col || row + col == n - 1){
            count = 0;
            for(int i = 0; i < n; i++){
                if(board[i][i] != piece){
                    break;
                }
                count++;
            }
            if(count == n){
                winner = player;
                return winner;
            }
            count = 0;
            for(int i = 0; i < n; i++){
                if(board[i][n - 1 - i] != piece){
                    break;
                }
                count++;
            }
            if(count == n){
                winner = player;
                return winner;
            }
        }
        return winner;
    }
}

//Method 2: O(1) per move() operation
class TicTacToe {

    /** Initialize your data structure here. */
    int[] rows;
    int[] cols;
    int diagonal;
    int antidiagonal;
    
    public TicTacToe(int n) {
        rows = new int[n];
        cols = new int[n];
        diagonal = 0;
        antidiagonal = 0;
    }
    
    /** Player {player} makes a move at ({row}, {col}).
        @param row The row of the board.
        @param col The column of the board.
        @param player The player, can be either 1 or 2.
        @return The current winning condition, can be either:
                0: No one wins.
                1: Player 1 wins.
                2: Player 2 wins. */
    public int move(int row, int col, int player) {
        int addPiece = player == 1? 1: -1;
        int n = rows.length;
        
        rows[row] += addPiece;
        cols[col] += addPiece;
        
        if(row == col){
            diagonal += addPiece;
        }
        if(row + col == n - 1){
            antidiagonal += addPiece;
        }
        
        if(Math.abs(rows[row]) == n || Math.abs(cols[col]) == n || Math.abs(diagonal) == n || Math.abs(antidiagonal) == n){
            return player;
        }
        return 0;
    }
}
