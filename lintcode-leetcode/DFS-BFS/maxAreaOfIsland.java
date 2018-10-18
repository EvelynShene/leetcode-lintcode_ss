/** 695. Max Area of Island(leetcode) / 1080. Max Area of Island(lintcode)
 *      Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 
 *  4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.
 *      Find the maximum area of an island in the given 2D array. (If there is no island, the maximum area is 0.)
 *
 *      Example: [[0,0,1,0,0,0,0,1,0,0,0,0,0],
 *                [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *                [0,1,1,0,1,0,0,0,0,0,0,0,0],
 *                [0,1,0,0,1,1,0,0,1,0,1,0,0],
 *                [0,1,0,0,1,1,0,0,1,1,1,0,0],
 *                [0,0,0,0,0,0,0,0,0,0,1,0,0],
 *                [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *                [0,0,0,0,0,0,0,1,1,0,0,0,0]]
 *            Given the above grid, return 6. 
 *              Note the answer is not 11, because the island must be connected 4-directionally.
 */
 
 //My Method:
 class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        int max_area = 0;
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n]; 
        
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 1 && !visited[i][j]){
                    int area = findArea(grid, i, j, visited);
                    max_area = Math.max(area, max_area);
                }
            }
        }
        
        return max_area;
    }
    
    public int findArea(int[][] grid, int row, int col, boolean[][] visited){
        int m = grid.length;
        int n = grid[0].length;
        visited[row][col] = true;
        int area = 1;
        int[] deta = {-1, 0, 1, 0, -1};
        
        for(int i = 0; i < 4; i++){
            int r = row + deta[i];
            int c = col + deta[i + 1];
            if(r >= 0 && r < m && c >= 0 && c < n && grid[r][c] == 1 && !visited[r][c]){
                area += findArea(grid, r, c, visited);
            }
        }
        return area;
    }
}
