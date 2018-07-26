/** 1000. Best Time to Buy and Sell Stock with Transaction Fee(lintcode)/714. Best Time to Buy and Sell Stock with Transaction Fee (leetcode)
 *      Your are given an array of integers prices, for which the i-th element is the price of a given stock on day i; 
 *   and a non-negative integer fee representing a transaction fee.
 *      You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction. 
 *   You may not buy more than 1 share of a stock at a time (ie. you must sell the stock share before you buy again.)
 *      Return the maximum profit you can make.
 *
 *   Note: 0 < prices.length <= 50000.
 *         0 < prices[i] < 50000.
 *         0 <= fee < 50000.
 *
 *   Example: Input: prices = [1, 3, 2, 8, 4, 9], fee = 2 ; Output: 8
 *    Explanation: The maximum profit can be achieved by:
 *                    Buying at prices[0] = 1
 *                    Selling at prices[3] = 8
 *                    Buying at prices[4] = 4
 *                    Selling at prices[5] = 9
 *                    The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
 */
 
 /* Method: Dynamic Programming 
  * Idea: 每一天都有两种状态，有股票在手(own)和没有股票在手(cash)；(没股票在手也就是换得了利润／钱在手，所以用cash表示)
  *       先分别用变量own[i]和cash[i]表示第i天有股票在手和没有股票在手时的最大利润。则在第i天时：
  *         1）若有股票在手：a)要么是第i-1天就有股票在手了（最大利润为own[i-1]）；
  *                       b)要么是第i天买进了股票，买股票花费了prices[i],此时的最大利润为cash[i-1] - prices[i]。
  *                               (只有卖出了股票才能买进，所以要想在第i天能买股票，第i-1天肯定没有股票在手，那天的最大利润是cash[i-1])
  *                两者取大就是第i天有股票在手的最大利润。
  *         2）若没有股票在手：a)要么是第i-1天就没有股票在手（最大利润为cash[i-1]）；
  *                         b)要么是第i-1天有股票然后第i天把股票卖了。卖出后，可得新利润为prices[i] - fee，所以第i天的总利润是：
  *                                             own[i-1] + prices[i] - fee
  *                两者取大就是第i天有股票在手的最大利润。
  *       own[i]和cash[i]的数值随i变化,但是我们只需要得到最后一天时的最大利润，并且最后一天没有股票在手(cash[i])肯定比有股票在手(own[i])利润高。
  *       所以只需要维护两个变量own和cash即可。
  */
 
  public int maxProfit(int[] prices, int fee) {
      // write your code here
      int cash = 0;
      int own = - prices[0];
      for(int i = 0; i < prices.length; i++){
          cash = Math.max(cash,own + prices[i] - fee);
          own = Math.max(own, cash - prices[i]);
      }
      return cash;
  }
