/** 174. Dungeon Game(leetcode) / 1346. Dungeon Game(lintcode)
 *      The demons had captured the princess (P) and imprisoned her in the bottom-right corner of a dungeon. 
 *  The dungeon consists of M x N rooms laid out in a 2D grid. Our valiant knight (K) was initially positioned in 
 *  the top-left room and must fight his way through the dungeon to rescue the princess.
 *      The knight has an initial health point represented by a positive integer. If at any point his health point 
 *  drops to 0 or below, he dies immediately.
 *      Some of the rooms are guarded by demons, so the knight loses health (negative integers) upon entering 
 *  these rooms; other rooms are either empty (0's) or contain magic orbs that increase the knight's health 
 *  (positive integers).
 *      In order to reach the princess as quickly as possible, the knight decides to move only rightward or 
 *  downward in each step.
 *      Write a function to determine the knight's minimum initial health so that he is able to rescue the 
 *  princess.
 *      For example, given the dungeon below, the initial health of the knight must be at least 7 if he follows 
 *  the optimal path RIGHT-> RIGHT -> DOWN -> DOWN.
 *           | -2(K) |  -3  | 3    |
 *           | -5    |  10  | 1    |
 *           | 10    |  30  | -5(P)|
 *
 *      Note: 1) The knight's health has no upper bound.
 *            2) Any room can contain threats or power-ups, even the first room the knight enters and the 
 *          bottom-right room where the princess is imprisoned.
 */
 
 /** Method: DP
  *       最开始的想法是比较右边和下边的数字的大小，去大的那个，但是这个算法对某些情况不成立， 比如：
  *           |  1(K) |  -3  |  3   |
  *           |  0    |  -2  |  0   |
  *           | -3    |  -3  | -3(P)|
  *       结果应该是3: 1 -> -3 -> 3 -> 0 -> -4; 但是用上述动态规划得到的是5: 1 -> 0 -> -2 -> 0 -> -3.
  *       逆向推正是本题的精髓所在: 建立一个二维数组dp，其中dp[i][j]用来表示以位置 (i, j) 出发的起始血量，
  *             最先处理的是公主所在的房间的起始生命值，然后慢慢向第一个房间扩散，不断的得到各个位置的最优的生命值。
  *       有些位置处的生命值为正数，减法后可能得到负数，那么就需取1。【因为knight活着的要求的生命值至少为1】
  */
 public int calculateMinimumHP(int[][] dungeon) {
    if(dungeon == null || dungeon.length == 0){
        return 0;
    }
    int m = dungeon.length;
    int n = dungeon[0].length;
    // dp[i][j] indicates the minimum initial health needed when knight start at dungeon[i][j] 
    int[][] dp = new int[m][n]; 

    for(int i = m - 1; i >= 0; i--){
        for(int j = n - 1; j >= 0; j--){
            if(i == m - 1 && j == n - 1){
                dp[i][j] = 1 - dungeon[i][j] <= 0? 1: 1 - dungeon[i][j];
            }
            else if(i == m - 1){
                dp[i][j] = dp[i][j + 1] - dungeon[i][j] <= 0? 1: dp[i][j + 1] - dungeon[i][j];
            }
            else if(j == n - 1){
                dp[i][j] = dp[i + 1][j] - dungeon[i][j] <= 0? 1: dp[i + 1][j] - dungeon[i][j];
            }
            else{
                dp[i][j] = Math.min(dp[i][j + 1] - dungeon[i][j] <= 0? 1: dp[i][j + 1] - dungeon[i][j], dp[i + 1][j] - dungeon[i][j] <= 0? 1: dp[i + 1][j] - dungeon[i][j]);
            }
        }
    }

    return dp[0][0];
}
