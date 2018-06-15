/** 115. Unique Paths II(lintcode)/63. Unique Paths II(leetcode)
 *  A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 *  The robot can only move either down or right at any point in time. 
 *  The robot is trying to reach the bottom-right corner of the grid. 
 *  Now consider if some obstacles are added to the grids. How many possible unique paths are there?
 */ 
 
 public int uniquePathsWithObstacles(int[][] obstacleGrid) {
    // write your code here
    int m = obstacleGrid.length;
    int n = obstacleGrid[0].length;
    int[][] paths = new int[m][n];

    for(int i = 0; i < m; i++){
        for(int j = 0; j < n; j++){
            if(obstacleGrid[i][j] == 0){
                if(i == 0 && j == 0){
                    paths[i][j] = 1;
                }
                else if(i == 0){
                    paths[i][j] = paths[i][j-1];
                }
                else if(j == 0){
                    paths[i][j] = paths[i-1][j];
                }
                else{
                    paths[i][j] = paths[i-1][j] + paths[i][j-1];
                }
            }
            else{
                paths[i][j] = 0;
            }
        }
    }
    return paths[m-1][n-1];
 }
