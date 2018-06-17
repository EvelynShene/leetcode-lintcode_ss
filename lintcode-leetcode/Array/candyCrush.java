/** 858. Candy Crush(lintcode) / 723. Candy Crush(leetcode-locked)
 *  This question is about implementing a basic elimination algorithm for Candy Crush.
 *  Given a 2D integer array board representing the grid of candy, different positive integers board[i][j] represent 
 *  different types of candies. A value of board[i][j] = 0 represents that the cell at position (i, j) is empty. 
 *  The given board represents the state of the game following the player's move. 
 *  Now, you need to restore the board to a stable state by crushing candies according to the following rules:
 *    1.If three or more candies of the same type are adjacent vertically or horizontally, 
 *       "crush" them all at the same time - these positions become empty.
 *    2.After crushing all candies simultaneously, if an empty space on the board has candies on top of itself, 
 *        then these candies will drop until they hit a candy or bottom at the same time. (No new candies will drop outside the top boundary.)
 *    3.After the above steps, there may exist more candies that can be crushed. If so, you need to repeat the above steps.
 *    4.If there does not exist more candies that can be crushed (ie. the board is stable), then return the current board.
 *  You need to perform the above rules until the board becomes stable, then return the current board.
 *  (类似消消乐游戏)
 */
 
 //My Method:[AC in lintcode]
 public class Solution {
    /**
     * @param board: a 2D integer array
     * @return: the current board
     */
    public int[][] candyCrush(int[][] board) {
        // Write your code here

        int row = board.length;
        int col = board[0].length;
        ArrayList<int[]> crush = new ArrayList<int[]>();
        
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                int hequal = j+1;
                while(hequal < col && board[i][j] > 0 && board[i][j] == board[i][hequal]){
                    hequal++;
                }
                if(hequal-j >= 3){
                    for(int x = j; x < hequal; x++){
                        int[] t = new int[2]; //ArrayList add方法会导致数据覆盖,如果不想覆盖，必须重新new新数组对象
                        t[0] = i;
                        t[1] = x;
                        crush.add(t);
                    }
                }
                j = hequal-1;
            }
        }
        
        for(int j = 0; j < col; j++){
            for(int i = 0; i < row; i++){
                int vequal = i+1;
                while(vequal < row && board[i][j] > 0 && board[i][j] == board[vequal][j]){
                    vequal++;
                }
                if(vequal-i >= 3){
                    for(int x = i; x < vequal; x++){
                        int[] t = new int[2];
                        t[1] = j;
                        t[0] = x;
                        crush.add(t);
                    }
                }
                i = vequal-1;
            }
        }
        
        if(crush.size() == 0){
            return board;
        }
        for(int i = 0; i < crush.size(); i++){
            int[] temp = crush.get(i);
            board[temp[0]][temp[1]] = 0;
        }
        //Drop
        for(int j = 0; j < col; j++){
            int index = row-1;
            for(int i = row-1; i >= 0; i--){
                if(board[i][j] != 0){
                    int temp = board[index][j];
                    board[index][j] = board[i][j];
                    board[i][j] = temp;
                    index--;
                }
            }
        }
        
        int[][] res = candyCrush(board);
        return res;
        
    }
}
 
