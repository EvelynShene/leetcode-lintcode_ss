/** 79. Word Search(leetcode) / 123. Word Search(lintcode)
 *     Given a 2D board and a word, find if the word exists in the grid.
 *     The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those
 *  horizontally or vertically neighboring. The same letter cell may not be used more than once.
 *
 *     Example: board =
 *                [
 *                  ['A','B','C','E'],
 *                  ['S','F','C','S'],
 *                  ['A','D','E','E']
 *                ]
 *              1) Given word = "ABCCED", return true.
 *              2) Given word = "SEE", return true.
 *              3) Given word = "ABCB", return false.
 */
 
 //My Method: DFS
 class Solution {
    public boolean exist(char[][] board, String word) {
        if(word == null || word.length() == 0){
            return true;
        }
        if(board == null || board.length == 0){
            return false;
        }
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        
        boolean res = false;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(board[i][j] == word.charAt(0)){
                    if(word.length() - 1 == 0){
                        return true;
                    }
                    visited[i][j] = true;
                    res = dfs(board, visited, i, j, word, 1); 
                    if(res){
                        return true;
                    }
                    visited[i][j] = false;
                }
            }
        }
        return false; 
    }
    
    public boolean dfs(char[][] board, boolean[][] visited, int row, int col, String word, int index){
        int m = board.length;
        int n = board[0].length;
        int[] dx = {0, 0, 1, -1};
        int[] dy = {-1, 1, 0, 0};
        boolean res = false;
        
        for(int i = 0; i < 4; i++){
            if(row + dx[i] >= 0 && row + dx[i] < m && col + dy[i] >= 0 && col + dy[i] < n){
                if(!visited[row + dx[i]][col + dy[i]] && board[row + dx[i]][col + dy[i]] == word.charAt(index)){
                    if(index == word.length() - 1){
                        return true;
                    }
                    visited[row + dx[i]][col + dy[i]] = true;
                    res = dfs(board, visited, row + dx[i], col + dy[i], word, index + 1);
                    if(res){
                        return true;
                    }
                    visited[row + dx[i]][col + dy[i]] = false; 
                }
            }
        }
        return false;
    }
}
