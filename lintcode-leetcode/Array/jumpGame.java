/** 116. Jump Game(lintcode) / 55. Jump Game(leetcode)
 *      Given an array of non-negative integers, you are initially positioned at the first index of the array.
 *      Each element in the array represents your maximum jump length at that position.
 *      Determine if you are able to reach the last index.
 *
 *      Example: 1) Input: [2,3,1,1,4] ; Output: true
 *               Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 *               2) Input: [3,2,1,0,4] ; Output: false
 *               Explanation: You will always arrive at index 3 no matter what. Its maximum
 *                            jump length is 0, which makes it impossible to reach the last index.
 *      
 */
 
 //My Method: Dynamic Programming - O(n^2)
 public boolean canJump(int[] nums) {
    if(nums == null){
        return false;
    }
    if(nums.length == 0){
        return true;
    }

    int n = nums.length;
    boolean[] dp = new boolean[n];//dp[i] = true means can reach to the index i
    dp[0] = true;
    for(int i = 0; i < n; i++){
        if(!dp[i]) break;
        if(i + nums[i] >= n-1){
            return true;
        }
        for(int j = 1; j <= nums[i]; j++){
            if(!dp[i+j]){
                dp[i+j] = true;
            }
        }
    }
    return false;
 }
 
 /* Method 2: Greeday - O(n) time complexity
  *    贪心算法：如果某个节点i可以通过某种路径到达终点last_index，那么所有可以到达节点i的其他节点也一定可以到达终点。
  *      1) 首先终点本身肯定可以到达自己，reach_point = last_index;
  *      2) 然后向前依次遍历，判断当前节点i是否能够达到终点reach_point。如果能够找到这样一个节点i，就将该节点设为新的终点reach_point。
  *    并按照之前的思路继续遍历，直到reach_point = 起始节点0，返回true。否则失败。
  *      【因为节点i可以到达最终重点last_index，所以所有能到达i的前向节点，最终都能先到i再到达最终重点。】
  */
 public boolean canJump(int[] nums) {
    if(nums == null){
        return false;
    }
    if(nums.length == 0){
        return true;
    }

    int last_index = nums.length-1;
    int reach_point = last_index;
    for(int i = last_index - 1; i >= 0; i--){
        if(nums[i] + i >= reach_point){
            reach_point = i;
        }
    }
    if(reach_point == 0)
        return true;
    return false;
 }
