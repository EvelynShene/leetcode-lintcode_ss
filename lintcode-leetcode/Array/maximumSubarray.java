/** 41. Maximum Subarray (lintcode)/53. Maximum Subarray(leetcode)
 *    Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum 
 *    and return its sum. Do it in time complexity O(n).
 */

//只考虑前面的连续数集的和是不是负数即可： sum + negative num < negative num
public class maximumSubarray {
    /**
     * @param nums: A list of integers
     * @return: A integer indicate the sum of max subarray
     */
    public int maxSubArray(int[] nums) {
        // write your code here
        int sum = 0;
        int max = nums[0];
        for(int i = 0 ; i < nums.length; i++){
            if(sum < 0){
                sum = nums[i];
            }
            else{
                sum += nums[i];
            }
            max = Math.max(sum,max);
        }
        return max;
    }
}

