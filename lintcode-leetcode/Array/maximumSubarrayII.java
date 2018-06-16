/**  42. Maximum Subarray II(lintcode)
 *    Given an array of integers, find two non-overlapping subarrays which have the largest sum.
 *    The number in each subarray should be contiguous. Return the largest sum.
 *    Example: 
 *          For given [1, 3, -1, 2, -1, 2], the two subarrays are [1, 3] and [2, -1, 2] or [1, 3, -1, 2] and [2], 
 *            they both have the largest sum 7.
 */ 
 
 //My Method: maxSum求子数组nums[l,k]的最大连续和，设定分割线，从0开始移动，调用maxSum求分割线两端的两个不重合子数组的最大连续和的和。
 public class Solution {
    /*
     * @param nums: A list of integers
     * @return: An integer denotes the sum of max two non-overlapping subarrays
     */
    public int maxTwoSubArrays(List<Integer> nums) {
        // write your code here
        int max = 0;
        int sum = 0;
        for(int i = 0; i < nums.size()-1; i++){
            sum =  maxSum(nums,0,i) + maxSum(nums,i+1,nums.size()-1);
            if(i == 0){
                max = sum;
            }
            max = Math.max(sum, max);
        }
        
        return max;
    }
    public int maxSum(List<Integer> nums, int l, int r){
        if(l == r){
            return nums.get(l);
        }    
        int max = nums.get(l);
        int sum = 0;
        for(int i = l; i <= r; i++){
            if(sum < 0){
                sum = nums.get(i);
            }
            else{
                sum += nums.get(i);
            }
            max = Math.max(max,sum);
        }
        return max;
    }
}

//Methed 2: Do it in time complexity O(n) (From jiuzhang)
// Hint: 从左至右，从右至左分别计算最大子数组和，然后刷一遍左右数组，找到最佳的划分位置，复杂度O(n)

public int maxTwoSubArrays(List<Integer> nums) {
        // write your code here
    int[] left = new int[nums.size()];
    int[] right = new int[nums.size()];

    int maxSum = Integer.MIN_VALUE;
    int currentSum = 0;
    for (int i = 0; i < nums.size();i++) {
        if (currentSum <= 0) {
            currentSum = nums.get(i);
        } else {
            currentSum += nums.get(i);
        }
        left[i] = maxSum = Math.max(currentSum, maxSum);
    }
    currentSum = 0;
    maxSum = Integer.MIN_VALUE;
    for (int i = nums.size() - 1; i >= 0;i--) {
        if (currentSum <= 0) {
            currentSum = nums.get(i);
        } else {
            currentSum += nums.get(i);
        }
        right[i] = maxSum = Math.max(currentSum, maxSum);
    }
    int split = 0;
    currentSum = 0;
    maxSum = Integer.MIN_VALUE;
    for (int i = 0; i < nums.size() - 1; i++) {
        if(left[i] + right[i+1] > maxSum) {
            maxSum = left[i] + right[i+1];
            split = i;
        }
    }
    return maxSum;
}
