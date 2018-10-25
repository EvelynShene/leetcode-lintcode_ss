/** 494. Target Sum(leetcode) / 1208. Target Sum(lintcode)
 *      You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + 
 *  and -. For each integer, you should choose one from + and - as its new symbol.
 *      Find out how many ways to assign symbols to make sum of integers equal to target S.
 *      
 *      Example: Input: nums is [1, 1, 1, 1, 1], S is 3.  Output: 5
 *              Explanation: 
 *                   -1+1+1+1+1 = 3
 *                   +1-1+1+1+1 = 3
 *                   +1+1-1+1+1 = 3
 *                   +1+1+1-1+1 = 3
 *                   +1+1+1+1-1 = 3
 *
 *      Note: 1) The length of the given array is positive and will not exceed 20.
 *            2) The sum of elements in the given array will not exceed 1000.
 *            3) Your output answer is guaranteed to be fitted in a 32-bit integer.
 */
 
 //My Method: Recursion
 class Solution {
    int res = 0;
    public int findTargetSumWays(int[] nums, int S) {
        getSumWays(nums, 0, S);
        return res;
    }
    
    public void getSumWays(int[] nums, int start, int target){
        if(target == 0 && start == nums.length){
            res++;
        }
        if(start == nums.length){
            return;
        }
        getSumWays(nums, start + 1, target - nums[start]);
        getSumWays(nums, start + 1, target + nums[start]);
    }
}

/* Method 2:
 *    Use assigned symbols to separate the array into two subarrays:
 *        1) The subarray P which contains the elements that are assigned +;
 *        2) The subarray N which contains the elements that are assigned -.
 *    We can easily have that: sum(P) - sum(N) = target
 *            => sum(P) - sum(N) + sum(P) - sum(N) = target + sum(P) + sum(N)
 *            => 2 * sum(P) = target + sum(whole array)
 *    So the problem becomes:
 *        Find the number of subarrays in which the sum of elements is equal to (target + sum(whole array)) / 2 
 */
 class Solution {  
    public int findTargetSumWays(int[] nums, int S) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int sum = 0;
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
        }
        if((sum + S) % 2 != 0 || sum < S){
            return 0;
        }
        
        sum = (sum + S) / 2;
        return findSubset(nums, sum);
    }
    
    public int findSubset(int[] nums, int target){
        int[] dp = new int[target + 1]; 
        dp[0] = 1;
        for (int n : nums)
            for (int i = target; i >= n; i--)
                dp[i] += dp[i - n]; 
        return dp[target];
    }
}
