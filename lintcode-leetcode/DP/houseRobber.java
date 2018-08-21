/** 198. House Robber(leetcode) / 392. House Robber(lintcode)
 *     You are a professional robber planning to rob houses along a street. Each house has a certain amount of 
 *  money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security
 *  system connected and it will automatically contact the police if two adjacent houses were broken into on the 
 *  same night.
 *      Given a list of non-negative integers representing the amount of money of each house, determine the 
 *  maximum amount of money you can rob tonight without alerting the police.
 *
 *      Example: 1) Given [3, 8, 4], return 8.
 *               2) Given [2, 1, 1, 2], return 4.
 *      
 *      Challenge: O(n) time and O(1) memory.
 */
 
 //My Method: DP - O(n) time and complexity
 public int rob(int[] nums) {
    if(nums == null || nums.length == 0){
        return 0;
    }
    int n = nums.length;
    int[] dp = new int[n+1]; // dp[i]表示nums中前i个数组成的数组中你可得的最大钱数
    for(int i = 1; i <= n; i++){
        if(i == 1){
            dp[i] = nums[i-1];
        }
        else{
            dp[i] = Math.max(dp[i-1], nums[i-1] + dp[i - 2]);
        }
    }
    return dp[n];
}

//Method 2: O(n) time and O(1) memory
public long houseRobber(int[] A) {
    if(A == null || A.length == 0){
        return 0;
    }
    int n = A.length;
    long preRob = 0;
    long preNotRob = 0;
    long res = 0;
    for(int i = 0; i < n; i++){
        res = Math.max(preRob, preNotRob + A[i]);
        preNotRob = Math.max(preRob,preNotRob);
        preRob = res;
    }
    return res;
}
