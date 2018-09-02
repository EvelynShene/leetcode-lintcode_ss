/** 515. Paint House(lintcode) / 256. Paint House(leetcode)
 *      There are a row of n houses, each house can be painted with one of the three colors: red, blue or green. 
 *  The cost of painting each house with a certain color is different. You have to paint all the houses such that
 *  no two adjacent houses have the same color.
 *      The cost of painting each house with a certain color is represented by a n x 3 cost matrix. For example, 
 *  costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1 with 
 *  color green, and so on... Find the minimum cost to paint all houses.
 *
 *      Note: All costs are positive integers.
 *
 *      Example: Given costs = [[14,2,11],[11,14,5],[14,3,10]] return 10
 *               house 0 is blue, house 1 is green, house 2 is blue, 2 + 5 + 3 = 10
 */
 
//Method: O(n) time and space complexity
public int minCost(int[][] costs) {
    if(costs == null || costs.length == 0){
        return 0;
    }

    int n = costs.length;
    int[][] dp = new int[n][3];
    dp[0][0] = costs[0][0];
    dp[0][1] = costs[0][1];
    dp[0][2] = costs[0][2];

    for(int i = 1; i < n; i++){
        dp[i][0] = costs[i][0] + Math.min(dp[i - 1][1], dp[i - 1][2]);
        dp[i][1] = costs[i][1] + Math.min(dp[i - 1][2], dp[i - 1][0]);
        dp[i][2] = costs[i][2] + Math.min(dp[i - 1][0], dp[i - 1][1]);
    }

    return Math.min(dp[n - 1][0], Math.min(dp[n - 1][1], dp[n - 1][2]));        
}

//Method 2: O(n) time and O(1) space complexity
public int minCost(int[][] costs) {
    if(costs == null || costs.length == 0){
        return 0;
    }

    int n = costs.length;
    int[] dp = new int[3];
    dp[0] = costs[0][0];
    dp[1] = costs[0][1];
    dp[2] = costs[0][2];

    for(int i = 1; i < n; i++){
        int tmp0 = costs[i][0] + Math.min(dp[1], dp[2]);
        int tmp1 = costs[i][1] + Math.min(dp[2], dp[0]);
        int tmp2 = costs[i][2] + Math.min(dp[0], dp[1]);
        dp[0] = tmp0;
        dp[1] = tmp1;
        dp[2] = tmp2;
    }

    return Math.min(dp[0], Math.min(dp[1], dp[2]));
}
