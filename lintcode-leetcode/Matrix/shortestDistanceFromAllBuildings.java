/** 803. Shortest Distance from All Buildings(lintcode)/317. Shortest Distance from All Buildings(leetcode - locked)
 *      You want to build a house on an empty land which reaches all buildings in the shortest amount of distance. 
 *  You can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:
 *        1) Each **0** marks an empty land which you can pass by freely.
 *        2) Each **1** marks a building which you cannot pass through.
 *        3) Each **2** marks an obstacle which you cannot pass through.
 *
 *      Example: For example, given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2):
 *                           1 - 0 - 2 - 0 - 1
 *                           |   |   |   |   |
 *                           0 - 0 - 0 - 0 - 0
 *                           |   |   |   |   |
 *                           0 - 0 - 1 - 0 - 0
 *               The point (1,2) is an ideal empty land to build a house, as the total travel distance of 3+3+1=7 
 *        is minimal. So return 7.
 */
 
 //Method: BFS - [idea from https://www.cnblogs.com/grandyang/p/5297683.html]
 public class Solution {
    public int shortestDistance(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] sumDis = new int[m][n]; 
        int step = 0;
        int res = 0;

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 1){
                    int[] pos = {i,j};
                    res = bfs(grid, pos, step, sumDis);
                    step--;
                }
            }
        }
        return res;
    }
    
    public int bfs(int[][] grid, int[] pos, int emptyVal, int[][] sumDis){
        int m = grid.length;
        int n = grid[0].length;
        int[] dx = {0,0,1,-1};
        int[] dy = {-1,1,0,0};
        int res = Integer.MAX_VALUE;
        List<int[]> q = new ArrayList<>();
        q.add(pos);
        int inc = 1;
        while(q.size() != 0){
            int size = q.size();
            while(size > 0){
                int[] tmp = q.get(0);
                q.remove(0);
                for(int i = 0; i < 4; i++){
                	int x = tmp[0] + dx[i];
                	int y = tmp[1] + dy[i];
                    if(x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == emptyVal){
                        sumDis[x][y] += inc;
                        grid[x][y]--;
                        int[] newpos = {x, y};
                        q.add(newpos);
                        res = Math.min(res, sumDis[x][y]);
                    }
                }
                size--;
            }
            inc++;
        }
        if(res == Integer.MAX_VALUE){
        	return -1;
        }
        return res;
    }
}
