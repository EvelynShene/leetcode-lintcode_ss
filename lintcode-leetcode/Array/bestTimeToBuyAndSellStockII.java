/** 122. Best Time to Buy and Sell Stock II (lintcode)/122. Best Time to Buy and Sell Stock II(leetcode)
 *    Say you have an array for which the ith element is the price of a given stock on day i.
 *    Design an algorithm to find the maximum profit. 
 *    You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).
 *  
 *    Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).
 *
 *    Example: Input: [7,1,5,3,6,4]; Output: 7
 *        Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.            
 */
 
 //Method 1:  TotalProfit = TotalProfit= ∑(peak - valley)
  public int maxProfit(int[] prices) {
      if(prices == null || prices.length <= 1){
          return 0;
      }
      int max_profit = 0;
      int peak = prices[0];
      int valley = prices[0];
      int i = 0;
      for(i = 1; i < prices.length-1; i++){
          if(prices[i] <= prices[i-1] && prices[i] <= prices[i+1]){
              valley = prices[i];
          }
          else if(prices[i] >= prices[i-1] && prices[i] >= prices[i+1]){
              peak = prices[i];
              max_profit += peak - valley; 
              valley = prices[i+1];
          }    
      }
      if( i == prices.length - 1 && prices[i] > valley){
          max_profit += prices[i] - valley; 
      }
      return max_profit;
  }
  
  //Method 2: [From leetcode]
    public int maxProfit(int[] prices) {
        int maxprofit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1])
                maxprofit += prices[i] - prices[i - 1];
        }
        return maxprofit;
    }
