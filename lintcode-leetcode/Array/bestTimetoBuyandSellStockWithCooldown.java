/** 995. Best Time to Buy and Sell Stock with Cooldown(lintcode) 
 *                                    /309. Best Time to Buy and Sell Stock with Cooldown(leetcode)
 *     Say you have an array for which the ith element is the price of a given stock on day i.
 *  Design an algorithm to find the maximum profit. You may complete as many transactions as you like 
 *  (ie, buy one and sell one share of the stock multiple times) with the following restrictions:
 *        1) You may not engage in multiple transactions at the same time 
 *           (ie, you must sell the stock before you buy again).
 *        2) After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
 *    
 *     Example: Input: [1,2,3,0,2]; Output: 3 
 *          Explanation: transactions = [buy, sell, cooldown, buy, sell]
 *  
 */

/* My Method: Dynamic Programming 
  * Idea: 每一天都有两种状态，有股票在手(own)和没有股票在手(cash)；(没股票在手也就是换得了利润／钱在手，所以用cash表示)
  *       先分别用变量own[i]和cash[i]表示第i天有股票在手和没有股票在手时的最大利润。则在第i天时：
  *         1）若有股票在手，即own[i]：
  *                 a)要么是第i-1天就有股票在手了（最大利润为own[i-1]）；
  *                 b)要么是第i天买进了股票，买股票花费了prices[i],此时的最大利润为cash[i-2] - prices[i]。
  *            (可以看做股票只有一支，只有卖出了才能买进。因为有冷冻期一天，要想在第i天能买股票，至少第i-1和i-2天肯定也没有股票在手，
  *             【因为如果第i-2天有股票在手，而第i-1天没有，那么说明是第i-1天把股票卖了，这样一来，第i天必须是冷冻期，不能买】
  *             所以最大利润是cash[i-2] - prices[i])
  *            => 两者取大就是第i天有股票在手的最大利润。
  *         2）若没有股票在手，即cash[i]：
  *                 a)要么是第i-1天就没有股票在手（最大利润为cash[i-1]）；
  *                 b)要么是第i-1天有股票然后第i天把股票卖了。卖出后，可得利润prices[i], 则最大利润为own[i-1] + prices[i].
  *            => 两者取大就是第i天有股票在手的最大利润。
  *        own[i]和cash[i]的数值随i变化,但是我们只需要得到最后一天时的最大利润，
  *     并且最后一天没有股票在手(cash[n])肯定比有股票在手(own[n])利润高, 所以只需要维护两个变量own和cash即可。
  */
  
  public int maxProfit(int[] prices) {
    if(prices == null || prices.length == 0){
        return 0;
    }
    int n = prices.length;
    int[] cash = new int[n+1];
    int[] own = new int[n+1];
    own[1] = -prices[0];
    for(int i = 1; i < n; i++){
        cash[i+1] = Math.max(cash[i],own[i] + prices[i]);
        own[i+1] = Math.max(own[i],cash[i-1] - prices[i]);
    }
    return cash[n];
 }
