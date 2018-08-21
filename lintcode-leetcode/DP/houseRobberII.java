/** 213. House Robber II(leetcode) / 534. House Robber II(lintcode)
 *      You are a professional robber planning to rob houses along a street. Each house has a certain amount of 
 *  money stashed. All houses at this place are arranged in a circle. That means the first house is the neighbor 
 *  of the last one. Meanwhile, adjacent houses have security system connected and it will automatically contact 
 *  the police if two adjacent houses were broken into on the same night.
 *      Given a list of non-negative integers representing the amount of money of each house, determine the 
 *  maximum amount of money you can rob tonight without alerting the police.
 *
 *      Example: 1) nums = [3,6,4], return 6;
 *               2) nums = [1,3,1,3,100], return 103
 */
 
 //My Method: 去掉第一个数和去掉最后一个数两种情况考虑，就同House Robber一样了，最后在比较两个值的大小。- O(n) time and space
class Solution {
    public int rob(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        if(nums.length == 1){
            return nums[0];
        }
        int res1 = subRob(nums,0);
        int res2 = subRob(nums,1);
        return Math.max(res1, res2);
    }   
    public int subRob(int[] nums, int start){
        int n = nums.length;
        int[] dp = new int[n];
        int j = start;
        for(int i = 1; i < n; i++){
            if(i == 1){
                dp[i] = nums[j];
            }
            else{
                dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[j]);
            }
            j++;
        }
        return dp[n-1];
    }
}
