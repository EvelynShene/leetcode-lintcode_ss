/** 322. Coin Change(leetcode) / 669. Coin Change(lintcode)
 *      You are given coins of different denominations and a total amount of money amount. Write a function to 
 *  compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be 
 *  made up by any combination of the coins, return -1.
 *
 *      Note: You may assume that you have an infinite number of each kind of coin.
 *
 *      Example: 1) Given coins = [1, 2, 5], amount = 11
 *                  return 3 (11 = 5 + 5 + 1)
 *               2) Given coins = [2], amount = 3
 *                  return -1.
 */
 
 //Method: 
 public int coinChange(int[] coins, int amount) {
    if(amount == 0){
        return 0;
    }
    if(coins == null || coins.length == 0){
        return -1;
    }
    Arrays.sort(coins);
    int[] dp = new int[amount + 1];
    for(int i = 0; i < coins.length; i++){
        if(coins[i] > amount){
            break;
        }
        dp[coins[i]] = 1;
    }
    for(int i = 1; i <= amount; i++){
        if(dp[i] == 0){
            dp[i] = Integer.MAX_VALUE;
            for(int k = 0; k < coins.length; k++){
                int money = coins[k];
                if(money > i){
                    break;
                }
                if(money == 0 || dp[money] == -1 || dp[i - money] == -1){
                    continue;
                }
                dp[i] = Math.min(dp[i], dp[money] + dp[i - money]);
            }
            if(dp[i] == Integer.MAX_VALUE){
                dp[i] = -1;
            }
        }
    }
    return dp[amount];
}
