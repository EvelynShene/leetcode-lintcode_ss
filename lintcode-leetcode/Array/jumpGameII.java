/** 117. Jump Game II(lintcode)/ 45. Jump Game II (leetcode)
 *    Given an array of non-negative integers, you are initially positioned at the first index of the array.
 *    Each element in the array represents your maximum jump length at that position.
 *    Your goal is to reach the last index in the minimum number of jumps.
 *
 *    Note: You can assume that you can always reach the last index.
 *    
 *    Example: Input: [2,3,1,1,4]; Output: 2
 *             Explanation: The minimum number of jumps to reach the last index is 2. 
 *                          Jump 1 step from index 0 to 1, then 3 steps to the last index.
 */
 
 /* Idea: 考虑每一步的最远可达位置，用fastest存储这个可达位置；
  *       [start,end]是当前步数下，还未考察过的数组位置，这些位置可能会更新fastest的值。
  *      例如 [2,3,1,3,1,1,1]
  *           1)考察[0,0]位置上，fastest = 2，更新下一步的可考察位置：[1,2]
  *           2)考察[1,2]位置上，fastest = 4，更新下一步的可考察位置：[3,4] 
  *                 (1,2的位置之前已经查过它们可达的最远位置，所以从3开始；4是走两步可达的最远位置)
  *           (......依此类推)
  */
 class Solution {
    public int jump(int[] nums) {
        int target = nums.length - 1;
        int step = 0;
        int fastest = 0;
        int start = 0;
        int end = 0;
        while(end < nums.length - 1){
            step++;
            for(int i = start; i <= end; i++){
                fastest = Math.max(nums[i] + i,fastest);
            }
            if(fastest >= target) break;
            start = end + 1;
            end = fastest;
        }
        return step;
    }
}
 
 
