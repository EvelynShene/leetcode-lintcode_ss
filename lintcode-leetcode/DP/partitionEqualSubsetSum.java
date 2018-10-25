/** 416. Partition Equal Subset Sum(leetcode)
 *      Given a non-empty array containing only positive integers, find if the array can be partitioned into two 
 *  subsets such that the sum of elements in both subsets is equal.
 *
 *      Note: 1) Each of the array element will not exceed 100.
 *            2) The array size will not exceed 200.
 *
 *      Example:1) Input: [1, 5, 11, 5] ; Output: true
 *                 Explanation: The array can be partitioned as [1, 5, 5] and [11].
 *              2) Input: [1, 2, 3, 5] ; Output: false
 *                 Explanation: The array cannot be partitioned into equal sum subsets.
 */
 
 //My Method: Recursive + HashMap
 class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
        }
        if(sum % 2 != 0){
            return false;
        }
        sum = sum / 2;
        Arrays.sort(nums);
        Map<Integer, Boolean> map = new HashMap<Integer, Boolean>();
        return getSum(nums, 0, sum, map);
    }
    
    public boolean getSum(int[] nums, int start, int target, Map<Integer, Boolean> map){
        if(target == 0){
            return true;
        }
        if(map.containsKey(target)){
            return map.get(target);
        }
        for(int i = start; i < nums.length; i++){
            if(nums[i] > target){
                break;
            }
            if(getSum(nums, i + 1, target - nums[i], map) || getSum(nums, i + 1, target, map)){
                return true;
            }
        }
        map.put(target, false);
        return false;
    }
}

/* Method 2: Dynamic Programming
 *    Idea: dp[i][j]表示前i个元素中是否存在能组成和为j的子数组；dp[i][j]的推到公式为：
 *          1) dp[i][j] = dp[i - 1][j]； 如果前i-1个元素有可以组成和为j的子数组，那么dp[i][j]一定也可以；
 *      或者 2) dp[i][j] = dp[i - 1][j - nums[i]]; 前i-1个元素有可以组成和为j - nums[i]的子数组，
 *             那么加上nums[i]，就可以组成和为j的子数组，即dp[i][j]存在可以组成和为j的子数组。
 */
public boolean canPartition(int[] nums) {
    if(nums == null || nums.length == 0){
        return false;
    }

    int sum = 0;
    int n = nums.length;
    for(int i = 0; i < n; i++){
        sum += nums[i];
    }
    if(sum % 2 != 0){
        return false;
    }
    sum = sum / 2;

    //dp[i][j] indicates that when givint 0-i items, whether you can get a subset such that 
    //     the sum of elements in this subset is equal to j
    boolean[][] dp = new boolean[n + 1][sum + 1];
    dp[0][0] = true;
    for(int i = 1; i <= n; i++){
        if(nums[i - 1] <= sum){
            dp[i][nums[i - 1]] = true;
        }
        for(int j = 0; j <= sum; j++){
            dp[i][j] = dp[i - 1][j];
            if(j >= nums[i - 1]){
                dp[i][j] |= dp[i - 1][j - nums[i - 1]];
            }
        }
    }
    return dp[n][sum];
}
