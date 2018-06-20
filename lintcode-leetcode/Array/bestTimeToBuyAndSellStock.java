/** 149. Best Time to Buy and Sell Stock(lintcode)/ 121. Best Time to Buy and Sell Stock(leetcode)
 *    Say you have an array for which the ith element is the price of a given stock on day i.
 *    If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock), 
 *    design an algorithm to find the maximum profit.
 *    Note that you cannot sell a stock before you buy one.
 *    
 *    Example: Input: [7,1,5,3,6,4] ; Output: 5
 *    Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 *    Not 7-1 = 6, you cannot sell a stock before you buy one..
 */
 
 //Method 1: Brute Force - O(n^2)
   public int maxProfit(int[] prices) {
      // write your code here
      int max_profit = 0;

      for(int i = prices.length-1; i >= 1; i--){
          for(int j = i - 1; j >= 0; j--){
              if(prices[i] > prices[j]){
                  max_profit = Math.max(prices[i] - prices[j], max_profit);
              }
          }
      }
      return max_profit;
  }

//Method 2: - O(n) Time and Space
  public int maxProfit(int[] prices) {
      // write your code here
      if(prices == null || prices.length == 0){
          return 0;
      }
      int max_profit = 0;
      int[] smallest_price = new int[prices.length]; // smallest_price[i] = the smallest price in subarray [0,i]
      smallest_price[0] = prices[0];
      for(int i = 1; i < prices.length; i++){
          smallest_price[i] = Math.min(smallest_price[i-1], prices[i]);
      }

      for(int i = prices.length-1; i >= 1; i--){
          max_profit = Math.max(max_profit, prices[i] - smallest_price[i-1]);
      }
      return max_profit;
  }
  
  /* Method 3 - O(n) Time and O(1) Space (From leetcode)
   * Idea: Maintain two variables 
   *         - minprice and maxprofit corresponding to the smallest price before current price and maximum profit at that day
   */
  public int maxProfit(int prices[]) {
      int minprice = Integer.MAX_VALUE;
      int maxprofit = 0;
      for (int i = 0; i < prices.length; i++) {
          if (prices[i] < minprice)
              minprice = prices[i]; // smallest price before day i+1
          else if (prices[i] - minprice > maxprofit)
              maxprofit = prices[i] - minprice;
      }
      return maxprofit;
  }
