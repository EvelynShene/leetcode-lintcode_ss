/** 516. Paint House II(lintcode) / 265. Paint House II(leetcode - locked)
 *      There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each
 *  house with a certain color is different. You have to paint all the houses such that no two adjacent houses 
 *  have the same color.
 *      The cost of painting each house with a certain color is represented by a n x k cost matrix. 
 *  For example, costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting 
 *  house 1 with color 2, and so on... Find the minimum cost to paint all houses.
 *
 *      Note: All costs are positive integers.
 *
 *      Example: Given n = 3, k = 3, costs = [[14,2,11],[11,14,5],[14,3,10]] return 10
 *               house 0 is color 2, house 1 is color 3, house 2 is color 2, 2 + 5 + 3 = 10
 *
 *      Challenge: Could you solve it in O(nk)?
 */

//My Method: O(nk) time and space complexity
public int minCostII(int[][] costs) {
    if(costs == null || costs.length == 0){
        return 0;
    }
    int n = costs.length;
    int k = costs[0].length;

    int[][] dp = new int[n][k];
    int min = Integer.MAX_VALUE;
    int second_min = Integer.MAX_VALUE;
    for(int i = 0; i < k; i++){
        dp[0][i] = costs[0][i];
        if(min > dp[0][i]){
            second_min = min;
            min = dp[0][i];
        }
        else if(second_min > dp[0][i]){
            second_min = dp[0][i];
        }
    }

    for(int i = 1; i < n; i++){
        for(int j = 0; j < k; j++){
            if(dp[i - 1][j] == min){
                dp[i][j] = costs[i][j] + second_min;
            } 
            else{
                dp[i][j] = costs[i][j] + min;
            }
        }
        min = Integer.MAX_VALUE;
        second_min = Integer.MAX_VALUE;
        for(int j = 0; j < k; j++){
            if(min > dp[i][j]){
                second_min = min;
                min = dp[i][j];
            }
            else if(second_min > dp[i][j]){
                second_min = dp[i][j];
            }
        }
    }   
    return min;
}
