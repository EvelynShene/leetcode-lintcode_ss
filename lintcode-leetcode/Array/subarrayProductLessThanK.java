/** 1075. Subarray Product Less Than K (lintcode)/ 713. Subarray Product Less Than K (leetcode)
 *    Your are given an array of positive integers nums.
 *    Count and print the number of (contiguous) subarrays where the product of all the elements in the subarray is less than k.
 *
 *    Note: 0 < nums.length <= 50000.
 *          0 < nums[i] < 1000.
 *          0 <= k < 10^6.
 *
 *    Example: Input: nums = [10, 5, 2, 6], k = 100; Output: 8
 *             Explanation: The 8 subarrays that have product less than 100 are: 
 *                    [10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6].
 *             Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.
 */
 
 //Method 1: Brute Force - O(n^2) (AC in lintcode but Time Limit Exceeded in leetcode)
  public int numSubarrayProductLessThanK(int[] nums, int k) {
      // Write your code here
      int product = 1;
      int count = 0;
      for(int i = 0; i < nums.length; i++){
          if(nums[i] >= k){
              continue;
          }
          count++;
          product = nums[i];
          for(int j = i+1; j < nums.length; j++){
              if(nums[j]*product < k){
                  count++;
                  product *= nums[j]; 
              }
              else{
                  break;
              }
          }
      }
      return count;
  }
  
  /* Method 2: Sliding Window - O(n) Time
   * Idea: 对每一个元素，找到可以以它为结尾的最长的满足乘积小于k的子数组。其对应的满足要求的子数组个数是数组的长度。
   */
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        // Write your code here
        if (k <= 1) return 0;
        int prod = 1, count = 0, left = 0;
        for (int right = 0; right < nums.length; right++) {
            prod *= nums[right];
            while (prod >= k) {
               prod /= nums[left];
               left++;
            }
            count += right - left + 1;
        }
        return count;
    }
 
 /* Method 3: Binary Search - O(nlogn) Time and O(n) Space (from leetcode)
  * Idea: 可对数组元素做log运算，这样题目就转化为求新数组中子数组之和小于logk的个数
  *       因为都是正整数，所以可以用二分查找找到最长的满足要求的子数组。
  */
  
   public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k == 0) return 0;
        double logk = Math.log(k);
        double[] prefix = new double[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            prefix[i+1] = prefix[i] + Math.log(nums[i]);
        }

        int ans = 0;
        for (int i = 0; i < prefix.length; i++) {
            int lo = i + 1, hi = prefix.length;
            while (lo < hi) {
                int mi = lo + (hi - lo) / 2;
                if (prefix[mi] < prefix[i] + logk - 1e-9) lo = mi + 1;
                else hi = mi;
            }
            ans += lo - i - 1;
        }
        return ans;
    }
