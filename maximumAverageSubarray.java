/** 868. Maximum Average Subarray I(lintcode)/643. Maximum Average Subarray I(leetcode)
 *  Given an array consisting of n integers, find the contiguous subarray of given length k that has the maximum average value.
 *  And you need to output the maximum average value.
 *  Example:
 *     Given nums = [1,12,-5,-6,50,3], k = 4, return 12.75 [(12-5-6+50)/4 = 51/4 = 12.75]
 */

//* My Method, AC in lintcode and leetcode, time complexity O(N^2)
public double findMaxAverage(int[] nums, int k) {
  // Write your code here
  int sum = 0;
  int max = 0;
  for(int i = 0; i < k; i++)
      max += nums[i];
  for(int i = 1; i <= nums.length-k; i++){
      sum = 0;
      for(int j = i;j < i+k; j++){
          sum += nums[j];
      }
      max = Math.max(max, sum);
  }
  return (float)max/k;
}

/** Approach #1 Cumulative Sum(from leetcode) [Time and space complexity = O(n)]
 *   sum[i] = nums[0]+nums[1]+...+nums[i];
 *   sum[i] - sume[i-k] = nums[i-k+1]+nums[i-k+2]+...+nums[i]; (sum of k numbers starts at nums[i-k+1])
 */
 public double findMaxAverage(int[] nums, int k) {
		int[] sum = new int[nums.length];
		sum[0] = nums[0];
		
    for (int i = 1; i < nums.length; i++)
		  sum[i] = sum[i - 1] + nums[i];
		
    double res = sum[k - 1] * 1.0 / k; //sum[k - 1] = nums[0]+nums[1]+...+nums[k-1]
		for (int i = k; i < nums.length; i++) {
			res = Math.max(res, (sum[i] - sum[i - k]) * 1.0 / k);
		}
		return res;
}

/** Approach #2 Sliding Window(from leetcode) [Time complexity = O(n);space complexity = O(1)]
 *   sum always indicates k sum of k numbers
 *   sum += nums[i] - nums[i-k] is the next sum of k numbers. [sum = n(1)+n(2)+..+n(k); next sum = sum +n(k+1)-n(1)]
 */
 public double findMaxAverage(int[] nums, int k) {
    double sum=0;
    for(int i=0;i<k;i++)
        sum+=nums[i];
    double res=sum;
    for(int i=k;i<nums.length;i++){
        sum+=nums[i]-nums[i-k];
            res=Math.max(res,sum);
    }
    return res/k;
}
 
