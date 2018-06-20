/** 151. Best Time to Buy and Sell Stock III(lintcode) / 123. Best Time to Buy and Sell Stock III(leetcode)
 *    Say you have an array for which the ith element is the price of a given stock on day i.
 *    Design an algorithm to find the maximum profit. You may complete at most two transactions.
 *    Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).
 *
 *    Example:Input: [3,3,5,0,0,3,1,4]; Output: 6
 *        Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 *                Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
 */
 
 //Method 1: Divide and conquer algorithm
 class Solution {
    public int maxProfit(int[] prices) {
        int max_profit = 0;
        for(int i = 0; i < prices.length; i++){
            int profit1 = maxSubProfit(prices, 0, i-1);
            int profit2 = maxSubProfit(prices,i,prices.length-1);
            max_profit = Math.max(max_profit,profit1+profit2);
        }
        return max_profit;
    }
    
    public int maxSubProfit(int[] prices, int start, int end){
        int max = 0;
        int min_price = Integer.MAX_VALUE;
        for(int i = start; i <= end; i++){
            if(prices[i] < min_price){
                min_price = prices[i];
            }
            else if(max < prices[i] - min_price){
                max = prices[i] - min_price;
            }
        }
        return max;
    }
}
