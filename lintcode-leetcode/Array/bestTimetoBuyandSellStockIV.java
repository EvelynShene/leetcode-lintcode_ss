/** 393. Best Time to Buy and Sell Stock IV(lintcode) / 188. Best Time to Buy and Sell Stock IV(leetcode)
 *      Say you have an array for which the ith element is the price of a given stock on day i.
 *  Design an algorithm to find the maximum profit. You may complete at most k transactions.
 *
 *      Note: You may not engage in multiple transactions at the same time (ie, you must sell the stock 
 *  before you buy again).
 *
 *      Example: 1) Input: [2,4,1], k = 2 ; Output: 2
 *               Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
 *               2) Input: [3,2,6,5,0,3], k = 2 ; Output: 7
 *               Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4.
 *                           Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 *
 */
 
 /* Method: Dynamic Programming - O(nk) time and O(nk) space complexity
  *    用两个数组global和local分别表示全局最优和局部最优：
  *     1) global[i][j]表示第i天最多完成j次交易可获得的最大利润；
  *     2) local[i][j]表示第i天最多完成j次交易可获得的最大利润，且第i天发生最后一次交易。
  *     3) global的动态规划方程： global[i][j] = Max(global[i-1][j], local[i][j]);
  *        即：要么第i天发生了最后一次交易-local[i][j]; 要么第i天没有交易，这时global[i][j] == global[i-1][j]
  *     4) local的动态规划方程，因为第i天发生了交易，所以考虑两种情况：
  *        a) 第i-1天已经完成了第j次交易，这时有local[i-1][j]，如果第i天想交易而不增加交易次数，只能把第i-1天的交易延迟到第i天
  *       【这种情况是有可能的利润变大的，如果从第i-2天开始都是递增的，那么延迟一天交易，利润会变大】
  *           此时有利润为：local[i-1][j] - prices[i-1] + prices[i] (第i-1天赚的prices[i-1]去掉，换成prices[i])
  *        b) 第i-1天之前完成的交易数最多j-1，这时交易次数不需要考虑了，重点在于最大利润：
  *       【考虑第i-1天买入股票，第i天卖出，这时可得到的利润是prices[i] - prices[i-1]，这个利润可能小于0，如果小于0就取0，
  *         取0之所以可以看作一次交易，是因为我们可以第i天买入再卖出，这样与题目不冲突：“j次交易”和“j-1次交易”都满足“最多j次”】
  *           这时有利润：global[i-1][j-1] + Max(prices[i] - prices[i-1],0);
  *
  *       *Question: 为什么只考虑第i-1天买入股票，第i天卖出呢？因为无法确定最后一次交易究竟是在哪一天买入的（在第i天卖出是确定的）
  *               所以也可能是prices[i] - prices[i - 2] 或prices[i - 3]...呀？
  *        Answer: 之所以只要考虑prices[i]-prices[i-1]，而不需要考虑其他的例如prices[i-2]，prices[i-3]。。。是因为：
  *           假如存在一个prices[i-k]使得差值(或者说利润)diff更大更好，那么global[i-1][j-1]必然已经把这个prices[i-k]考虑到
  *           现在如果我们要再用prices[i-k]，就会违反题目里的“You may not engage in multiple transactions at the same time 
  *           (ie, you must sell the stock before you buy again).”规定。所以唯一还没考虑的就只有prices[i]-prices[i-1]这一对差值。           
  *     【以上谈到的交易都指“卖出”！】
  */ 
  class Solution {
    public int maxProfit(int k, int[] prices) {
        if(prices == null || prices.length == 0){
            return 0;
        }
        int n = prices.length;
        if(k >= n){
            return maxProfit2(prices);
        }
        
        int[][] local = new int[n][k+1];
        int[][] global = new int[n][k+1];
        for(int i = 1; i < n; i++){
            for(int j = 1; j <= k; j++){
                int diff = prices[i] - prices[i-1];
                local[i][j] = Math.max(local[i-1][j] + diff, global[i-1][j-1] + Math.max(diff,0));
                global[i][j] = Math.max(global[i-1][j],local[i][j]);
            }
        }
        return global[n-1][k];
    }
    
    public int maxProfit2(int[] prices){
        int max = 0;
        int peak = prices[0]; 
        int valley = prices[0];
        int i = 1;
        for(; i < prices.length-1; i++){
            if(prices[i] >= prices[i-1] && prices[i] >= prices[i+1]){
                peak = prices[i];
                max += peak - valley;
                valley = prices[i+1];
            }
            else if(prices[i] <= prices[i-1] && prices[i] <= prices[i+1]){
                valley = prices[i];
            }
        }
        if(i == prices.length - 1 && prices[i] > valley){
            max += prices[i] - valley;
        }
        return max;
    }
}
