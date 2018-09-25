/** 453. Minimum Moves to Equal Array Elements(leetcode) / 1231. Minimum Moves to Equal Array Elements(lintcode)
 *      Given a non-empty integer array of size n, find the minimum number of moves required to make all array 
 *  elements equal, where a move is incrementing n - 1 elements by 1.
 *
 *      Example: 1) Input:[1,2,3] ; Output: 3
 *            Explanation: Only three moves are needed (remember each move increments two elements):
 *                          [1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]
 */
 
 /* Method: Math
  *    思路：假设移动最小步数是m, 数组和为sum, 那么最后整个数组总共添加了m * (n-1)；此时整个数组的和是 sum + m * (n - 1);
  *         又设最后整个数组的值都是x，那么最后得到的数组和又可以表示为 x * n； =》 sum + m * (n - 1) = x * n
  *         再设原始数组的最小数为minNum, 那么最后的 x = minNum + m；带入方程，可得： m = sum - minNum * n
  */ 
public int minMoves(int[] nums) {
    if(nums == null || nums.length == 0){
        return 0;
    }
    int minNum = Integer.MAX_VALUE;
    int sum = 0;
    for(int i = 0; i < nums.length; i++){
        sum += nums[i];
        minNum = Math.min(minNum, nums[i]);
    }
    return (sum - minNum * nums.length);
}
