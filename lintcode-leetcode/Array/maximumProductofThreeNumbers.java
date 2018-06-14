/**  1119. Maximum Product of Three Numbers(lintcode)/628. Maximum Product of Three Numbers(leetcode)
 *   Given an integer array, find three numbers whose product is maximum and output the maximum product.
 *   Note: Consider 2 situations:
 *       1)If list contains negative value, need to check if 2 negative number's production could exceed positive numbers.
 *       2)If only contains postive value, just take top 3 value and multiple them together.
 */
 
 public int maximumProduct(int[] nums) {
    // Write your code here
    Arrays.sort(nums);
    int len = nums.length;

    int res1 = nums[0]*nums[1]*nums[len-1];
    int res2 = nums[len-1]*nums[len-2]*nums[len-3];

    return Math.max(res1,res2);
 }
