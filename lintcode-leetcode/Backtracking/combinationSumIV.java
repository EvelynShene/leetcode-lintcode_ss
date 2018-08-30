/** 377. Combination Sum IV(leetcode) / 564. Combination Sum IV(lintcode)
 *     Given an integer array with all positive numbers and no duplicates, find the number of possible combinations
 *  that add up to a positive integer target.
 *
 *     Example: nums = [1, 2, 3] ; target = 4
 *
 *              The possible combination ways are:
 *                  (1, 1, 1, 1)
 *                  (1, 1, 2)
 *                  (1, 2, 1)
 *                  (1, 3)
 *                  (2, 1, 1)
 *                  (2, 2)
 *                  (3, 1)
 *             Note that different sequences are counted as different combinations. Therefore the output is 7.
 *
 *     Follow up:
 *        1) What if negative numbers are allowed in the given array?
 *        2) How does it change the problem?
 *        3) What limitation we need to add to the question to allow negative numbers?
 */
 
 //Method: Backtracking + dp
 class Solution {
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        Arrays.sort(nums);
        if(nums == null || nums.length == 0 || target < nums[0]){
            return 0;
        }
        
        return findCombine(nums, target, dp);
    }
    
    public int findCombine(int[] nums, int target, int[] dp){
        if(dp[target] != -1){
            return dp[target];
        }
        int count = 0;
        for(int i = 0; i < nums.length; i++){
            if(target < nums[i]){
                break;
            }
            if(target == nums[i]){
                count++;
            }
            else{
                count += findCombine(nums, target - nums[i], dp);
            }
        }
        dp[target] = count;
        return count;
    }
}
