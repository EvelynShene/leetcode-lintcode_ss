/** 395. Coins in a Line II (lintcode)
	* 	There are n coins with different value in a line. 
	* 	Two players take turns to take one or two coins from left side until there are no more coins left. 
	* 	The player who take the coins with the most value wins.
	* 	Could you please decide the first player will win or lose?
	* 
	*  Example:
	* 		Given values array A = [1,2,2], return true.
	* 		Given A = [1,2,4], return false.
	*/
 
 /* 
  * Method: Use dynamic programming(动态规划)
  * Idea: 设置 dp[i] = 当剩下i个硬币时，当前player可以获得的最大value值。
  *     初始化：dp[0] = 0;
  *           dp[1] = values[n-1];
  *           dp[2] = values[n-1] + values[n-2];
  *           dp[3] = values[n-3] + values[n-2];
  *     当是Player X的回合，当前还剩i个硬币时：（对手Player Y）
  *     1)X只拿1个硬币，即values[n-i]：
  *           (a)Y只拿1个硬币，即values[n-i+1]，那么剩下i-2个硬币时，X最多可以拿到的value值是dp[i-2];
  *           (b)Y拿2个硬币，即values[n-i+1]+values[n-i+2]，那么剩下i-3个硬币时，X最多可以拿到的value值是dp[i-3].
  *       在对手Y的回合里，对手Y会尽可能让X拿得少，所以X真正能拿到的最大值是：case1 = values[n-i]+min(dp[i-2],dp[i-3]).
  *
  *     2)X拿2个硬币，即values[n-i]+values[n-i+1]：
  *           (a)Y只拿1个硬币，即values[n-i+2]，那么剩下i-3个硬币时，X最多可以拿到的value值是dp[i-3];
  *           (b)Y拿2个硬币，即values[n-i+2]+values[n-i+3]，那么剩下i-4个硬币时，X最多可以拿到的value值是dp[i-4].
  *       在对手Y的回合里，对手Y会尽可能让X拿得少，所以X真正能拿到的最大值是：case2 = values[n-i]++values[n-i+1]+min(dp[i-3],dp[i-4]).
  *     
  *     3)在X的回合里，X会让自己尽可能多拿，所以X在还剩i个硬币时，做出的最优选择是：dp[i] = max(case1,case2)
  *  (这是博弈论中的一种经典思想——Minimax)         
  *
  */
  
	public boolean firstWillWin(int[] values) {
        // write your code here
        if(values.length < 3){
            return true;
        }
        int sum = 0;
        int n = values.length;
        int[] dp = new int[n];
        dp[0] = 0;
        dp[1] = values[n-1];
        dp[2] = values[n-1] + values[n-2];
        dp[3] = values[n-3] + values[n-2];
        sum = dp[3] + values[n-1];
        for (int i = 4; i <= n ; i++){
            if(i < n){
                sum += values[i];
            }
            int case1 = values[n-i] + Math.min(dp[i-2],dp[i-3]);
            int case2 = values[n-i] + values[n-i+1]+Math.min(dp[i-3],dp[i-4]);
            dp[i] = Math.max(case1,case2);
        } 
        if(dp[n] > sum - dp[n]){
            return true;
        }
        else{
            return false;
        }
    }
