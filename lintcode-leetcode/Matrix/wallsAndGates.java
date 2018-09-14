/** 663. Walls and Gates(lintcode) / 286. Walls and Gates(leetcode - locked)
 *      You are given a m x n 2D grid initialized with these three possible values.
 *         1) -1 - A wall or an obstacle.
 *         2) 0 - A gate.
 *         3) INF - Infinity means an empty room. We use the value 2^31 - 1 = 2147483647 to represent INF as you 
 *            may assume that the distance to a gate is less than 2147483647.
 *      Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should
 *    be filled with INF.
 *
 *      Example: Given the 2D grid:
 *                    INF  -1  0  INF
 *                    INF INF INF  -1
 *                    INF  -1 INF  -1
 *                      0  -1 INF INF
 *              return the result:
 *                    3  -1   0   1
 *                    2   2   1  -1
 *                    1  -1   2  -1
 *                    0  -1   3   4
 */
 
 //My Method: BFS - 对每一个门，找它到所有empty room的距离，每次更新empty room的最短距离
 public class Solution {
    public void wallsAndGates(int[][] rooms) {
        if(rooms == null || rooms.length == 0 || rooms[0].length == 0){
            return;
        }
        int m = rooms.length;
        int n = rooms[0].length;
        
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                //find the distance from gate-[i][j] to all empty rooms and update the nearest distance
                if(rooms[i][j] == 0){
                    bfs(rooms, i, j);
                }
            }
        }
    }
    
    public void bfs(int[][] rooms, int row, int col){
        int[] deta = {-1, 0, 1, 0, -1};
        int m = rooms.length;
        int n = rooms[0].length;
        
        Queue<Integer> q = new LinkedList<Integer>();
        q.offer(row * n + col);
        int inc = 1;
        
        while(!q.isEmpty()){
            int size = q.size();
            while(size > 0){
                int index = q.poll();
                for(int i = 0; i < 4; i++){
                    row = index / n + deta[i];
                    col = index % n + deta[i + 1];
                    if(row >= 0 && row < m && col >= 0 && col < n && rooms[row][col] >= inc){
                        if(rooms[row][col] != -1 && rooms[row][col] != 0){
                            q.offer(row * n + col);
                            rooms[row][col] = Math.min(rooms[row][col], inc);
                        }
                    }
                }
                size--;
            }
            inc++;
        }
    }
}
