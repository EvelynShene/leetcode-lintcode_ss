/** 200. Number of Islands(leetcode) / 433. Number of Islands(lintcode)
 *      Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded 
 *  by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges 
 *  of the grid are all surrounded by water.
 *  
 *      Example: 1) Input:
 *                     11110
 *                     11010
 *                     11000
 *                     00000
 *                  Output: 1
 *               2) Input:
 *                     11000
 *                     11000
 *                     00100
 *                     00011
 *                  Output: 3 
 */
 
 //My Method: DFS or BFS (BFS better than DFSww)
 class Solution {
    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0){
            return 0;
        }
        int c = 0;
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == '1' && !visited[i][j]){
                    visited[i][j] = true;
                    bfs(grid, i, j, visited); 
                 // dfs(grid, i, j, visited);
                    c++;
                }
            }
        }
        
        return c;
    }
    
    public void bfs(char[][] grid, int row, int col, boolean[][] visited){
        int[] deta = {-1, 0, 1, 0, -1};
        int n = grid[0].length;
        Queue<Integer> q = new LinkedList<Integer>();
        q.offer(row * n + col);
        
        while(!q.isEmpty()){
            int size = q.size();
            while(size > 0){
                int index = q.poll();
                for(int i = 0; i < 4; i++){
                    row = index / n + deta[i];
                    col = index % n + deta[i + 1];
                    if(row >= 0 && row < grid.length && col >= 0 && col < n && grid[row][col] == '1' && !visited[row][col]){
                        visited[row][col] = true;
                        q.offer(row * n + col);
                    }
                }
                size--;
            }
        }
    }
    
    public void dfs(char[][] grid, int row, int col, boolean[][] visited){
        int[] deta = {-1, 0, 1, 0, -1};
        int n = grid[0].length;
        Stack<Integer> stk = new Stack<Integer>();
        stk.push(row * n + col);
        
        while(!stk.isEmpty()){
            int index = stk.pop();
            for(int i = 0; i < 4; i++){
                row = index / n + deta[i];
                col = index % n + deta[i + 1];
                if(row >= 0 && row < grid.length && col >= 0 && col < n && grid[row][col] == '1' && !visited[row][col]){
                    visited[row][col] = true;
                    stk.push(row * n + col);
                }
            }
        }
    }
}
