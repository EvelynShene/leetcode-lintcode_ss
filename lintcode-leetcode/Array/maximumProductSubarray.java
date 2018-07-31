/** 152. Maximum Product Subarray(leetcode) / 191. Maximum Product Subarray(lintcode)
 *      Given an integer array nums, find the contiguous subarray within an array (containing at least one number) 
 *  which has the largest product.
 *
 *      Example: 1) Input: [2,3,-2,4] ; Output: 6
 *                Explanation: [2,3] has the largest product 6.
 *               2) Input: [-2,0,-1] ; Output: 0
 *                Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 */
 
 //Method 1: Brute Force - O(n^2) time complexity
 public int maxProduct(int[] nums) {
    if(nums == null || nums.length == 0){
        return 0;
    }
    int max = Integer.MIN_VALUE;
    int product = 1;
    for(int i = 0; i < nums.length; i++){
        product = nums[i];
        max = Math.max(max,product);
        for(int j = i+1; j < nums.length; j++){
            if(nums[j] == 0){
                product = 0;
                max = Math.max(max,product);
                break;
            }
            product *= nums[j];
            max = Math.max(max,product);
        }  
    }
    return max;
 }
 
 /* Method 2: DP - O(n) time and space complexity
  *    1) 维护两个数组：imax[i]表示子数组[0,i]内的最大乘积；imin[i]表示子数组[0,i]内的最小乘积
  *    2) 遍历数组，找到所有的imax和imin，并在更新imax过程中更新最大乘积res。
  *   【原理：子数组乘积最大值的可能为：
  *             a) 碰到一个正数，累乘最大值还得最大值；
  *             b) 碰到了一个负数，累乘最小值变成最大值。
  *          所以每次要保存累乘的最大值和最小值。
  *    3) 同时还有一个选择区间起点的问题:
  *         如果之前的最大和最小值同当前元素相乘之后，没有当前元素大（或小）那么当前元素就可作为新的起点(即开启新区间)。
  *      eg：前一个元素为0的情况，{1,0,5}，到5的时候5应该作为一个最大值，也就是新的起点。
  *                       同样，{1,0,-2}，-2比当前最小值还小，所以更新为当前最小值，并成为新的起点。
  */  
  public int maxProduct(int[] nums) {
    if(nums == null || nums.length == 0){
        return 0;
    }

    int[] imax = new int[nums.length];
    int[] imin = new int[nums.length];
    imax[0] = nums[0];
    imin[0] = nums[0];
    int res = nums[0];
    for(int i = 1; i < nums.length; i++){
        imax[i] = Math.max(Math.max(imax[i-1] * nums[i],imin[i-1] * nums[i]),nums[i]);
        imin[i] = Math.min(Math.min(imax[i-1] * nums[i],imin[i-1] * nums[i]),nums[i]);
        res = Math.max(res,imax[i]);
    }       
    return res;
 }

 //Method 2 简化空间复杂度为O(1)
 public int maxProduct(int[] nums) {
    if(nums == null || nums.length == 0){
        return 0;
    }
    int imax = nums[0];
    int imin = nums[0];
    int res = imax;
    for(int i = 1; i < nums.length; i++){
        int temp = imax;
        imax = Math.max(Math.max(imax*nums[i],imin*nums[i]),nums[i]);
        imin = Math.min(Math.min(temp*nums[i],imin*nums[i]),nums[i]);
        res = Math.max(res,imax);
    }
    return res;
 }
