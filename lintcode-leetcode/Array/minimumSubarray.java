/** 44. Minimum Subarray (lintcode)
 *    Given an array of integers, find the subarray with smallest sum. Return the sum of the subarray.
 *    Example: For [1, -1, -2, 1], return -3.
 */

// Hint: min存局部最小值，决定是否将当前nums.get(i)加入前一段局部最小值；minSum存到下标i为止的minArray值
public int minSubArray(List<Integer> nums) {
    // write your code here
    int min = nums.get(0);
    int minSum = min;

    for(int i = 1; i < nums.size(); i++){
        min = Math.min(min + nums.get(i),nums.get(i));
        minSum = Math.min(min,minSum);
    }

    return minSum;
}
