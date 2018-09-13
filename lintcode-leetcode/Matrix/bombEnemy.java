/** 553. Bomb Enemy(lintcode) / 361. Bomb Enemy (leetcode)
 *      Given a 2D grid, each cell is either a wall 'W', an enemy 'E' or empty '0' (the number zero), return the 
 *  maximum enemies you can kill using one bomb.
 *      The bomb kills all the enemies in the same row and column from the planted point until it hits the wall 
 *  since the wall is too strong to be destroyed.
 *      You can only put the bomb at an empty cell.
 *
 *      Example: Given a grid:
 *                       0 E 0 0
 *                       E 0 W E
 *                       0 E 0 0
 *               return 3. (Placing a bomb at (1,1) kills 3 enemies)
 */
 
 //My Method:
 public int maxKilledEnemies(char[][] grid) {
    if(grid == null || grid.length == 0){
        return 0;
    }
    int m = grid.length;
    int n = grid[0].length;
    int[] up_enemy = new int[n];
    int max = 0;
    
    for(int i = 0; i < m; i++){
        int left_enemy = 0;
        for(int j = 0; j < n; j++){
            if(grid[i][j] == 'E'){
                left_enemy++;
                up_enemy[j]++;
            }
            else if(grid[i][j] == 'W'){
                left_enemy = 0;
                up_enemy[j] = 0;
            }
            else{
                int res = left_enemy + up_enemy[j];
                for(int r = j + 1; r < n; r++){
                    if(grid[i][r] == 'E'){
                        res++;
                    }
                    else if(grid[i][r] == 'W'){
                        break;
                    }
                }
                for(int d = i + 1; d < m; d++){
                    if(grid[d][j] == 'E'){
                        res++;
                    }
                    else if(grid[d][j] == 'W'){
                        break;
                    }
                }
                max = Math.max(max,res);
            }
        }
    }
    return max;
}
