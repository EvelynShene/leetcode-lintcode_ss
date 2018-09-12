/** 329. Longest Increasing Path in a Matrix(leetcode) / 1291. Longest Increasing Path in a Matrix(lintcode)
 *      Given an integer matrix, find the length of the longest increasing path.
 *      From each cell, you can either move to four directions: left, right, up or down. 
 *  You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).
 *      
 *      Example: 1) Input: nums = 
 *                      [
 *                        [9,9,4],
 *                        [6,6,8],
 *                        [2,1,1]
 *                      ] 
 *                  Output: 4 
 *                      Explanation: The longest increasing path is [1, 2, 6, 9].
 *               2) Input: nums = 
 *                      [
 *                        [3,4,5],
 *                        [3,2,6],
 *                        [2,2,1]
 *                      ] 
 *                  Output: 4 
 *                      Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
 */
 
 /* My Method: DFS + Memorization - O(mn) time and space complexity
  *      Idea: 1) For every cell do DFS to find it longest increasing path in the ascending order
  *            2) each cell has 4 direction to check
  *            3) maintain a maxlen[][] matrix to store each cell's max increasing path ended at this cell
  *                 a) use matrix[i + x][j + y] <= matrix[i][j], no need a visited[m][n] array
  *                 b) only need to check when matrix[i + x][j + y] == 0 
  *                     [if not 0, means this cell already visited and calculated before, just return the result]
  */
  
 class Solution {
    public int longestIncreasingPath(int[][] matrix) {
        if(matrix == null || matrix.length == 0){
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] maxlen = new int[m][n];
        int max = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                dfs(matrix, maxlen, i, j);
                max = Math.max(max, maxlen[i][j]);
            }
        }
        return max;
    }
    
    public void dfs(int[][] matrix, int[][] maxlen, int row, int col){
        int[] dx = {0, 0, 1, -1};
        int[] dy = {-1, 1, 0, 0};
        int m = matrix.length; 
        int n = matrix[0].length;
        int max = 1;
        for(int i = 0; i < 4; i++){
            if(row + dx[i] < m && row + dx[i] >= 0 && col + dy[i] < n && col + dy[i] >= 0){
                if(matrix[row + dx[i]][col + dy[i]] < matrix[row][col]){
                    if(maxlen[row + dx[i]][col + dy[i]] == 0){
                        dfs(matrix, maxlen, row + dx[i], col + dy[i]);
                    }
                    max = Math.max(max, 1 + maxlen[row + dx[i]][col + dy[i]]);
                }
            }
        }
        maxlen[row][col] = max;
    }
}
