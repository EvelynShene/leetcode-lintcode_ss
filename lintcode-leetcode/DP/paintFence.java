/** 276. Paint Fence(leetcode) / 514. Paint Fence(lintcode)
 *      There is a fence with n posts, each post can be painted with one of the k colors.
 *      You have to paint all the posts such that no more than two adjacent fence posts have the same color.
 *      Return the total number of ways you can paint the fence.
 *
 *      Note: n and k are non-negative integers.
 *
 *      Example: Given n=3, k=2 return 6
 *                       post 1,   post 2, post 3
 *                 way1    0         0       1 
 *                 way2    0         1       0
 *                 way3    0         1       1
 *                 way4    1         0       0
 *                 way5    1         0       1
 *                 way6    1         1       0
 */
 
 /* Method 1: DP - O(n) time and space complexity
  *     dp[i][0]: i+1 个post, 其中 post-i 和 post-i+1 是同色的
  *     dp[i][1]: i+1 个post, 其中 post-i 和 post-i+1 是不同色的
  */
 public int numWays(int n, int k) {
    if(n == 0){
        return 0;
    }
    int[][] dp = new int[n][2];
    dp[0][0] = 0;
    dp[0][1] = k;
    for(int i = 1; i < n; i++){
        dp[i][0] = dp[i - 1][1];
        dp[i][1] = (dp[i - 1][0] * (k - 1)) + (dp[i - 1][1] * (k - 1));
    }
    return dp[n - 1][0] + dp[n - 1][1];
 }
 
 //Method 2: DP - O(n) time and O(1) space complexity
 public int numWays(int n, int k) {
    if(n == 0){
        return 0;
    }
    int[] dp = new int[2];
    dp[0] = 0;
    dp[1] = k;
    for(int i = 1; i < n; i++){
        int tmp = dp[0];
        dp[0] = dp[1];
        dp[1] = (tmp * (k - 1)) + (dp[1] * (k - 1));
    }
    return dp[0] + dp[1];
 }
