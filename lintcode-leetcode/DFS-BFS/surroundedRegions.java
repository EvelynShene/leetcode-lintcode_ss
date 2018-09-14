/** 130. Surrounded Regions(leetcode) / 477. Surrounded Regions(lintcode)
 *      Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.
 *      A region is captured by flipping all 'O's into 'X's in that surrounded region.
 *
 *      Example: Input:
 *                  X X X X
 *                  X O O X
 *                  X X O X
 *                  X O X X
 *            After running your function, the board should be:
 *                  X X X X
 *                  X X X X
 *                  X X X X
 *                  X O X X
 */
 
 //My Method: DFS or BFS (BFS better)
 class Solution {
    public void solve(char[][] board) {
        if(board == null || board.length == 0 || board[0].length == 0){
            return;
        }
        int m = board.length;
        int n = board[0].length;
        
        for(int j = 0; j < n; j++){
            if(board[0][j] == 'O'){
                board[0][j] = '2';
                bfs(board, 0, j);
            }
            if(board[m - 1][j] == 'O'){
                board[m - 1][j] = '2';
                bfs(board, m - 1, j);
            }
        }
        
        for(int i = 1; i < m - 1; i++){
            if(board[i][0] == 'O'){
                board[i][0] = '2';
                bfs(board, i, 0);
            }
            if(board[i][n - 1] == 'O'){
                board[i][n - 1] = '2';
                bfs(board, i, n - 1);
            } 
        }
       
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(board[i][j] == 'O'){
                    board[i][j] = 'X';
                }
                else if(board[i][j] == '2'){
                    board[i][j] = 'O';
                }
            }
        }
    }
    //Method 1:
    public void dfs(char[][] board, int row, int col){
        int m = board.length;
        int n = board[0].length;
        int[] deta = {-1, 0, 1, 0, -1};
        Stack<Integer> stk = new Stack<Integer>();
        stk.push(row * n + col);
        
        while(!stk.isEmpty()){
            int index = stk.pop();
            for(int i = 0; i < 4; i++){
                row = index / n + deta[i];
                col = index % n + deta[i + 1];
                if(row >= 0 && row < m && col >= 0 && col < n && board[row][col] == 'O'){
                    stk.push(row * n + col);
                    board[row][col] = '2';
                }
            }
        }
    }
    //Method 2:
    public void bfs(char[][] board, int row, int col){
        int m = board.length;
        int n = board[0].length;
        int[] deta = {-1, 0, 1, 0, -1};
        Queue<Integer> q = new LinkedList<Integer>();
        q.offer(row * n + col);
        
        while(!q.isEmpty()){
            int size = q.size();
            while(size > 0){
                int index = q.poll();
                for(int i = 0; i < 4; i++){
                    row = index / n + deta[i];
                    col = index % n + deta[i + 1];
                    if(row >= 0 && row < m && col >= 0 && col < n && board[row][col] == 'O'){
                        q.offer(row * n + col);
                        board[row][col] = '2';
                    }
                }
                size--;
            }
        }
    }
}
