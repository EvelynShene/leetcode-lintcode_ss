/** 114. Unique Paths(lintcode)/62. Unique Paths(leetcode)
 *  A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 *  The robot can only move either down or right at any point in time. 
 *  The robot is trying to reach the bottom-right corner of the grid. How many possible unique paths are there?
 */ 
 
 public int uniquePaths(int m, int n) {
    int[][] paths = new int[m][n];

    for(int i = 0; i < m; i++){
        for(int j = 0; j < n; j++){
            if(i == 0 || j == 0){
                paths[i][j] = 1;
            }
            else{
                paths[i][j] = paths[i-1][j] + paths[i][j-1];
            }
        }
    }
    return paths[m-1][n-1];
 }
